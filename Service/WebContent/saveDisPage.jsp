<%@ page language="java" contentType="text/html; charset=GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ºÃ³ÔÀÐ·þÎñÆ÷¶Ë</title>
</head>
<body>
	<div style="overflow: auto; width: 100%; text-align: center">
		<table class="tab_css_1">
			<s:actionerror cssStyle="color:red" />
			<form action="dishSave.action" method="post">
				<s:iterator value="#request.update" id="s" status="st">
					<tr>
						<td>ÇëÑ¡Ôñ²ËÆ·ÀàÐÍ</td>
						<td><select name="searchType">
								<option selected="selected" value="1">·¹Ê³Àà</option>
								<option value="2">ÃæµãÀà</option>
								<option value="3">ÃæÌõÀà</option>
								<option value="4">ÒûÁÏÐ¡³ÔÀà</option>
						</select></td>
					</tr>
					<tr>
						<td>ÄúµÄ²ËÃû:</td>
						<td><input type="text" name="dish.dishName" />
							</div></td>
					</tr>
					<tr>
						<td>ÄúµÄ²ËÆ·¼Û¸ñ:</td>
						<td><input type="text" name="dish.price" />
					</tr>
					<tr>
						<td>ÄúµÄ²ËÆ·ÐÅÏ¢:</td>
						<td><input type="text" name="dish.dishMessage" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Ìá½»" /></td>
						<td><input type="button" value="·µ»Ø"
							onclick="javascript:history.back();"></td>
						<td></td>
					</tr>
				</s:iterator>
			</form>
		</table>
		</div>
</body>
</html>