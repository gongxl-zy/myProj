package com.gxl.biz;

import java.util.List;

import com.gxl.pm.po.PmRole;

public interface RoleBiz {
	public Long countRoles(PmRole role) throws Exception;

	public List<PmRole> getRolesPage(PmRole role,int offset,int rows) throws Exception;
	
	public void txOpRole(PmRole role,String operSign,String opUserId) throws Exception;
}
