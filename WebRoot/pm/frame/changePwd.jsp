<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>修改密码</title>
<jsp:include page="./header.jsp"></jsp:include>
</head>
<body>
	<form id="J_edit_form">
		<table class="table_form" >
		  <tr>
			<th>原密码：</th>
			<td>
				<input name="oldPwd" id="J_oldPwd" type="password" />
			</td>
			<td><span style="color:#ff9900;">*</span></td>
		  </tr>
		  <tr>
			<th>新密码：</th>
			<td>
				<input name="newPwd" id="J_newPwd" type="password" class="easyui-validatebox"/>
			</td>
			<td><span style="color:#ff9900;">*</span></td>
		  </tr>
		  <tr>
			<th>确认密码：</th>
			<td>
				<input name="rePwd" id="J_rePwd" type="password" class="easyui-validatebox"
					validType="equalTo['#J_newPwd']" invalidMessage="两次输入密码不匹配"/>
			</td>
			<td><span style="color:#ff9900;">*</span></td>
		  </tr>
		</table>
		<div class="sys1_buttons">
			<a class="form_button2" iconCls="icon-search" id="J_button_confirm">确 定</a>
			<!-- <a class="form_button2" iconCls="icon-search" id="J_button_cancel">取 消</a> -->
		</div>
	</form>
	<!-- 初始化组件 -->
	<script type="text/javascript">

	/*查询表单*/
	var J_oldPwd=$('#J_oldPwd');
	var J_newPwd=$('#J_newPwd');
	var J_rePwd=$('#J_rePwd');
	
	var J_edit_form = $('#J_edit_form');
	var J_button_confirm = $('#J_button_confirm');
	var J_button_cancel = $('#J_button_cancel');

	$(function(){
		J_button_confirm.click(confirmClick);
		J_button_cancel.click(cancelClick);
		J_edit_form.form('clear');
	});

	/*确认操作至服务器*/
	function confirmClick() {
		if(J_newPwd.val()=='' || J_rePwd.val()=='' || J_oldPwd.val()==''){
			$.messager.alert("提示信息","不能有空密码！");
			return false;
		}
		if(J_newPwd.val()!=J_rePwd.val()){
			$.messager.alert("提示信息","两次输入的密码不一致，请重新输入");
			J_rePwd.focus();
			return false;
		}
		if(J_newPwd.val()==J_oldPwd.val()){
			$.messager.alert("提示信息","新密码与旧密码相同，操作无效！");
			return false;
		}
		var formData = J_edit_form.serialize();
		$.ajax({
			type : "POST",
			data : formData,
			url : 'changePwd.do',
			dataType : "json",
			success : function(data) {
				if(data.flag==true){
					slideMessager(data.msg?data.msg:data.obj);
					J_edit_form.form('clear');					
				}else
					$.messager.alert("提示信息",data.msg?data.msg:data.obj);
			}
		});
		return false;
	}

	/*取消操作*/
	function cancelClick() {
		
		return false;
	}
	</script>
</body>
</html>
