<#include "../main/top.ftl">
<form id="myForm">
    <input type="hidden" id="id" name="id" value="${cityBean.id?c}"/>
    <input type="hidden" id="idOlder" name="idOlder" value="${cityBean.id?c}"/>
    <input type="hidden" id="cityNameOlder" name="cityNameOlder" value="${cityBean.cityName!''}">
    <input type="hidden" id="parentIdOlder" name="parentIdOlder" value="${cityBean.flag!''}">
    <input type="hidden" id="baiduCityIdOlder" name="baiduCityIdOlder" value="${cityBean.citycode!''}">
    <#--<input type="hidden" id="weatherIdOlder" name="weatherIdOlder" value="${cityBean.weatherId!''}">-->
    <input type="hidden" id="zipOlder" name="zipOlder" value="${cityBean.zip!''}">
    <div class="modal-body  fontsize-14">
        <div class="col-xs-6">
            <div class="form-group">
                <label>自身级别</label>
            <#if cityBean.level == 2>
                <input type="text" readonly  class="form-control" id="cityId" value="市">
            <#elseif cityBean.level == 3>
                <input type="text" readonly  class="form-control" id="cityId" value="县">
            </#if>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>所属上级编号</label>
                <div class="clearfix"></div>
                <div class="row">
                <#if cityBean.level == 2>
                    <input type="text" class="form-control"
                           value="${cityBean.province!''}${cityBean.cityDisplayName!'' }" readonly>
                <#elseif cityBean.level == 3>
                    <input type="text" class="form-control" value="${cityBean.province!''}" readonly>
                </#if>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>城市名称</label>
                <input type="text" class="form-control input-sm" name="cityName" id="cityName"
                       value="${cityBean.cityName!'' }">
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>城市编号</label>
                <input type="text" class="form-control input-sm" value="${cityBean.adcode!''}" disabled>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>是否直辖市</label>
                <select id="flag" name="flag" data-toggle="select"
                        class="form-control select select-default select-sm">
                    <option value="1"  <#if cityBean.flag == 1>selected</#if>>是</option>
                    <option value="2"  <#if cityBean.flag == 2>selected</#if>>否</option>
                </select>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>高德地图代码</label>
                <input type="text" class="form-control input-sm" name="citycode" id="baiduCityId"
                       value="${cityBean.citycode !''}">
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>城市经度</label>
                <input type="text" id="longitude" name="longitude" value="${cityBean.longitude?c!'' }" class="form-control input-sm">
            </div>
        </div>
        <div class="col-xs-6">
            <div class="form-group">
                <label>城市纬度</label>
                <input type="text" id="latitude" name="latitude" value="${cityBean.latitude?c!''}" class="form-control input-sm" >
            </div>
        </div>
        <div class="clearfix"></div>
      <#--  <div class="col-xs-6">
            <div class="form-group">
                <label>中国天气网城市代码</label>
                <input type="text" name="weatherId" id="weatherId" value="${cityBean.weatherId!''}"
                       class="form-control input-sm">
            </div>
        </div>-->
        <div class="col-xs-6">
            <div class="form-group">
                <label>邮编</label>
                <input type="text" maxlength="6" class="form-control input-sm" name="zip" id="zip"
                       value="${cityBean.zip!''}">
            </div>
        </div>
    </div>
    <div class="clearfix"></div>
    <div class="modal-footer margin-top-15">
        <button type="button" class="btn btn-primary center-block" type="submit" id="submitButton"
                onclick="confirmSubmit()">　提　交　
        </button>
    </div>
</form>
<#include "../main/footer.ftl">
<script type="text/javascript">
    function confirmSubmit() {
        if (IsFormChangeTrue("myForm")) {
            var url = getRootPath() + '/cityAction/update.do';
            var json = {
                'cityName':$("#cityName").val(),
                'flag': $("#flag").val(),
                'citycode': $("#baiduCityId").val(),
                'latitude': $("#latitude").val(),
                'longitude': $("#longitude").val(),
                'zip': $("#zip").val(),
                'id':$("#id").val()
            };
            window.parent.$.ajaxDefaultCall(url, json, function () {
                window.parent.location.href = getRootPath() + '/view/city/list.html'
            });
            // window.parent.$.ajaxDefaultCall(url,$("#myForm").serialize(),function(){$("#modalEdit").modal('hide');window.parent.tableRefresh()});
        }else{
            window.parent.location.href = getRootPath() + '/view/city/list.html'
        }
    }
</script>
