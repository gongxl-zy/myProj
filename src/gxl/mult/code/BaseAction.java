package gxl.mult.code;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	private static final long serialVersionUID = -5999690958076532001L;
	
	private String msg;//字符串消息
	private Integer page;//页数
	private Integer rows;//最大行数
	
	/**
	 * 返回json到页面
	 * @param obj 对象
	 * @param msg 字符串消息
	 * @param flag 是否成功
	 */
	protected void returnResultJson(Object obj, boolean flag) {
		returnResultJson(obj,null,flag);
	}
	protected void returnResultJson(boolean flag , String msg) {
		returnResultJson(null,msg,flag);
	}
	protected void returnResultJson(Object obj, String msg, boolean flag) {
		PrintWriter pw = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("obj", obj);
			map.put("msg", msg);
			map.put("flag", Boolean.valueOf(flag));
			JSONObject resultmessage = JSONObject.fromObject(map);
			getResponse().setCharacterEncoding("UTF-8");
			getResponse().setContentType("application/json");
			getResponse().setHeader("Cache-Control", "no-cache");
			pw = getResponse().getWriter();
			pw.write(resultmessage.toString());
		} catch (Exception e) {
			e.printStackTrace();
			pw.write("系统异常，请联系管理员");
		} finally {
			pw.flush();
			pw.close();
		}
	}
	
	@SuppressWarnings("rawtypes")
	protected void returnResultJsonTree(List list) {
		PrintWriter pw = null;
		try {
			JSONArray resultmessage = JSONArray.fromObject(list);
			getResponse().setCharacterEncoding("UTF-8");
			getResponse().setContentType("application/json");
			getResponse().setHeader("Cache-Control", "no-cache");
			pw = getResponse().getWriter();
			System.out.println(resultmessage.toString());
			pw.write(resultmessage.toString());
		} catch (Exception e) {
			e.printStackTrace();
			pw.write("系统异常，请联系管理员");
		} finally {
			pw.flush();
			pw.close();
		}
	}
	
	/**
	 * 返回easyUi json到页面
	 * @param list 列表
	 * @param num 数目
	 * @param datePattern Date类型数据返回格式
	 */

	protected void returnEasyUIResultJson(Object list, Long num) {
		returnEasyUIResultJson(list,num,"yyyy-MM-dd HH:mm:ss");
	}
	protected void returnEasyUIResultJson(Object list, Long num,String datePattern) {
		PrintWriter pw = null;
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor(datePattern));
			//jsonConfig.setJsonPropertyFilter(new JsonPropertyFilter());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", list);
			map.put("total", TypeCast.longToInt(num));
			JSONObject resultmessage= JSONObject.fromObject(map, jsonConfig);
			getResponse().setCharacterEncoding("UTF-8");
			getResponse().setContentType("application/json");
			getResponse().setHeader("Cache-Control", "no-cache");
			pw = getResponse().getWriter();
			pw.write(resultmessage.toString());
			System.out.println(resultmessage.toString());
		} catch (Exception e) {
			e.printStackTrace();
			pw.write("系统异常，请联系管理员");
		} finally {
			pw.flush();
			pw.close();
		}
	}
	
	/**
	 * 获取request
	 * @return
	 */
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	/**
	 * 获取response
	 * @return
	 */
	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	/**
	 * 获取context
	 * @return
	 */
	public ActionContext getContext(){
		return ServletActionContext.getContext();
	}
	
	/**
	 * 获取pageContext
	 * @return
	 */
	public PageContext getPageContext(){
		return ServletActionContext.getPageContext();
	}
	
	/**
	 * 获取servletContext
	 * @return
	 */
	public ServletContext getServletContext(){
		return ServletActionContext.getServletContext();
	}
	
	/**
	 * 获取 session
	 * @return
	 */
	public HttpSession getSession(){
		return getRequest().getSession();
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getOffset() {
		return (page - 1) * rows;
	}
}
