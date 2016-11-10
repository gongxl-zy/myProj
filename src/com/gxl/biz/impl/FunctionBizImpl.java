package com.gxl.biz.impl;

import gxl.mult.code.Uuid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxl.biz.FunctionBiz;
import com.gxl.biz.RefreshCacheBiz;
import com.gxl.common.Constants;
import com.gxl.common.StringUtils;
import com.gxl.dao.PublicDao;
import com.gxl.entity.PFunction;
import com.gxl.pm.po.NewEntity;
import com.gxl.pm.po.PmFunction;

@Service(value="functionBiz")
public class FunctionBizImpl implements FunctionBiz{
	@Autowired private PublicDao publicDao;
	@Autowired private RefreshCacheBiz refreshCacheBiz;
	
	@Override
	public Long countFunctions(PmFunction function) throws Exception{
		String hql = "select count(m) from PFunction m";
		return publicDao.countByHql(hql);
	}
	
	@Override
	public List<PmFunction> getFunctionsPage(PmFunction function,int offset,int rows) throws Exception{
		String hql = "from PFunction order by funcSort";
		List<Object> list = publicDao.selectPageListByHql(hql,offset,rows);
		List<PmFunction> rtnList = new ArrayList<PmFunction>();
		for(Object obj : list){
			PFunction pFunction = (PFunction)obj;
			rtnList.add(new PmFunction(pFunction));
		}
		return rtnList;
	}
	
	@Override
	public void txOpFunction(PmFunction function,String operSign,String opUserId) throws Exception{
		PFunction pFunction = NewEntity.getPFunction(function);
		if(operSign.equals(Constants.OP_A)){
			pFunction.setFuncId(Uuid.build());
			pFunction.setCreaterId(opUserId);
			pFunction.setCreateTime(StringUtils.getNowTimeMinStr());
			publicDao.add(pFunction);
		}else if(operSign.equals(Constants.OP_D)){
			publicDao.del(pFunction);
		}else if(operSign.equals(Constants.OP_U)){
			pFunction.setUpdaterId(opUserId);
			pFunction.setUpdateTime(StringUtils.getNowTimeMinStr());
			publicDao.upd(pFunction);
		}
		refreshCacheBiz.refreshFuncCache();
		refreshCacheBiz.refreshSysCache();
	}
}
