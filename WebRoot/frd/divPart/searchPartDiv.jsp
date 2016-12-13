<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style type="text/css">
.searchPart{
	width: 100%;
	height: 40px;
	background-color: white;
	margin-top: 8px;
	float: left;
}
.searchPart img{
	float: left;
	height: 25px;
	margin: 7px;
	
}
.searchPart form{
	float: left;
	margin-top: 8px;
}
.searchPart form select{
	margin-right: 10px;
}
.leftBlank10px{
	margin-left: 10px;
}
</style>
<div class="searchPart">
	<img src="images/search.png"/>
	<form action="" method="post">
		我想找：<select id="findSex"></select>
		年龄：<select id="findAgeBgn"></select>
		至<select class="leftBlank10px" id="findAgeEnd"></select>
		地区：<select id="findArea"></select>
		<input class="leftBlank10px" type="submit" value="搜索"/>
	</form>
</div>