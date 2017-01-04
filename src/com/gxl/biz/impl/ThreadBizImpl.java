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

	@Override
	public void txAddVtlMbr(List<String> mbrList,List<String> mbrDtList) throws Exception {
		String sql = "insert into p_mbr_info(mbr_id,mbr_name,sex,age,height,month_income,hava_house,location,native_place,weight,education,marry_status,occupation,pic_url,type,origin) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		publicDao.jdbcExecuteBatch(sql, 16, mbrList);
		sql = "insert into p_mbr_detail(mbr_id,need_child,can_yidi,like_type,can_sex,with_parents,best_part,hobbys,soliloquy) values(?,?,?,?,?,?,?,?,?)";
		publicDao.jdbcExecuteBatch(sql, 9, mbrDtList);
		
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
