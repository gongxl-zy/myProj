<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.gxl.pm.po.PmFunction" %>
<%@ page import="java.util.*" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>菜单管理</title>
<jsp:include page="../frame/header.jsp"></jsp:include>
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
}
.ztree li span.button.allot {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -128px -33px;
	vertical-align: top;
}
.table_ckb tr th:nth-child(3){
	padding-left:10px;
}
</style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',border:false,collapsible:false" style="width:400px;padding:10px;">
			<div id="p" class="easyui-panel" title="菜单树形列表" style="width:auto;height:418px;">
				<div class="div-left5 ztree" id="treeleft"></div>
			</div>
		</div>
		<div data-options="region:'center',border:false" style="padding:10px 10px 10px 0px ;">
			<div class="easyui-panel" title="明细" id="rsPanel">
				<form id="J_edit_form">
					<input name="operSign" type="hidden" id="J_operSign" value="add" />
					<input name="menuId" type="hidden" id="l_menuId" />
					<input name="upMenuId" type="hidden" id="l_upMenuId" />
					<input name="createrId" type="hidden"/>
					<input name="createTime" type="hidden"/>
					<!-- table:把数据放在表格中 -->
					<table class="table_form">
						<tr>
							<th>菜单名：</th>
							<td><input name="menuName" type="text" id="l_menuName" class="easyui-validatebox" data-options="required:true" /> <span style="color:#ff9900">*</span></td>
						</tr>
						<tr>
							<th>父资源名称：</th>
							<td><input name="upMenuName" type="text" id="l_upMenuName" disabled="disabled" class="easyui-validatebox" /></td>
						</tr>
						<tr>
							<th>菜单类型：</th>
							<td><select name="menuType" id="l_menuType" class="easyui-validatebox" data-options="required:true" onchange="changeType();">
									<option value="1" selected="selected">1-模块</option>
									<option value="2">2-程序</option>
							</select> <span style="color:#ff9900">*</span></td>
						</tr>
						<tr>
							<th>菜单性质：</th>
							<td><select name="menuProp" id="l_menuProp" class="easyui-validatebox" data-options="required:true">
									<option value="1">1-开发</option>
									<option value="2">2-管理</option>
									<option value="3">3-经理</option>
									<option value="3">4-通用</option>
							</select> <span style="color:#ff9900">*</span></td>
						</tr>
						<tr>
							<th>菜单状态：</th>
							<td><select name="menuState" id="l_menuState" class="easyui-validatebox" data-options="required:true">
									<option value="0">0-停用</option>
									<option value="1" selected="selected">1-启用</option>
							</select> <span style="color:#ff9900">*</span></td>
						</tr>
						<tr>
							<th>菜单排序：</th>
							<td><input name="menuSort" type="text" id="l_menuSort" value="1" class="easyui-validatebox" readonly="true" data-options="required:true" /> <span style="color:#ff9900">*</span></td>
						</tr>
						<tr>
							<th>菜单链接：</th>
							<td><input name="menuLink" type="text" id="l_menuLink" class="easyui-validatebox" size="60" /></td>
						</tr>
						<tr>
							<th>菜单描述：</th>
							<td><textarea rows="2" cols="50" name="menuDesc" id="l_menuDesc"></textarea></td>
						</tr>
					</table>
					<div class="sys1_buttons">
					<a href="#" class="form_button2" id="J_button_confirm">确 定</a>
					<a href="#" class="form_button2" id="J_button_cancel">取 消</a>
					</div>
				</form>
			</div>
			<div id="funcPanel" class="easyui-panel" title="程序功能配置" data-options="iconCls:'icon-save'">
				<form id="f_edit_form">
					<input name="operSign" type="hidden" id="f_operSign" value="funcMng" />
					<input name="menuId" type="hidden" />
					<input name="menuProp" type="hidden" />
					<input name="menuFuncs" type="hidden" id="f_menuFuncs" />
					<!-- table:把数据放在表格中 -->
					<table class="table_form">
						<tr>
							<th>程序名称：</th>
							<td><input name="menuName" type="text" class="easyui-validatebox" readonly="readonly" />
							<span style="color:#ff9900">*</span></td>
						</tr>
						<tr>
							<th>功能选择：</th>
							<td>
								<table class="table_ckb">
								<% 
								@SuppressWarnings("unchecked")
								List<PmFunction> funcList = (List<PmFunction>)session.getAttribute("funcList");
								int point = 0;
								out.print("<tr>");
								for(PmFunction func : funcList){
									if(point == 4){
										out.print("</tr><tr>");
										point = 0;
									}
									out.print("<th>" + func.getFuncName() + "</th>");
									out.print("<td><input type='checkbox' name='menuDesc' value='" + func.getFuncCode() + "'/></td>");
									point ++;
								}
								out.print("</tr>");
								%>
								</table>
							</td>
						</tr>
					</table>
					<div class="sys1_buttons">
						<a class="form_button2" onclick="f_confirmClick();">确 定</a>
						<a class="form_button2" onclick="cancelClick();">取 消</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 树操作 -->
	<script type="text/javascript">
		function addHoverDom(treeId, treeNode) {
			if(!checkAuth('add'))return;//无添加权限
			addAllot(treeId,treeNode);
			if(treeNode.menuType != '1')return;//无添加权限
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag
					|| $("#addBtn_" + treeNode.tId).length > 0)
				return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
					+ "' title='添加子菜单' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_" + treeNode.tId);
			if (btn)
				btn.bind("click", function() {
					addThis(treeId, treeNode);
					return false;
				});
		}
		function addAllot(treeId,treeNode){
			if(treeNode.menuType != '2')return;//无添加权限
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag
					|| $("#allotBtn_" + treeNode.tId).length > 0)
				return;
			var addStr = "<span class='button allot' id='allotBtn_" + treeNode.tId
					+ "' title='功能分配' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#allotBtn_" + treeNode.tId);
			if (btn)
				btn.bind("click", function() {
					allotThis(treeId, treeNode);
					return false;
				});
		}
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_" + treeNode.menuId).unbind().remove();
		}
		function showRemoveBtn(treeId, treeNode) {
			return !treeNode.isParent && checkAuth('del');
		}
		function showRenameBtn(treeId, treeNode) {
			return checkAuth('edit') ? true : false;
		}
		function allotThis(treeId,treeNode){
			rsPanel.panel('close');
			funcPanel.panel('open');
			f_edit_form.form("clear")
			f_operSign.val('funcMng');
			f_edit_form.form('load', treeNode);
			if(treeNode.menuFuncs == '')return;
			var funcArr = treeNode.menuFuncs.split('|');
			if (funcArr.length > 0) {
				for (var i = 0; i < funcArr.length; i++) {
					$("input:checkbox[value="+ funcArr[i] + "]").prop('checked', true);
				}
			}
		}
		//新增
		function addThis(treeId, treeNode){
			rsPanel.panel('open');
			funcPanel.panel('close');
			if (treeNode.menuType != '1') {
				$.messager.alert("提示信息", "选中节点下不得添加子节点！");
				return;
			}
			cancelClick();
			l_upMenuId.val(treeNode.menuId);
			l_upMenuName.val(treeNode.menuName);

			if (treeNode.children == undefined) {
				l_menuSort.val(1);
			} else {
				var nodes = treeNode.children;
				l_menuSort.val(Number(nodes[nodes.length - 1].menuSort) + 1);
			}
			//处理表单
			rsPanel.panel({title:'添加菜单'});
			J_operSign.val('add');
			l_menuName.focus();
		}
		
		//原本用于阻止编辑名称，这里用来打开编辑
		function beforeEditName(treeId, treeNode) {
			rsPanel.panel('open');
			funcPanel.panel('close');
			//处理表单
			initform(treeNode,'编辑菜单');
			console.debug(treeNode.getParentNode());
			if(treeNode.getParentNode()!=null){
				l_upMenuName.val(treeNode.getParentNode().menuName);
			}
			J_operSign.val('edit');
			document.all.l_menuType.readOnly = true;
			l_menuName.focus();

			return false;
		}
		//原本用于阻止删除，这里用来打开删除
		function beforeRemove(treeId, treeNode) {
			rsPanel.panel('open');
			funcPanel.panel('close');
			initform(treeNode,'删除部门');
			if (confirm("您确定要删除本资源？") == true) {
				J_operSign.val('del');
				confirmClick();
			}else{
				beforeEditName(treeId, treeNode);
			}
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
		var J_operSign =$('#J_operSign');
		var l_menuId =$('#l_menuId');		
		var l_upMenuId=$('#l_upMenuId');
		var l_menuName=$('#l_menuName');
		var l_upMenuName=$('#l_upMenuName');
		var l_menuType=$('#l_menuType');
		var l_menuProp=$('#l_menuProp');
		var l_menuState=$('#l_menuState');
		var l_menuSort=$('#l_menuSort');
		var l_menuLink=$('#l_menuLink');
		/*操作按钮*/
		var rsPanel=$('#rsPanel');
		var funcPanel =$('#funcPanel');
		
		var f_operSign =$('#f_operSign');
		
		var f_edit_form = $('#f_edit_form');
		var J_edit_form = $('#J_edit_form');
		var J_button_confirm = $('#J_button_confirm');
		var J_button_cancel = $('#J_button_cancel');

		var zTreeObj;
		var zTreeOpenList={};

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
				url : 'menu/query.do',
				dataFilter : updateIcon
			},
			data : {
				simpleData : {
					enable : true,
					rootPId : null,
					idKey : 'menuId',
					pIdKey : 'upMenuId'
				},
				key : {
					name : 'menuName'
				}
			},
			callback : {
				beforeDrag:beforeDrag,
				beforeEditName : beforeEditName,
				beforeRemove : beforeRemove,
				onClick:function(event, treeId, treeNode){
					beforeEditName(treeId, treeNode);
				}
			}
		};

		$(function() {
			J_button_confirm.click(confirmClick);
			J_button_cancel.click(cancelClick);

			zTreeObj = $.fn.zTree.init($("#treeleft"), setting);
			rsPanel.panel('open');
			funcPanel.panel('close');
			if(checkAuth('add')){
				cancelClick();
				rsPanel.panel({title:'添加菜单'});
				J_operSign.val('add');
				l_menuName.focus();
			}else
				rsPanel.panel({title:'请在右侧选择操作类型'});
		});
		function updateIcon(treeId, parentNode, responseData) {
			if (responseData) {
				for (var i = 0; i < responseData.length; i++) {
					responseData[i].open=(zTreeOpenList[responseData[i].menuId]||!responseData[i].upMenuId)?true:false;
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
			rsPanel.panel({title:name});
			J_edit_form.form('load', rowData);
		}
		function cancelClick() {
			J_edit_form.form("clear");
			f_edit_form.form("clear");
			if(checkAuth('add')){
				rsPanel.panel({title:'添加菜单'});
				J_operSign.val('add');
				l_menuName.focus();
			}else
				rsPanel.panel({title:'请在右侧选择操作类型'});
			l_menuType.val('1');
			l_menuProp.val('1');
			l_menuState.val('1');
			document.all.l_menuLink.readOnly = false;
			document.all.l_menuType.readOnly = false;
			var nodes = zTreeObj.getNodes();
			l_menuSort.val(Number(nodes[nodes.length - 1].menuSort) + 1);
			return false;
		}
		function confirmClick() {
			var isValid = $("#J_edit_form").form('validate');
			if (!isValid) {
				return isValid;
			}
			opAjax(J_edit_form.serialize());
			return false;
		}
		function f_confirmClick() {
			var result = "";
			$('input:checkbox:checked').each(function() {
				result += $(this).val() + '|';
			});
			if (result != "")
				result = result.substr(0, result.length - 1);
			$("#f_menuFuncs").val(result);
			var formData = f_edit_form.serialize();

			opAjax(formData);
		}
		function opAjax(formData) {
			$.ajax({
				type : "POST",
				data : formData,
				url : 'menu/oper.do',
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
					zTreeOpenList[list[i].menuId]=true;
				}
			}
			zTreeObj.reAsyncChildNodes(null, "refresh",true);
			//刷新
			$('#l_upMenuId').combotree('reload');
		}
	</script>
</body>
</html>