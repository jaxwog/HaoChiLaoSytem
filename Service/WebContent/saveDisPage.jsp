<%@ page language="java" contentType="text/html; charset=GB2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>好吃佬服务器端</title>
</head>
<body>
	<div style="overflow: auto; width: 100%; text-align: center">
		<table class="tab_css_1">
			<s:actionerror cssStyle="color:red" />
			<form action="dishSave.action" method="post">
				<s:iterator value="#request.update" id="s" status="st">
					<tr>
						<td>请选择菜品类型</td>
						<td><select name="searchType">
								<option selected="selected" value="1">饭食类</option>
								<option value="2">面点类</option>
								<option value="3">面条类</option>
								<option value="4">饮料小吃类</option>
						</select></td>
					</tr>
					<tr>
						<td>您的菜名:</td>
						<td><input type="text" name="dish.dishName" />
							</div></td>
					</tr>
					<tr>
						<td>您的菜品价格:</td>
						<td><input type="text" name="dish.price" />
					</tr>
					<tr>
						<td>您的菜品信息:</td>
						<td><input type="text" name="dish.dishMessage" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="提交" /></td>
						<td><input type="button" value="返回"
							onclick="javascript:history.back();"></td>
						<td></td>
					</tr>
				</s:iterator>
			</form>
		</table>
		</div>
</body>
</html>