<%@ page language="java" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<LINK href="${pageContext.request.contextPath}/css/admin.css"
	type="text/css" rel="stylesheet">
<title>�ó��з�������</title>
</head>
<body>

	<div style="overflow: auto; width: 100%; text-align: center">
		<table class="tab_css_1">
			<form action="booupdate.action" method="post">
				<s:iterator value="#request.update" id="s" status="st">
					<tr>
						<td>�û���</td>
						<td><input type="text" name="book.username"
							value="${s.username}" readonly="readonly" /></td>
					</tr>
						<tr>
						<td>�û��ֻ�</td>
						<td><input type="text" name="book.phone"
							value="${s.phone}" readonly="readonly" /></td>
					</tr>
						<tr>
						<td>�û���ַ</td>
						<td><input type="text" name="book.address"
							value="${s.address}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>�̼���</td>
						<td><input type="text" name="book.busunessname"
							value="${s.busunessname}" readonly="readonly" /></td>
					</tr>

					<tr>
						<td>��Ʒ��</td>
						<td><input type="text" name="book.dishName"
							value="${s.dishName}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>Ԥ��ʱ��</td>
						<td><input type="text" name="book.bookTime"
							value="${s.bookTime}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>����</td>
						<td><input type="text" name="book.price" value="${s.price}"
							readonly="readonly" /></td>
					</tr>
						<tr>
						<td>����</td>
						<td><input type="text" name="book.number" value="${s.number}"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td>�����Ѷ�</td>
						<td><input type="text" name="book.totalconsumption"
							value="${s.totalconsumption}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>�����Ƿ����</td>
						<td><select name="finish">
								<option selected="selected" value="1">δ���</option>
								<option value="2">�����ȷ��</option>
								<option value="3">���������</option>
						</select></td>
					</tr>

					<tr>
						<td><input type="submit" value="�ύ"
							onclick="return validate();" /></td>
						<td><input type="button" value="����"
							onclick="javascript:history.back();"></td>
						<td><input type="hidden" name="book.bookId"
							value="${s.bookId}" /></td>
					</tr>
				</s:iterator>
			</form>
		</table>
</body>
</html>