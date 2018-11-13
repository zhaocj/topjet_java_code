var $tableDom;
var ParamsJsons;
/**
 * 
 * @param url 数据源
 * @param title modal标题
 * @param type model大小类型(sm,lg)
 * @param isRefreshflag 判断是否需要刷新页面 0：不需要,1:需要,2:删除session
 */
function modalEdit(url, title,type,isRefreshflag) {
    /*	$('.modal-body').append('<iframe id="editIframeId" scrolling="no" frameborder="0" onLoad="iFrameHeight()" src="' + url + '" ></iframe>');*/
    setIframeSrc(url);
    setModalTitle(title,type,isRefreshflag);
    setIframeSize();
    $('#modalEdit').modal({
        /* remote:'<iframe  src="' + url + '" style="width:100%;height:100%;"></iframe>',*/
        backdrop: 'static',
    });
}

function createQueryTable(conditions){
	var html = [];
	html.push('<form class="form-inline queryParameters-margin">');
	html.push('<table class="table">');
	var buttonList = [];
	$.each(conditions,function(i,condition){
		var type = condition.type;
		if(type == 'button'){
			buttonList.push(condition);
		}else{
			if(i%4 == 0){
				html.push('<tr>');
			}
			html.push('<td>');
			html.push('<div class="form-group">');
			html.push('<label for="'+condition.nameId+'Id">'+condition.label+'</label>');
			 if(type == 'select'){
				 html.push('<select id="'+condition.nameId+'Id" name="'+condition.nameId+'"  class="form-control">');
					html.push('<option value="">请选择</option>');
					$.each(condition.data,function(i,selects){
						html.push('<option value="'+selects.value+'">'+selects.display+'</option>');
					});
					html.push('</select>');
			 }else{
					html.push('<input type="'+type+'" name="'+condition.nameId+'" class="form-control" id="'+condition.nameId+'Id" placeholder="请输入'+condition.label+'">');
			}
			html.push('</div>');
			html.push('</td>');
			if((i+1) > 3 && (i+1)%4 == 0){
				html.push('</tr>');
			}
		}
	});
	if(conditions.length%4 != 0){
		html.push('</tr>');
	}
	html.push('</table>');
	html.push('<div id="toolbar">');
	html.push('<button type="button" class="btn btn-primary" onclick="tableQuery()">查询</button>');
	$.each(buttonList,function(i,buttons){
		html.push('<button type="button" class="btn btn-primary" onclick="'+buttons.nameId+'">'+buttons.label+'</button>');
	});
	html.push('</div>');
	html.push('</form>');
	html.push("<table id='table'></table>");
	$(document.body).append(html.join(""));
}

/**
 *
 * @param table tableId
 * @param columns 列属性数组
 * @param url table远程加载的url
 * @param params 远程加载的参数(类型为function)
 * @param toolbarId  操作按钮块DIV的ID
 *  * @param bootreFlag  是否显示refresh button  bootreFlag = 1表示隐藏
 */
function setTableInfo(table, columns, url, ParamsJson,toolbarId,bootreFlag,tableHeight) {

    ParamsJsons = ParamsJson;
    $tableDom = $('#' + table);
    if(isNull(tableHeight)){
    	tableHeight=600;
    }
    $tableDom.bootstrapTable({
    	method:'POST',
        url: url,
        buttonsAlign: 'left',
        contentType:'application/x-www-form-urlencoded;charset=utf-8',
        toolbarAlign:'right',
        toolbar:'#'+toolbarId,
        /*showFooter:true,*/
        pagination: true,
        sidePagination: 'server',
        silentSort: true,
        pageList: [10, 25, 50],

        pageSize: isNull(getCookie("pageSizeReco1"))?(isNull(getCookie("pageSizeReco2"))?10:getCookie("pageSizeReco2")):getCookie("pageSizeReco1"),
        showExport: false,
        pageNumber:isNull(getCookie("pageNumberReco1"))?(isNull(getCookie("pageNumberReco2"))?1:getCookie("pageNumberReco2")):getCookie("pageNumberReco1"),
      //  pageNumber:($("#pageNumberReco1").length >0&& $("#pageNumberReco1").val()!= 1)?$("#pageNumberReco1").val():1,
        /*   exportDataType:exportType, */
        queryParams: refreshParamsJson,
        sortName: 'id',
        sortOrder: 'desc',
        search: false,
        showHeader: true,
        showColumns: true,
        showRefresh: true,
        showToggle: true,
        /*showPaginationSwitch: true,*/
        cache: false,
        striped: true,
        paginationDetailHAlign: 'left',
        paginationHAlign: 'right',
        height:tableHeight,
        columns: columns
    });
    tableToolbarMargin(bootreFlag);
}


$(window).resize(function () {
    setIframeSizes();
})

function tableToolbarMargin(bootreFlag){
    if(!isNull(bootreFlag) && bootreFlag == 1){
        $('.fixed-table-toolbar').children(".pull-left").css("display","none");
    }else{
        $('.fixed-table-toolbar').addClass('bootstrap-table-toolbar-margin');
    }
}


function setIframeSizeMax() {
    $("#editIframeId").width($(".modal-dialog").outerWidth()-50);
    $("#editIframeId").height($('#editIframeId').contents().height() + 60);
}

function setIframeSize() {
    $("#editIframeId").width($(".modal-dialog").outerWidth() - 30);
    $("#editIframeId").height($('#editIframeId').contents().height() + 30);
}

function setIframeSizes() {
    $("#editIframeId").width($(".modal-dialog").outerWidth() - 30);
    $("#editIframeId").height($('#editIframeId').contents().find("body").height() + 30);
}

function setIframeSrc(url) {
    $("#editIframeId").attr('src', url);
}

function setModalTitle(title,type,isRefreshflag) {
    if (!isNull(title)) {
        $("#myModalLabel").text(title);
    }
    $("#isRefreshFlag").val(isRefreshflag);//判断是否需要刷新页面
    if(!isNull(type)){
        $('.modal-dialog').removeClass('modal-lg modal-sm');
        if(type=='sm'){
            $('.modal-dialog').addClass('modal-sm');
        }else if(type=='lg'){
        	 $('.modal-dialog').addClass('modal-lg');
        }
    }
}

function refreshParamsJson() {
    var params = ParamsJsons();
    var pageNumber = $(".pagination .active a").text();
    var pageSize = $(".page-size").text();
    if(params.cookieFlag == 1){
        if(!isNull(getCookie("pageNumberReco1"))){
            pageNumber = getCookie("pageNumberReco1");
            pageSize = getCookie("pageSizeReco1");
        }
    }
    if(params.cookieFlag == 2){
        if(!isNull(getCookie("pageNumberReco2"))){
            pageNumber = getCookie("pageNumberReco2");
            pageSize = getCookie("pageSizeReco2");
        }
    }
    var page = {'rows': isNull(pageSize) ? 10 : pageSize, 'page': isNull(pageNumber) ? 1 : pageNumber};
    var paramsJson = $.extend({}, params, page);
    return paramsJson;
}

function closeModal() {
    $('#modalEdit').modal('hide');
    /*tableRefresh();*/
}

$(function(){
 $("#modalEdit").on("hidden.bs.modal",function(e) {
/* $(this).removeData('bs.modal');
 $('#editIframeId').remove();
 setIframeSrc('');
 $tableDom.bootstrapTable('refresh');*/
     tableRefresh();

 });

 });
 

function tableQuery() {
     var days =$('#days').val();
    var amount =$('#amount').val();
    if((days != '' && amount =='')|| (days == '' && amount !='')){
        bootbox.alert({
            size : 'small',
            message :"必须同时输入天数和订单数",
        });
    }
    $tableDom.bootstrapTable('selectPage', 1);
    if($('tr').is('.no-records-found')){
    	tableRefresh();
    }
    /*no-records-found*/
    var sourceType = $('#sourceType').val();
    var auditAllName = $('#auditAllName').val();
    if(sourceType != '' && auditAllName == ''){
            alert("审核人姓名不能为空!");
            return;
    }
    else if(sourceType == '' && auditAllName != ''){
        alert("请选择审核类型！");
        return;
    }
}

function tableRefresh() {
    $tableDom.bootstrapTable('refresh');
}

