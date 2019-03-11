<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://localhost:8080/struts2/css/login.css" type="text/css">
<title>欢迎登陆</title>
</head>
<body>
	<div >
		<form action="login" class="form">
			<div class="context">
				<span class="title">项目管理系统</span><br>
				<input type="text" name="username" class="username" placeholder="用户名" required="required" />	<br>	
				<input type="password" name="password" class="password" placeholder="密码" required="required"/><br>
				<% 
					if(request.getAttribute("msg")!=null){
				%>
				<div class="msg">
					<span class="message"> <%= request.getAttribute("msg") %> </span><br>
				</div>
				<% } %>
				<input type="submit" value="登录" class="submit"/>	
			</div>
		</form>
	</div>
</body>
</html>