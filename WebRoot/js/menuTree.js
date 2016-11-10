
/**
 * 三级菜单树
 * Author : 王帅
 * 2013/01/15
 * update 2015/9/8 hk 事件监听替换为事件委派
 */
function MenuImageOnClickEvent(obj,a,clazz,b){
			var parentobj = $(obj).parent();
			if(clazz == "subItem"){
				$(obj).siblings(".subItem").removeClass("open");
				$(obj).addClass("open");
				
			}else{
				if(!$(obj).hasClass("open")){
					$(obj).siblings(".sub").slideUp(100);
					$(obj).siblings(".topFolder").removeClass("open");
					$(obj).next(".sub").slideDown(100);
					$(obj).addClass("open");
				}else{
					$(obj).next(".sub").slideUp(100);
					$(obj).removeClass("open");
				}
			}
		}
		$(document).ready(function(){
			$('#menu').delegate(".topFolder,.subFolder,.subItem","mouseover",function(){
				if(!$(this).hasClass("open")){
					$(this).stop();
					$(this).fadeTo("fast",0.33);
					$(this).addClass("over");
					$(this).fadeTo("fast",1);
				}
			});
			$('#menu').delegate(".topFolder,.subFolder,.subItem","mouseout",function(){
				$(this).removeClass("over");
			});
			$(".topFolder,.subFolder,.subItem").each(function(){
				var text = $(this).text();
				if(text.length > 16){
					$(this).text(text.substring(0, 16) + '...');
				}
			});
		});
