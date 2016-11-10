<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page isELIgnored="false"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的平台</title>
<jsp:include page="./header.jsp"></jsp:include>
<script src="../js/menuTree.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="../css/frame/pmFrame.css" /> 
<style type="">
</style> 
</head>

<body class="easyui-layout">
    <!-- 布局顶部 -->
    <div data-options="region:'north'" class="frameNorth">
	    <div class="north_logo"></div>
	    <div class="north_sys">
	        <a id="J_change_pwd" href="javascript:void(0);" rel="<c:url value="frame/changePwd.jsp"/>" 
	        	title="密码修改" class="north_changePwd"><img src="images/changePwd.png" style="max-width:54px;max-height:54px;" /></a>
	        <a id="J_change_pwd" href="logout.do" title="退出"><img src="images/20140728-4.png" /></a>
	    </div>
	</div>
	
	<!-- 布局左边 -->
    <div data-options="region:'west'" title="菜单导航" class="frameLeft">      					
	    <div id="menu" style="z-index: 99999; " >
            <!-- 动态加载菜单 -->
            <c:forEach items="${sessionScope.pmMenu}" var="pmMenu">
            <div class="topFolder" onclick="MenuImageOnClickEvent(this,'V','topFolder','top');" id="${pmMenu.menuId}"><img src="images/icon4.jpg" /><a>${pmMenu.menuName}</a></div>
                <div class="sub" >
                    <c:forEach items="${pmMenu.subMenus}" var="subRes_1">               
                        <c:if test="${fn:length(subRes_1.subMenus)!=0}">
                        <div class="subFolder" onclick="MenuImageOnClickEvent(this,'V','subFolder','folder');" id="${subRes_1.menuId}"><a>${subRes_1.menuName}</a></div>
                            <div class="sub" >
                                <c:forEach items="${subRes_1.subMenus}" var="subRes_2">
                                <div class="subItem J_aClass"  id="${subRes_2.menuId}"><a href="javascript:void(0);" rel="${subRes_2.menuLink}">${subRes_2.menuName}</a></div>
                                </c:forEach> 
                            </div>
                        </c:if>
                        <c:if test="${subRes_1.menuLink!=''}">
                            <div class="subFolder J_aClass"  id="${subRes_1.menuId}"><a href="javascript:void(0);" rel="${subRes_1.menuLink}">${subRes_1.menuName}</a></div>
                        </c:if>                                                   
                    </c:forEach>
                </div>    
            </c:forEach>
            <!-- 动态加载菜单 -->	                    									                										 			  																			
		</div>										
    </div>  
    
    <!--布局中间  -->
    <div data-options="region:'center',fit:false,border:true">  
		<div id="index_tabs" style="overflow: hidden;">		
		        		  		   
		</div>  
    </div>  
    
    <!-- 布局底部 -->
    <div data-options="region:'south',border:true" class="frameBottom" >
        <div class="bottom_right">
	    	用户：<span id="J_user" >${sessionScope.pmUser.userName}[${sessionScope.pmUser.userNo}]</span>
	    	<div style="margin-left:50px;float:right" id="sysTime"></div>
	    </div>
	</div>
	
	<!-- 工具栏 -->
   	<div id="tab-tools" style="right: 25px; height: 25px;">
		<a href="javascript:void(0)" class="easyui-menubutton" menu="#mm" data-options="plain:true,iconCls:'icon-add'" ></a>
	</div>	
	<div id="mm" class="easyui-menu" style="width:150px;" class="easyui-menu" data-options="onClick:menuHandler">
		<div data-options="name:'delete_current'"  iconCls="icon-undo">关闭当前页</div>
		<div data-options="name:'delete_other'"  iconCls="icon-redo">关闭其它页</div>
		<div data-options="name:'delete_all'" > 关闭所有页</div>
		<div data-options="name:'refresh_current'" > 刷新当前页</div>
		<div class="menu-sep"></div>
	</div>

	<script type="text/javascript" >
	var index_layout;
	var index_tabs;
	var J_change_pwd=$("#J_change_pwd");
	var isAjaxLoginDoneRefreshTab=false;//登录成功后是否刷新当前tab
	
	var tmp_addTab=-1;//添加的标签索引
	
	var sysFunctionData = ${sessionScope.pmLimit};
	
	function realSysTime(){
		var now=new Date(); //创建Date对象
		var year=now.getFullYear(); //获取年份
		var month=now.getMonth(); //获取月份
		var date=now.getDate(); //获取日期
		var day=now.getDay(); //获取星期
		var hour=now.getHours(); //获取小时
		var hourStr = hour<10?("0"+hour):(""+hour);
		var minu=now.getMinutes(); //获取分钟
		var minuStr = minu<10?("0"+minu):(""+minu);
		var sec=now.getSeconds(); //获取秒钟
		var secStr = sec<10?("0"+sec):(""+sec);
		month=month+1;
		var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
		var week=arr_week[day]; //获取中文的星期
		var time="时间："+year+"年"+month+"月"+date+"日 "+week+" "+hourStr+":"+minuStr+":"+secStr; //组合系统时间
		document.getElementById("sysTime").innerHTML=time; //显示系统时间
	}
	
	$(document).ready(function(){
		aClassClick(); 
		window.setInterval("realSysTime()",1000); //实时获取并显示系统时间
		J_change_pwd.click(changePwd);
		
		//index_layout初始化
		index_layout = $('#index_layout').layout({
			fit : true
		});
		index_layout.layout('resize');
				
		//tab页面初始化
        /* index_tabs = $('#index_tabs').tabs({
			fit : true,
			border : false  
		}); */ 
		
  	   //tab页面初始化
   	   index_tabs = $('#index_tabs').tabs({
   			fit : true,
   			border : false,
   			tools:'#tab-tools',
   			onAdd:function(title,index){
   				tmp_addTab==index;//添加时不刷新
   				$('#mm').menu('appendItem', {
   					name: title,
   					text: title,
   					onclick: function(){
   						$('#index_tabs').tabs('select',title);
   					}
   				});
   			},
   			onClose:function(title,index){
   				var item = $('#mm').menu('findItem', title);
   				$('#mm').menu('removeItem', item.target);
   			},
   			onSelect:function(title,index){
   				if(tmp_addTab!=title){//排序添加标签是触发的选中事件
   					var dg=index_tabs.tabs('getSelected').children().get(0).contentWindow.dataGridTable;
   					if(dg){
   						dg.datagrid('reload');
   						console.info('刷新'+title+'的列表数据成功');
   					}
   				}
   			}
   		});
	});

   //事件绑定
	function aClassClick(){ 
		$('#menu').delegate(".J_aClass",'click',function(){
        	var _aObj=$(this).find("a");
        	var _title=_aObj.text();
        	var _url=_aObj.attr("rel");
		//拼位置字符串
		var str='';
		var p1=$(this).parent().prev();
		var moduleName=p1[0].id;
		str=p1.children('a').text();
		if(p1.hasClass('subFolder')){
			str=p1.parent().prev().children('a').text()+'>>'+str;
		}
		str = str + ' >> ' + _title;
		var programName=this.id;
        	//console.info(_title);
        	//console.info(_url);
        	//alert(_url);
        	addTab({
				url : _url,
				title : _title
			},str,moduleName,programName);
		})
		
	}
     /**
      *package:/layout/west.jsp
      *function: addTab
      *params: params:参数
      *description:  点击树节点菜单生成tab页
      *author:tianbin 
      *Date:2013年07月16日 09:38:25
      *update:2015/7/18
      **/ 
     function addTab(params,str,moduleName,programName){
    	  var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:100%;" '+(str?('onload="doneTab(this,\''+str+'\',\''+moduleName+'\',\''+programName+'\')"'):'')+'></iframe>';
		  var t = $('#index_tabs');
          var opts={
             title:params.title,   
             content:iframe,   
             closable:true,
             iconCls : params.iconCls,
             fit:true
          };
	        if (t.tabs('exists', opts.title)) {
				t.tabs('select', opts.title);
				parent.$.messager.progress('close');
			} else {
				t.tabs('add', opts);
				var ec=t.tabs('getSelected').children()[0].contentWindow;
				ec.moduleName=moduleName;
				ec.programName=programName;
			}
      }	
	function doneTab(e,str,moduleName,programName){
		fq=e.contentWindow.jQuery;
		e.contentWindow.moduleName=moduleName;
		e.contentWindow.programName=programName;
		if(fq)
			fq('body').prepend('<div class="path_div">您的位置：首页 >> '+str+'</div>');
	}
	  
	  /**
	   *密码修改
	   **/
	  function changePwd(){
		  var _title="密码修改";
      	  var _url=J_change_pwd.attr("rel");
		  //console.info(_title);
      	  //console.info(_url);
      	  
      	 addTab({
			url : _url,
			title : _title
		 });
	  }
	  
	  function menuHandler(item){
	    	var name = item.name;
 			var tab = $('#index_tabs').tabs('getSelected');
	    	if(name == 'delete_current'){
	 			if (tab){
	 				var index = $('#index_tabs').tabs('getTabIndex', tab);
	 				$('#index_tabs').tabs('close', index);
	 			}
	    	 }else if(name =='delete_other'){
	    		delother();
	    	 }else if(name == 'delete_all'){
	    	 	delall();
	    	 }else if(name == 'refresh_current'){
	 			if (tab){
	 				var ec=tab.children()[0].contentWindow;
	 				var moduleName=ec.moduleName;
	 				var programName=ec.programName;
	 				ec=tab.panel('open').panel('refresh').children()[0].contentWindow;
	 				ec.moduleName=moduleName;
	 				ec.programName=programName;
	 			}	    	 	
	    	 }
		 }
	     
	     function delother(){
	    	 var tabSel = index_tabs.tabs('getSelected');
	    	 var indexSel = index_tabs.tabs('getTabIndex', tabSel);
	    	 
	    	 var tabs = index_tabs.tabs('tabs');
	    	 var len = tabs.length;
	    	 for(var i=len-1;i>=0;i--){
	    		if(i!=indexSel){
				 	index_tabs.tabs('close', i);
	    		} 
	 		 }
	     }
	     
	     function delall(){
	    	 var tabs = index_tabs.tabs('tabs');
	    	 var len = tabs.length;
	    	 for(var i=len-1;i>=0;i--){
					index_tabs.tabs('close', i);
	 		 }
	     }
	  	
</script>

<script type="text/javascript" >
   //TODO其它逻辑处理
</script>

</body>
</html>
