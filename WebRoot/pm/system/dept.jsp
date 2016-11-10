<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>部门管理</title>
<jsp:include page="../frame/header.jsp"></jsp:include>
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
}
</style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',border:false,collapsible:false" style="width:400px;padding:10px;">
			<div id="p" class="easyui-panel" title="部门树形列表" style="width:auto;height:418px;">
				<div class="div-left5 ztree" id="treeleft"></div>
			</div>
		</div>
		<div data-options="region:'center',border:false" style="padding:10px 10px 10px 0px ;">
			<div class="easyui-panel" title="明细" id="p_edit_form">
				<form id="J_edit_form">
					<input name="operSign" type="hidden" id="J_operSign" value="add" />
					<input name="deptId" type="hidden" />
					<input name="upDeptId" type="hidden"/>
					<input name="createrId" type="hidden"/>
					<input name="createTime" type="hidden"/>
					<!-- table:把数据放在表格中 -->
					<table class="table_form">
						<tr>
							<th>部门名称：</th>
							<td><input name="deptName" id="l_deptName" /><span style="color:#ff9900;">*</span></td>
						</tr>
						<tr>
							<th>部门级别：</th>
							<td><input name="deptLevel" id="l_deptLevel" readonly="readonly"/><span style="color:#ff9900;">*</span></td>
						</tr>
						<tr>
							<th>部门负责人：</th>
							<td><input name="deptMngId" id="l_deptMngId" class="easyui-combogrid" panelHeight="auto"/><span style="color:#ff9900;">*</span></td>
						</tr>
						<tr>
							<th>上级部门：</th>
							<td><input name="upDeptName" id="l_upDeptName" disabled="disabled"/></td>
						</tr>
						<tr>
							<th>部门状态：</th>
							<td><select name="deptState" id="l_deptState" class="easyui-validatebox" data-options="required:true">
									<option value="0">0-停用</option>
									<option value="1" selected="selected">1-启用</option>
							</select> <span style="color:#ff9900">*</span></td>
						</tr>
						<tr>
							<th>部门描述：</th>
							<td><textarea rows="3" cols="50" name="deptDesc" id="l_deptDesc"></textarea></td>
						</tr>
					</table>
					<div class="sys1_buttons">
						<a href="#" class="form_button2" id="J_button_confirm">确 定</a>
						<a href="#" class="form_button2" id="J_button_cancel">取 消</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 树操作 -->
	<script type="text/javascript">
		function addHoverDom(treeId, treeNode) {
			if(!checkAuth('add'))return;//无添加权限
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag
					|| $("#addBtn_" + treeNode.tId).length > 0)
				return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
					+ "' title='添加子部门' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_" + treeNode.tId);
			if (btn)
				btn.bind("click", function() {
					addThis(treeId, treeNode);
					return false;
				});
		}
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_" + treeNode.tId).unbind().remove();
		}
		function showRemoveBtn(treeId, treeNode) {
			return !treeNode.isParent && checkAuth('del');
		}
		function showRenameBtn(treeId, treeNode) {
			return checkAuth('edit') ? true : false;
		}
		//新增
		function addThis(treeId, treeNode){
			//处理表单
			var level = Number(treeNode.deptLevel)+1;
			var data = {
					upDeptId:treeNode.deptId,
					upDeptName:treeNode.deptName,
					deptLevel:level
			}
			initform(data,'添加部门');
			J_operSign.val('add');
			l_deptName.focus();
		}
		
		//原本用于阻止编辑名称，这里用来打开编辑
		function beforeEditName(treeId, treeNode) {
			//处理表单
			initform(treeNode,'编辑部门');
			J_operSign.val('edit');
			l_deptName.focus();

			return false;
		}
		//原本用于阻止删除，这里用来打开删除
		function beforeRemove(treeId, treeNode) {
			initform(treeNode,'删除部门');
			J_operSign.val('del');
			confirmClick();
			return false;
		}
		//阻止移动
		function beforeDrag(treeId, treeNodes) {
			return false;
		}
	</script>
	<!-- 初始化组件 -->
	<script type="text/javascript">
		/*编辑表单 */
		var J_operSign = $('#J_operSign');
		var l_deptName = $('#l_deptName');
		var l_deptLevel = $('#l_deptLevel');
		
		var l_deptMngId = $('#l_deptMngId');
		var userCombogridTable;
		
		/*操作按钮*/
		var p_edit_form=$('#p_edit_form');
		var J_edit_form = $('#J_edit_form');
		var J_button_confirm = $('#J_button_confirm');
		var J_button_cancel = $('#J_button_cancel');

		var zTreeObj;
		var zTreeOpenList={};
		function initMngUserList(){
			userCombogridTable = l_deptMngId.combogrid({
				url : 'user/queryList.do',
				method : 'post',
				panelWidth : 500,
				rownumbers : true,
				striped : true,//设置为true将交替显示行背景
				idField : 'userId',
				textField : 'userName',
				fitColumns : true,
				columns : [ [ //数据表格列配置对象{
					{field : 'userNo',title : '用户号',width : 200}, 
					{field : 'userName',title : '用户名',width : 200},
					{field : 'roleName',title : '角色',width : 200},
					{field : 'deptName',title : '部门',width : 200},
					{field : 'userPhone',title : '手机号',width : 200}
				] ],
				/* onHidePanel : function() {
					editLimit();
				}, */
				onChange : OnChangeText
			}).combogrid('grid');
		}
		function OnChangeText(){
			userCombogridTable.datagrid('load',{userNo:l_deptMngId.combogrid('getText')});
		}
		var setting = {
			view : {
				addHoverDom : addHoverDom,
				removeHoverDom : removeHoverDom,
				selectedMulti : false,
				showIcon : true,
				showLine : true
			},
			edit : {
				enable : true,
				editNameSelectAll : true,
				removeTitle:'删除',
				renameTitle:'修改',
				showRemoveBtn : showRemoveBtn,
				showRenameBtn : showRenameBtn
			},
			async : {
				enable : true,
				url : 'dept/query.do',
				dataFilter : updateIcon
			},
			data : {
				simpleData : {
					enable : true,
					rootPId : null,
					idKey : 'deptId',
					pIdKey : 'upDeptId'
				},
				key : {
					name : 'deptName'
				}
			},
			callback : {
				beforeDrag:beforeDrag,
				beforeEditName : beforeEditName,
				beforeRemove : beforeRemove,
				onClick:function(event, treeId, treeNode){
					if(checkAuth('edit')){
						//处理表单
						initform(treeNode,'编辑部门');
						J_operSign.val('edit');
						l_deptName.focus();
					}else if(checkAuth('view')){
						initform(treeNode,"查看商户");
					}
				}
			}
		};

		$(function() {
			J_button_confirm.click(confirmClick);
			J_button_cancel.click(cancelClick);

			zTreeObj = $.fn.zTree.init($("#treeleft"), setting);
			
			initMngUserList();
			if(checkAuth('add')){
				p_edit_form.panel({title:'添加部门'});
				J_operSign.val('add');
				l_deptName.focus();
			}else
				p_edit_form.panel({title:'请在右侧选择操作类型'});
		});
		function updateIcon(treeId, parentNode, responseData) {console.log(parentNode, responseData);
			if (responseData) {
				for (var i = 0; i < responseData.length; i++) {
					responseData[i].open=(zTreeOpenList[responseData[i].deptId]||!responseData[i].parentOrg)?true:false;
					responseData[i].name = responseData[i].ccbinsChnFullnm;
					responseData[i].icon = "./css/zTree/zTreeStyle/img/diy/1_close.png";
				}
			}
			return responseData;
		}
	</script>

	<!-- 表单操作 -->
	<script type="text/javascript">
		function initform(rowData,name) {
			p_edit_form.panel({title:name});
			J_edit_form.form('load', rowData);
		}
		function cancelClick() {
			J_edit_form.form("clear");
			if(checkAuth('add')){
				p_edit_form.panel({title:'添加部门'});
				J_operSign.val('add');
				l_deptName.focus();
			}else
				p_edit_form.panel({title:'请在右侧选择操作类型'});
			return false;
		}
		function confirmClick() {
			if (J_operSign.val() == '')
				return false;
			if($('#l_deptName').val()==''){
				$.messager.alert("提示信息", "部门名称不可为空！");
				return false;				
			}
			if($('#l_deptEmp').val()==''){
				$.messager.alert("提示信息", "部门负责人姓名不可为空！");
				return false;				
			}
			opAjax(J_edit_form.serialize());
			return false;
		}
		function opAjax(formData) {
			$.ajax({
				type : "POST",
				data : formData,
				url : 'dept/oper.do',
				dataType : "json",
				success : function(data) {
					if(data.flag==true){
						zTreeOpenList = {};
						var list = zTreeObj.transformToArray(zTreeObj.getNodes());
						for (var i = 0; i < list.length; i++) {
							if (list[i].open) {
								zTreeOpenList[list[i].menuId] = true;
							}
						}
						zTreeObj.reAsyncChildNodes(null, "refresh");
						slideMessager(data.obj);
						cancelClick();
					}else{
						$.messager.alert("提示信息",data.obj);
					}
				}
			});
		}
		function searchFun(){
			zTreeOpenList={};
			var list=zTreeObj.transformToArray(zTreeObj.getNodes());
			for(var i=0;i<list.length;i++){
				if(list[i].open){
					zTreeOpenList[list[i].deptId]=true;
				}
			}
			zTreeObj.reAsyncChildNodes(null, "refresh",true);
			//刷新
			$('#l_parentOrg').combotree('reload');
		}
	</script>
</body>
</html>