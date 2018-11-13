/**
 * Created by zhengjm on 2016/8/19.
 */

var RW_conent;
var i=1;
var mydate = new Date();
var str = mydate.getFullYear()+"-"+(mydate.getMonth()+1)+"-"+mydate.getDate()+" "+mydate.getHours()+":"+mydate.getMinutes();
$(function() {
    auto();
    $("#contentmenu").mCustomScrollbar();
    //var ss = $('body').find('.contentBox');
    //var tt = ss.clone(true);
    //var conent='<div class="col-xs-12 addRW numP">'+tt+'</div>';
    //$(".contentBox").append(tt);
})
$(".form_datetime").datetimepicker({
    linkFormat: "yyyy-mm-dd hh:ii",
    language: 'zh-CN',
    startDate: str
});
//菜单展开动画
jQuery(document).ready(function(e) {
    e(".dropmenu").click(function(t) {
        t.preventDefault();
        e(this).parent().find("ul").first().slideToggle();
        e(this).find(".icodown").hasClass("glyphicon-chevron-up") ? e(this).find(".icodown").removeClass("glyphicon-chevron-up").addClass("glyphicon-chevron-down") : e(this).find(".icodown").removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-up")
    })
})
//菜单打开iframe链接
var avtieArr = null;
function addTab(title, url, e,flag) {
    if (avtieArr == null) {
        avtieArr = e.addClass("avtie")
    } else {
        avtieArr.removeClass("avtie");
        avtieArr = null;
        avtieArr = e.addClass("avtie");
    }

    arr = e.addClass("avtie");
    if(flag=="Y"){
            var content = '<iframe class="iframeId" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;"></iframe>';
            $('#tt').tabs('add', {
                title: title,
                content: content,
                closable: true,
                height: "auto"
            });
        auto();
    }else{
        if ($('#tt').tabs('exists', title)) {
            $('#tt').tabs('select', title);
        } else {
            var content = '<iframe class="iframeId" scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;"></iframe>';
            $('#tt').tabs('add', {
                title: title,
                content: content,
                closable: true,
                height: "auto"
            });
        }
        auto();
    }

}
//复选框全选
function checkAll(obj, className) {

    $(className + " input[type='checkbox']").prop('checked', $(obj).prop('checked'));
}
$('body').on('hidden.bs.modal', '.modal',
    function() {
        $(this).removeData('bs.modal');
    });
//加减方法
function addNum(id) {
    var addvar = $(id).val();
    addvar++;
    $(id).val(addvar);
}
function delNum(id) {
    var delvar = $(id).val();
    if (delvar > 0) {
        delvar--;
        $(id).val(delvar);
    } else {
        $(delvar).val(0)
    };
}
//自适应高度方法
function auto() {
    $("#contentmenu").css("height", window_h = $(window).height() - 53);
    if ($(".iframeId").length > 0) {
        $(".iframeId").css("height", window_h = $(window).height() - 98);
    }
}
$(window).resize(function() {
    auto()
});
