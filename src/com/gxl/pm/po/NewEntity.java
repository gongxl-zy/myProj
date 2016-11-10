package com.gxl.pm.po;

import com.gxl.entity.PDept;
import com.gxl.entity.PFunction;
import com.gxl.entity.PMenu;
import com.gxl.entity.PRole;
import com.gxl.entity.PUser;
/**
 * 子类转父类-->hql需要
 * @author gongxl
 *
 */
public class NewEntity {
	
	/**
	 * PmMenu-->PMenu
	 * @param pmObj
	 * @return
	 */
	public static PMenu getPMenu(PmMenu pmObj){
		PMenu pMenu = new PMenu();
		pMenu.setMenuId(pmObj.getMenuId());
		pMenu.setMenuName(pmObj.getMenuName());
		pMenu.setMenuType(pmObj.getMenuType());
		pMenu.setMenuProp(pmObj.getMenuProp());
		pMenu.setMenuSort(pmObj.getMenuSort());
		pMenu.setMenuState(pmObj.getMenuState());
		pMenu.setUpMenuId(pmObj.getUpMenuId());
		pMenu.setMenuIcon(pmObj.getMenuIcon());
		pMenu.setMenuLink(pmObj.getMenuLink());
		pMenu.setMenuDesc(pmObj.getMenuDesc());
		pMenu.setCreaterId(pmObj.getCreaterId());
		pMenu.setCreateTime(pmObj.getCreateTime());
		pMenu.setUpdaterId(pmObj.getUpdaterId());
		pMenu.setUpdateTime(pmObj.getUpdateTime());
		return pMenu;
	}
	
	/**
	 * PmFunction-->PFunction
	 * @param pmObj
	 * @return
	 */
	public static PFunction getPFunction(PmFunction pmObj) {
		PFunction pFunction = new PFunction();
		pFunction.setFuncId(pmObj.getFuncId());
		pFunction.setFuncCode(pmObj.getFuncCode());
		pFunction.setFuncName(pmObj.getFuncName());
		pFunction.setFuncIcon(pmObj.getFuncIcon());
		pFunction.setFuncSort(pmObj.getFuncSort());
		pFunction.setFuncState(pmObj.getFuncState());
		pFunction.setFuncDesc(pmObj.getFuncDesc());
		pFunction.setCreaterId(pmObj.getCreaterId());
		pFunction.setCreateTime(pmObj.getCreateTime());
		pFunction.setUpdaterId(pmObj.getUpdaterId());
		pFunction.setUpdateTime(pmObj.getUpdateTime());
		return pFunction;
	}
	
	/**
	 * PmRole-->PRole
	 * @param pmObj
	 * @return
	 */
	public static PRole getPRole(PmRole pmObj) {
		PRole pRole = new PRole();
		pRole.setRoleId(pmObj.getRoleId());
		pRole.setRoleName(pmObj.getRoleName());
		pRole.setRoleDesc(pmObj.getRoleDesc());
		pRole.setRoleState(pmObj.getRoleState());
		pRole.setCreaterId(pmObj.getCreaterId());
		pRole.setCreateTime(pmObj.getCreateTime());
		pRole.setUpdaterId(pmObj.getUpdaterId());
		pRole.setUpdateTime(pmObj.getUpdateTime());
		return pRole;
	}
	
	/**
	 * PmUser-->PUser
	 * @param pmObj
	 * @return
	 */
	public static PUser getPUser(PmUser pmObj){
		PUser pUser = new PUser();
		pUser.setUserId(pmObj.getUserId());
		pUser.setUserNo(pmObj.getUserNo());
		pUser.setUserPwd(pmObj.getUserPwd());
		pUser.setUserName(pmObj.getUserName());
		pUser.setRoleId(pmObj.getRoleId());
		pUser.setDeptId(pmObj.getDeptId());
		pUser.setUserLevel(pmObj.getUserLevel());
		pUser.setUserPhone(pmObj.getUserPhone());
		pUser.setUserEmail(pmObj.getUserEmail());
		pUser.setUserState(pmObj.getUserState());
		pUser.setIsOnline(pmObj.getIsOnline());
		pUser.setOnlineIp(pmObj.getOnlineIp());
		pUser.setCreaterId(pmObj.getCreaterId());
		pUser.setCreateTime(pmObj.getCreateTime());
		pUser.setUpdaterId(pmObj.getUpdaterId());
		pUser.setUpdateTime(pmObj.getUpdateTime());
		return pUser;
	}
	
	/**
	 * PmDept-->PDept
	 * @param pmObj
	 * @return
	 */
	public static PDept getPDept(PmDept pmObj){
		PDept pDept =  new PDept();
		pDept.setDeptId(pmObj.getDeptId());
		pDept.setDeptName(pmObj.getDeptName());
		pDept.setDeptDesc(pmObj.getDeptDesc());
		pDept.setDeptLevel(pmObj.getDeptLevel());
		pDept.setDeptMngId(pmObj.getDeptMngId());
		pDept.setUpDeptId(pmObj.getUpDeptId());
		pDept.setDeptState(pmObj.getDeptState());
		pDept.setCreaterId(pmObj.getCreaterId());
		pDept.setCreateTime(pmObj.getCreateTime());
		pDept.setUpdaterId(pmObj.getUpdaterId());
		pDept.setUpdateTime(pmObj.getUpdateTime());
		return pDept;
	}
}
