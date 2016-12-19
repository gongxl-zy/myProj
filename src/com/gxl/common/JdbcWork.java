package com.gxl.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.jdbc.Work;

public class JdbcWork implements Work{
	private String sql;
	private Integer sign;
	public ResultSet resultSet;
	public Integer resultNum;
	public Integer paramNum;
	public List<String> paramList;
	public JdbcWork(String sql,Integer sign){
		this.sql = sql;
		this.sign = sign;
	}
	/**
	 * 批量录入，默认参数都是string类型
	 * @param sql
	 * @param sign
	 * @param pn
	 * @param list
	 */
	public JdbcWork(String sql,Integer sign,Integer pn,List<String> list){
		this.sql = sql;
		this.sign = sign;
		this.paramNum = pn;
		this.paramList = list;
	}
	@Override
	public void execute(Connection connection) throws SQLException {
		if(sign == Constants.JDBC_QUERY){
			PreparedStatement ps=connection.prepareStatement(sql);
			resultSet = ps.executeQuery();
		}else if(sign == Constants.JDBC_UPDATE){
			PreparedStatement ps=connection.prepareStatement(sql);
			resultNum = ps.executeUpdate();
		}else if(sign == Constants.JDBC_BATCH){
			PreparedStatement ps=connection.prepareStatement(sql);
			int k = 0;
			for(int i = 0; i < paramList.size(); i ++){
				k ++;
				ps.setString(k, paramList.get(i));
				if(k == paramNum){
					k = 0;
					ps.addBatch();
				}
			}
			ps.executeBatch();
		}
	}
}
