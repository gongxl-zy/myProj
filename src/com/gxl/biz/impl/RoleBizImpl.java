package com.gxl.biz.impl;

import gxl.mult.code.Uuid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxl.biz.RefreshCacheBiz;
import com.gxl.biz.RoleBiz;
import com.gxl.common.Constants;
import com.gxl.common.StringUtils;
import com.gxl.dao.PublicDao;
import com.gxl.entity.PRole;
import com.gxl.pm.po.NewEntity;
import com.gxl.pm.po.PmRole;

@Service(value="roleBiz")
public class RoleBizImpl implements RoleBiz{
	@Autowired private PublicDao publicDao;
	@Autowired private RefreshCacheBiz refreshCacheBiz;
	
	@Override
	public Long countRoles(PmRole role) throws Exception{
		String hql = "select count(m) from PRole m";
		return publicDao.countByHql(hql);
	}
	
	@Override
	public List<PmRole> getRolesPage(PmRole role,int offset,int rows) throws Exception{
		String hql = "from PRole order by roleName";
		List<Object> list = publicDao.selectPageListByHql(hql,offset,rows);
		List<PmRole> rtnList = new ArrayList<PmRole>();
		for(Object obj : list){
			PRole pRole = (PRole)obj;
			rtnList.add(new PmRole(pRole));
		}
		return rtnList;
	}
	
	@Override
	public void txOpRole(PmRole role,String operSign,String opUserId) throws Exception{
		PRole pRole = NewEntity.getPRole(role);
		if(operSign.equals(Constants.OP_A)){
			pRole.setRoleId(Uuid.build());
			pRole.setCreaterId(opUserId);
			pRole.setCreateTime(StringUtils.getNowTimeMinStr());
			publicDao.add(pRole);
		}else if(operSign.equals(Constants.OP_D)){
			publicDao.del(pRole);
		}else if(operSign.equals(Constants.OP_U)){
			pRole.setUpdaterId(opUserId);
			pRole.setUpdateTime(StringUtils.getNowTimeMinStr());
			publicDao.upd(pRole);
		}
		refreshCacheBiz.refreshSysCache();
	}
}
