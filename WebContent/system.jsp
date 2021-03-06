<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>项目管理系统</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700">
<!-- https://fonts.google.com/specimen/Roboto -->
<link rel="stylesheet"
	href="/struts2/css/fontawesome.min.css">
<!-- https://fontawesome.com/ -->
<link rel="stylesheet"
	href="/struts2/css/bootstrap.min.css">
<!-- https://getbootstrap.com/ -->
<link rel="stylesheet"
	href="/struts2/css/templatemo-style.css">

</head>

<body id="reportsPage">
	<div class="" id="home">
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
							<i class="fas fa-tachometer-alt"></i> 项目管理 <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="manage-person">
							<i class="fas fa-tachometer-alt"></i> 人员管理 <span
							class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="manage-task">
							<i class="fas fa-shopping-cart"></i> 任务管理
					</a></li>

					<li class="nav-item"><a class="nav-link active" href="#">
							<i class="far fa-user"></i> 系统维护
					</a></li>
					<li class="nav-item"><a class="nav-link" href="query-info">
							<i class="far fa-user"></i> 信息查询
					</a></li>
				</ul>
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link d-block"
						href="login.jsp"> ${username}, <b>登出</b>
					</a></li>
				</ul>
			</div>
		</div>

		</nav>
		<div class="container">
			<div class="row">
				<div class="col">
					<p class="text-white mt-5 mb-5">
						信息维护
					</p>
				</div>
			</div>

			<div class="col-12 tm-block-col">
				<div
					class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
					<h2 class="tm-block-title">人员信息</h2>
					<div style="margin-bottom: 20px">
							<input class="search_pName" id="uName" type="text" placeholder="请输入用户名" name="uName"  />
							<input type="image" src="/struts2/img/search.png" 
							style="width:20px;margin-left:10px;height:20px;" onclick="Query()"/>
							<input type="image" src="/struts2/image/icon_add.png" 
							style="width:20px;margin-left:10px;height:20px;" onclick="Add()"/>							
					</div>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">序号</th>
								<th scope="col">用户名</th>
								<th scope="col">密码</th>
								<!-- <th scope="col">手机号码</th> -->
								<th scope="col">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="u" varStatus="status">
								<tr>
									<th scope="row"><b>${status.index+1}</b></th>
									<td>${u.username}</td>
									<td><input type=password readonly="readonly"  style= "outline: none;color: #fff;background-color:transparent;border:none; " id="password${status.index+1 }" value="******"/></td>
									<%-- <td>${u.tel}</td> --%>
									<td>
									<button type="button" id="system_edit${status.index+1}" class="table_button" onclick="edit(${u.uId},${status.index+1})">编辑</button>
									<button type="button" id="system_save${status.index+1}" style="display:none;" class="table_button" onclick="save(${u.uId},${status.index+1})">保存</button>
									<button type="button" id="system_cancel${status.index+1}" style="display:none;" class="table_button" onclick="cancel(${status.index+1})">取消</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<script src="js/jquery-3.3.1.min.js"></script>
			<script src="js/index.js"></script>
			<!-- https://jquery.com/download/ -->
			<script src="js/moment.min.js"></script>
			<!-- https://momentjs.com/ -->
			<script src="js/Chart.min.js"></script>
			<!-- http://www.chartjs.org/docs/latest/ -->
			<script src="js/bootstrap.min.js"></script>
			<!-- https://getbootstrap.com/ -->
			<script src="js/tooplate-scripts.js"></script>
			<script>
				Chart.defaults.global.defaultFontColor = 'white';
				let ctxLine, ctxBar, ctxPie, optionsLine, optionsBar, optionsPie, configLine, configBar, configPie, lineChart;
				barChart, pieChart;
				// DOM is ready
				$(function() {
					drawLineChart(); // Line Chart
					drawBarChart(); // Bar Chart
					drawPieChart(); // Pie Chart

					$(window).resize(function() {
						updateLineChart();
						updateBarChart();
					});
				})
				function edit(val,id){
					var system_edit = "system_edit"+id;
					var system_save = "system_save"+id;
					var system_cancel = "system_cancel"+id;
					var password = "password"+id;
					document.getElementById(system_edit).style.display="none";
					document.getElementById(system_save).style.display="";
					document.getElementById(system_cancel).style.display="";
					var td = document.getElementById(password);
					td.removeAttribute("readonly");
					td.style.border="";
					td.value="";
				}
				function cancel(id){
					var system_edit = "system_edit"+id;
					var system_save = "system_save"+id;
					var system_cancel = "system_cancel"+id;
					var password = "password"+id;
					document.getElementById(system_edit).style.display="";
					document.getElementById(system_save).style.display="none";
					document.getElementById(system_cancel).style.display="none";
					var td = document.getElementById(password);
					td.setAttribute("readonly","readonly");
					td.style.border="none";
					td.value="******";
				}
				function save(val,id){
					var system_edit = "system_edit"+id;
					var system_save = "system_save"+id;
					var system_cancel = "system_cancel"+id;
					var password = "password"+id;
					document.getElementById(system_edit).style.display="";
					document.getElementById(system_save).style.display="none";
					document.getElementById(system_cancel).style.display="none";
					var td = document.getElementById(password);
					td.setAttribute("readonly","readonly");
					td.style.border="none";
					var password = td.value;
					td.value="******";
					$.ajax({
			        	  type: 'POST',
			        	  url: 'editUserPassword.action',
			        	  data: {uid:val,password:password},
			        	  success: function(data){
		                      
		                   }
		      		});
				}
				function Query(){
					var uName = document.getElementById("uName").value;
					window.location.href="userSearch.action?uName="+uName;
				}
				function Add(){
					var uName = document.getElementById("uName").value;
					window.location.href="addUser.action?uName="+uName;
				}
			</script>
</body>

</html>