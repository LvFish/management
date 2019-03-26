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
	href="http://localhost:8080/struts2/css/fontawesome.min.css">
<!-- https://fontawesome.com/ -->
<link rel="stylesheet"
	href="http://localhost:8080/struts2/css/bootstrap.min.css">
<!-- https://getbootstrap.com/ -->
<link rel="stylesheet"
	href="http://localhost:8080/struts2/css/templatemo-style.css">

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
					<li class="nav-item"><a class="nav-link active" href="index">
							<i class="fas fa-tachometer-alt"></i> 项目管理 <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="manage-person">
							<i class="fas fa-tachometer-alt"></i> 人员管理 <span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="manage-task">
							<i class="fas fa-shopping-cart"></i> 任务管理
					</a></li>

					<li class="nav-item"><a class="nav-link" href="accounts.html">
							<i class="far fa-user"></i> 系统维护
					</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> <i class="fas fa-cog"></i> <span>
								信息查询 <i class="fas fa-angle-down"></i>
						</span>
					</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
							<a class="dropdown-item" href="#">Profile</a> <a
								class="dropdown-item" href="#">Billing</a> <a
								class="dropdown-item" href="#">Customize</a>
						</div></li>
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

			<div class="col-12 tm-block-col" id="back-div">
				<div
					class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
					<h2 class="tm-block-title">项目信息</h2>
					<div style="float: right">
						<button class="btn-tm-block" onclick="edit()" id="edit">编辑</button>
						<button class="btn-tm-block" onclick="cancel()" id="cancel"
							style="display: none">取消</button>
						<button class="btn-tm-block" onclick="save()" id="save"
							style="display: none">保存</button>
						<button class="btn-tm-block" onclick="back()" id="back">返回</button>
					</div>
					<div style="margin-top:100px">
						<div>
							<div class="detail-div" style="display:none;">
								<span class="detail-span">pid</span>
								<input class="detail-input" id="pid" type="text" value="${project.pid }" disabled="disabled"/>
							</div>
							<div class="detail-div">
								<span class="detail-span">项目名</span>
								<input class="detail-input" id="pName" type="text" value="${project.pName }" disabled="disabled"/>
							</div>
							<div class="detail-div"> 
								<span class="detail-span">创建时间</span>
								<input class="detail-input" id="create" type="text" value="${project.create }" disabled="disabled" />
							</div>
							<div class="detail-div">
								<span class="detail-span">创建人员</span>
								<input class="detail-input" id="createByName" type="text" value="${project.createByName }" disabled="disabled" />
							</div>
						</div>
						<div style="margin-top:40px">
							<div class="detail-div">
								<span class="detail-span" >负责人</span>
								<input onclick="findUser()" readonly="readonly" class="detail-input" id="headName"  type="text" value="${project.headName }" disabled="disabled"/>
							</div>
							<div class="detail-div" style="display:none">
								<span class="detail-span">负责人编号</span>
								<input onclick="findUser()" readonly="readonly" class="detail-input" id="headId"  type="text" value="${project.headId }" disabled="disabled"/>
							</div>
							<div class="detail-div">
								<span class="detail-span">项目状态</span>
								<input class="detail-input" id="status" type="text" value="${project.status }" disabled="disabled"/>
							</div>
							<div class="detail-div">
								<span class="detail-span">修改时间</span>
								<input class="detail-input" id="modifyTime" type="text" value="${project.modifyTime }" disabled="disabled"/>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="show_table" style="display:none;position: absolute;" id="show_table">
				<div class="col-12 tm-block-col">
				<div
					class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
					<h2 class="tm-block-title">所有人员</h2>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">序号</th>
								<th scope="col">名称</th>
								<th scope="col">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userList}" var="u" varStatus="status">
								<tr>
									<th scope="row"><b>${status.index+1}</b></th>
									<td>${u.username }</td>
									<td><button type="button" class="table_button" onclick="chooseUser('${u.uId}','${u.username }')">选中</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
			</div>
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
				var pName,headName,status;
				var id,username;
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
				function edit() {
					var editBtn = document.getElementById("edit");
					editBtn.style.display = "none";
					var backBtn = document.getElementById("back");
					backBtn.style.display = "none";
					var cancelBtn = document.getElementById("cancel");
					cancelBtn.style.display = "";
					var saveBtn = document.getElementById("save");
					saveBtn.style.display = "";
					pName = document.getElementById("pName").value;
					headName = document.getElementById("headName").value;
					status = document.getElementById("status").value;
					document.getElementById("pName").removeAttribute("disabled");
					document.getElementById("headName").removeAttribute("disabled");
					document.getElementById("status").removeAttribute("disabled");
					id = document.getElementById("headId").value;
				}
				function cancel() {
					var editBtn = document.getElementById("edit");
					editBtn.style.display = "";
					var backBtn = document.getElementById("back");
					backBtn.style.display = "";
					var cancelBtn = document.getElementById("cancel");
					cancelBtn.style.display = "none";
					var saveBtn = document.getElementById("save");
					saveBtn.style.display = "none";
					document.getElementById("pName").setAttribute("disabled","disabled");
					document.getElementById("headName").setAttribute("disabled","disabled");
					document.getElementById("status").setAttribute("disabled","disabled");
					document.getElementById("pName").value = pName;
					document.getElementById("headName").value = headName;
					document.getElementById("status").value = status;
				}
				function back() {
					window.location.href="index.action";
				}
				function save() {
					window.location.href="updateProject.action?headId="+id+"&pName="+document.getElementById("pName").value
							+"&status="+document.getElementById("status").value+"&pid="+document.getElementById("pid").value;
				}
				function findUser(){
					var obj  = document.getElementById("show_table");
					obj.style.display = "block";
				}
				function chooseUser(id,username){
					this.id = id;
					this.username = username;
					document.getElementById("show_table").style.display="none";
					document.getElementById("headName").value = username;
				}
			</script>
</body>

</html>