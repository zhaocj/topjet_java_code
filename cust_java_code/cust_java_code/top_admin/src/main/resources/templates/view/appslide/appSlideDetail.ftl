<#include "../main/top.ftl">
<link href="<@s.url '/umeditor/themes/default/css/umeditor.css'/>" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<@s.url '/umeditor/third-party/template.min.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<@s.url '/umeditor/umeditor.config.js'/>"></script>
<script type="text/javascript" charset="utf-8" src="<@s.url '/umeditor/umeditor.min.js'/>"></script>
<script type="text/javascript" src="<@s.url '/umeditor/lang/zh-cn/zh-cn.js'/>"></script>
<style>

    .edui-container,.edui-body-container{width:100% !important;}
    .edui-body-container{min-height:300px;}
</style>
<div class="contentBox" style="padding:10px">
    <div class="fixed-table-toolbar">
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-2); return false">轮播图管理</a> /
        <#switch aim.type>
            <#case 1> <a href="#" onclick="javascript:history.back(-1); return false">APP轮播图管理</a>/ 修改内容<#break>
            <#case 2> <a href="#" onclick="javascript:history.back(-1); return false">商城轮播图管理</a>/ 修改内容<#break>
        </#switch>
        </div>
        <div class="columns columns-left btn-group pull-right">
            <button type="button" id="myButton" class="btn btn-primary btn-sm margin-left-10" onclick="update()">提交修改</button>
        </div>
    </div>
    <div class="clearfix line1px"></div>
    <div class="container-fluid">
        <form id="myForm">
            <div class="row">
                <div class="col-xs-4">
                    <div class="form-group">
                        <label>web标题</label>
                        <input type="text" name="title" id="title"  class="form-control" value="${aim.title}">
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 20px;">
                <div class="col-xs-3">
                    <iframe id="ifr" name="myframe" width="320px" height="568px" frameborder="0" src="${request.contextPath}/view/appslide/iphone.html"></iframe>
                </div>
                <!--style给定宽度可以影响编辑器的最终宽度-->
                <div class="col-xs-9">
                    <script type="text/plain" id="content" style="width:1000px;height:508px;">${aim.content}</script>
                    <div id="btns">
                        <button class="btn" id='btn'>预览</button>
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>
<input type="hidden" id="id" name="id" value="${aim.id!}">
<script type="text/javascript">
    $(function(){
        $.topjectValidate('myForm');
        if(typeof FileReader==='undefined'){
            bootbox.alert({
                size : 'small',
                message :"浏览器不支持图片预览，请更换浏览器!",
            });
        }
    });


    function update(){
        var bt=$('#myButton').button('loading');
        if(!$.topjectIsValidate('myForm')){
            bt.button('reset');
            return;
        }
        var text =UM.getEditor("content").getContent();
        var json = {
            'id':$("#id").val(),
            'title':$("#title").val(),
            'content': text
        }
        var data = json;
        var url = getRootPath()+'/appSlide/updateAppSlide.do';
        window.parent.$.ajaxDefaultCall(url,data,function () {
            window.history.back();
        });
    }
    var um = UM.getEditor('content');
    //当点击时，超过100个字符时，所触发的事件
    UM.getEditor('content').addListener('keyup',function(editor){
                var content = UM.getEditor('content').getContent();
                if(content.length>=200){
                    alert("只能输入200个字符");
                }
            }
    );

    var html = '';
    var ifr = document.getElementById('ifr');
    document.getElementById('btn').onclick = function () {
        var arr = [];
        arr.push(UM.getEditor('content').getContent());
        html = arr.join("\n");
        ifr.contentWindow.document.getElementById('uu').innerHTML = html
    };


</script>
<#include "../main/footer.ftl">