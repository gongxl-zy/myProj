<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>查询管理</title>
<jsp:include page="../frame/header.jsp"></jsp:include>
</head>
<body>
	<div id="tb" style="padding:5px;height:auto;border:none;background:none;">
		<a class="easyui-linkbutton" onclick="showEditPanel('add');">添加</a>
	</div>
	<div id="listPanel" class="easyui-panel" border="false">
		<div style="width:96%;margin:10px auto; ">
			<div id="dataGrid"  style="height:380px" > </div>
		</div>
	</div>
	<div id="editPanel" class="easyui-panel" border="false">
		<form id="J_edit_form">
			<input name="operSign" type="hidden" id="J_operSign" value="add"/>
			<table class="table_form" style="width:98%">
			  <tr>
				<th>表清单：</th>
				<td style="width:160px">
					<input url="query/queryTables.do" class="easyui-combobox"
						data-options="editable:false,panelHeight:'auto',valueField:'columnTable',textField:'tableName',onSelect:onSelectTable" />
				</td>
				<td style="width:100px"> <a class="easyui-linkbutton" iconCls="icon-add" onclick="addAgain();">再次添加表</a> </td>
				<th style="width:80px">查询清单：</th>
				<td style="width:160px">
					<input url="query/querySqs.do" class="easyui-combobox"
						data-options="editable:false,panelHeight:'auto',valueField:'queryCode',textField:'queryName',onSelect:onSelectSq" />
				</td>
				<td style="width:120px"> <a class="easyui-linkbutton" iconCls="icon-add" onclick="addSqAgain();">再次添加查询</a> </td>
			  </tr>
			  <tr>
				<th colspan="7"> <div id="tableConfig"> </div> </th>
			  </tr>
			  <tr>
				<th colspan="7"> <div id="columnConfig" > </div> </th>
			  </tr>
			  <tr>
				<th>where语句：</th>
				<td colspan="5">
					<input name="whereSql" class="easyui-textbox" data-options="multiline:true" style="width:600px;height:48px" />
				</td>
			  </tr>
			  <tr>
				<th>SQL语句：</th>
				<td colspan="5">
					<input name="querySql" id="l_querySql" class="easyui-textbox" data-options="multiline:true,editable:false" style="width:600px;height:150px" />
				</td>
				<td style="width:200px"></td>
			  </tr>
			  <tr>
				<th>分组排序语句：</th>
				<td colspan="5">
					<input name="goSql" class="easyui-textbox" data-options="multiline:true" style="width:600px;height:48px" />
				</td>
			  </tr>
			  <tr>
				<th>查询代码：</th>
				<td colspan="5">
					<input  name="queryCode" class="easyui-textbox"  style="width:200px;" />
				</td>
			  </tr>
			  <tr>
				<th>查询名：</th>
				<td colspan="5">
					<input name="queryName" class="easyui-textbox"  style="width:200px;" />
				</td>
			  </tr>
			  <tr>
				<th>查询类型：</th>
				<td colspan="5">
					<select name="queryType" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
							<option value="1">全查询</option>
							<option value="2">子查询</option>
					</select>
				</td>
			  </tr>
			  <tr>
				<th>查询描述：</th>
				<td colspan="5">
					<input name="queryDesc" class="easyui-textbox" data-options="multiline:true" style="width:600px;height:48px" />
				</td>
			  </tr>
			</table>
			<div class="sys_buttons">
				<a class="easyui-linkbutton" iconCls="icon-ok" onClick="confirmClick();">确 定</a>
				<a class="easyui-linkbutton" iconCls="icon-no">取 消</a>
			</div>
		</form>
	</div>
	<div id="tableConfigDlg" class="easyui-dialog commDialog" title="配置操作" 
		data-options="closed:true">
		<form id="tc_form">
			<div class="mb20">
				<div>左联接条件：</div>
				<input id="l_joinCond" class="easyui-textbox" data-options="multiline:true,prompt:'输入left join的on条件。。。'" style="height:50px"/>
			</div>
			<div>
				<div>显示字段清单：</div>
				<input id="l_columns" class="easyui-combobox" data-options="editable:false,multiple:true,panelHeight:'auto',
					valueField:'columnId',textField:'columnName'"/>
			</div>
			<div class="mt30">
				<a class="easyui-linkbutton" iconCls="icon-ok" onClick="setConfig();">OK</a>
			</div>
		</form>
	</div>
	<!-- 初始化组件 -->
	<script type="text/javascript">
	var dataGridTable;
	var tableConfigDg;
	var columnConfigDg;

	var J_edit_form=$('#J_edit_form');
	var J_operSign=$('#J_operSign');

	var l_querySql=$('#l_querySql');	
	
	var listPanel = $('#listPanel');
	var editPanel = $('#editPanel');
	
	var nowOptTableIndex =-1;//正在配置的表所在Index
	var nowOptTable;//正在配置的表
	var lastAddTable;//上次添加的表
	var nowOptClmIndex =-1;//正在配置的字段所在Index
	var nowOptClm;//正在配置的字段
	
	$(function(){
		editPanel.panel('close');//初始时，编辑面板关闭
		dataGridInit();
		createTableConfigDg();
		createColumnConfigDg();
	});
	
	function showEditPanel(operSign){
		editPanel.panel('open');
		listPanel.panel('close');
		J_operSign.val(operSign);
	}
	function dataGridInit(){
		dataGridTable=$('#dataGrid').datagrid({//实例化table表单
			url:'query/query.do',
			pagination: true,//设置true将在数据表格底部显示分页工具栏。
			fitColumns:true,//填充整行
			rownumbers: true,//设置为true将显示行数。
			striped:true,
			pageList:[5,10,20],
			pageSize:10,
			striped:true,//设置为true将交替显示行背景
			iconCls:'icon-ok',
			singleSelect:true,
			method:'post',
			toolbar:'#tb',
			columns:[[ //数据表格列配置对象
				{field:'ck', checkbox:true},
				{field:'queryCode',title:'查询代码',align:'center',width:70},
				{field:'queryName',title:'查询名',align:'center',width:70},
				{field:'queryType',title:'查询类型',width:50,
					formatter : function(val, rec) {return formatter(val,'1-全查询,2-子查询')}
				},
				{field:'queryState',title:'查询状态',width:50,
					formatter : function(val, rec) {return formatter(val,'0-停用,1-启用')}
				},
				{field:'queryDesc',title:'查询描述',align:'center',width:200},
				{field:'createrName',title:'创建人',align:'center',width:60},
				{field:'createTime',title:'创建时间',align:'center',width:100},
				{field:'updaterName',title:'更新人',align:'center',width:60},
				{field:'updateTime',title:'更新时间',align:'center',width:100},
			]],
		});
	}
	var lastAddSq;
	var nowOptTableAname;
	/** 子查询下拉框选择事件 **/
	function onSelectSq(rec){
		lastAddSq = rec;
		nowOptTableIndex = tableConfigDg.datagrid('getRows').length;
		nowOptTableAname = 't'+(nowOptTableIndex+1);
		tableConfigDg.datagrid('insertRow',getSubQueryInitConf(lastAddSq));
		$.post('query/queryQcs.do',{queryCode:lastAddSq.queryCode},function(data){
			for(var i = 0 ; i < data.length ; i ++){
				addColumnSq(data[i]);
			}
		});
	}
	
	function addSqAgain(){
		nowOptTableIndex = tableConfigDg.datagrid('getRows').length;
		nowOptTableAname = 't'+(nowOptTableIndex+1);
		tableConfigDg.datagrid('insertRow',getSubQueryInitConf(lastAddSq));
		$.post('query/queryQcs.do',{queryCode:lastAddSq.queryCode},function(data){
			for(var i = 0 ; i < data.length ; i ++){
				addColumnSq(data[i]);
			}
		});
	}
	
	/** 添加字段行 **/
	function addColumnSq(rec){
		nowOptClmIndex = columnConfigDg.datagrid('getRows').length;
		columnConfigDg.datagrid('insertRow',getClmInitConfSq(rec));
		nowOptClmIndex = -1;
	}
	
	/** 获取字段行数据 **/
	function getClmInitConfSq(rec){
		var data = {index: nowOptClmIndex,
			row:{
				tableAname:nowOptTableAname,
				cqCode:nowOptTableAname+'.'+rec.propCode,
				propCode:rec.propCode,
				columnName :rec.columnName,
				cqName:rec.cqName,
				cqSort:nowOptClmIndex+1,
				cqShow:rec.cqShow,
				cqExport:rec.cqExport,
				cqOption:rec.cqOption,
				cqOptSet:rec.cqOptSet
			}
		}
		return data;
	}
	
	/** 获取子查询初始化表数据的配置 **/
	function getSubQueryInitConf(rec){
		console.log(rec);
		var data = {index: nowOptTableIndex,
			row:{
				tableType:'2',
				tableCode:rec.queryCode,
				tableName:rec.queryName,
				tableSort :nowOptTableIndex+1,
				tableAname:'t'+(nowOptTableIndex+1),
				sqSql:rec.querySql
			}
		}
		return data;
	}
	function onSelectTable(rec){
		lastAddTable = rec;
		nowOptTableIndex = tableConfigDg.datagrid('getRows').length;
		tableConfigDg.datagrid('insertRow',getTableInitConf(lastAddTable));
	}
	function addAgain(){
		if(lastAddTable != null){
			nowOptTableIndex = tableConfigDg.datagrid('getRows').length;
			tableConfigDg.datagrid('insertRow',getTableInitConf(lastAddTable));
		}
	}
	function getTableInitConf(rec){
		var data = {index: nowOptTableIndex,
			row:{
				tableType:'1',
				tableCode:rec.columnTable,
				tableName:rec.tableName,
				tableSort :nowOptTableIndex+1,
				tableAname:'t'+(nowOptTableIndex+1)
			}
		}
		return data;
	}
	
	function createTableConfigDg(){
		tableConfigDg = $('#tableConfig').datagrid({
			fitColumns:true,//填充整行
			striped:true,//设置为true将交替显示行背景
			method:'post',
			singleSelect:true,
			rownumbers: true,//设置为true将显示行数。
			autoRowHeight:true,
			title:'表选项',
			columns:[[ //数据表格列配置对象
				{field:'tableType',title:'表类型',width:50,
					formatter : function(val, rec) {return formatter(val,'1-数据库表,2-子查询')}
				},
				{field:'tableCode',title:'表代码',align:'center',width:80},
				{field:'tableName',title:'表名称',align:'center',width:80},
				{field:'tableAname',title:'表别名',align:'center',width:30},
				{field:'joinCond',title:'左联接条件',align:'center',width:400},
				{field:'operate',title:'操作',align:'center',width:100,  
		             formatter:optFormatter,
		        }
			]],
		});
	}
	
	function optFormatter(value, row, index){
		return '<a class="cellButton" onclick="optRow('+index+');">配置</a>'+
		'<a class="cellButton" onclick="delRow('+index+');">删除</a>';
	}
	
	function optRow(index){
		nowOptTableIndex = index;
		tableConfigDg.datagrid('selectRow', index);
		nowOptTable = tableConfigDg.datagrid('getSelected');
		var title = nowOptTable.tableName+" "+nowOptTable.tableCode+" "+nowOptTable.tableAname;
		$('#tableConfigDlg').panel('setTitle',title);
		$('#tableConfigDlg').dialog('open');
		$('#tableConfigDlg').window('center');
		if(nowOptTable.tableType == '1'){
			$('#l_columns').combobox('reload','query/queryTcs.do?tableCode='+nowOptTable.tableCode);
		}
	}
	function delRow(index){
		var lastIndex = tableConfigDg.datagrid('getRows').length-1;
		if(lastIndex != index){
			showErr('只能从最后一行开始删除！');
		}else{
			delColumn(index);
			tableConfigDg.datagrid('deleteRow',index);
			showSucc('成功删除最后一行的表数据');
		}
	}
	function delColumn(index){
		tableConfigDg.datagrid('selectRow', index);
		nowOptTable = tableConfigDg.datagrid('getSelected');
		var sign = nowOptTable.tableAname + ".";
		if(columnConfigDg != null){
			var columns = columnConfigDg.datagrid('getRows');
			for(var i = 0 ; i < columns.length ; i ++){
				if(columns[i].cqCode.indexOf(sign)>=0){
					columns[i].cqCodeOld = columns[i].cqCode;
					columns[i].cqCode = '已删';
					columns[i].tableAname = '已删';
					columnConfigDg.datagrid('refreshRow',i);
				}
			}
		}
	}
	function setConfig(){
		updColumns($('#l_columns').combobox('getValues'));
		nowOptTable.joinCond = $('#l_joinCond').textbox('getValue');
		tableConfigDg.datagrid('refreshRow',nowOptTableIndex);
		$('#tableConfigDlg').dialog('close');
		$('#tc_form').form('clear');
		createSql();
	}
	function updColumns(ids){
		var idStr = ids + "";
		var columns = $('#l_columns').combobox('getData');
		var idArr = idStr.split(",");
		for(var i = 0 ; i < idArr.length ; i ++){
			for(var j = 0 ; j < columns.length ; j ++){
				if(idArr[i]==columns[j].columnId){
					addColumn(columns[j]);
					break;
				}
			}
		}
	}
	
	/** 添加字段行 **/
	function addColumn(rec){
		nowOptClmIndex = columnConfigDg.datagrid('getRows').length;
		columnConfigDg.datagrid('insertRow',getClmInitConf(rec));
		nowOptClmIndex = -1;
	}
	
	/** 获取字段行数据 **/
	function getClmInitConf(rec){
		var data = {index: nowOptClmIndex,
			row:{
				tableAname:nowOptTable.tableAname,
				cqCode:nowOptTable.tableAname+'.'+rec.columnCode,
				propCode:rec.propCode,
				columnName :rec.columnName,
				cqName:rec.columnName,
				cqSort:nowOptClmIndex+1,
				cqShow:'1',
				cqExport:'1',
				cqOption:'0'
			}
		}
		return data;
	}

	var yesOrNo = "0-否,1-是";
	var startOrStop = "0-停用,1-启用";
	
	/** 创建字段datagrid **/
	function createColumnConfigDg(){
		columnConfigDg = $('#columnConfig').datagrid({
			fitColumns:true,//填充整行
			striped:true,//设置为true将交替显示行背景
			method:'post',
			singleSelect:true,
			rownumbers: true,//设置为true将显示行数。
			autoRowHeight:true,
			title:'字段配置',
			columns:[[ //数据表格列配置对象
				{field:'tableAname',title:'所属表别名',align:'center',width:40},
				{field:'columnName',title:'字段原名',align:'center',width:60},
				{field:'cqCode',title:'字段查询码',align:'center',width:150,editor:'text'},
				{field:'propCode',title:'属性代码',align:'center',width:50,editor:'text'},
				{field:'cqName',title:'字段查询名',align:'center',width:50,editor:'text'},
				{field:'cqSort',title:'默认排序',align:'center',width:50,editor:'numberbox'},
				{field:'cqShow',title:'默认显示',align:'center',width:50,editor:getCbbEditor(yesOrNo),
					formatter : function(val, rec) {return formatter(val,yesOrNo)}
				},
				{field:'cqExport',title:'默认导出',align:'center',width:50,editor:getCbbEditor(yesOrNo),
					formatter : function(val, rec) {return formatter(val,yesOrNo)}
				},
				{field:'cqOption',title:'下拉框',align:'center',width:50,editor:getCbbEditor(yesOrNo),
					formatter : function(val, rec) {return formatter(val,yesOrNo)}
				},
				{field:'cqOptSet',title:'下拉框选项',align:'center',width:50,editor:'text'},
				{field:'operate',title:'操作',align:'center',width:60,
		             formatter:optClmFormatter,
		        }
			]],
			onClickRow:clickColumn
		});
	}
	function optClmFormatter(value, row, index){
		return '<a class="cellButton" onclick="delClmRow('+index+');">删除</a>';
	}
	
	/** 点击字段行进行编辑 **/
	function clickColumn(rowIndex,rowData){
		if(rowData.cqCode=='已删' && rowData.tableAname=='已删'){
			columnConfigDg.datagrid('endEdit', nowOptClmIndex);
			nowOptClmIndex = -1;
			return;
		}
		if (nowOptClmIndex != rowIndex){
			columnConfigDg.datagrid('endEdit', nowOptClmIndex);
			columnConfigDg.datagrid('beginEdit', rowIndex);
			nowOptClmIndex = rowIndex;
		}else{
			columnConfigDg.datagrid('endEdit', nowOptClmIndex);
			nowOptClmIndex = -1;
		}
		createSql();
	}
	/** 删除字段行 **/
	function delClmRow(index){
		columnConfigDg.datagrid('selectRow', index);
		nowOptClm = columnConfigDg.datagrid('getSelected');
		nowOptClm.cqCode='已删';
		nowOptClm.tableAname='已删';
		columnConfigDg.datagrid('refreshRow',index);
	}
	
	/** 生成sql语句 **/
	function createSql(){
		if(tableConfigDg == null || tableConfigDg.datagrid('getRows').length == 0){
			return;
		}
		var tables = tableConfigDg.datagrid('getRows');
		var columns = columnConfigDg.datagrid('getRows');
		var sql = "select ";
		for(var i = 0 ; i < columns.length ; i ++){
			sql = sql + ((tables.length==1) ? columns[i].cqCode.substr(3) : columns[i].cqCode) + " " + columns[i].propCode + ",";
		}
		sql = sql.substring(0,sql.length-1) + " from ";
		var tableCode;
		for(var i = 0 ; i < tables.length ; i ++){
			tableCode = (tables[i].tableType == '1') ? tables[i].tableCode : ("("+tables[i].sqSql+")");
			console.log(tableCode+tables.length);
			tableCode = tableCode + " " + ((tables.length==1) ? "" : tables[i].tableAname);
			console.log(tableCode+tables.length);
			if(tables[i].joinCond == ""){
				sql = sql + (i==0?"":",") + tableCode;
			}else{
				sql = sql + " left join " + tableCode + " on " + tables[i].joinCond;
			}			
		}
		l_querySql.textbox('setValue',sql);
	}
	
	/** 建立数组 **/
	function pushText(name,row,list,contentext){
		for(var ele in list){
			contentext.push({name : name+'.'+list[ele],value : row[list[ele]]});
		}
	}
	
	/**提交到后台 **/
	function confirmClick() {
		var contentext = [];
		var tables = tableConfigDg.datagrid('getRows');
		var columns = columnConfigDg.datagrid('getRows');
		if(tables != null && tables.length > 0){
			var columnProps = new Array("tableAname","columnName","cqCode","propCode","cqName","cqSort","cqShow","cqExport","cqOption","cqOptSet");
			for(var i = 0 ; i < columns.length ; i ++){
				pushText('cpList[' + i + ']',columns[i],columnProps,contentext);
			}
			var tableProps = new Array("tableType","tableCode","tableAname","joinCond");
			for(var i = 0 ; i < tables.length ; i ++){
				pushText('tpList[' + i + ']',tables[i],tableProps,contentext);				
			}
		}
		console.debug(contentext);
		jQuery.merge(contentext,J_edit_form.serializeArray());
		var formData = jQuery.param(contentext);
		$.post('query/op.do',formData,function(data){});
	}
	</script>
</body>
</html>