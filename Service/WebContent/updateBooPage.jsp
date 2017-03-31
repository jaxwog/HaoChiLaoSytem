<%@ page language="java" pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<LINK href="${pageContext.request.contextPath}/css/admin.css"
	type="text/css" rel="stylesheet">
<title>好吃佬服务器端</title>
</head>
<body>

	<div style="overflow: auto; width: 100%; text-align: center">
		<table class="tab_css_1">
			<form action="booupdate.action" method="post">
				<s:iterator value="#request.update" id="s" status="st">
					<tr>
						<td>用户名</td>
						<td><input type="text" name="book.username"
							value="${s.username}" readonly="readonly" /></td>
					</tr>
						<tr>
						<td>用户手机</td>
						<td><input type="text" name="book.phone"
							value="${s.phone}" readonly="readonly" /></td>
					</tr>
						<tr>
						<td>用户地址</td>
						<td><input type="text" name="book.address"
							value="${s.address}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>商家名</td>
						<td><input type="text" name="book.busunessname"
							value="${s.busunessname}" readonly="readonly" /></td>
					</tr>

					<tr>
						<td>菜品名</td>
						<td><input type="text" name="book.dishName"
							value="${s.dishName}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>预定时间</td>
						<td><input type="text" name="book.bookTime"
							value="${s.bookTime}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>单价</td>
						<td><input type="text" name="book.price" value="${s.price}"
							readonly="readonly" /></td>
					</tr>
						<tr>
						<td>份数</td>
						<td><input type="text" name="book.number" value="${s.number}"
							readonly="readonly" /></td>
					</tr>
					<tr>
						<td>总消费额</td>
						<td><input type="text" name="book.totalconsumption"
							value="${s.totalconsumption}" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>订单是否完成</td>
						<td><select name="finish">
								<option selected="selected" value="1">未完成</option>
								<option value="2">店家已确认</option>
								<option value="3">店家已派送</option>
						</select></td>
					</tr>

					<tr>
						<td><input type="submit" value="提交"
							onclick="return validate();" /></td>
						<td><input type="button" value="返回"
							onclick="javascript:history.back();"></td>
						<td><input type="hidden" name="book.bookId"
							value="${s.bookId}" /></td>
					</tr>
				</s:iterator>
			</form>
		</table>
</body>
</html>