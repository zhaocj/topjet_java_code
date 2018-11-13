$(function () {
    jQuery.ajaxDefaultCall = function (url, dataJson, callback) {
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: url,
            data: dataJson,
            dataType: "json",
            cache: false,
            traditional: true,
            success: function (data) {
                if (data != null) {
                    bootbox.alert({
                        buttons: {
                            ok: {
                                label: '确定'
                            }
                        },
                        size: 'small',
                        message: data.msg,
                        callback: function () {
                            if (typeof callback == "function") {
                                callback(data);
                            }
                        },
                        title: "提示信息",
                    });
                }
            },
            error: function (xhr) {

                var sessionStatus = xhr.getResponseHeader('sessionstatus');
                var errorMSG = xhr.getResponseHeader("errorMSG");

                if (sessionStatus == 'timeout') {
                    // var top = getTopWinowd();
                    // var yes = confirm('由于您长时间没有操作, 登录状态已过期, 请重新登录.');
                    // if (yes) {
                    //     top.location.href = getContextPath() + '/view/main/login.html';
                    // }
                } else if (errorMSG != undefined && errorMSG != null) {
                    alert(errorMSG);
                } else {
                    alert("网络异常");
                }
            }
        });
    };
    jQuery.callbackTest = function (damsg, callback) {
        alert(damsg);
        if (typeof callback == "function")
            callback();
    }
});


/**
 * 在页面中任何嵌套层次的窗口中获取顶层窗口
 * @return 当前页面的顶层窗口对象
 */
function getTopWinowd() {
    var p = window;
    while (p != p.parent) {
        p = p.parent;
    }
    return p;
}

function getContextPath() {
    var contextPath = document.location.pathname;
    var index = contextPath.substr(1).indexOf("/");
    contextPath = contextPath.substr(0, index + 1);
    delete index;
    return contextPath;
}

$.ajaxSetup({
    complete: function(xhr,status) {
        var sessionStatus = xhr.getResponseHeader('sessionstatus');
        if(sessionStatus == 'timeout') {
            var top = getTopWinowd();
            var yes = confirm('由于您长时间没有操作, 登录状态已过期, 请重新登录.');
            if (yes) {
                top.location.href = getContextPath() + '/view/main/login.html';
            }
        }
    }
});
