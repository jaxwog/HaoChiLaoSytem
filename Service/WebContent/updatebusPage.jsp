<%@ page language="java" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<LINK href="${pageContext.request.contextPath}/css/admin.css"
	type="text/css" rel="stylesheet">
<title>好吃佬服务器端</title>
<script type="text/javascript">
function validate() {
	//使用正则表达式去除前后空格
	String.prototype.Trim = function() {
		return this.replace(/^\s+|\s+$/g, "");
	}
	var username = document.getElementById("username").value.Trim();
	var pwd = document.getElementById("pwd").value.Trim();
	var name = document.getElementById("name").value.Trim();
	var num = document.getElementById("num").value.Trim();
	var address = document.getElementById("address").value.Trim();
	if (username.length == 0) {
		document.getElementById("error").innerHTML = "店名不能为空";
		return false;
	}
	if (pwd.length == 0) {
		document.getElementById("error").innerHTML = "店家电话不能为空";
		return false;
	}
	if (name.length == 0) {
		document.getElementById("error").innerHTML = "店名不能为空";
		return false;
	}
	if (num.length == 0) {
		document.getElementById("error").innerHTML = "店家电话不能为空";
		return false;
	}
	if (address.length == 0) {
		document.getElementById("error").innerHTML = "店家地址不能为空";
		return false;
	}
	return true;
}
</script>
</head>
<body>

	<div style="overflow: auto; width: 100%; text-align: center">
		<table class="tab_css_1">
			<form action="busupdate.action" method="post">
				<s:iterator value="#request.update" id="s" status="st">
					<tr>
						<td>请输入您的用户名:</td>
						<td><input type="text" name="bus.bususername"
							value="${s.bususername}"  id="username" /></td>
						<td><div id="error" style="color: red"></td>
					</tr>
						<tr>
						<td>请输入您的密码:</td>
						<td><input type="text" name="bus.buspassword"
							value="${s.buspassword}"  id="pwd" /></td>
						<td><div id="error" style="color: red"></td>
					</tr>
					<tr>
						<td>请输入您的商家名:</td>
						<td><input type="text" name="bus.busunessname"
							value="${s.busunessname}"  id="name" /></td>
						<td><div id="error" style="color: red"></td>
					</tr>
				<tr>
					<td>请输入店家电话:</td>
					<td><input type="text"  name="bus.busunessPhone" id="num"  value="${s.busunessPhone}" /></td>
					<td><div id="error" style="color: red"></td>
				</tr>
				<tr>
					<td>请输入店家地址:</td>
					<td><input type="text"  name="bus.busunessAddress" id="address" value="${s.busunessAddress}"  /></td>
					<td><div id="error" style="color: red"></td>
				</tr>
					<tr>
						<td><input type="submit" value="提交"
							onclick="return validate();" /></td>
						<td><input type="button" value="返回"
							onclick="javascript:history.back();">
						</td>
						<td>
						<input type="hidden" name="bus.busunessid" value="${s.busunessid}" />
						</td>
					</tr>
				</s:iterator>
			</form>
		</table>
</body>
</html>