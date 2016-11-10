package com.gxl.pm.intcpt;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.gxl.common.Constants;
import com.gxl.pm.po.PmUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class pmAccsIntcpt extends AbstractInterceptor {

	private static final long serialVersionUID = 5741720237584871680L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		//System.out.println(request.getHeader("Accept").indexOf("application/json")==0);//请求json时
		if ((checkURI(request.getRequestURI()))&&(!isAuthenticated(request))) {
			System.out.println("未通过登录拦截器");
			if(request.getHeader("Accept").indexOf("application/json")!=0){//非请求json时
				if(request.getRequestURI().endsWith("index.action"))return "login";
				return "needLogin";
			}
			PrintWriter pw = null;
			HttpServletResponse response=ServletActionContext.getResponse();
			try {
				pw = response.getWriter();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("needLogin", Boolean.valueOf(true));
				map.put("total", 0);//保证页面表格列表不报错
				map.put("rows", new int[]{});//保证页面表格列表不报错
				map.put("flag", Boolean.valueOf(false));
				JSONObject resultmessage = JSONObject.fromObject(map);
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				pw.write(resultmessage.toString());
			} catch (Exception e) {
				pw.write("系统异常，请联系管理员");
			} finally {
				pw.flush();
				pw.close();
			}
			return null;
		}
		return invocation.invoke();
	}
	/**
	 * 检查不是login页面
	 * @param path
	 * @return
	 */
	private boolean checkURI(String path) {
		if ((path.endsWith("login.action"))||(path.endsWith("ajaxLogin.action")) || (path.endsWith("login.jsp"))) {
			return false;
		}
		return (path.endsWith(".action")) || (path.endsWith(".jsp"));
	}

	/**
	 * 检查session有user
	 * @param request
	 * @return
	 */
	private boolean isAuthenticated(HttpServletRequest request) {
		HttpSession session = request.getSession();
		PmUser user = (PmUser) session.getAttribute(Constants.PM_USER);
		return user != null;
	}
}