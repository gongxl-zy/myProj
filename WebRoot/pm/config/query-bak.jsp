<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查询管理</title>
<jsp:include page="../frame/header.jsp"></jsp:include>
</head>
<body>
	<div class="content_wrap">
		<div class="zTreeBg">
			<ul id="treeMenu" class="ztree"></ul>
		</div>
		<div class="easyui-tabs">
			<div title="表选项">
				<form id="J_edit_form">
					<input name="operSign" type="hidden" id="J_operSign" value="add"/>
					<input name="userId" type="hidden" />
					<table class="table_form" style="width:98%" >
					  <tr>
						<th style="width:80px">表清单：</th>
						<td>
							<input name="table" id="l_table" url="query/queryTables.do" class="easyui-combobox"
								data-options="editable:false,panelHeight:'auto',valueField:'columnTable',textField:'tableName',onSelect:onSelectTable" />
						</td>
					  </tr>
					  <tr>
						<th colspan="5">
							<div id="tableConfig" class="easyui-datagrid" data-options="collapsible:true,border:false,doSize:false">
							</div>
						</th>
					  </tr>
					</table>
					<div class="sys1_buttons">
						<a class="easyui-linkbutton"  id="J_button_confirm">确 定</a>
						<a class="easyui-linkbutton"  id="J_button_cancel">取 消</a>
					</div>
				</form>
			</div>
			<div title="字段选项">
			</div>
		</div>
	</div>
	<div id="tableConfigDlg" class="easyui-dialog commDialog" title="配置操作" 
		data-options="closed:true">
		<form id="tc_form">
			<div class="mb20">
				<div>on条件：</div>
				<input id="l_condition" class="easyui-textbox" data-options="multiline:true,prompt:'输入left join的on条件。。。'" style="height:50px"/>
			</div>
			<div>
				<div>显示字段清单：</div>
				<input id="l_columns" class="easyui-combobox" data-options="editable:false,multiple:true,panelHeight:'auto',
					valueField:'columnId',textField:'columnComment'"/>
			</div>
			<div class="mt30">
				<a class="easyui-linkbutton" iconCls="icon-ok" onClick="setConfig();">OK</a>
			</div>
		</form>
	</div>
	<!-- 初始化组件 -->
	<script type="text/javascript">
	/*table表单 */
	var callback,setting;
	
	var tableConfigDg;
	var editIndex =-1;
	var editRow;
	$(function(){
		loadMenus();
		loadDataGrid();
	});
	
	function loadMenus(){
		callback = { onClick : onClickMenu };
		setting = getNoCheckTreeSets('menuId','parentId','menuName',callback);
		$.post('query/queryMenus.do',function(data){
			$.fn.zTree.init($("#treeMenu"), setting, data.obj);
		});
	}
	
	function loadDataGrid(){
		tableConfigDg = $('#tableConfig').datagrid({
			fitColumns:true,//填充整行
			striped:true,//设置为true将交替显示行背景
			method:'post',
			singleSelect:true,
			rownumbers: true,//设置为true将显示行数。
			autoRowHeight:true,
			columns:[[ //数据表格列配置对象
				{field:'tableCode',title:'表代码',align:'center',width:60},
				{field:'tableName',title:'表名称',align:'center',width:60},
				{field:'tableAname',title:'表别名',align:'center',width:30},
				{field:'joinCond',title:'左联接条件',align:'center',width:150},
				{field:'columns',title:'显示字段清单',align:'center',width:150},
				{field:'operate',title:'操作',align:'center',width:100,  
		             formatter:optFormatter,
		        }
			]],
		});
	}
	
	function optFormatter(value, row, index){
		return '<a onclick="optRow('+index+');">配置</a>'+
		'<a style="margin-left:20px;" onclick="delRow('+index+');">删除</a>';
	}
	
	function optRow(index){
		editIndex = index;
		tableConfigDg.datagrid('selectRow', index);
		editRow = tableConfigDg.datagrid('getSelected');
		var title = editRow.tableComment+" "+editRow.tableName+" "+editRow.tableCode;
		$('#tableConfigDlg').panel('setTitle',title)
		$('#tableConfigDlg').dialog('open');
		$('#tableConfigDlg').window('center');
		$('#l_columns').combobox('reload','query/queryTcs.do?paramTable='+editRow.tableName);
	}
	function delRow(index){
		alert('删除');
	}
	function onClickMenu(event, treeId, treeNode, clickFlag) {
		console.debug(event);
		console.debug(treeId);
		console.debug(treeNode);
		console.debug(clickFlag);
	}
	
	function onSelectTable(rec){
		editIndex = tableConfigDg.datagrid('getRows').length;
		tableConfigDg.datagrid('insertRow',getTableInitConf(rec));
	}
	
	function getTableInitConf(rec){
		var data = {index: editIndex,
			row:{
				tableCode:rec.columnTable,
				tableName:rec.tableName,
				tableSort :editIndex+1,
				tableAname:'t'+(editIndex+1)
			}
		}
		return data;
	}
	
	function setConfig(){
		editRow.columnIds = $('#l_columns').combobox('getValues');
		editRow.columns = getColumnNames(editRow.columnIds);
		editRow.joinCond = $('#l_condition').textbox('getValue');
		tableConfigDg.datagrid('refreshRow',editIndex);
		$('#tableConfigDlg').dialog('close');
	}
	
	function getColumnNames(ids){
		var idStr = ids + "";
		var columns = $('#l_columns').combobox('getData');
		var columnNames = "";
		var idArr = idStr.split(",");
		for(var i = 0 ; i < idArr.length ; i ++){
			for(var j = 0 ; j < columns.length ; j ++){
				if(idArr[i]==columns[j].columnId){
					columnNames = columnNames+"," + columns[j].columnComment;
					break;
				}
			}
		}
		if(columnNames != ""){
			return columnNames.substring(1);
		}else{
			return "";
		}
	}
	</script>
</body>
</html>