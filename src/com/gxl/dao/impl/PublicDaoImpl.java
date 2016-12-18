package com.gxl.dao.impl;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxl.common.Constants;
import com.gxl.common.JdbcWork;
import com.gxl.dao.PublicDao;

@Service(value="publicDao")
public class PublicDaoImpl implements PublicDao{
	@Autowired private SessionFactory sessionFactory;
	
	@Override
	public void add(Object object) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		session.save(object);
	}
	
	@Override
	public void del(Object object) throws Exception{
		Session session = sessionFactory.getCurrentSession();
	    session.delete(object);
	}
	
	@Override
	public void upd(Object object) throws Exception{
		Session session = sessionFactory.getCurrentSession();
	    session.update(object);
	}
	@Override
	public Iterator<Object> selectItorByHql(String hql) throws Exception{
		List<Object> list = selectListByHql(hql);
		return list.iterator();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectListByHql(String hql) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	@Override
	public Iterator<Object> selectPageItorByHql(String hql,int offset,int rows) throws Exception{
		List<Object> list = selectPageListByHql(hql,offset,rows);
		return list.iterator();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> selectPageListByHql(String hql,int offset,int rows) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		return query.list();
	}
	
	@Override
	public Long countByHql(String hql) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		return (Long)query.uniqueResult();
	}
	
	@Override
	public void executeUpdate(String hql,String[] conds) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if(conds != null && conds.length > 0){
			for(int i = 0; i < conds.length; i ++){
				query.setString(i, conds[i]);
			}
		}
		query.executeUpdate();
	}
	
	@Override
	public ResultSet query(String sql) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		JdbcWork jdbcWork = new JdbcWork(sql,Constants.JDBC_QUERY);
		session.doWork(jdbcWork);
		return jdbcWork.resultSet;
	}
}
