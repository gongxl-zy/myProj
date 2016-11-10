package com.gxl.biz;

import java.util.List;

import com.gxl.entity.PUser;
import com.gxl.pm.po.PmUser;

public interface UserBiz {
	public PUser getUserInfo(Integer sign,String param) throws Exception;
	
	public Long countUsers(PmUser user) throws Exception;

	public List<PmUser> getUsersPage(PmUser user,int offset,int rows) throws Exception;
	
	public void txOpUser(PmUser user,String operSign,String opUserId) throws Exception;
}
