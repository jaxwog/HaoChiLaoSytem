<%@ page language="java" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="page" uri="/WEB-INF/mytld/pagetag.tld"%>
<html>
<head>
<LINK href="${pageContext.request.contextPath}/css/admin.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript">
	function deleteChecked() {
		flag = false;
		var de = document.getElementsByName("ids");
		for (i = 0; i < de.length; i++) {
			if (de[i].checked == true) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			alert("至少选择一个待删除记录");
			return false;
		}
		if (!confirm("确定删除?")) {
			return false;
		}
	}
</script>
</head>
<body>
	<table cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
		<tr height=28>
			<td
				background=${pageContext.request.contextPath}/images/title_bg1.jpg>当前位置:管理商家信息
			</td>
		</tr>
		<tr>
			<td bgColor=#b1ceef height=1></TD>
		</tr>
		<tr height=20>
			<td
				background=${pageContext.request.contextPath}/images/shadow_bg.jpg></TD>
		</tr>
	</table>
	<div style="overflow: auto; width: 100%; text-align: center">
		<table border="1" class="tab_css_1">
			<thead>
				<th>商家用户名</th>
				<th>商家用户密码</th>
				<th>商家名</th>
				<th>商家电话</th>
				<th>商家地址</th>
				<th>查看本店菜品</th>
				<th>添加菜品</th>
				<th>修改</th>
			</thead>
			<s:iterator value="#request.userList" id="s" status="st">
				<form action="BusdelChecked.action">
					<tr class="tr_css" align="center">
						<td><s:property value="#s.bususername" /></td>
						<td><s:property value="#s.buspassword" /></td>
						<td><s:property value="#s.busunessname" /></td>
						<td><s:property value="#s.busunessPhone" /></td>
						<td><s:property value="#s.busunessAddress" /></td>
						<td><s:a href="listDish.action?busunessid=%{#s.busunessid}">查看本店菜品</s:a></td>
						<td><s:a href="saveDish.action?busunessid=%{#s.busunessid}">添加菜品</s:a></td>
						<td><s:a
								href="updateBus.action?bususername=%{#s.bususername}">修改</s:a></td>
					</tr>
			</s:iterator>
			</form>
		</table>
	</div>
</body>
</html>