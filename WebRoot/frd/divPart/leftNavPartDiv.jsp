<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
.leftNavPart{
	margin-top: 10px;
	width: 20%;
	height: 600px;
	background-color: white;
	float: left;
}
.selfShow{
	width:120px;
	height: 270px;
	margin:auto;
}
.borderLine{
	width: 100%;
	border-top: 10px solid #F0F0F0;
}
.selfShow div{
	float:left;
	width: 100%;
	height: 1px;
	border-top: 1px solid #F0F0F0;
	margin-top: 20px;
	margin-bottom: 10px;
}
.selfShow a{
	display:block;
	text-align: center;
	margin-top:10px;
}
.selfShow img{
	width:84px;
	height:84px;
	border-radius:65px;
}
.honorIcon{
	width: 25px;
	height: 25px;
	margin-right: 2.5px;
	margin-left: 2.5px;
	float: left;
	border-radius: 15px;
}
.honor{
	background:url(images/member.bmp) no-repeat;
	background-size: cover;
}
.goldNum{
	width: 100%;
	height: 35px;
	background-color:#DFDFDF;
	padding-top: 10px;
}
.goldNum div{
	width: 80%;
	margin: auto;
}
.goldNum a{
	float: right;
}
.emailUrl{
	display: block;
	width: 100%;
	height: 32px;
	text-align: center;
	background-color:red;
	color: white;
	font-size: 18px;
	padding-top: 5px;
}
</style>
<div class="leftNavPart">
	<div class="selfShow">
		<a href="frdCommon.jsp"><img alt="个人头像" src="images/member.bmp"></a>
		<a href="frdCommon.jsp">多米乐猪</a>
		<a href="frdCommon.jsp">预览我的资料</a>
		<div></div>
		<a href="frdCommon.jsp" class="honorIcon honor" title="aaa"></a>
		<a href="frdCommon.jsp" class="honorIcon honor" title="aaa"></a>
		<a href="frdCommon.jsp" class="honorIcon honor" title="aaa"></a>
		<a href="frdCommon.jsp" class="honorIcon honor" title="aaa"></a>
		<a href="frdCommon.jsp" class="honorIcon honor" title="aaa"></a>
		<a href="frdCommon.jsp" class="honorIcon honor" title="aaa"></a>
		<a href="frdCommon.jsp" class="honorIcon honor" title="aaa"></a>
		<a href="frdCommon.jsp" class="honorIcon honor" title="aaa"></a>
	</div>
	<div class="goldNum">
		<div>金币：0<a href="">充值</a></div>		
	</div>
	<div class="borderLine"></div>
	<a class="emailUrl" href="frdCommon.jsp">私信（4封）</a>
	<div class="borderLine"></div>
</div>