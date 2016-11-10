/**
 * 交易页面公共
 * 
 **/
/**
 * 监听页面所有的ajax
 * 用于处理：需要重新登录情况
 */
$( document ).ajaxSuccess(function( event, xhr, settings ){
	console.debug('[请求地址]',settings.url,'[响应数据类型]',settings.dataType);
	if(settings.dataType=='json'){
		var data=$.parseJSON(xhr.responseText);
		if(data.needLogin){
			showAjaxLogin();
		}
		if(data.noLimit){
			$.messager.alert("提示信息",data.msg);
		}
	}
});
/**
 * 当接收不到programName时尝试主动提取
 */
if(!window.programName){
	window.programName=location.href.substr($('base').attr('href').length).replace(/\/.*?$/,'');
}

/**
 * 工具栏按钮,所用按钮都会调用同一个op<ProgramName>(功能参数)
 * @param moduleNamemo 模块名(实际对应字段为编号)
 * @param programName 程序名(实际对应字段为编号)
 */
function toolbarOne() {
	//return toolbarAdd();
	var frametoolbar = new Array();
	var sysFunctionData = top.sysFunctionData;//取功能数据
	if (programName) {
		var modFuncList = sysFunctionData[programName];
		if (modFuncList == undefined) {
			return;
		}
		$.each(modFuncList, function(i, tm) {
			frametoolbar.push({
				text : tm.menuName,
				iconCls : tm.menuIcon,
				handler : function(){
					/**
					 * 保持驼峰命名方式
					 */
					eval('runFunctionOne(oper,"'+tm.menuLink+'","'+tm.menuName+'")');
				}
			}, '-');
		});
	}
	return frametoolbar;
}

/*function toolbarAdd(){
	var frametoolbar = new Array();
	frametoolbar.push({
		text : '添加',
		iconCls : '',
		handler : function(){
			eval('runFunctionOne(opFunction,"add","添加")');
		}
	}, '-');
	return frametoolbar;
}*/


/**
 * 检查权限，返回true or false
 * @param moduleNamemo 模块名(实际对应字段为编号)
 * @param programName 程序名(实际对应字段为编号)
 * @param funcCode 功能码
 */
function checkAuth(funcCode){
	var sysFunctionData = top.sysFunctionData;//取功能数据
	if (programName) {
		var modFuncList = sysFunctionData[programName];
		if (modFuncList == undefined) {
			return false;
		}
		for(var i=0;i<modFuncList.length;i++){
			if(modFuncList[i].funcCode==funcCode){
				return true;
			}
		}
		return false;
	}
	return false;
}

/**
 * 调用指定函数
 * @param funcName 函数
 */
function runFunction(funcName){
	if(isFunction(funcName)){
		funcName();
	}
	else{
		$.messager.alert('错误', '未找到对应的JS函数：'+funcName+',请联系管理员!', 'icon-error');
	}
}
/**
 * 调用指定函数,并附带一个参数
 * @param funcName 函数
 */
function runFunctionOne(funcName,code,codeName){
	if(isFunction(funcName)){
		funcName(code,codeName);
	}
	else{
		$.messager.alert('错误', '未找到对应的JS函数：'+funcName+',请联系管理员!', 'icon-error');
	}
}

/**
 * 判断是否存在该函数
 * @param  函数名称
 * @returns true/false
 */
function isFunction(funcName){
	if(funcName && typeof(funcName)==='function'){
		return true;
	}
	else{
		return false;
	}
}

/**
 * 从frame生成tab页
 * @param params
 */
function frameAddTab(params){
	var topAddTab=top.addTab;
	if(isFunction(topAddTab)){
		topAddTab(params);
	}else{
		$.messager.alert('错误', '必须访问主框架才能继续后面操作!', 'icon-error');
	}
}
/**
 * 从frame生成tab页
 * @param params
 */
function frameAddOrUpdateTab(params){
	var topAddTab=top.addTab;
	if(isFunction(topAddTab)){
		var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:100%;" ></iframe>';
		  var t = top.$('#index_tabs');
        var opts={
           title:params.title,   
           content:iframe,   
           closable:true,
           iconCls : params.iconCls,
           fit:true
        };
	        if (t.tabs('exists', opts.title)) {
				t.tabs('close', opts.title);
				t.tabs('add', opts);
				top.$.messager.progress('close');
			} else {
				t.tabs('add', opts);
			}
	}else{
		$.messager.alert('错误', '必须访问主框架才能继续后面操作!', 'icon-error');
	}
}

function slideMessager(msg){
	$.messager.show({
		title:'提示',
		msg:msg,
		timeout:3000,
		showType:'slide'
	});
}
function showAjaxLogin(){
	top.J_ajax_login.dialog('open');
}
/**
 * 发送POST请求到url地址
 * @param url 链接
 * @param formData 数据
 */
function postOpAjax(url,formData){
	$.ajax({
		type: "POST",
		data:formData ,
		url:url,
		dataType:"json",
		success: function(data){
			if(data.flag==true){
				searchFun();//刷新dategrid
				cancelClick();//返回dategrid清单
				slideMessager(data.msg?data.msg:data.obj);
			}else if(!data.needLogin)
				$.messager.alert("提示信息",data.msg?data.msg:data.obj);
		},
		error:function(){
			$.messager.alert("警告","无法连接服务器或者服务器出现错误，请联系系统管理员");
		}
	});
}
/**
 * 导出公共
 */
function postOpExport(){
	var divExport=$('#divExport');
	if(divExport.length==0){
		divExport=$('<div id="divExport"><p style="margin: 10px;text-align: center;">起始日期：<input id="de_startDate"/></p><p style="margin: 10px;text-align: center;">结束日期：<input id="de_endDate"/></p><p style=" display: none;"><iframe id="de_iframe"></iframe></p></div>');
		divExport.appendTo('body');
		$('#de_startDate').datebox();
		$('#de_endDate').datebox();
		$('#de_progressbar').progressbar();
		divExport.dialog({
		    title: '选择导出日期范围',
		    width: 300,
		    height: 150,
		    buttons:[{
		    	text:'导出',
		    	handler:function(){
		    		$('#de_iframe').get(0).src=programName+'/op.action'+"?startDate="+$('#de_startDate').datebox('getValue')+"&endDate="+$('#de_endDate').datebox('getValue')+"&operSign=export";
		    	}
		    },{
		    	text:'取消',
		    	handler:function(){divExport.dialog('close');}
		    }]
		});
	}else{
		divExport.dialog('open');
	}
}