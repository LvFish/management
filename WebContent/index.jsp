<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>项目管理系统</title>
<link rel="stylesheet" href="http://localhost:8080/struts2/css/index.css" type="text/css">

<script type="text/javascript">
	function btn(val){
		alert(val.value);
	}
</script>
</head>
<body>
    <div class="div1">
   		<span class="title">功能选择</span>
    </div>
    <div class="div2">
   		<input type="button" value="项目管理" class="btn btn1" onClick="btn(this)">
   		<input type="button" value="任务管理" class="btn btn3" onClick="btn(this)">
   		<input type="button" value="信息查询" class="btn btn5" onClick="btn(this)">
    </div>
    <div class="div3">
   		<input type="button" value="人员管理" class="btn btn2" onClick="btn(this)">
    	<input type="button" value="系统维护" class="btn btn4" onClick="btn(this)">
    </div>
</body>
</html>