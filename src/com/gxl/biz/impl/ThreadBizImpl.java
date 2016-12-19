package com.gxl.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxl.biz.ThreadBiz;
import com.gxl.common.Cache;
import com.gxl.dao.PublicDao;

@Service(value="threadBiz")
public class ThreadBizImpl implements ThreadBiz {
	
	@Autowired private PublicDao publicDao;
	
	@Override
	public void txAddAreaCode(List<String> areaList) throws Exception {
		String sql = "insert into p_area values (?,?,?,?)";
		publicDao.jdbcExecuteBatch(sql, 4, areaList);
		sql = "delete from p_wait_search";
		publicDao.jdbcExecute(sql);
		sql = "insert into p_wait_search values (?,?)";
		List<String> urlList = new ArrayList<String>();
		for(String key : Cache.urlMap.keySet()){
			urlList.add(key);
			urlList.add(""+Cache.urlMap.get(key));
		}
		publicDao.jdbcExecuteBatch(sql, 2, urlList);
	}

}
