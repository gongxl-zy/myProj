<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri='/struts-tags' prefix='s' %>
<html>
	<head>
		<title>�û�ע��ҳ��</title>
	</head>
	<body>
		<form action="register.do" method="post">
			<table width="100%" cellspacing="0" cellpadding="3" align="center"
				style="text-align:center; border:1px #cccccc solid;">
				<tr style="background-color:#CCCCFF;">
					<td colspan="2">
						��дע����Ϣ
					</td>
				</tr>
				<tr>
					<td align="right" style="width:320px;" >
						��¼���ƣ�
					</td>
					<td align="left">
						<input type="text" name="user.no" style="width:220px;" />									
					</td>
				</tr>
				<tr>
					<td align="right" style="width:320px;">
						��¼���룺
					</td>
					<td align="left">
						<input type="password" name="user.pwd" style="width:220px;" />									
					</td>
				</tr>
				<tr style="background-color:#CCCCFF;">
					<td colspan="2">
						<input name="register" type="submit" id="register" value="ע��" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
