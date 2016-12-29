<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
.leftNavPart{
	margin-top: 10px;
	width: 200px;
	height: auto;
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
	padding-top: 10px;
}
.navTitle{
	width: 100%;
	height: 30px;
}
.titleLine{
	width: 80%;
	margin:10px auto;
	border-left: 3px solid red;
	padding-left: 5px;
}
.left{float:left;}
.right{float:right;}
.servLink{
	font-size: .9em;
	width: 80%;
	margin: auto;
	height: 70px;
}
.servLink div{
	margin-top: 10px;
	height: 20px;
}
.servLink a{
	width: 80%;
	margin:5px auto;
}
.servLink a:hover{
	text-decoration:underline;
}
.menuLink{
	font-size: .9em;
	width: 100%;
	height: auto;
}
.menuLink a{
	display: block;
	padding-top: 15px;
	height: 30px;
}
.menuLink a:hover{
	background-color: #F5F5F5;
	color: black;
}
.w80{
	width: 80%;
	margin: auto;
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
	<div class="navTitle">
		<div class="titleLine">我的服务</div>
	</div>
	<div>
		<div class="servLink">
			<div class="left">[<a href="frdCommon.jsp">写信包月</a>]</div>
			<div class="right">[<a href="frdCommon.jsp">金币充值</a>]</div>
			<div class="left">[<a href="frdCommon.jsp">抢镜头</a>]</div>
		</div>
	</div>
	<div class="borderLine"></div>
	<div class="navTitle">
		<div class="titleLine">我的有缘</div>
	</div>
	<div class="menuLink">
		<a href="frdCommon.jsp">
			<div class="w80">
				<div class="left">最近访客</div>
				<div class="right">></div>
			</div>
		</a>
		<a href="frdCommon.jsp">
			<div class="w80">
				<div class="left">我的关注</div>
				<div class="right">></div>
			</div>
		</a>
		<a href="frdCommon.jsp">
			<div class="w80">
				<div class="left">我收到的礼物</div>
				<div class="right">></div>
			</div>
		</a>
		<a href="frdCommon.jsp">
			<div class="w80">
				<div class="left">最近联系人</div>
				<div class="right">></div>
			</div>
		</a>
		<a href="frdCommon.jsp">
			<div class="w80">
				<div class="left">我的信箱</div>
				<div class="right">></div>
			</div>
		</a>
		<a href="frdCommon.jsp">
			<div class="w80">
				<div class="left">我的服务</div>
				<div class="right">></div>
			</div>
		</a>
	</div>
	<div class="borderLine"></div>
	<div class="navTitle">
		<div class="titleLine">我的资料</div>
	</div>
	<div class="menuLink">
		<a href="frdCommon.jsp">
			<div class="w80">
				<div class="left">最近访客</div>
				<div class="right">></div>
			</div>
		</a>
	</div>
</div>