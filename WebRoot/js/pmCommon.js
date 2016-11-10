/**
 * 生成dategrid表头
 */
function getDategridColumns(data){
	/*var columns = [[ //数据表格列配置对象
		{field:'ck', checkbox:true},
		{field:'menuId',title:'menuId',hidden:true},
		{field:'menuNo',title:'菜单编号',align:'center',width:80,
	    	formatter : function(val,rec) {return cellTip(val,rec.menuDesc)}
		},
		{field:'menuName',title:'菜单名',align:'center',width:100,
	    	formatter : function(val,rec) {return cellTip(val,rec.menuDesc)}
		},
		{field:'menuType',title:'菜单类型',align:'center',width:60,
			formatter : function(val, rec) {return formatter(val,'1-模块,2-程序,3-功能')}
		},
		{field:'menuProp',title:'菜单性质',align:'center',width:60,
			formatter : function(val, rec) {return formatter(val,'1-默认,2-通用,3-平台,4-开发')}
		},
		{field:'menuSort',title:'菜单排序',align:'center',width:60},
		{field:'prtMenuName',title:'父菜单',align:'center',width:80},
		{field:'menuIcon',title:'菜单图标',align:'center',width:100},
		{field:'menuLink',title:'菜单链接',align:'center',width:110},
		{field:'createrName',title:'创建人',align:'center',width:80},
		{field:'createTime',title:'创建时间',align:'center',width:120},
		{field:'updaterName',title:'更新人',align:'center',width:80},
		{field:'updateTime',title:'更新时间',align:'center',width:120},
	]];*/
	var columns = [[]];
	columns[0].push({field:'ck', checkbox:true});
	columns[0].push({field:'menuNo',title:'菜单编号',align:'center',width:80,
    	formatter : function(val,rec) {return cellTip(val,rec.menuDesc)}
	});
	console.debug(columns);
	return columns;
}

/**
 * 例：{field:'state',title:'状态',width:120,
 *		  formatter : function(val, rec) {return formatter(val,'0-停用,1-启用')}
 *	  },
 * @param val,list
 */
function formatter(val,list){
	var unit = list.split(',');
	for ( var i = 0; i < unit.length; i++) {			    
		var one = unit[i].split('-');
		if(val == one[0])
			return one[1];
	}
}

function getArray(list){
	var arr = [];
	var unit = list.split(',');
	for ( var i = 0; i < unit.length; i++) {
		var one = unit[i].split('-');
		arr.push({id:one[0],val:one[1]});
	}
	return arr;
}

/**
 * dategrid-columns-editor【获取combobox配置】
 * @param list
 * @returns {___anonymous2051_2222}
 */
function getCbbEditor(list){
	var editor = {  
		type:'combobox',  
		options:{
			required:true,  
			editable:false,
			panelHeight:'auto',
			data:getArray(list),  
			valueField:'id',  
			textField:'val'
		}
	}
	console.log(editor);
	return editor;
}

function getNoCheckTreeSets(id,pId,name,callback){
	var setting = {
		view : { showIcon : true, showLine : true },
		async : { enable : true },
		check : {
			enable : false,
			chkStyle : "checkbox",
			chkboxType: { "Y": "ps", "N": "s" }
		},
		data : {
			keep : {parent : true, leaf : true },
			simpleData : {
				enable : true,
				rootPId : null,
				idKey : id,
				pIdKey : pId
			},
			key : { name : name }
		},
		callback : callback
	};
	return setting;
}

function showErr(content){
	$("#J_dialog_box").dialogBox({
		type: 'error',
		hasClose: true,
		hasMask: true,
		effect: 'flip-horizontal',
		title: '错误',
		content: content
	});
}
function showSucc(content){
	$("#J_dialog_box").dialogBox({
		type: 'correct',
		autoHide: true,
		time: 1600,
		effect: 'flip-horizontal',
		title: '成功',
		content: content
	});
}

/**
 * datagrid单元格显示提示语
 * @param val
 * @param tip
 * @returns
 */
function cellTip(val,tip){
	if(tip != null && tip != "")
		return '<span href="#" title='+tip+' class="easyui-tooltip">' + val + '</span>';
	else 
		return val;
	
}

function getEditor(sign){
	var rtnEditor;
	switch(sign){
	case 1:
		
		break;
	}
	return rtnEditor;
}