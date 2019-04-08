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

					<li class="nav-item"><a class="nav-link" href="system">
							<i class="far fa-user"></i> 系统维护
					</a></li>
					<li class="nav-item  active"><a class="nav-link" href="#">
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
						欢迎, <b> ${username} ${permission}</b>
					</p>
				</div>
			</div>

			<div class="col-12 tm-block-col">
				<div
					class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
					<h2 class="tm-block-title">项目信息</h2>
					<div style="margin-bottom: 20px">
							<select id="select_class" class="select_class">
								<option value="project">项目</option>
								<option value="task">任务</option>
								<option value="person">人员</option>
							</select>
							<input class="search_pName" id="pName" type="text" placeholder="请输入名称" name="pName"  />
							<input type="image" src="/struts2/img/search.png" 
							style="width:20px;margin-left:10px;height:20px;" onclick="Query()"/>							
					</div>
					<%
						if(request.getAttribute("info_type") != "0")
						{
					%>
					<div >
						<table class="table">
						<thead>
							<tr>
							<th scope="col">序号</th>
							<c:forEach items="${titleList}" var="p" varStatus="status">
								<th scope="col">${p}</th>
							</c:forEach>
							</tr>
						</thead>
						<tbody>
							<%
							if(request.getAttribute("info_type") == "1")
							{
							%>
							<c:forEach items="${projectList}" var="p" varStatus="status">
							<tr>
									<th scope="row"><b>${status.index+1}</b></th>
									<td>${p.pName}</td>
									<td>${p.create }</td>
									<td>${p.createByName }</td>
									<td>${p.headName }</td>
									<td>${p.status }</td>
									<td>${p.modifyTime }</td>
								</tr>
							</c:forEach>
							<%
							}else if(request.getAttribute("info_type") == "2")
							{
							%>
							<c:forEach items="${taskList}" var="p" varStatus="status">
							<tr>
									<th scope="row"><b>${status.index+1}</b></th>
									<td>${p.taskName}</td>
									<td >${p.uName}</td>
									<td>${p.create }</td>
									<td>${p.createByName }</td>
									<%-- <td class="status">${p.status }</td> --%>
									<td>${p.modifyTime }</td>
								</tr>
							</c:forEach>
							<%
							}else if(request.getAttribute("info_type") == "3")
							{
							%>
							<c:forEach items="${userList}" var="p" varStatus="status">
							<tr>
									<th scope="row"><b>${status.index+1}</b></th>
									<td>${p.username}</td>
									<%-- <td>${p.tel }</td> --%>
								</tr>
							</c:forEach>
							<%
							}
							%>
								
						</tbody>
					</table>
					</div>
					<%
						}
					%>
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
				function Query(){
					var select = document.getElementById("select_class").value;
					var name = document.getElementById("pName").value;
					window.location.href="querybyName.action?type="+select+"&name="+name; 
				}
			</script>
</body>

</html>