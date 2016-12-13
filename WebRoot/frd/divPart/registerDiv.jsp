<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="login" id="registerDiv">
	<form action="" method="post">
		<table>
			<tr>
				<td>注册方式：</td>
				<td>
					<input type="radio" name="type" id="" value="1" />邮箱注册
					<input type="radio" name="type" id="" value="2" />手机注册
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
