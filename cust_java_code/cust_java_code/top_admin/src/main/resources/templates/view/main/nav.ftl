<!-- Brand and toggle get grouped for better mobile display -->
<#if remind.allRemind>
<input id="sessionSysUserFlag" type="hidden" value="1">
</#if>
<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-5"><span
            class="sr-only">Toggle navigation</span></button>
    <a class="navbar-brand" href="#">交运配货网后台</a></div>
<#if account!=''>
<div>
    <iframe src="${request.contextPath}/edb_bar/phoneBar/phonebar.html?loginName=${account}&&password=${password}&&loginType=gateway"
            width="100%" height="100%" id="myIframe" scrolling="no" style="float: left;width: 1000px;height: 50px;border: 0;position: absolute; background: none">

    </iframe>
</div>
</#if>

<!-- Collect the nav links, forms, and other content for toggling -->
<div class="collapse navbar-collapse" id="navbar-collapse-5">
<#if remind.truckSearch || remind.orderSearch || remind.userSearch>
    <form class="navbar-form navbar-left" action="#" role="search">
        <select name="navSearch" data-toggle="select" onchange='$("#navcontent").val("");'
                class="select select-inverse select-sm select2-offscreen"
                id="navSearch">
            <#if remind.truckSearch>
                <option value="1">车辆查询</option></#if>
            <#if remind.orderSearch>
                <option value="2">订单查询</option></#if>
            <#if remind.userSearch>
                <option value="3">用户查询</option></#if>
        </select>
        <div class="form-group">
            <div class="input-group">
                <div class="input-group-btn">
                </div>
                <input class="form-control width-300" id="navcontent" type="text" placeholder="请输入查讯内容">
                <span class="input-group-btn">
          <button type="button" class="btn"><span class="icon-search" id="search"></span></button>
          </span></div>
        </div>
    </form>
</#if>
    <ul class="nav navbar-nav navbar-right">
    <#--<#if remind.allRemind>-->
        <#--<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><b-->
                <#--class="icon-envelope-alt margin-right-5 fontsize-14"></b>待处理任务提醒<span class="navbar-unread"-->
                                                                                      <#--id="allCount">  ${remind.allCount}</span></a>-->
            <#--<ul class="dropdown-menu">-->
                <#--<#if remind.userTruckRemind>-->
                    <#--<li><a onclick="addTab('车辆认证审核',getRootPath() + '/view/truckVerify/truckVerifyList.html',$(this))"-->
                           <#--href="#"><span class="pull-left">车辆认证审核</span><span class="badge pull-right"-->
                                                                               <#--id="userTruckCount">${remind.userTruckCount}</span></a>-->
                    <#--</li></#if>-->
                <#--<#if remind.userRegisterRemind>-->
                    <#--<li><a onclick="addTab('会员认证审核',getRootPath() + '/view/memberAudit/list.html?',$(this))"-->
                           <#--href="#"><span class="pull-left">会员认证审核</span><span class="badge pull-right"-->
                                                                               <#--id="userRegisterCount">${remind.userRegisterCount}</span></a>-->
                    <#--</li></#if>-->
                <#--<#if remind.frightFirstRemind>-->
                    <#--<li>-->
                        <#--<a onclick="addTab('运费补贴一审',getRootPath() + '/view/bonusAudit/listTransportFirst.html?flag = 1',$(this))"-->
                           <#--href="#"><span class="pull-left">运费补贴一审</span><span class="badge pull-right"-->
                                                                               <#--id="frightFirstCount">${remind.frightFirstCount}</span></a>-->
                    <#--</li></#if>-->
                <#--<#if remind.frightSecondRemind>-->
                    <#--<li>-->
                        <#--<a onclick="addTab('运费补贴二审',getRootPath() + '/view/bonusAudit/listTransportSecond.html?flag = 1',$(this))"-->
                           <#--href="#"><span class="pull-left">运费补贴二审</span><span class="badge pull-right"-->
                                                                               <#--id="frightSecondCount">${remind.frightSecondCount}</span></a>-->
                    <#--</li></#if>-->
                <#--<#if remind.agencyFirstRemind>-->
                    <#--<li>-->
                        <#--<a onclick="addTab('中介费补贴一审',getRootPath() + '/view/bonusAudit/listAgentFeeFirst.html?flag = 1',$(this))"-->
                           <#--href="#"><span class="pull-left">中介费补贴一审</span><span class="badge pull-right"-->
                                                                                <#--id="agencyFirstCount">${remind.agencyFirstCount}</span></a>-->
                    <#--</li></#if>-->
                <#--<#if remind.agencySecondRemind>-->
                    <#--<li>-->
                        <#--<a onclick="addTab('中介费补贴二审',getRootPath() + '/view/bonusAudit/listAgentFeeSecond.html?flag = 1',$(this))"-->
                           <#--href="#"><span class="pull-left">中介费补贴二审</span><span class="badge pull-right"-->
                                                                                <#--id="agencySecondCount">${remind.agencySecondCount}</span></a>-->
                    <#--</li></#if>-->
                <#--<#if remind.recoFirstRemind>-->
                    <#--<li>-->
                        <#--<a onclick="addTab('推荐补贴一审',getRootPath() + '/view/bonusAudit/listRecommendFirst.html?flag = 1',$(this))"-->
                           <#--href="#"><span class="pull-left">推荐补贴一审</span><span class="badge pull-right"-->
                                                                               <#--id="recoFirstCount">${remind.recoFirstCount}</span></a>-->
                    <#--</li></#if>-->
                <#--<#if remind.recoSecondRemind>-->
                    <#--<li>-->
                        <#--<a onclick="addTab('推荐补贴二审',getRootPath() + '/view/bonusAudit/listRecommendSecond.html?flag = 1',$(this))"-->
                           <#--href="#"><span class="pull-left">推荐补贴二审</span><span class="badge pull-right"-->
                                                                               <#--id="recoSecondCount">${remind.recoSecondCount}</span></a>-->
                    <#--</li></#if>-->
            <#--</ul>-->
        <#--</li>-->
    <#--</#if>-->
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><b
                class="icon-user margin-right-5"></b>${Session['session_user'].nickName}<b
                class="icon-caret-down margin-left-10"></b></a>
            <ul class="dropdown-menu">
                <li><a data-toggle="modal" data-target="#myModal" href="${request.contextPath}/view/main/updatePwd.html"
                       data-backdrop="false"><i class="icon-lock"></i>　修改密码</a></li>
                <li><a href="#" onclick="logout()"><i class="icon-off"></i>　退出登录</a></li>
            </ul>
        </li>
    </ul>
</div>
<!-- /.navbar-collapse -->

<script>


    if ($("#sessionSysUserFlag").length > 0 && $("#sessionSysUserFlag").val() == 1) {
        window.setInterval(function () {
            toGetRemindCount();
        }, 30000);

    }
    function toGetRemindCount() {
        var url = getRootPath() + '/login/remindCount.do';
        $.ajax({
            type: 'post',
            url: url,
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            dataType: "json",
            cache: false,
            data: {},
            success: function (remind) {
                if (remind.userRegisterRemind) {
                    $("#userRegisterCount").text(remind.userRegisterCount);
                }
                if (remind.userTruckRemind) {
                    $("#userTruckCount").text(remind.userTruckCount);
                }
                if (remind.frightFirstRemind) {
                    $("#frightFirstCount").text(remind.frightFirstCount);
                }
                if (remind.frightSecondRemind) {
                    $("#frightSecondCount").text(remind.frightSecondCount);
                }
                if (remind.agencyFirstRemind) {
                    $("#agencyFirstCount").text(remind.agencyFirstCount);
                }
                if (remind.agencySecondRemind) {
                    $("#agencySecondCount").text(remind.agencySecondCount);
                }
                if (remind.recoFirstRemind) {
                    $("#recoFirstCount").text(remind.recoFirstCount);
                }
                if (remind.recoSecondRemind) {
                    $("#recoSecondCount").text(remind.recoSecondCount);
                }
            }
        })
    }

    function logout() {
        var url = getRootPath() + '/login/logout.do';
        $.ajaxDefaultCall(url, [], function () {
            window.location.href = getRootPath() + "/view/main/login.html";
        });
    }
    $("#search").on("click", function () {
        if ($("#navcontent").val() == "") {
            return;
        }
        var type = $("#navSearch").val();
        var value = $("#navcontent").val();
        var url = getRootPath() + '/memberAction/memberDetail.do?mobile=' + value + '&title=' + '';
        var title = "用户查询";
        if (type == 1) {
            url = getRootPath() + '/truck/edit.do?plateNo=' + value + '&title=' + '';
            title = "车辆查询";
        } else if (type == 2) {
            title = "订单查询";
            url = getRootPath() + '/order/detail.do?serialNo=' + value + '&title=' + '';
        }
        addTab(title, url, $(this), "Y");
    })

</script>