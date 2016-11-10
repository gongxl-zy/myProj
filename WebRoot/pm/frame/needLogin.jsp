<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>请重新登录</title>
<script type="text/javascript" >
if(typeof top.isAjaxLoginDoneRefreshTab=="undefined"){
	location.href='./';
}else{
	top.isAjaxLoginDoneRefreshTab=true;
	top.J_ajax_login.dialog('open');
}
</script>
</head>
<body>
</body>
</html>
