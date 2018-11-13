<#include "../../main/top.ftl">
<div class="contentBox" style="padding:10px">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">用户管理</a> /
<#if user??>修改用户<#else>新增用户</#if>
        </div>
    </div>
    <div align="center">
        <form id="myForm" class="form-inline">
            <div class="form-group pull-left fontsize-14">
                <font>所属部门:</font>
                <select class="form-control" id="roleId" name="roleId" required>
                    <#if currUser??&&currUser.superAdmin==2>
                        <#list roleDataList as roleData>
                            <#if currRole??&&roleData.id==(currRole.id!"")>
                            <option value="${roleData.id }"
                                    <#if currRole??&&roleData.id==(currRole.id!"")>selected</#if>>${roleData.roleName}</option>
                            </#if>
                        </#list>
                    <#else>
                        <#list roleDataList as roleData>
                                 <option value="${roleData.id }"
                                             <#if currRole??&&roleData.id==(currRole.id!"")>selected</#if>>${roleData.roleName}</option>
                        </#list>
                    </#if>
                </select>
            </div>
            <div class="form-group pull-left  -14">
                <font>&nbsp;&nbsp;邮箱:</font>
                <input type="email" name="email" class="form-control" id="exampleInputName1" onblur="checkEmail()" value="${(user.email)!""}"
                       placeholder="请输入邮箱" required>
            </div>
            <div class="form-group pull-left fontsize-14">
                <font>&nbsp;&nbsp;用户姓名:</font>
                <label for="exampleInputName2"></label>
                <input type="text" class="form-control" name="nickName" id="exampleInputName2"
                       value="${(user.nickName)!""}"
                       placeholder="请输入姓名">
            </div>
            <div class="form-group pull-left fontsize-14">
                <font>&nbsp;&nbsp;登录密码:</font>
                <input type="text" name="pwd" class="form-control" id="pwd" value=""
                       placeholder="如需修改密码，请输入" required>
            </div>
            <input type="hidden" name="id" id="userId" value="${(user.id)!""}">
            <div class="columns columns-left btn-group pull-right">

            <#if user??>
                <#if Session['session_user'].superAdmin==1  ||  Session['session_user'].id == user.createBy>
                    <button type="button" class="btn btn-primary btn-sm" id="submit" data-loading-text="正在提交..."
                            autocomplete="off">确认修改用户
                    </button>
                </#if>
            <#else>
                <button type="button" class="btn btn-primary btn-sm" id="submit" data-loading-text="正在提交..."
                        autocomplete="off">确认新增用户
                </button>
            </#if>
            </div>
        </form>
    </div>
    <div class="clearfix"></div>
    <div class="title-h1">分配权限:</div>
    <table class="table tableInput">
        <tbody id="roleList">
        </tbody>
    </table>
</div>
<!--end-->
<input type="hidden" id="superAdmin" name="id" value="${currUser.superAdmin}">
<script type="text/javascript">
    <!--定义StringBuilder对象-->
    function StringBuilder(value) { this.strings = new Array("");  this.append(value); }
    StringBuilder.prototype.append = function (value) {if (value) {this.strings.push(value); }};
    StringBuilder.prototype.clear = function () {this.strings.length = 1; };
    StringBuilder.prototype.toString = function () {return this.strings.join("");};
    <!--同步获取权限json-->
    var getTree = function () {
        var id = $("#roleId").val();
        var userid=$("#userId").val();
        var url = getRootPath() + '/sysRoleController/getMenuTree.do?&id=' + id+"&userId="+userid;
        var treeData;
        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: url,
            dataType: "json",
            cache: false,
            async: false,
            success: function (data) {
                treeData = data;
            }
        });
        return treeData;
    };
    <!--根据权限json组装输出UI-->
    var initAuthority = function () {
        var isLeader=$("#superAdmin").val()=="2";
        var rightList = getTree();
        var sbChildren = new StringBuilder();
        var sbParent = new StringBuilder();
        var disabled=" disabled=\"disabled\" ";
        <!--输出所有父节点Begin-->
        $.each(rightList, function (i, itemParent) {
            if (itemParent.pid == "0") {
                sbChildren.append("<tr class=\"formID1\">");
                var isParentSelect = " checked=\"checked\" ";
                var hasChildren = false;
                var colCount=0;
                <!--输出当前父节点下所有子节点Begin-->
                $.each(rightList, function (i, itemChildren) {
                    if (itemChildren.pid == itemParent.id) {
                        hasChildren = true;
                        var checkboxSelected = " checked=\"checked\" ";
                        <!--控制表格跨列Begin-->
                        var colspanChildren="";
                        var countChildren=0;
                        <!--计子节点个数-->
                        $.each(rightList, function (j, itemChildrenTemp) {
                            if (itemChildrenTemp.pid == itemParent.id) {
                                countChildren++;
                            }
                            if(isLeader&&itemChildrenTemp.isCheck != "true")
                            {
                                countChildren--;
                            }
                        });
                        <!--取模4不等于0，说明不能整行输出，且在输出最后一行的时候-->
                        if(countChildren%4!=0&&(countChildren-1)==colCount&&countChildren>1)
                        {
                            <!--计算需要跨越的列数-->
                            colspanChildren=" colspan=\""+(5-(countChildren%4))+"\"";
                        }
                        <!--控制表格跨列End-->
                        if(isLeader) {//当是部门领导的情况下，展示该部门能够向下分配的权限

                                 if(itemChildren.isShow == "true") {
                                     sbChildren.append("<td" + colspanChildren + "><input name=\"children\" type=\"checkbox\" parent=\"" + itemParent.id + "\" " + (itemChildren.isCheck == "true" ? checkboxSelected : "") + " data=\"" + itemChildren.id + "\" " + " >" + itemChildren.text + "</td>");
                                     colCount++;
                                 }
                        }else{
                            sbChildren.append("<td" + colspanChildren + "><input name=\"children\" type=\"checkbox\" parent=\"" + itemParent.id + "\" " + (itemChildren.isCheck == "true" ? checkboxSelected : "") +   (itemChildren.isShow == "true" ? "": disabled) +" data=\"" + itemChildren.id + "\" " + " >" + itemChildren.text + "</td>");
                            colCount++;
                        }
                        <!--每一行输出结束闭合-->
                        if (colCount % 4 == 0) {
                            sbChildren.append("</tr>");
                            sbChildren.append("<tr class=\"formID1\">");
                        }
                        if (itemChildren.isCheck == "false") {
                            isParentSelect = "";
                        }
                    }
                });
                <!--输出当前父节点下所有子节点end-->
                if (!hasChildren) {
                    isParentSelect = "";
                }
                sbChildren.append("</tr>");
                sbParent.append("<tr>");
                sbParent.append("<td colspan=\"4\" class=\"info\"><input name=\"parent\" type=\"checkbox\"" + isParentSelect  +(isLeader?"":disabled)+" data=\"" + itemParent.id + "\">" + itemParent.text + "</td>");
                sbParent.append("</tr>");
                sbParent.append(sbChildren.toString());
                sbChildren.clear();
            }
        });
        <!--输出所有父节点end-->
        $("#roleList").html(sbParent.toString());
        $("input[name=parent]").click(function () {
            if ($(this).is(":checked")) {
                $("input[parent='" + $(this).attr("data") + "']").prop("checked", true);
            } else {
                $("input[parent='" + $(this).attr("data") + "']").removeAttr("checked");
            }
        }).each(function () {
            <!--移去没有子节点的父节点-->
            var countChildren=0;
            $("input[name=children][parent='"+$(this).attr("data")+"']").each(function () {
                countChildren++;
            });
            if(countChildren==0)
                $(this).parent().parent().hide();
        });
        $("input[name=children]").click(function () {
            var isChildrenAllSelected=true;
            $("input[parent='"+$(this).attr("parent")+"']").each(function () {
                if (!$(this).is(":checked")){
                    isChildrenAllSelected=false;
                }
            });
            var currentParentNode=$("input[name='parent'][data='"+$(this).attr("parent")+"']");
            if(isChildrenAllSelected){
                currentParentNode.prop("checked",true);
            }else{
                currentParentNode.removeAttr("checked");
            }
        });
    };
    $("#roleId").click(function () {
        initAuthority();
    });
    initAuthority();
    $("#submit").click(function () {
        if (!isEmail($("#exampleInputName1").val())) {
            bootbox.alert({
                size: 'small',
                message: "请填写正确邮箱地址",
            });
            return false;
        }
        if ($("#exampleInputName2").val()=='') {
            bootbox.alert({
                size: 'small',
                message: "请填写正确用户名",
            });
            return false;
        }
        if ($("#pwd").length>0&&$("#pwd").val().length>0&&$("#pwd").val().length<6) {
            bootbox.alert({
                size: 'small',
                message: "请填写六位及以上的密码",
            });
            return false;
        }
        var arrayPara = new StringBuilder();
        $("input:checkbox[name='children']:checked").each(function () {
            arrayPara.append("&menuIds=");
            arrayPara.append($(this).attr("data"));
        });
        var url = getRootPath() + '/sysUser/saveOrUpdate.do';
        var paras=$('#myForm').serialize() + arrayPara.toString();

        $.ajax({
            type: "POST",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url: url,
            data: paras,
            dataType: "json",
            cache: false,
            traditional: true,
            success: function (data) {
                if (data != null) {
                    if (data.status == "04") {
                        bootbox.alert({
                            buttons: {
                                ok: {
                                    label: '确定'
                                }
                            },
                            size: 'small',
                            message: "邮箱已存在",
                            title: "提示信息"
                        });
                        $("#exampleInputName1").val("");
                    } else {
                        bootbox.alert({
                            buttons: {
                                ok: {
                                    label: '确定'
                                }
                            },
                            size: 'small',
                            message: data.msg,
                            callback: function () {
                                location.href = "../view/sysAdmin/sysUser/list.html";
                            },
                            title: "提示信息"
                        });
                    }

                }
            },
            error: function (xhr) {

                var sessionStatus = xhr.getResponseHeader('sessionstatus');
                var errorMSG = xhr.getResponseHeader("errorMSG");

                if (sessionStatus == 'timeout') {
                    var top = getTopWinowd();
                    var yes = confirm('由于您长时间没有操作, session已过期, 请重新登录.');
                    if (yes) {
                        top.location.href = getContextPath() + '/view/main/login.html';
                    }
                } else if (errorMSG != undefined && errorMSG != null) {
                    alert(errorMSG);
                } else {
                    alert("网络异常");
                }
            }
        });
    });
    var isEmail = function (str) {
        var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
        return reg.test(str);
    };

    function checkEmail(){
        var email=$("#exampleInputName1").val()+"";
        $.ajax({
            type: "POST",
            url : getRootPath() + '/sysUser/checkEmail.do',
            data: email,
            contentType:"application/json",
            success: function(data){
                if (data.success == "false") {
                    bootbox.alert({
                        size: 'small',
                        message: "该用户已存在，请重新输入邮箱!",
                    });
                    $("#exampleInputName1").val("");
                    return false;
                }
            }
        })
    }
</script>
<#include "../../main/footer.ftl">