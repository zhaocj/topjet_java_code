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
        <div class="navmenu"><a href="#" onclick="javascript:history.back(-2); return false">问题管理</a> /
        <#switch helpCategoryModel.version>
            <#case 1> <a href="#" onclick="javascript:history.back(-1); return false">发货版</a>/ 修改内容<#break>
            <#case 2> <a href="#" onclick="javascript:history.back(-1); return false">接货版</a>/ 修改内容<#break>
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
                        <label>问题内容</label>
                        <input type="text" name="title" id="title"  class="form-control" value="${helpQuestionModel.title}">
                    </div>
                </div>
                <div class="col-xs-6" style="margin-top: 30px;">
                    <div class="form-group">
                        <label>问题类型</label>
                        <select id="name" name="name" data-toggle="select"
                                class="form-control select select-default select-sm" style="width: 450px;">
                            <#if helpCategoryModel.version == 1>
                                <option value="1" <#if helpCategoryModel.name == "账号问题">selected</#if>>账号问题</option>
                                <option value="2" <#if helpCategoryModel.name == "找车问题">selected</#if>>找车问题</option>
                                <option value="3" <#if helpCategoryModel.name == "发货问题">selected</#if>>发货问题</option>
                                <option value="4" <#if helpCategoryModel.name == "钱包问题">selected</#if>>钱包问题</option>
                            </#if>
                            <#if helpCategoryModel.version == 2>
                                <option value="1" <#if helpCategoryModel.name == "账号问题">selected</#if>>账号问题</option>
                                <option value="2" <#if helpCategoryModel.name == "找货问题">selected</#if>>找货问题</option>
                                <option value="3" <#if helpCategoryModel.name == "承运问题">selected</#if>>承运问题</option>
                                <option value="4" <#if helpCategoryModel.name == "钱包问题">selected</#if>>钱包问题</option>
                            </#if>
                        </select>
                        <#--<label for="fileId1">上传图片</label>
                        <div class="input-group" style="margin-left: 600px; margin-top: -30px;">
                            <input id="fileId1" type="file" onchange="readFile(this,'icon','fileImageId1')">
                            <img id="fileImageId1" src="${helpCategoryModel.icon!}" required width="56" height="56"  style="margin-top: 10px;margin-left: 10px!important;"
                                 class="topjet-img-size"/>
                        </div>-->
                        <div class="col-xs-6" style="margin-left: 555px; margin-top: -30px;">
                            <div class="form-group">
                                <label style="margin-left: -45px;">常见问题</label>
                                <select id="sortNo" name="sortNo" data-toggle="select" style="width: 450px;margin-top: -35px;margin-left: 10px;"
                                        class="form-control select select-default select-sm select2-offscreen" tabindex="-1"
                                        title="">
                                    <option value="1" <#if helpQuestionModel.sortNo == 1>selected</#if>>已开启</option>
                                    <option value="0" <#if helpQuestionModel.sortNo == 0>selected</#if>>已关闭</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 20px;">
                <div class="col-xs-3">
                    <iframe id="ifr" name="myframe" width="320px" height="568px" frameborder="0" src="${request.contextPath}/view/helpCenter/iphone.html"></iframe>
                </div>
                <!--style给定宽度可以影响编辑器的最终宽度-->
                <div class="col-xs-9">
                    <script type="text/plain" id="content" style="width:1000px;height:508px;">${helpQuestionModel.content}</script>
                    <div id="btns">
                        <button class="btn" id='btn'>预览</button>
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>
<input type="hidden" id="icon" name="icon" value="${helpCategoryModel.icon!}">
<input type="hidden" id="helpCategoryID" name="helpCategoryID" value="${helpCategoryModel.helpCategoryID!}">
<input type="hidden" id="helpQuestionID" name="helpQuestionID" value="${helpQuestionModel.helpQuestionID!}">
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
        var filter = function(str){// 特殊字符转义
            str += ''; // 隐式转换
            str = str.replace(/%/g, '%25');
            str = str.replace(/\+/g, '%2B');
            str = str.replace(/ /g, '%20');
            str = str.replace(/\//g, '%2F');
            str = str.replace(/\?/g, '%3F');
            str = str.replace(/&/g, '%26');
            str = str.replace(/=/g, '%3D');
            str = str.replace(/#/g, '%23');
            return str;
        }
        var json = {
            'helpQuestionID':$("#helpQuestionID").val(),
            'helpCategoryID':$("#helpCategoryID").val(),
            'name':$("#name").val(),
            'title': $("#title").val(),
            'sortNo':$("#sortNo").val(),
            'version':$("#version").val(),
            'content': text
        }
        var data = filter(JSON.stringify(json));
        data = JSON.parse(data)
        var url = getRootPath()+'/helpCenter/updateQuestion.do';
        window.parent.$.ajaxDefaultCall(url,data,function () {
            window.history.back();
        });
    }
    var um = UM.getEditor('content');
    //当点击时，超过200个字符时，所触发的事件
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