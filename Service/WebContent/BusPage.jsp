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
			alert("����ѡ��һ����ɾ����¼");
			return false;
		}
		if (!confirm("ȷ��ɾ��?")) {
			return false;
		}
	}
</script>
</head>
<body>
	<table cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
		<tr height=28>
			<td
				background=${pageContext.request.contextPath}/images/title_bg1.jpg>��ǰλ��:�����̼���Ϣ
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
				<th>�̼��û���</th>
				<th>�̼��û�����</th>
				<th>�̼���</th>
				<th>�̼ҵ绰</th>
				<th>�̼ҵ�ַ</th>
				<th>�鿴�����Ʒ</th>
				<th>��Ӳ�Ʒ</th>
				<th>�޸�</th>
			</thead>
			<s:iterator value="#request.userList" id="s" status="st">
				<form action="BusdelChecked.action">
					<tr class="tr_css" align="center">
						<td><s:property value="#s.bususername" /></td>
						<td><s:property value="#s.buspassword" /></td>
						<td><s:property value="#s.busunessname" /></td>
						<td><s:property value="#s.busunessPhone" /></td>
						<td><s:property value="#s.busunessAddress" /></td>
						<td><s:a href="listDish.action?busunessid=%{#s.busunessid}">�鿴�����Ʒ</s:a></td>
						<td><s:a href="saveDish.action?busunessid=%{#s.busunessid}">��Ӳ�Ʒ</s:a></td>
						<td><s:a
								href="updateBus.action?bususername=%{#s.bususername}">�޸�</s:a></td>
					</tr>
			</s:iterator>
			</form>
		</table>
	</div>
</body>
</html>