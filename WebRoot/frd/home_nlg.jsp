<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath+"/frd/"%>" />
		<title>我的首页</title>
		<link rel="stylesheet" type="text/css" href="css/common.css"/>
		<link rel="stylesheet" type="text/css" href="css/layout.css"/>
	</head>
	<body>
		<div class="header fixed">
			<div class="topnav">
				<a href="frdCommon.jsp"><img class="logo" src="images/logo.png" alt="logo"/></a>
				<a class="mainmenu selected" href="frdCommon.jsp"><span class="homeIcon"></span>首页</a>
				<a class="mainmenu" href="frdCommon.jsp"><span class="searchIcon"></span>搜缘</a>
				<a class="mainmenu" href="frdCommon.jsp"><span class="letterIcon"></span>信箱</a>
				<a class="mainmenu" href="frdCommon.jsp"><span class="manIcon"></span>主页</a>
				<a class="mainmenu" href="frdCommon.jsp"><span class="setIcon"></span>设置</a>
				<a class="submenu" href="frdCommon.jsp">登录</a>
				<a class="submenu" href="frdCommon.jsp">注册</a>
			</div>
		</div>
		<!-- 当页头（50px）固定（position:fixed）时，加载此div纠正盒子系统 -->
		<div class="take50pxRow"></div>
		<div class="container">
			<img class="banner" src="images/banner.jpg" alt="任性交友，让交友更任性"/>
			<jsp:include page="divPart/loginPartDiv.jsp"/>
			<jsp:include page="divPart/searchPartDiv.jsp"/>
			<jsp:include page="divPart/showPartDiv.jsp"/>
			<jsp:include page="divPart/webUrlsPartDiv.jsp"/>
			<jsp:include page="divPart/frdUrlsPartDiv.jsp"/>
			<jsp:include page="divPart/ptnUrlsPartDiv.jsp"/>
			<jsp:include page="divPart/leftNavPartDiv.jsp"/>
			<jsp:include page="divPart/rightContPartDiv.jsp"/>
		</div>
		<div class="cutRow15px"></div>
		<script src="frdCommon.js" type="text/javascript" charset="utf-8"></script>
		<jsp:include page="divPart/publicLoad.jsp"/>
	</body>
</html>