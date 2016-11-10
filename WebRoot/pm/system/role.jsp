<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>角色管理</title>
<jsp:include page="../frame/header.jsp"></jsp:include>
</head>
<body>
	<!-----------------------------表格----------------------------->
	<div id="listPanel" class="easyui-panel" border="false">
		<div style="width:96%;margin:10px auto; ">
			<div id="dataGrid"  style="height:415px"  title="查询结果"> </div>
		</div>
		<!-- 查询条件 -->
		<div id="tb" style="padding:5px;height:auto;border:none;background:none;">
			<table class="table_form2">
				<tr>
					<th>角色名：</th>
					<td><input name="roleName" type="text" id="J_roleName" /></td>
					<th><a class="form_button3" id="J_submit_search">查询</a></th>
				</tr>
			</table>
		</div>
	</div>
	<!-- ------------------------录入--------------------------- -->
	<div id="editPanel" class="easyui-panel" border="false">
		<table class="table_form_title">
			<tr>
				<td><p id="p_edit_form">明细</p></td>
			</tr>
		</table>
		<table class="table_form_box"><tr><td>
			<form id="J_edit_form">
				<input name="operSign" type="hidden" id="J_operSign" value="add"/>
				<input name="roleId" type="hidden" />
				<input name="createrId" type="hidden" />
				<input name="updaterId" type="hidden" />
				<!-- table:把数据放在表格中 -->
				<table class="table_form" >
				  <tr>
					<th>角色名：</th>
					<td>
						<input name="roleName" type="text" id="l_roleName"  />
					</td>
					<th>角色状态：</th>
					<td>
						<select name="roleState" id="l_roleState" >
						<option value="0">停用</option>
						<option value="1">启用</option>
						</select>
					</td>
				  </tr>
				  <tr>
					<th>角色描述：</th>
					<td colspan="3">
						<textarea rows="3" cols="65" name="roleDesc"></textarea>
					</td>
				  </tr>
				  <tr>
					<th>创建人：</th>
					<td>
						<input name="createrName" type="text" disabled="disabled" />
					</td>
					<th>创建时间：</th>
					<td>
						<input name="createTime" type="text" readonly="readonly" />
					</td>
				  </tr>
				  <tr>
					<th>更新人：</th>
					<td>
						<input name="updaterName" type="text" disabled="disabled" />
					</td>
					<th>更新时间：</th>
					<td>
						<input name="updateTime" type="text" readonly="readonly" />
					</td>
				  </tr>
				</table>
				<div class="sys1_buttons">
					<a class="form_button2" id="J_button_confirm">确 定</a>
					<a class="form_button2" id="J_button_cancel">取 消</a>
				</div>
			</form>
		</td></tr></table>
	</div>
	<!-- 初始化组件 -->
	<script type="text/javascript">

	/*查询表单*/
	var J_roleName=$('#J_roleName');
	
	/*编辑表单 */
	var p_edit_form=$('#p_edit_form');
	var J_operSign=$('#J_operSign');
	var edit_focus = $('#l_roleName');//增改查操作时，光标首先停留的位置
	var l_roleState = $('#l_roleState');

	var listPanel = $('#listPanel');
	var editPanel = $('#editPanel');

	/*table表单 */
	var dataGridTable;

	/*操作按钮*/
	var J_edit_form =$('#J_edit_form');

	var J_submit_search = $('#J_submit_search');
	var J_button_confirm = $('#J_button_confirm');
	var J_button_cancel = $('#J_button_cancel');

	$(function(){
		J_submit_search.click(searchFun);
		J_button_confirm.click(confirmClick);
		J_button_cancel.click(cancelClick);
		editPanel.panel('close');//初始时，编辑面板关闭
		dataGridInit();
	});

	</script>
	<!-- 表单操作 -->
	<script type="text/javascript">
	function dataGridInit(){
		dataGridTable=$('#dataGrid').datagrid({//实例化table表单
			url:'role/query.action',
			pagination: true,//设置true将在数据表格底部显示分页工具栏。
			/* collapsible:true,  //折叠按钮 */
			fitColumns:true,//填充整行
			rownumbers: true,//设置为true将显示行数。
			striped:true,
			pageList:[5,10,20],
			pageSize:10,
			striped:true,//设置为true将交替显示行背景
			iconCls:'icon-ok',
			toolbar:'#tb',
			singleSelect:true,
			method:'post',
			columns:[[ //数据表格列配置对象
				{field:'ck', checkbox:true},
				{field:'roleName',title:'角色名',width:60},
				{field:'roleState',title:'角色状态',width:60,
					formatter : function(val, rec) {return formatter(val,'0-停用,1-启用')}
				},
				{field:'roleDesc',title:'角色描述',width:200},
				{field:'createrName',title:'创建人',width:60},
				{field:'createTime',title:'创建时间',width:100},
				{field:'updaterName',title:'更新人',width:60},
				{field:'updateTime',title:'更新时间',width:100},
			]],
		});
		var pager = dataGridTable.datagrid('getPager'); // get the pager of datagrid
		pager.pagination({
			buttons:toolbarOne()
		});
	}

	/*将选中项的信息加载如编辑视图*/
	function initform(rowData) {
		J_edit_form.form('load', rowData);
	}

	/*查询重载信息列表*/
	function searchFun() {
		dataGridTable.datagrid('load',{
			roleName:J_roleName.val(),
		});
		return false;
	}

	var opUrl = 'role/oper.do';
	/*确认操作至服务器*/
	function confirmClick() {
		if(J_operSign.val()=='')
			return false;
		if(J_operSign.val()=='view'){
			cancelClick();
			return false;
		}
		postOpAjax(opUrl,J_edit_form.serialize());
		return false;
	}

	/*取消操作返回信息列表*/
	function cancelClick() {
		J_edit_form.form("clear");
		J_operSign.val('add');
		listPanel.panel('open');
		editPanel.panel('close');
		return false;
	}

	/*toolbar操作回调函数*/
	function oper(operSign,fName){
		var rowData;
		if(operSign != 'add'){//增加以外的操作需要选择一行数据
			rowData = dataGridTable.datagrid('getSelected');
			if(rowData==null){//增加以外的操作需要选择一行数据！
				$.messager.alert("info","请选择一行后进行操作.");
				return;
			}
			initform(rowData);//将list中选中的数据行赋值到编辑表单
			if(operSign != 'view' && operSign != 'edit'){//如果不是查看和修改的操作，则直接提交返回
				J_operSign.val(operSign);//获取操作码
				confirmClick();
				return;
			}
		}else{//增加操作
			J_edit_form.form("clear");//增加操作需要清空编辑表单
			l_roleState.val("1");
		}
		p_edit_form.text(fName+'角色管理信息');//修改编辑表单的标题
		J_operSign.val(operSign);//获取操作码
		listPanel.panel('close');//关闭list面板
		editPanel.panel('open');//打开编辑面板 
		edit_focus.focus();//将光标放在初始编辑框中
	}
	</script>
</body>
</html>
