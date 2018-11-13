<#include "../../main/topTable.ftl">
<div class="contentBox" style="padding:10px">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-1); return false">菜单管理 </a> / 子菜单</div>
        <div class="columns columns-left btn-group pull-right">
            <button type="button" class="btn btn-primary btn-sm" onclick="add()">新增菜单
            </button>
        </div>
    </div>
    <table id='table' class="table table-striped table-bordered table-hover table-condensed"></table>
</div>
<input type="hidden" value="${parentId?c}" id="parentId">
<script type="text/javascript">
$(function () {
	var columns=[
				{
					field : 'name',
					title : '名字',
					align : 'center',
                    sortable: true,
				},
				{
					field : 'createTime',
					title : '创建时间',
					align : 'center',
					formatter : function(value, row, index) {
						return  formatDate(new Date(value),"yyyy-MM-dd");
					} ,
                    sortable: true,
				},
				{
					
					field : 'updateTime',
					title : '修改时间',
					align : 'center',
					formatter : function(value, row, index) {
						return  formatDate(new Date(value),"yyyy-MM-dd");
					} ,
                    sortable: true,
				},
				{
								field : 'action',
								title : '操作',
								align:'center',
								formatter :function(value, row, index){
									 return [
									    	 '<button type="button" class="btn btn-success  btn-xs" onclick="edit('+row.id+')">修改</button>',
									    	 '<button type="button" class="btn btn-danger  btn-xs" onclick="removeUser('+row.id+')">刪除</button>'
									    	 ].join(' ');
							} 
				},
		];
		var url = getRootPath() + '/sysMenu/list.do';
		setTableInfo('table', columns, url,getQueryParams,'toolbar');
		$("#submit").click(function(){
			if($("#name").val()=="")
			{
                bootbox.alert({
                    size : 'small',
                    message :"请输入正确的菜单名"
                });
				return false;
			}
			var paras={id:$("#menuId").val(),name:$("#name").val(),url:$("#url").val(),parentId:$("#parentIds").val(),cssStyle:$("#cssStyle").val(),rank:$("#rank").val()};
			var url= getRootPath() +  '/sysMenu/saveOrUpdate.do';
            $.ajaxDefaultCall(url,paras,function () {
                parent.location.reload();
            });
			return false;
		});
	});
   function getQueryParams() {
 		var json = {'parentId':$('#parentId').val()};
		return json;
	};
	function removeUser(id) {
		bootbox.confirm("确认删除?", function(result) {
			if(result){
                var paras = {'id': id};
                var url=getRootPath() + '/sysMenu/delete.do';
                $.ajaxDefaultCall(url,paras,function () {
                    parent.location.reload();
                });
			}
		});
	};
	function edit(id) {
		initMenu();
		$('#myModal').modal('show');
		$.ajax({
			type : 'post',
			url : getRootPath()+'/sysMenu/queryMenuItem.do?&id='+id,
			dataType:"json",
			success : function(data) {
				$("#menuId").val(data.id);
				$("#name").val(data.name);
				$("#url").val(data.url);
				$("#cssStyle").val(data.cssStyle);
                $("#rank").val(data.rank);
			}
		});
	};
	function add(){
		initMenu();
		initFormData();
		$('#myModal').modal('show');
	};
	function initFormData() {
        $("#menuId,#name,#url,#cssStyle,#rank").val("");
	};
	function initMenu()
	{
		$.ajax({
			type : 'post',
			url : getRootPath()+'/sysMenu/queryParentList.do',
			dataType:"json",
			success : function(data) {
				$("#parentIds").empty();
				$("#parentIds").append("<option value =\"0\">顶级菜单</option>)");
				$(data.rows).each(function(index,item){
					$("#parentIds").append("<option value =\""+item.id+"\">"+item.name+"</option>)");
				});
                $("#parentIds option[value='"+$('#parentId').val()+"']").attr("selected",true);
			}
		});
	};
</script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	 aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">新增菜单</h4>
			</div>
			<form id="myForm">
				<div class="modal-body">
					<div class="form-group">
						<label for="exampleInputEmail1">菜单名字:</label><input id="name" name="name"
																			class="form-control"
																			placeholder="请输入菜单名">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">菜单连接:</label><input name="url" id="url"
																			class="form-control"
																			placeholder="请输入url">
					</div>
                    <div class="form-group">
                        <label for="cssStyle">菜单图标:</label><input name="cssStyle" id="cssStyle"
                                                                  class="form-control"
                                                                  placeholder="请输入cssStyle">
                    </div>
                    <div class="form-group">
                        <label for="rank">菜单排序:</label><input name="rank" id="rank"
                                                                  class="form-control"
                                                                  placeholder="数字越小越靠前">
                    </div>
					<div class="form-group">
						<label for="exampleInputEmail1">菜单级别:</label> <select
							id="parentIds" name="parentIds"
							class="form-control select select-default">
                        <!--data-toggle="select"-->
					</select>
					</div>
				</div>
				<input type="hidden" id="menuId"  name="id" value="">
				<div class="modal-footer">
					<button type="button" id="submit" class="btn btn-primary center-block">
						提 交</button>
				</div>
			</form>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<#include "../../main/footer.ftl">