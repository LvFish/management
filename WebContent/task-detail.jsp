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
<script src="js/jquery-3.3.1.min.js"></script>
<script type="Text/JavaScript">
var tid;
var tid2;
var td;
$(document).ready(function(){
	$("table tr").dblclick(function(){
		var obj  = document.getElementById("show_table");
		obj.style.display = "block";
		var me = $(this);
	    td = me.find("td.uName");
	    var tds = me.find("td");
	    tid = tds[0].innerHTML;
	    tid2 = tds[1].innerHTML;
	});
	/* $("table .status").dblclick(function(){
	    
	}); */
})
</script>
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
					<li class="nav-item"><a class="nav-link active" href="manage-task">
							<i class="fas fa-shopping-cart"></i> 任务管理
					</a></li>

					<li class="nav-item"><a class="nav-link" href="system">
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
						项目：${pName}
					</p>
				</div>
			</div>

			<div class="col-12 tm-block-col">
				<div
					class="tm-bg-primary-dark tm-block tm-block-taller tm-block-scroll">
					<!-- <!-- <h2 class="tm-block-title">所属项目</h2> -->
					<div style="margin-bottom: 20px">
							<input class="search_pName" id="taskName" type="text" placeholder="新建任务" name="taskName"  />
							<input type="image" src="/struts2/image/icon_add.png" 
							style="width:20px;margin-left:10px;height:20px;" onclick="Add()"/>							
					</div>
					<table class="table">
						<thead>
							<tr>
								<th scope="col">序号</th>
								<th scope="col">人员</th>
								<th scope="col">任务</th>
								<th scope="col">创建时间</th>
								<th scope="col">创建人</th>
								<!-- <th scope="col">任务状态</th> -->
								<th scope="col">修改时间</th>
								<th scope="col">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${taskList}" var="p" varStatus="status">
								<tr>
									<th scope="row"><b>${status.index+1}</b></th>
									<td style="display:none">${p.tid }</td>
									<td class="uName">${p.uName}</td>
									<td>${p.taskName}</td>
									<td>${p.create }</td>
									<td>${p.createByName }</td>
									<%-- <td class="status">${p.status }</td> --%>
									<td>${p.modifyTime }</td>
									<td><button type="button" class="table_button" onclick="edit(${p.tid})">删除</button> 
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
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
				function edit(val){
					window.location.href="deleteTaskByTId.action?id="+val;
				}
				function Add(){
					var taskName = document.getElementById("taskName").value;
					window.location.href="addTask.action?taskName="+taskName;
				}
				function chooseUser(uid,username){
					document.getElementById("show_table").style.display="none";
					td.text(username); 
					/* alert(username+" "+uid); */
					$.ajax({
			        	  type: 'POST',
			        	  url: 'editUser.action',
			        	  data: {tid:tid,uid:uid,type:tid2},
			        	  success: function(data){
		                      
		                   }
		      		});
				}
			</script>
</body>

</html>