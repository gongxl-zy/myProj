<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html lang="en"> <!--<![endif]-->
<head>
<title>登陆界面</title>
<link rel="stylesheet" type="text/css" href="../css/frame/loginStyle.css" />
</head>
<body>
<div class="container">
	<section id="content">
		<form action="login" method="post">
			<h1>欢迎登陆</h1>
			<div>
				<input type="text" placeholder="用户名" required="required" id="username" value="${userNo}" name="userNo" />
			</div>
			<div>
				<input type="password" placeholder="密码" required="required" id="password" value="${userPwd}" name="userPwd" />
			</div>
			<div style="left: 1px;">
				<b style="color:red">${msg}</b>
			</div>
			<div>
				<input type="submit" value="登录" />
				<a href="#">忘记密码？</a>
				<a href="#">注册</a>
			</div>
		</form><!-- form -->
		<div class="button">
			<a href="#">下载资源文件</a>
		</div><!-- button -->
	</section><!-- content -->
</div><!-- container -->
</body>
</html>