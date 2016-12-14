<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="loginbar">
	<div class="tabmenu">
		<div class="hover" id="registerTab" onmouseover="nowtab(1)">注册</div>
		<div id="loginTab" onmouseover="nowtab(2)">登录</div>
	</div>
	<div class="login" id="register">
		<form action="" method="post">
			<table>
				<tr>
					<td>注册方式：</td>
					<td>
						<input type="radio" name="type" id="" value="1" />
						<span onclick="setCheck('type','1');">邮箱注册</span>
						<input type="radio" name="type" id="" value="2" />
						<span onclick="setCheck('type','2');">手机注册</span>
						
					</td>
				</tr>
				<tr>
					<td><span id="type">注册邮箱：</span></td>
					<td><input type="text" name="email" id="email" /></td>
				</tr>
				<tr>
					<td><span id="pwd">设置密码：</span></td>
					<td><input type="text" name="pwd" id="pwd" /></td>
				</tr>
				<tr>
					<td align="right">我是：</td>
					<td>
						<input type="radio" name="sex" id="" value="1" />
						<span onclick="setCheck('sex','1');">帅哥</span>
						<input type="radio" name="sex" id="" value="2" />
						<span onclick="setCheck('sex','2');">美女</span>
					</td>
				</tr>
				<tr>
					<td align="right"><span id="pwd">年龄：</span></td>
					<td><input type="text" name="age" id="age" /></td>
				</tr>
				<tr>
					<td align="right"><span id="pwd">验证码：</span></td>
					<td><input type="text" name="check" id="check" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="submit" value="确认注册"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="hide" id="login">
		<form action="" method="post">
			<table>
				<tr>
					<td>登录账号：</td>
					<td><input type="text" name="email" id="email" /></td>
				</tr>
				<tr>
					<td>登录密码：</td>
					<td><input type="text" name="pwd" id="pwd" /></td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="submit" value="立即登录"/></td>
				</tr>
			</table>
		</form>
	</div>
</div>