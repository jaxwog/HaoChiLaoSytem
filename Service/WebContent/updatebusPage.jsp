<%@ page language="java" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<LINK href="${pageContext.request.contextPath}/css/admin.css"
	type="text/css" rel="stylesheet">
<title>�ó��з�������</title>
<script type="text/javascript">
function validate() {
	//ʹ��������ʽȥ��ǰ��ո�
	String.prototype.Trim = function() {
		return this.replace(/^\s+|\s+$/g, "");
	}
	var username = document.getElementById("username").value.Trim();
	var pwd = document.getElementById("pwd").value.Trim();
	var name = document.getElementById("name").value.Trim();
	var num = document.getElementById("num").value.Trim();
	var address = document.getElementById("address").value.Trim();
	if (username.length == 0) {
		document.getElementById("error").innerHTML = "��������Ϊ��";
		return false;
	}
	if (pwd.length == 0) {
		document.getElementById("error").innerHTML = "��ҵ绰����Ϊ��";
		return false;
	}
	if (name.length == 0) {
		document.getElementById("error").innerHTML = "��������Ϊ��";
		return false;
	}
	if (num.length == 0) {
		document.getElementById("error").innerHTML = "��ҵ绰����Ϊ��";
		return false;
	}
	if (address.length == 0) {
		document.getElementById("error").innerHTML = "��ҵ�ַ����Ϊ��";
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
						<td>�����������û���:</td>
						<td><input type="text" name="bus.bususername"
							value="${s.bususername}"  id="username" /></td>
						<td><div id="error" style="color: red"></td>
					</tr>
						<tr>
						<td>��������������:</td>
						<td><input type="text" name="bus.buspassword"
							value="${s.buspassword}"  id="pwd" /></td>
						<td><div id="error" style="color: red"></td>
					</tr>
					<tr>
						<td>�����������̼���:</td>
						<td><input type="text" name="bus.busunessname"
							value="${s.busunessname}"  id="name" /></td>
						<td><div id="error" style="color: red"></td>
					</tr>
				<tr>
					<td>�������ҵ绰:</td>
					<td><input type="text"  name="bus.busunessPhone" id="num"  value="${s.busunessPhone}" /></td>
					<td><div id="error" style="color: red"></td>
				</tr>
				<tr>
					<td>�������ҵ�ַ:</td>
					<td><input type="text"  name="bus.busunessAddress" id="address" value="${s.busunessAddress}"  /></td>
					<td><div id="error" style="color: red"></td>
				</tr>
					<tr>
						<td><input type="submit" value="�ύ"
							onclick="return validate();" /></td>
						<td><input type="button" value="����"
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