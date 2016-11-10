package com.gxl.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.jdbc.Work;

public class JdbcWork implements Work{
	private String sql;
	private Integer sign;
	public ResultSet resultSet;
	public Integer resultNum;
	public JdbcWork(String sql,Integer sign){
		this.sql = sql;
		this.sign = sign;
	}
	@Override
	public void execute(Connection connection) throws SQLException {
		if(sign == Constants.JDBC_QUERY){
			PreparedStatement ps=connection.prepareStatement(sql);
			//ps.setString(1, "123456");
			resultSet = ps.executeQuery();
		}else if(sign == Constants.JDBC_UPDATE){
			PreparedStatement ps=connection.prepareStatement(sql);
			//ps.setString(1, "123456");
			resultNum = ps.executeUpdate();
		}else if(sign == Constants.JDBC_UPDATE_BATCH){
			PreparedStatement ps=connection.prepareStatement(sql);
			//ps.setString(1, "123456");
			resultNum = ps.executeUpdate();
		}
	}
}
