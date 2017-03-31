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
<table cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
	<tr height=28>
		<td background=${pageContext.request.contextPath}/images/title_bg1.jpg>当前位置:注册用户
		</td>
	</tr>
	<tr>
		<td bgColor=#b1ceef height=1></TD>
	</tr>
	<tr height=20>
		<td background=${pageContext.request.contextPath}/images/shadow_bg.jpg></TD>
	</tr>
</table>
<body>
	<div style="overflow: auto; width: 100%; text-align: center">

		<table border="1" class="tab_css_1">
			<s:actionerror cssStyle="color:red" />
			<s:form action="busregister" theme="simple" method="post"
				enctype="multipart/form-data">
				<tr>
					<td>请输入店家用户名:</td>
					<td><s:textfield name="bus.bususername" id="username" /></td>
					<td><div id="error" style="color: red"></td>
				</tr>
				<tr>
					<td>请输入店家密码:</td>
					<td><s:password name="bus.buspassword" id="pwd" /></td>
					<td><div id="error" style="color: red"></td>
				</tr>
				<tr>
					<td>请输入店家店名:</td>
					<td><s:textfield name="bus.busunessname" id="name" /></td>
					<td><div id="error" style="color: red"></td>
				</tr>
				<tr>
					<td>请输入店家电话:</td>
					<td><s:textfield name="bus.busunessPhone" id="num" /></td>
					<td><div id="error" style="color: red"></td>
				</tr>
				<tr>
					<td>请输入店家地址:</td>
					<td><s:textfield name="bus.busunessAddress" id="address" /></td>
					<td><div id="error" style="color: red"></td>
				</tr>

				<tr>
					<td><s:submit value="提交" onclick="return validate();" /></td>
					<td><s:reset value="重置"></s:reset></td>
					<td><input type="button" value="返回"
						onclick="javascript:history.back();"></td>
				</tr>
			</s:form>
			</form>
		</table>
</body>
</html>