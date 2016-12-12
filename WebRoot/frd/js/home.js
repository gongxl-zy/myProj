function $(id){
	return document.getElementById(id);
}
var registerLi = $("registerLi");//注册标签
var loginLi = $("loginLi");//登录标签
var registerDiv = $("registerDiv");//注册div
var loginDiv = $("loginDiv");//登录div
var showPart = $("showPart");//会员展示窗口
var webUrlsPart = $("webUrlsPart");//网站链接窗口
/**
 * 创建顶部导航栏
 * @param {Object} actSign
 * @param {Object} isLogin
 */
function getNavTop(){

}
/**
 * 切换登录和注册框
 * @param {Object} sign
 */
function nowtab(sign){
	if(sign == 1){
		registerLi.className="hover";
		loginLi.className="";
		registerDiv.className="login";
		loginDiv.className="hideDiv";
	}else{
		registerLi.className="";
		loginLi.className="hover";
		registerDiv.className="hideDiv";
		loginDiv.className="login";
	}
}

function getChoice(id,str){
	var obj=$(id);
	obj.length = 0;
	var arr1 = str.split(",");
	for(var i in arr1){
		var arr2 = arr1[i].split("-");
		addOption(obj,arr2[1],arr2[0])
	}
}
function addOption(obj,text,value){
	var op = document.createElement("option");
	op.value = value;
	op.text = text;
	obj.add(op);
}
function setCheck(name,value){
	var rs = document.getElementsByName("a1");
	for(var i =0;i<rs.length;i++){
        if(rs[i].value == value){
            rs[i].checked=true;
            break;
        }
    }
}
function pushMember(imgUrl,nickName,info1,info2,mbrUrl){
	var htmlCode = '<div class="showMember"><img src="'+imgUrl+'"/><div class="showInfo"><span>'+nickName+'</span>';
	htmlCode = htmlCode + '<span>'+info1+'</span><span>'+info2+'</span><a href="'+mbrUrl+'">去看看</a></div></div>';
	showPart.innerHTML = showPart.innerHTML + htmlCode;
}
function pushWebUrl(desc,webUrl,num){
	var htmlCode;
	if ((num+1) % 10 == 0){
		/*htmlCode = '<a href="'+webUrl+'">'+desc+'</a>';*/
		htmlCode = '<a href="'+webUrl+'"><span>'+desc+'</span></a>';
	}else{
		htmlCode = '<a class="rightBorder" href="'+webUrl+'"><span>'+desc+'</span></a>';
	}
	webUrlsPart.innerHTML = webUrlsPart.innerHTML + htmlCode;
}
