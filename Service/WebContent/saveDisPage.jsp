<%@ page language="java" contentType="text/html; charset=GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>�ó��з�������</title>
</head>
<body>
	<div style="overflow: auto; width: 100%; text-align: center">
		<table class="tab_css_1">
			<s:actionerror cssStyle="color:red" />
			<form action="dishSave.action" method="post">
				<s:iterator value="#request.update" id="s" status="st">
					<tr>
						<td>��ѡ���Ʒ����</td>
						<td><select name="searchType">
								<option selected="selected" value="1">��ʳ��</option>
								<option value="2">�����</option>
								<option value="3">������</option>
								<option value="4">����С����</option>
						</select></td>
					</tr>
					<tr>
						<td>���Ĳ���:</td>
						<td><input type="text" name="dish.dishName" />
							</div></td>
					</tr>
					<tr>
						<td>���Ĳ�Ʒ�۸�:</td>
						<td><input type="text" name="dish.price" />
					</tr>
					<tr>
						<td>���Ĳ�Ʒ��Ϣ:</td>
						<td><input type="text" name="dish.dishMessage" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="�ύ" /></td>
						<td><input type="button" value="����"
							onclick="javascript:history.back();"></td>
						<td></td>
					</tr>
				</s:iterator>
			</form>
		</table>
		</div>
</body>
</html>