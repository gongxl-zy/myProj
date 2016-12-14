function $(id){
	return document.getElementById(id);
}
var registerTab = $("registerTab");//注册标签
var loginTab = $("loginTab");//登录标签
var register = $("register");//注册div
var login = $("login");//登录div
var showPart = $("showPart");//会员展示窗口
var webUrlsPart = $("webUrlsPart");//网站链接窗口
var frdUrlsPart = $("frdUrlsPart");//友情链接窗口
var ptnUrlsPart = $("ptnUrlsPart");//合作伙伴链接窗口
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
		registerTab.className="hover";
		loginTab.className="";
		register.className="login";
		login.className="hide";
	}else{
		registerTab.className="";
		loginTab.className="hover";
		register.className="hide";
		login.className="login";
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
	var rs = document.getElementsByName(name);
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
function pushFrdUrl(desc,frdUrl,num){
	var htmlCode = '<a href="'+frdUrl+'"><span>'+desc+'</span></a>';;
	frdUrlsPart.innerHTML = frdUrlsPart.innerHTML + htmlCode;
}
function pushPtnUrl(desc,frdUrl,num){
	var htmlCode = '<a href="'+frdUrl+'"><span>'+desc+'</span></a>';;
	ptnUrlsPart.innerHTML = ptnUrlsPart.innerHTML + htmlCode;
}
