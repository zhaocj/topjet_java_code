<#include "../../main/top.ftl">
<div class="contentBox" style="padding:10px">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">部门管理</a> / 部门信息</div>
    </div>
    <div align="center">
        <form id="myForm" class="form-inline">
            <div class="form-group pull-left fontsize-14">
                <font>&nbsp;&nbsp;部门名称:</font>
                <input type="text" class="form-control" id="roleName" placeholder="请输入新增的部门名称"
                       value="${(sysRoleModel.roleName)!""}" required>
            </div>
            <input type="hidden"
                   id="sysRoleModelId" name="id" value="${(sysRoleModel.id)!""}">
            <div class="columns columns-left btn-group pull-right">
                <button type="button" class="btn btn-primary btn-sm" id="submit" data-loading-text="正在提交..."
                        autocomplete="off"> <#if sysRoleModel??>确认修改部门<#else>确认添加部门</#if>
                </button>
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
<script type="text/javascript">
    <!--定义StringBuilder对象-->
    function StringBuilder(value){	this.strings = new Array("");this.append(value);}
    StringBuilder.prototype.append = function (value){	if (value){	this.strings.push(value);}};
    StringBuilder.prototype.clear = function (){this.strings.length = 1;};
    StringBuilder.prototype.toString = function ()	{return this.strings.join("");	};
    <!--同步获取权限json-->
    var getTree=function(){
        var id=$('#sysRoleModelId').val();
        var url = getRootPath() + '/sysRoleController/getMenuTree.do?&id='+id;
        var treeData;
        $.ajax({
            type:"POST",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            url:url,
            dataType:"json",
            cache:false,
            async :false,
            success:function(data){
                treeData=data;
            }
        });
        return treeData;
    };
    $(function () {
        var rightList=getTree();
        var sbChildren=new StringBuilder();
        var sbParent=new StringBuilder();

        <!--输出所有父节点Begin-->
        $.each(rightList, function(i, itemParent) {
            if(itemParent.pid=="0"){
                sbChildren.append("<tr class=\"formID1\">");
                var isParentSelect=" checked=\"checked\" ";
                var hasChildren = false;
                var colCount=0;
                <!--输出当前父节点下所有子节点Begin-->
                $.each(rightList, function(i, itemChildren){
                    if(itemChildren.pid==itemParent.id) {
                        hasChildren = true;
                        var checkboxSelected=" checked=\"checked\" ";
                        <!--控制表格跨列Begin-->
                        var colspanChildren="";
                        var countChildren=0;
                        $.each(rightList, function (j, itemChildrenTemp) {
                            if (itemChildrenTemp.pid == itemParent.id) {
                                countChildren++;
                            }
                        });
                        <!--取模4不等于0，说明不能整行输出，且在输出最后一行的时候-->
                        if(countChildren%4!=0&&(countChildren-1)==colCount&&countChildren>1)
                        {
                            <!--计算需要跨越的列数-->
                            colspanChildren=" colspan=\""+(5-(countChildren%4))+"\"";
                        }
                        <!--控制表格跨列End-->
                        sbChildren.append("<td"+colspanChildren+"><input name=\"children\" type=\"checkbox\" parent=\""+itemParent.id+"\" "+(itemChildren.isCheck=="true"?checkboxSelected:"")+" data=\""+itemChildren.id+"\" "+" >"+itemChildren.text+"</td>");
                        colCount++;
                        <!--每一行输出结束闭合-->
                        if(colCount%4==0)
                        {
                            sbChildren.append("</tr>");
                            sbChildren.append("<tr class=\"formID1\">");
                        }
                        if(itemChildren.isCheck=="false")
                        {
                            isParentSelect="";
                        }
                    }
                });
                <!--输出当前父节点下所有子节点end-->
                if (!hasChildren) {
                    isParentSelect = "";
                }
                sbChildren.append("</tr>");
                sbParent.append("<tr>");
                sbParent.append("<td colspan=\"4\" class=\"info\"><input name=\"parent\" type=\"checkbox\""+isParentSelect+" data=\""+itemParent.id+"\">"+itemParent.text+"</td>");
                sbParent.append("</tr>");
                sbParent.append(sbChildren.toString());
                sbChildren.clear();
            }
        });
        <!--输出所有父节点end-->
        $("#roleList").html(sbParent.toString());
        $("input[name=parent]").click(function () {
            if ($(this).is(":checked")) {
                $("input[parent='" + $(this).attr("data") + "']").prop("checked",true);
            } else {
                $("input[parent='" + $(this).attr("data") + "']").removeAttr("checked");
            }
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
    });
    $("#submit").click(function () {


        if(isNull($('#roleName').val())){
            bootbox.alert("部门名称不能为空");
            return;
        }
            var bt=$('#myButton').button('loading');
            var url = getRootPath() + '/sysRoleController/saveOrUpdate.do';
            var fromParams={'roleName':$('#roleName').val(),'state':"0",'descr':'','id':$('#sysRoleModelId').val()};
            var array=new Array();
            $("input:checkbox[name='children']:checked").each(function () {
                    array.push($(this).attr("data"));
            });
            var treeArray={'menuIds':array};
            var data=$.extend({}, fromParams, treeArray);
            $.ajaxDefaultCall(url,data,function () {
                location.href = "../view/sysAdmin/sysRole/list.html";
            });
    });
</script>
<#include "../../main/footer.ftl">