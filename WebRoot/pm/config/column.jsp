<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>配置管理</title>
<jsp:include page="../frame/header.jsp"></jsp:include>
</head>
<body>
	<!-----------------------------表格----------------------------->
	<div id="listPanel" class="easyui-panel" border="false">
		<div style="width:96%;margin:10px auto; ">
			<div id="dataGrid"  style="height:380px" > </div>
		</div>
	</div>
	<!-- 查询条件 -->
	<div id="tb" style="padding:5px;height:auto;border:none;background:none;">
		<a class="easyui-linkbutton" onclick="refresh();">刷新</a>
	</div>
	<!-- 初始化组件 -->
	<script type="text/javascript">
	/*table表单 */
	var dataGridTable;

	$(function(){
		dataGridInit();
	});
	
	function refresh(){
		$.post('column/op.do',{operSign:'refresh'},function(data){});	
	}
	function dataGridInit(){
		dataGridTable=$('#dataGrid').datagrid({//实例化table表单
			url:'column/query.do',
			pagination: true,//设置true将在数据表格底部显示分页工具栏。
			/* collapsible:true,  //折叠按钮 */
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
				{field:'columnCode',title:'字段代码',width:100},
				{field:'columnName',title:'字段名',width:120},
				{field:'propCode',title:'属性代码',width:120},
				{field:'propState',title:'属性代码状态',width:100,
					formatter : function(val, rec) {return formatter(val,'0-未生成,1-已生成')}
				},
				{field:'columnTable',title:'所在表代码',width:140},
				{field:'tableName',title:'所在表名称',width:140},
				{field:'columnSort',title:'字段排序',width:100},
				{field:'columnType',title:'字段类型',width:100}
			]],
		});
		var pager = dataGridTable.datagrid('getPager'); // get the pager of datagrid
		pager.pagination({
			buttons:toolbarOne()
		});
	}
	</script>
</body>
</html>
