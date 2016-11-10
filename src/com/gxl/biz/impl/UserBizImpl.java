package com.gxl.biz.impl;

import gxl.mult.code.Uuid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxl.biz.RefreshCacheBiz;
import com.gxl.biz.UserBiz;
import com.gxl.common.Constants;
import com.gxl.common.StringUtils;
import com.gxl.dao.PublicDao;
import com.gxl.entity.PUser;
import com.gxl.pm.po.NewEntity;
import com.gxl.pm.po.PmUser;

@Service(value="userBiz")
public class UserBizImpl implements UserBiz{
	@Autowired private PublicDao publicDao;
	@Autowired private RefreshCacheBiz refreshCacheBiz;
	
	@Override
	public PUser getUserInfo(Integer sign,String param) throws Exception{
		String hql = "from PUser where ";
		String strParam = "'" + param + "'";
		if(sign == 1){
			hql = hql + "userId="+strParam;
		}else if(sign == 2){
			hql = hql + "userNo="+strParam;
		}else if(sign == 3){
			hql = hql + "userPhone="+strParam;
		}else if(sign == 4){
			hql = hql + "userEmail="+strParam;
		}
		Iterator<Object> itor = publicDao.selectItorByHql(hql);
		if(itor.hasNext()){
			return (PUser)itor.next();
		}
		return null;
	}
	@Override
	public Long countUsers(PmUser user) throws Exception{
		String hql = "select count(m) from PUser m";
		return publicDao.countByHql(hql);
	}
	
	@Override
	public List<PmUser> getUsersPage(PmUser user,int offset,int rows) throws Exception{
		String hql = "from PUser order by userNo";
		List<Object> list = publicDao.selectPageListByHql(hql,offset,rows);
		List<PmUser> rtnList = new ArrayList<PmUser>();
		for(Object obj : list){
			PUser pUser = (PUser)obj;
			rtnList.add(new PmUser(pUser));
		}
		return rtnList;
	}
	
	@Override
	public void txOpUser(PmUser user,String operSign,String opUserId) throws Exception{
		PUser pUser = NewEntity.getPUser(user);
		if(operSign.equals(Constants.OP_A)){
			pUser.setUserId(Uuid.build());
			pUser.setCreaterId(opUserId);
			pUser.setCreateTime(StringUtils.getNowTimeMinStr());
			publicDao.add(pUser);
		}else if(operSign.equals(Constants.OP_D)){
			publicDao.del(pUser);
		}else if(operSign.equals(Constants.OP_U)){
			pUser.setUpdaterId(opUserId);
			pUser.setUpdateTime(StringUtils.getNowTimeMinStr());
			publicDao.upd(pUser);
		}
		refreshCacheBiz.refreshSysCache();
	}
}
