<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
	window.onload = function(){
		getChoice('findSex','2-女朋友,1-男朋友');
		getChoice('findAgeBgn','0-不限,18-18岁,19-19岁,20-20岁');
		getChoice('findAgeEnd','0-不限,18-18岁,19-19岁,20-20岁');
		getChoice('findArea','北京-北京,湖北-湖北,湖南-湖南,上海-上海');
		for(var i = 0; i < 9; i ++){
			pushMember('images/member.bmp','完美的昵称','25岁 | 165cm','初中及以下 | 湖北 武汉','home.jsp');
		}
		for(var i = 0; i < 30; i ++){
			pushWebUrl('北京交友网','frdCommon.jsp',i);
		}
		for(var i = 0; i < 10; i ++){
			pushFrdUrl('58同城网','frdCommon.jsp',i);
		}
		for(var i = 0; i < 10; i ++){
			pushPtnUrl('58同城网','frdCommon.jsp',i);
		}
	};
</script>