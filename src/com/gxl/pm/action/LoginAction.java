package com.gxl.pm.action;

import gxl.mult.code.SecretPst;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxl.biz.MenuBiz;
import com.gxl.biz.UserBiz;
import com.gxl.common.BasePmAction;
import com.gxl.common.Cache;
import com.gxl.common.CheckUtils;
import com.gxl.common.Constants;
import com.gxl.common.MenuUtils;
import com.gxl.entity.PUser;
import com.gxl.pm.po.PmMenu;
import com.gxl.pm.po.PmUser;

/*@ParentPackage("platMng-default")*/
@Namespace("/pm")
public class LoginAction extends BasePmAction{
	private static final long serialVersionUID = 2275954075331627014L;
	@Autowired private UserBiz userBiz;
	@Autowired private MenuBiz menuBiz;
	
	PmUser user = new PmUser();
	
	public void setUser(PmUser user) {
		this.user = user;
	}

	@Override
	public Object getModel() {
		return user;//模型驱动
	}
	
	@Action(value = "init", results = {@Result(name = "success", location = "/pm/frame/login.jsp")})
	public String init(){
		return SUCCESS;//页面初始化
	}
	
	@Action(value = "index", results = {
			@Result(name = "success", location = "/pm/frame/mainframe.jsp"),
			@Result(name = "error", type="redirectAction", location = "init") })
	public String index(){//进入主页
		PUser u = (PUser) getSession().getAttribute(Constants.PM_USER);
		if(u == null){
			return ERROR;
		}
		System.out.println("获取菜单...");
		try {
			// 获取用户所有菜单
			List<PmMenu> list = Cache.gerMenusByRoleId(u.getRoleId());
			// 取菜单存到Session中
			this.getSession().setAttribute(Constants.PM_MENU, MenuUtils.getPmMenu(list));
			// 取权限存到页面
			this.getSession().setAttribute(Constants.PM_LIMIT, JSONObject.fromObject(MenuUtils.getLimit(list)).toString());
			System.out.println("获取菜单成功...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Action(value = "login", results = { 
			@Result(name = "success", type="redirectAction", location = "index"),
			@Result(name = "error", location = "/pm/frame/login.jsp") })
	public String login() {//登陆入口
		PUser u = null;
		//屏蔽GET
		if(getRequest().getMethod().equals("GET")){
			return LOGIN;
		}
		try {
			//userBiz.query();
			String rtn = checkUser(u);
			return rtn.equals("")?SUCCESS:rtn;
		} catch (Exception e) {
			e.printStackTrace();
			return msgErrReturn("login.err");
		}
	}

	@Action(value = "changePwd")
	public void changePwd(){
		try {
			PUser u = (PUser) getSession().getAttribute(Constants.PM_USER);
			String old = new String(SecretPst.encrypt(user.getOldPwd()));
			if(u.getUserPwd().equals(old)){
				u.setUserPwd(new String(SecretPst.encrypt(user.getNewPwd())));
				userBiz.txOpUser(new PmUser(u),Constants.OP_U,u.getUserId());
				// 存入到Session中
				getSession().setAttribute(Constants.PM_USER, u);
				
				returnResultJson("密码修改成功！",true);
			}else{
				returnResultJson("密码修改失败，原密码不正确！",false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "logout", results = {@Result(name = "success", location = "/pm/frame/login.jsp")})
	public String logout() {
		getSession().invalidate();
		return SUCCESS;
	}
	
	/**
	 * 验证用户输入的登陆信息
	 * @param u
	 * @return
	 * @throws Exception
	 */
	private String checkUser(PUser u) throws Exception{
		long start = System.currentTimeMillis();
		boolean bGetUser = false;
		boolean bRightPwd = false;
		
		if (CheckUtils.isNull(user.getUserNo())) {
			return msgErrReturn("login.userNoNull");//用户号不可为空
		}
		if (CheckUtils.isNull(user.getUserPwd())) {
			return msgErrReturn("login.userPassNull");//密码不可为空
		}
		
		String userPass = new String(SecretPst.encrypt(user.getUserPwd()));//密码加密
		for(int i = 2 ; i <= 4 ; i ++){
			u = userBiz.getUserInfo(i, user.getUserNo());//分别根据账号，手机号，邮箱号从数据库查询用户
			if(u == null){
				continue;
			}else{
				bGetUser = true;
				if (!u.getUserPwd().equals(userPass)) {
					continue;
				}else{
					bRightPwd = true;
					break;
				}
			}
		}
		if(!bGetUser){
			return msgErrReturn("login.userNoFund");//用户号不存在
		}
		if (!bRightPwd) {
			return msgErrReturn("login.userPassErr");//用户密码错误
		}
		// 存入到Session中
		getSession().setAttribute(Constants.PM_USER, u);
		this.setMsg("login.success");
		long end = System.currentTimeMillis();
		System.out.println("登录耗时："+(end-start)+"ms");
		return "";
	}
}
