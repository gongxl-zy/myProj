<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
.loginPart{
	height: 400px;
	width: 39.5%;
	margin-top: 5px;
	margin-left: 5px;
	float: left;
}
.loginPart .menu{
	width: 100%;
	height: 30px;
}
.loginPart .menu div{
	height: 30px;
	width: 50%;
	float: left;
	background-color: rgb(245,245,245);
}
.loginPart .menu span{
	display:block;
	width:50px;
	margin:4px auto;
}
.loginPart .menu .hover{
	background-color: white;
}
.loginPart .login{
	width: 100%;
	height: 370px;
	float: left;
	background-color: white;
}
.loginPart .hideDiv{
	display: none;
}
.loginPart .login form{
	margin-left: 50px;
}
.loginPart .login td{
	align-content: ;
	padding-top: 10px;
}	
</style>
<div class="loginPart">
	<div class="menu">
		<div class="hover" id="registerTab" onmouseover="nowtab(1)"><span >注册</span></div>
		<div id="loginTab" onmouseover="nowtab(2)"><span>登录</span></div>
	</div>
	<jsp:include page="registerDiv.jsp"/>
	<jsp:include page="loginDiv.jsp"/>
</div>