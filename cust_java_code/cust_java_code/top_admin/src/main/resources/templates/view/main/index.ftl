<#--<#import "/spring.ftl" as s />-->
<#include "top.ftl">
<nav class="navbar navbar-inverse navbar-static-top" role="navigation">
<#include "nav.ftl">
</nav>
<div id="conBOX">
  <div id="contentmenu">
    <#include "leftMenu.ftl">
  </div>
  <div id="content">
  <div id="tt" class="easyui-tabs" style="width:100%;height:auto;">
	<div title="首页">
    <#--<iframe class="iframeId" scrolling="auto" frameborder="0" src="${request.contextPath}/view/main/index.html" style="width:100%;"></iframe>-->
	</div>
</div>
  </div>
</div>
<!--新增菜单-->
<!-- /.modal -->
<#include "footer.ftl">
<div class="modal fade" id="myModal">
<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header"></div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script>
    /*已移之定时任务*/
    /*window.setInterval(function (){
        toCreateTasks();
    },300000);

    function toCreateTasks(){
        $.ajax({
            type : 'post',
            url : getRootPath()+'/taskAssignAction/createAndAssignTasks.do',
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            dataType:"json",
            cache:false,
            data : {},
            success : function() {
            }
        })
    }*/
</script>