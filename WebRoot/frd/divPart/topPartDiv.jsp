<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
.topPart{
	width: 1000px;
	margin: auto;
	height: 50px;
	font-size: 1.2em;
}
.topPart img{
	padding-top: 4px;
	height: 80%;
	float: left;
	margin-right: 30px;
}
.topPart .nav{
	display:block;
	color: white;
}
.topPart .left{
	float: left;
	height: 78%;
	width: 80px;
	padding-top:10px;
	padding-left:10px;
	border-radius:15px;
}
.topPart .right{
	float: right;
	font-size: 0.8em;
	height: 73%;
	width: 40px;
	padding-top:12px;
	padding-left:10px;
}
.topPart a:hover{
	background-color: #FF80FF;
}
.topPart .active{
	background-color: #FF80FF;
}
.icon{
	display:block;
	margin-top:2px;
	margin-right:4px;
	float:left;
	width:23px;
	height:23px;
}
.homeIcon{
	background:url(images/home.png) no-repeat;
	background-size: cover;
}
.searchIcon{
	background:url(images/search.png) no-repeat;
	background-size: cover;
}
.letterIcon{
	background:url(images/letter.png) no-repeat;
	background-size: cover;
}
.manIcon{
	background:url(images/man.png) no-repeat;
	background-size: cover;
}
.womanIcon{
	background:url(images/woman.png) no-repeat;
	background-size: cover;
}
.setIcon{
	background:url(images/set.png) no-repeat;
	background-size: cover;
}
</style>
<div class="topPart">
	<a href="frdCommon.jsp"><img src="images/logo.png" alt="logo"/></a>
	<a class="nav left active" href="frdCommon.jsp"><span class="icon homeIcon"></span>首页</a>
	<a class="nav left" href="frdCommon.jsp"><span class="icon searchIcon"></span>搜缘</a>
	<a class="nav left" href="frdCommon.jsp"><span class="icon letterIcon"></span>信箱</a>
	<a class="nav left" href="frdCommon.jsp"><span class="icon manIcon"></span>主页</a>
	<a class="nav left" href="frdCommon.jsp"><span class="icon setIcon"></span>设置</a>
	<a class="nav right" href="frdCommon.jsp">注册</a>
	<a class="nav right" href="frdCommon.jsp">登录</a>
</div>