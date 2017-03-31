<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>好吃佬服务器端</title>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}

.STYLE3 {
	font-size: 15px;
	color: #adc9d9;
}
</style>
<script language="javascript">
	function check1() {
		if (admin.adminname.value == "") {
			alert("管理员用户名不能为空！");
			admin.adminname.focus();
		} else if (admin.adminpassword.value == "") {
			alert("管理员密码不能为空！");
			admin.adminpassword.focus();
		} else {
			admin.action = "adminlogin";
			admin.target = "_top"
		}
	}
	function reload() {
		document.getElementById("code").setAttribute("src",
				"code.jsp?a=" + new Date().getTime());
	}
</script>
</head>

<body>
	<table width="100%" height="100%" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<td bgcolor="#1075b1">&nbsp;</td>
		</tr>
		<tr>
			<td height="608" background="images/login_03.gif">
				<table width="847" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="318" background="images/login_04a.gif"><s:actionerror
								cssStyle="color:red" /></td>
					</tr>
					<tr>
						<td height="84">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">

								<s:form action="adminlogin" name="admin" theme="simple">
									<tr>
										<td width="240" height="24" valign="bottom" align="right"><div
												align="right">
												<span class="STYLE3">用户名:</span>
											</div></td>
										<td height="24" width="240" valign="bottom">
											<div align="left">
												<s:textfield name="AdminName"
													cssStyle="width:160px; height:17px; background-color:#87adbf; border:solid 1px #153966; font-size:12px; color:#283439;" />
										</td>
										<td rowspan="3">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="270" height="25"><div align="left">
															<s:submit value="提交" onclick="check1()" /></td>
												</tr>
												<tr>
													<td width="270" height="25"><div align="left">
															<s:reset value="重置" /></td>
												</tr>

											</table>
										</td>
									</tr>

									<tr>
										<td height="24" width="240" valign="bottom" align="right"><div
												align="right">
												<span class="STYLE3">密码:</span>
											</div></td>
										<td><s:password name="AdminPassword"
												cssStyle="width:160px; height:17px; background-color:#87adbf; border:solid 1px #153966;
font-size:12px; color:#283439;" />
										</td>
									</tr>
									<tr>
										<td height="24" width="240" valign="bottom" align="right"><div
												align="right">
												<span class="STYLE3">验证码</span>
											</div></td>

										<td><input
											style="width: 100px; height: 17px; background-color: #87adbf; border: solid 1px #153966; font-size: 12px; color: #283439;"
											type=text maxLength=10 size=10 name="validateCode"><img
											id="code" style="CURSOR: pointer" onclick="reload1();"
											src="code.jsp" alt="点击刷新验证码"> <a href="#"
											onclick="reload();">看不清</a></td>
									</tr>
								</s:form>
							</table>
						</td>
					</tr>

					<tr>
						<td height="206" background="images/login_11.gif">&nbsp;</td>
					</tr>
				</table>
			</td>
		<tr>
			<td bgcolor="#152753">&nbsp;</td>
		</tr>
	</table>
</body>
</html>