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
		<meta charset="UTF-8">
		<title>我的首页</title>
		<link rel="stylesheet" type="text/css" href="frdCommon.css"/>
	</head>
	<body>
		<div class="navigation">
			<jsp:include page="divPart/topPartDiv.jsp"/>
		</div>
		<div class="container">
			<%-- <jsp:include page="divPart/bannerDiv.jsp"/>
			<jsp:include page="divPart/loginPartDiv.jsp"/>
			<jsp:include page="divPart/searchPartDiv.jsp"/>
			<jsp:include page="divPart/showPartDiv.jsp"/>
			<jsp:include page="divPart/webUrlsPartDiv.jsp"/>
			<jsp:include page="divPart/frdUrlsPartDiv.jsp"/>
			<jsp:include page="divPart/ptnUrlsPartDiv.jsp"/> --%>
			<jsp:include page="divPart/leftNavPartDiv.jsp"/>
			<jsp:include page="divPart/rightContentPartDiv.jsp"/>
		</div>
		<div class="splitLine"></div>
		<script src="frdCommon.js" type="text/javascript" charset="utf-8"></script>
		<jsp:include page="divPart/publicLoad.jsp"/>
	</body>
</html>