<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商户功能管理</title>
<jsp:include page="../frame/header.jsp"></jsp:include>
</head>
<body>
	<div style="height:30px;padding-left:30px;padding-top:5px;">
		<span>选择角色:</span>
		<input id="J_roleId" name="roleId" class="easyui-combobox" url="role/queryList.do"
			data-options="valueField:'roleId',textField:'roleName',editable:false,panelHeight:'auto',onChange:changeRole" />
	</div>
	<div style="height:390px;width:300px;left:20px;position:absolute;">
		<div class="easyui-panel" title="未分配的资源" style="height:390px">
			<div class="div-left5 ztree" style="margin-left:50px;" id="notSelectedTree"></div>
		</div>
	</div>
	<div style="height:390px;width:110px;left:321px;position:absolute;">
		<div style="padding-top:100px;text-align: center;">
			<a href="#" class="form_button2"   id="J_button_confirm">分 配</a>
			<a href="#" class="form_button2"   id="J_button_cancel">取 消</a>
		</div>
	</div>
	<div style="height:400px;width:300px;left:432px;position:absolute;">
		<div class="easyui-panel" title="已分配的资源" style="height:390px">
			<div class="div-left5 ztree" style="margin-left:50px;" id="selectedTree"></div>
		</div>
	</div>
	<!-- 初始化组件 -->
	<script type="text/javascript">
	/* 视 图 */
	var selectedTree;
	var notSelectedTree;
	
	var J_roleId = $('#J_roleId');

	/*操作按钮*/
	var J_button_confirm = $('#J_button_confirm');
	var J_button_cancel = $('#J_button_cancel');

	var setting = {
		view : {
			showIcon : true,
			showLine : true
		},
		async : {
			enable : true,
		},
		check : {
			checked : "isChecked",
			enable : true,
			chkStyle : "checkbox",
			chkboxType : {
				"Y" : "ps",
				"N" : "s"
			}
		},
		data : {
			keep : {
				parent : true,
				leaf : true
			},
			simpleData : {
				enable : true,
				rootPId : null,
				idKey : 'menuId',
				pIdKey : 'upMenuId'
			},
			key : {
				name : 'menuName'
			}
		}
	};

	$(function() {
		J_button_confirm.click(confirmClick);
		J_button_cancel.click(cancelClick);
	});
	
	function changeRole(newValue, oldValue){
		if(newValue != oldValue)
			zTreeInit(newValue);
	}
	
	function zTreeInit(roleId){
		$.ajax({ 
			type: "POST",
			data:{roleId : roleId},
			url:'roleMenu/query.do',
			dataType : "json",
			success : function(data) {
				putData(data);
			}
		});
	}
	var LEFT = 0;
	var RIGHT = 1;
	var BOTH = 2;
	
	var shutDown=0
	var startUp=1
	
	function putData(data){
		var treeNodes_st=[],treeNodes_nst=[],tmp={};
		for(var i=0;i<data.length;i++){
			data[i].position=data[i].state;
		}
		setPosition(data);
		var menuIds=''
		for(var i=0;i<data.length;i++){
			if(data[i].state==LEFT && data[i].position==BOTH){
				if(menuIds=='')
					menuIds=data[i].menuId;
				else
					menuIds+='|'+data[i].menuId;
			}
			if(data[i].position==LEFT || data[i].position==BOTH)
				treeNodes_nst.push(data[i]);
			if(data[i].position==RIGHT || data[i].position==BOTH)
				treeNodes_st.push(data[i]);
		}
		if(menuIds != ''){
			var formData = {
				roleId : J_roleId.combobox('getValue'),
				menuId : menuIds,
				state : startUp,
			};
			$.ajax({ 
				type: "POST",
				data:formData,
				url:'roleMenu/config.do',
				dataType : "json",
				success : function(data) {}
			});
		}
		var zTreeOpenList = putOpenNodes(selectedTree);
		selectedTree = $.fn.zTree.init($("#selectedTree"), setting, treeNodes_st);
		rtnOpenTreeNodes(zTreeOpenList,selectedTree);
		
		zTreeOpenList = putOpenNodes(notSelectedTree);
		notSelectedTree = $.fn.zTree.init($("#notSelectedTree"), setting, treeNodes_nst);
		rtnOpenTreeNodes(zTreeOpenList,notSelectedTree);
		console.debug(notSelectedTree);
	}
	function setPosition(data){
		var opTime = 0;
		for(var i=0;i<data.length;i++){
			if(data[i].upMenuId != undefined){
				for(var j=0;j<data.length;j++){
					if(data[j].menuId == data[i].upMenuId){
						if(data[j].position != BOTH && data[j].position != data[i].position){
							data[j].position = BOTH;
							opTime++;
						}
						break;
					}
				}
			}
		}
		if(opTime > 0){
			setPosition(data);
		}
	}
	function putOpenNodes(zTreeObj){
		var arr=[];
		if(zTreeObj!=undefined){
			var list=zTreeObj.transformToArray(zTreeObj.getNodes());
			for(var i=0;i<list.length;i++){
				if(list[i].open){
					arr.push(list[i].menuId);
				}
			}
		}
		return arr;
	}
	function rtnOpenTreeNodes(zTreeOpenList,zTreeObj){
		if(zTreeOpenList.length>0){
			for(var i=0;i<zTreeOpenList.length;i++){
				var node = zTreeObj.getNodeByParam('menuId',zTreeOpenList[i]);
				if(node != undefined){
					zTreeObj.expandNode(node);
				}
			}
		}
	}
	
	/*确认操作至服务器*/
	function confirmClick() {
		var checkedNodes=notSelectedTree.getCheckedNodes(true);
		if(checkedNodes.length>0){ //判断是否有节点被选中
			postOp(checkedNodes,startUp);
		}
		return false;
	}

	/*取消操作返回信息列表*/
	function cancelClick() {
		var checkedNodes=selectedTree.getCheckedNodes(true);
		if(checkedNodes.length>0){ //判断是否有节点被选中
			postOp(checkedNodes,shutDown);
		}
		return false;
	}
	
	function postOp(checkedNodes,state){
		var menuIds=''
		if(!checkAuth('edit')){
			$.messager.alert("提示信息", "当前用户无权进行该操作");
			return false;
		}
		for(var i=0; i<checkedNodes.length; i++){
			if(menuIds=='')
				menuIds=checkedNodes[i].menuId;
			else
				menuIds+='|'+checkedNodes[i].menuId;
		}
		var roleId = J_roleId.combobox('getValue');
		if(menuIds!=''){
			var formData = {
				roleId : roleId,
				menuId : menuIds,
				state : state
			};
			$.ajax({
				type: "POST",
				data:formData,
				url:'roleMenu/config.do',
				dataType : "json",
				success : function(data) {
					if(data.flag==true){
						zTreeInit(roleId);
						slideMessager(data.obj);
					}else
						$.messager.alert("提示信息", data.obj);
				}
			});
		}
		return false;
	}
	</script>
</body>
</html>