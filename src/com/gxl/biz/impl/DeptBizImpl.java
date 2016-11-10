package com.gxl.biz.impl;

import gxl.mult.code.Uuid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxl.biz.DeptBiz;
import com.gxl.biz.RefreshCacheBiz;
import com.gxl.common.Cache;
import com.gxl.common.Constants;
import com.gxl.common.StringUtils;
import com.gxl.dao.PublicDao;
import com.gxl.entity.PDept;
import com.gxl.pm.po.NewEntity;
import com.gxl.pm.po.PmDept;

@Service(value="deptBiz")
public class DeptBizImpl implements DeptBiz{
	@Autowired private PublicDao publicDao;
	@Autowired private RefreshCacheBiz refreshCacheBiz;
	
	@Override
	public void txOpDept(PmDept dept,String operSign,String opUserId) throws Exception{
		PDept pDept = NewEntity.getPDept(dept);
		if(operSign.equals(Constants.OP_A)){
			pDept.setDeptId(Uuid.build());
			pDept.setCreaterId(opUserId);
			pDept.setCreateTime(StringUtils.getNowTimeMinStr());
			publicDao.add(pDept);
		}else if(operSign.equals(Constants.OP_D)){
			publicDao.del(pDept);
		}else if(operSign.equals(Constants.OP_U)){
			pDept.setUpdaterId(opUserId);
			pDept.setUpdateTime(StringUtils.getNowTimeMinStr());
			publicDao.upd(pDept);
		}
		refreshCacheBiz.refreshSysCache();
	}
	
	@Override
	public List<PmDept> getDepts() throws Exception{
		List<Object> list = publicDao.selectListByHql("from PDept order by deptLevel, deptMngId");
		List<PmDept> rtnList = new ArrayList<PmDept>();
		Cache.tempMap.clear();
		for(Object obj : list){
			PDept dept = (PDept)obj;
			rtnList.add(new PmDept(dept));
		}
		return rtnList;
	}
}
