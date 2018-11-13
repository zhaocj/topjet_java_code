<#include "../main/top.ftl">
<div class="contentBox" style="padding:10px">
    <div class="fixed-table-toolbar">
        <div class="columns columns-left btn-group pull-right">
            <button id="sycId" type="button" class="btn btn-default btn-sm margin-left-10" onclick="toUpdate()">点击生效
            </button>
        </div>
    </div>
    <table class="table table-bordered" id="btnedit">
        <thead>
        <tr>
            <th style="width:120px">电话调查时长</th>
            <th style="width:120px">操作</th>
        </tr>
        </thead>
        <tr>
            <td width="10px" id=duration class="idedit" olderValue="${callSurveyDurationBean.duration}"
                >${callSurveyDurationBean.duration}</td>
            <td width="10px"><input type="button" class="btn btn-primary btn-sm" value="编辑"></td>
        </tr>
    </table>
</div>
<#include "../main/footer.ftl">
<script type="text/javascript">
    var buttonFlag = false;
    $("#btnedit input:button").click(function () {
        var str = $(this).val() == "编辑" ? "确定" : "编辑";
        $(this).val(str);   // 按钮被点击后，在“编辑”和“确定”之间切换
        if ($(this).val() == "编辑") {
            $(this).removeClass("btn-info");
            $(this).addClass("btn-primary");
        } else if ($(this).val() == "确定") {
            $(this).removeClass("btn-primary");
            $(this).addClass("btn-info")
        }
        ;
        var changeFlag = false;
        $(this).parent().siblings(".idedit").each(function () {  // 获取当前行的其他单元格
            var obj_text = $(this).find("input:text");    // 判断单元格下是否有文本框
            var olderValue = $(this).attr("olderValue");
            if (!obj_text.length) {   // 如果没有文本框，则添加文本框使之可以编辑
                if ($(this).find("font").length > 0) {
                    $(this).children("font").remove();
                    $(this).html("<input type='text'  onblur='checkValue1(this)'  onkeyup='this.value=this.value.replace(\/\\D\/g, \"\" ) value='" + $(this).text() + "'><font>%</font>");
                } else {
                    $(this).html("<input type='text'  onblur='checkValue1(this)' onkeyup='this.value=this.value.replace(\/\\D\/g, \"\" )' style='width:100px' value='" + $(this).text() + "'>");
                }
            } else {   // 如果已经存在文本框，则将其显示为文本框修改的值
                if ($(this).find("font").length > 0) {
                    if (obj_text.val() == "") {
                        $(this).html(olderValue + "<font>%</font>");
                    } else {
                        $(this).html(obj_text.val() + "<font>%</font>");
                    }
                } else {
                    if (obj_text.val() == "") {
                        $(this).html(olderValue);
                    } else {
                        $(this).html(obj_text.val());
                    }
                }

                if (!changeFlag && !isNull(olderValue) && olderValue != obj_text.val()) {
                    changeFlag = true;
                }
            }
        });
        if (changeFlag) {
            $(this).parent().parent().addClass("danger");
            buttonFlag = true;
        } else {
            if (!$(this).parent().parent().hasClass("abc")) {
                $(this).parent().parent().removeClass("danger");
            }
        }
        if ($(this).val() == "编辑") {
            if ($("#btnedit .danger").not(".abc").length > 0 || buttonFlag) {
                $("#sycId").removeAttr("disabled");
                $("#sycId").removeClass("btn-default");
                $("#sycId").addClass("btn-primary");
            } else {
                $("#sycId").attr("disabled", "disabled");
                $("#sycId").addClass("btn-default");
                $("#sycId").removeClass("btn-primary");
            }
        }
    });


    function checkValue1(obj) {
        if (!(/^[1-9]\d*|0$/.test(obj.value))) {
            bootbox.alert({
                size: 'larger',
                message: '请输入整数！',
            });
            $(obj).val("");
            return;
        }
    }


    function toUpdate() {
        var url = getRootPath() + '/callSurveyDuration/update.do';
        var json = {
            duration:$("#duration").text()
        }
        window.parent.$.ajaxDefaultCall(url, json, function () {
            window.location.href = getRootPath() + '/callSurveyDuration/callSurveyDurationList.do'
        });
    }
</script>
