package com.gxl.dao;

import java.util.Iterator;
import java.util.List;

public interface PublicDao {
	/**
	 * 添加数据
	 * @param object
	 * @throws Exception
	 */
	public void add(Object object) throws Exception;
	
	/**
	 * 删除数据
	 * @param object
	 * @throws Exception
	 */
	public void del(Object object) throws Exception;
	
	/**
	 * 更新数据
	 * @param object
	 * @throws Exception
	 */
	public void upd(Object object) throws Exception;
	
	/**
	 * hql查询
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public Iterator<Object> selectItorByHql(String hql) throws Exception;
	public List<Object> selectListByHql(String hql) throws Exception;
	
	/**
	 * hql分页查询
	 * @param hql
	 * @param offset
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	public Iterator<Object> selectPageItorByHql(String hql,int offset,int rows) throws Exception;
	public List<Object> selectPageListByHql(String hql,int offset,int rows) throws Exception;
	
	/**
	 * 计算总数
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public Long countByHql(String hql) throws Exception;
	
	public void executeUpdate(String hql,String[] conds) throws Exception;
	
	/**
	 * 获取查询列表
	 */
	public void query() throws Exception;
	
	
}
