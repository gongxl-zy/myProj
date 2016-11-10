<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
	String managePath = basePath+"/pm";
%>
<base href="<%=managePath+"/"%>">
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="renderer" content="webkit"> 
<meta name="description" content="gxl" />
<meta content="我的平台" name="keywords" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<!-- jQuery引入 -->
<script type="text/javascript" src="../js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="../js/jquery/jquery.dialogBox.js"></script>
<!-- easyUI引入 -->
<script type="text/javascript" src="../js/jquery/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/jquery/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/jquery/zTree/jquery.ztree.all-3.5.min.js" ></script>
<!-- 公共js引入 -->
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/pmCommon.js"></script>
<!-- css引入 -->
<link rel="stylesheet" type="text/css" href="../css/comm/pmCommon.css" />
<link rel="stylesheet" type="text/css" href="../css/comm/jquery.dialogbox.css" />

<link rel="stylesheet" type="text/css" href="../js/jquery/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="../js/jquery/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="css/zTree/zTreeStyle/zTreeStyle.css"/>
<link rel="stylesheet" type="text/css" href="css/form.css" />
<body>
	<!-- 公共弹窗 -->
	<div id="J_dialog_box"></div>
	<script type="text/javascript">
	function checkAuth(sign){
		return true;
	}
	</script>
</body>


