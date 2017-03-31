<%@ page language="java" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="page" uri="/WEB-INF/mytld/pagetag.tld"%>
<html>
<head>
<LINK href="${pageContext.request.contextPath}/css/admin.css"
	type="text/css" rel="stylesheet">
</head>
<body>
	<table cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
		<tr height=28>
			<td
				background=${pageContext.request.contextPath}/images/title_bg1.jpg>当前位置:管理订单信息
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
				<th>用户名</th>
				<th>用户手机</th>
				<th>用户地址</th>
				<th>商家名</th>
				<th>菜品名</th>
				<th>预定时间</th>
				<th>单价</th>
				<th>份数</th>
				<th>总消费额</th>
				<th>订单是否完成</th>
			</thead>
			<s:iterator value="#request.userList" id="s" status="st">
				<tr class="tr_css" align="center">
					<td><s:property value="#s.username" /></td>
					<td><s:property value="#s.phone" /></td>
					<td><s:property value="#s.address" /></td>
					<td><s:property value="#s.busunessname" /></td>
					<td><s:property value="#s.dishName" /></td>
					<td><s:property value="#s.bookTime" /></td>
					<td><s:property value="#s.number" /></td>
					<td><s:property value="#s.price" /></td>
					<td><s:property value="#s.totalconsumption" /></td>
					<td><s:property value="#s.bookFinish" /></td>
				</tr>
			</s:iterator>
			</form>
		</table>
		<page:page pager="${pb}" />
	</div>

</body>
</html>