<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>项目管理系统</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700" />
<!-- https://fonts.google.com/specimen/Open+Sans -->
<link rel="stylesheet"
	href="/struts2/css/fontawesome.min.css" />
<!-- https://fontawesome.com/ -->
<link rel="stylesheet"
	href="/struts2/css/bootstrap.min.css" />
<!-- https://getbootstrap.com/ -->
<link rel="stylesheet"
	href="/struts2/css/templatemo-style.css">

</head>

<body>
	<div>
		<nav class="navbar navbar-expand-xl">
		<div class="container h-100">
			<a class="navbar-brand" href="index">
				<h1 class="tm-site-title mb-0">项目管理系统</h1>
			</a>
			<button class="navbar-toggler ml-auto mr-0" type="button"
				data-toggle="collapse" data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<i class="fas fa-bars tm-nav-icon"></i>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mx-auto h-100">
					<li class="nav-item"><a class="nav-link" href="index">
							<i class="fas fa-tachometer-alt"></i> 项目管理 <span
							class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="manage-person">
							<i class="fas fa-tachometer-alt"></i> 人员管理 <span
							class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="manage-task">
							<i class="fas fa-shopping-cart"></i> 任务管理
					</a></li>

					<li class="nav-item"><a class="nav-link" href="system">
							<i class="far fa-user"></i> 系统维护
					</a></li>
					<li class="nav-item"><a class="nav-link" href="query-info">
							<i class="far fa-user"></i> 信息查询
					</a></li>
				</ul>
			</div>
		</div>
		</nav>
	</div>

	<div class="container tm-mt-big tm-mb-big">
		<div class="row">
			<div class="col-12 mx-auto tm-login-col">
				<div class="tm-bg-primary-dark tm-block tm-block-h-auto">
					<div class="row">
						<div class="col-12 text-center">
							<h2 class="tm-block-title mb-4">欢迎登录项目管理系统</h2>
						</div>
					</div>
					<div class="row mt-2">
						<div class="col-12">
							<form action="login" method="post" class="tm-login-form">
								<div class="form-group">
									<label for="username">用户名</label> <input name="username"
										type="text" class="form-control validate" id="username"
										value="" required />
								</div>
								<div class="form-group mt-3">
									<label for="password">密码</label> <input name="password"
										type="password" class="form-control validate" id="password"
										value="" required />
								</div>
								<div class="form-group mt-4">
									<%
										if (request.getAttribute("msg") != null) {
									%>
									<div  style="text-align: center;margin-bottom: 10px;">
										<span  style="color:red;"> <%=request.getAttribute("msg")%>
										</span><br>
									</div>
									<%
										}
									%>
									<%
										if (request.getSession().getAttribute("msg_system") != null) {
									%>
									<div  style="text-align: center;margin-bottom: 10px;">
										<span  style="color:red;"> <%=request.getSession().getAttribute("msg_system")%>
										</span><br>
									</div>
									<%
										}
									%>
									<button type="submit"
										class="btn btn-primary btn-block text-uppercase">
										登录</button>
								</div>
								<!--  <button class="mt-5 btn btn-primary btn-block text-uppercase">
                    Forgot your password?
                  </button> -->
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.3.1.min.js"></script>
	<!-- https://jquery.com/download/ -->
	<script src="js/bootstrap.min.js"></script>
	<!-- https://getbootstrap.com/ -->
</body>
</html>
