<%@ page language="java" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript" type="text/javascript">
	function logout() {
		if (confirm("您确定要退出吗？"))
			top.location = "adminlogin.jsp";
		return false;
	}
	function startTime() {
		var today = new Date()
		var years = today.getFullYear();
		var months = today.getMonth();
		var d = today.getDate()
		var h = today.getHours()
		var m = today.getMinutes()
		var s = today.getSeconds()
		// add a zero in front of numbers<10  
		months = months + 1
		months = checkTime(months)
		d = checkTime(d)
		m = checkTime(m)
		s = checkTime(s)
		var weekday = new Array(7)
		weekday[0] = "星期日"
		weekday[1] = "星期一"
		weekday[2] = "星期二"
		weekday[3] = "星期三"
		weekday[4] = "星期四"
		weekday[5] = "星期五"
		weekday[6] = "星期六"
		var w = weekday[today.getDay()]
		document.getElementById('ShowTime').innerHTML = years + "年" + months
				+ "月" + d + "日 " + w + " " + h + ":" + m + ":" + s;
		t = setTimeout('startTime()', 500)
	}
	function checkTime(i) {
		if (i < 10) {
			i = "0" + i
		}
		return i
	}
</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px;
	color: #000000;
}

.STYLE5 {
	font-size: 12
}

.STYLE7 {
	font-size: 12px;
	color: #FFFFFF;
}
-->
</style>
</head>

<body onLoad="startTime()">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="57" background="images/main_03.gif"><table
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="378" height="57" background="images/main_01.gif">&nbsp;</td>
						<td>&nbsp;</td>
						<td width="281" valign="bottom"><table width="100%"
								border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="33" height="27"><img src="images/main_05.gif"
										width="33" height="27" /></td>
									<td width="248" background="images/main_06.gif"><table
											width="225" border="0" align="center" cellpadding="0"
											cellspacing="0">
											<tr>
												<td height="17"><div align="right"></div></td>
												<td><div align="right"></div></td>
												<td><div align="right"></div></td>
											</tr>
										</table></td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="40" background="images/main_10.gif"><table
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="194" height="40" background="images/main_07.gif">&nbsp;</td>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td width="21">&nbsp;</td>
									<td width="35" class="STYLE7"><div align="center">&nbsp;</div>
									</td>
									<td width="21" class="STYLE7"><img
										src="images/main_15.gif" width="19" height="14" /></td>
									<td width="35" class="STYLE7"><div align="center">
											<a href="javascript:history.go(1);">前进</a>
										</div></td>
									<td width="21" class="STYLE7"><img
										src="images/main_17.gif" width="19" height="14" /></td>
									<td width="35" class="STYLE7"><div align="center">
											<a href="javascript:history.go(-1);">后退</a>
										</div></td>
									<td width="21" class="STYLE7">&nbsp;</td>
									<td width="35" class="STYLE7"><div align="center">&nbsp;</div>
									</td>
									<td width="21" class="STYLE7"></td>
									<td width="35" class="STYLE7">&nbsp;
										</div>
									</td>
									<td>&nbsp;</td>
								</tr>
							</table></td>
						<td width="248" background="images/main_11.gif"><table
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="16%"><span class="STYLE5"></span></td>
									<td width="75%"><div align="center">
											<span class="STYLE7">
												<div id="ShowTime"></div>
											</span>
										</div></td>
									<td width="9%">&nbsp;</td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
		<tr>
			<td height="30" background="images/main_31.gif"><table
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="8" height="30"><img src="images/main_28.gif"
							width="8" height="30" /></td>
						<td width="147" background="images/main_29.gif"><table
								width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="24%">&nbsp;</td>
									<td width="43%" height="20" valign="bottom" class="STYLE1">管理菜单</td>
									<td width="33%">&nbsp;</td>
								</tr>
							</table></td>
						<td width="39"><img src="images/main_30.gif" width="39"
							height="30" /></td>
						<td><table width="100%" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td height="20" valign="bottom"><span class="STYLE1">当前用户：<font
											color="red">管理员 </font> &nbsp;&nbsp; <A style="COLOR: #fff"
											href="target=adminmain"></span></td>
									<td valign="bottom" class="STYLE1"><div align="right">
											<img src="images/sj.gif" width="6" height="7" /> <A
												id=HyperLink3 onclick="logout()" style="COLOR: black"
												href="javascript:window.opener=null;%20window.close();"
												target="main">退出系统</A>
										</div></td>
								</tr>
							</table></td>
						<td width="17"><img src="images/main_32.gif" width="17"
							height="30" /></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>
