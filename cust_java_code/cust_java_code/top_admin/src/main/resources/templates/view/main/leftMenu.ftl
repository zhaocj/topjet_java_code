<#assign base=request.contextPath />
<ul class="parentId">
  <#-- <li><a href="javascript:void(0)" class="dropmenu" onclick="addTab('平台首页','iframe/index.html',$(this))"><i class="glyphicon glyphicon-home"></i>平台首页</a></li>-->
   <#list menuList as parentsItem>
   		<li><a href="javascript:void(0)" class="dropmenu"><i class="${parentsItem.cssStyle}" ></i>${parentsItem.text}<i class="glyphicon glyphicon-chevron-down icodown"></i></a>
   			<ul class="childrenMenu">
   				<#list parentsItem.children as childItem>
   					<li><a href="javascript:void(0)"  onclick="addTab('${childItem.text}','${base}${childItem.url}',$(this))"><i class="${childItem.cssStyle}"></i>${childItem.text}</a></li>
   				</#list>
   			</ul>
 		</li>
   </#list>
   <li class="margin-bottom-50"></li>
</ul>
