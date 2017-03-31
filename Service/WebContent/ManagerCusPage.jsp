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
				background=${pageContext.request.contextPath}/images/title_bg1.jpg>当前位置:管理用户信息
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
				<th>电子邮箱</th>
				<th>用户电话</th>
				<th>用户地址</th>
				<th>删除</th>
			</thead>
			<s:iterator value="#request.userList" id="s" status="st">
				<form action="CusdelChecked.action">
					<tr class="tr_css" align="center">
						<td><s:property value="#s.username" /></td>
						<td><s:property value="#s.userEmail" /></td>
						<td><s:property value="#s.userPhone" /></td>
						<td><s:property value="#s.userAddress" /></td>
						<td><input type="checkbox" name="ids" value="${s.userid}">
							<s:a href="cusDelete.action?username=%{#s.username}"
								onclick="return confirm('确定删除该用户吗？');">删除</s:a></td>
					</tr>
			</s:iterator>
			<tr>
				<td colspan="6" align="right"><input type="submit"
					value="删除所选记录" onclick="return CusdelChecked();" /></td>
			</tr>
			</form>

		</table>
		<page:page pager="${pb}" />
	</div>
</body>
</html>