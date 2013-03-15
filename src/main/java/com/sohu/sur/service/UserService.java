package com.sohu.sur.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.sur.dao.admin.MallFuncDao;
import com.sohu.sur.dao.admin.MallRoleDao;
import com.sohu.sur.dao.admin.MallRoleFuncDao;
import com.sohu.sur.dao.admin.MallUserDao;
import com.sohu.sur.dao.admin.MallUserRoleDao;
import com.sohu.sur.model.admin.MallFunc;
import com.sohu.sur.model.admin.MallRole;
import com.sohu.sur.model.admin.MallRoleFunc;
import com.sohu.sur.model.admin.MallUser;
import com.sohu.sur.model.admin.MallUserRole;
import com.sohu.sur.util.PaginationSupport;

/**
 * 权限管理实现类
 * 
 * @author xuewuhao
 * 
 */
@Service
public class UserService {
	private static final Log log = LogFactory.getLog(UserService.class);
	@Autowired
	private MallUserDao mallUserDao;
	@Autowired
	private MallRoleDao mallRoleDao;
	@Autowired
	private MallFuncDao mallFuncDao;
	@Autowired
	private MallRoleFuncDao mallRoleFuncDao;
	@Autowired
	private MallUserRoleDao mallUserRoleDao;

	public MallUser loginCheck(String name, String pwd) {
		// TODO Auto-generated method stub
		List<MallUser> listScoreUser = mallUserDao.checkMallUser(name, pwd);
		if (listScoreUser != null && listScoreUser.size() > 0) {
			return listScoreUser.get(0);
		} else {
			return null;
		}

	}

	public List<MallFunc> findFuncbyPid(long pid) {
		return this.mallFuncDao.findFuncbyPid(pid);
	}

	/**
	 * 查找用户角色
	 * 
	 * @param mallUser
	 * @return
	 */
	public List<MallRole> getUserRole(MallUser mallUser) {
		List listParams = new ArrayList();
		List<MallRole> list = new ArrayList<MallRole>();
		String hql = "select r from MallUserRole u,MallRole r where u.roleId=r.roleId ";
		if (mallUser != null) {
			hql += " and u.userId = ?";
			listParams.add(mallUser.getId());
		}
		hql += " order by  r.createTime desc";
		list = mallUserRoleDao.getAll(hql, listParams.toArray());
		return list;
	}

	/**
	 * 查找所有用户
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */

	public PaginationSupport getUserAll(int page, int pageSize) {
		int begin = (page - 1) * pageSize;
		String params[] = new String[] {};
		String hql = "select u.id,u.name,u.status,u.createTime,r.roleName from MallUser u,MallRole r,MallUserRole ur where u.id=ur.userId and ur.roleId=r.roleId";
		// hql = "select u from MallUser u";
		int rowCount = mallRoleFuncDao.getTotalCount(hql, params);
		List list = mallRoleFuncDao.getListByPosition(begin, pageSize, hql, params);
		PaginationSupport ps = new PaginationSupport(list, rowCount, begin, pageSize);
		return ps;
	}

	/**
	 * 查找角色功能
	 * 
	 * @param mallRole
	 * @return
	 */
	public List<MallFunc> getRoleFunc(MallRole mallRole) {
		List listParams = new ArrayList();
		List<MallFunc> list = new ArrayList();
		String hql = "select f from MallRoleFunc u,MallFunc f where u.funcId=f.funcId ";
		if (mallRole != null) {
			hql += " and u.roleId = ?";
			listParams.add(mallRole.getRoleId());
		}
		hql += " order by  f.createTime desc";
		list = mallRoleFuncDao.getAll(hql, listParams.toArray());
		return list;
	}

	/**
	 * 查找用户功能
	 * 
	 * @param mallUser
	 * @return
	 */
	public List<MallFunc> getUserFunc(MallUser mallUser) {
		List<MallRole> lmr = getUserRole(mallUser);
		List<MallFunc> lmf = getRoleFunc(lmr.get(0));
		return lmf;
	}

	public MallUser findUserById(long id) {
		// TODO Auto-generated method stub
		return this.mallUserDao.get(id);
	}

	public MallRole findRoleById(long id) {
		// TODO Auto-generated method stub
		return this.mallRoleDao.get(id);
	}

	public MallFunc findFuncById(long id) {
		// TODO Auto-generated method stub
		return this.mallFuncDao.get(id);
	}

	/**
	 * 修改密码用
	 * 
	 * @param mu
	 */
	public void saveMallUser(MallUser mu) {
		this.mallUserDao.saveOrUpdate(mu);
	}

	/**
	 * 保存用户 删除用户所有角色 赋予新角色
	 * 
	 * @param user
	 * @param roleTmp
	 */
	public void saveMallUser(MallUser user, Long userRoleId) {
		// 保存用户
		this.mallUserDao.saveOrUpdate(user);
		// 删除原来有的角色
		String hqlUserRoles = "select ur from MallUserRole ur where ur.userId=" + user.getId();
		List<MallUserRole> listUserRole = this.mallUserRoleDao.getList(hqlUserRoles);
		if (listUserRole != null && listUserRole.size() > 0)
			for (MallUserRole ur : listUserRole) {
				this.mallUserRoleDao.remove(ur);
			}
		// 保存用户角色
		MallUserRole userRole = new MallUserRole();
		userRole.setUserId(user.getId());
		userRole.setRoleId(userRoleId);
		this.mallUserRoleDao.saveOrUpdate(userRole);
	}

	/**
	 * 查找用户现有角色中间表记录
	 * 
	 * @param userId
	 * @return
	 */
	public List<MallUserRole> findMallUserRole(Long userId) {
		String hqlUserRoles = "select ur from MallUserRole ur where ur.userId=" + userId;
		List<MallUserRole> listUserRole = this.mallUserRoleDao.getList(hqlUserRoles);
		return listUserRole;
	}

	public void saveMallRole(MallRole sr) {
		// TODO Auto-generated method stub
		this.mallRoleDao.saveOrUpdate(sr);
	}

	/**
	 * 更新角色功能
	 * 
	 * @param userId
	 * @param roles
	 * @return
	 */
	public boolean saveRoleFunc(Long roleId, String[] funcs) {
		boolean b = false;
		try {
			List listParams = new ArrayList();
			List<MallRoleFunc> list = new ArrayList();
			MallRoleFunc roleFuncTmp = null;
			String hql = "select  u  from MallRoleFunc u where 1=1 ";
			if (roleId != null) {
				hql += " and u.roleId = ?";
				listParams.add(roleId);
			}
			list = mallRoleFuncDao.getAll(hql, listParams.toArray()); // 查找角色功能列表
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					mallRoleFuncDao.remove(list.get(i)); // 删除角色原有功能
				}
			}
			// 设置角色新功能
			for (int m = 0; m < funcs.length; m++) {
				roleFuncTmp = new MallRoleFunc();
				roleFuncTmp.setRoleId(roleId);
				roleFuncTmp.setFuncId(new Long(funcs[m].toString()));
				mallRoleFuncDao.saveOrUpdate(roleFuncTmp);
			}
			b = true;
		} catch (RuntimeException e) {
			b = false;
			log.error("更新角色功能异常", e);
			throw new RuntimeException();
		}
		return b;
	}

	public void saveMallFunc(MallFunc sf) {
		// TODO Auto-generated method stub
		this.mallFuncDao.saveOrUpdate(sf);
	}

	public List<MallUser> selectAllMallUser() {
		// TODO Auto-generated method stub
		return this.mallUserDao.getList("select u from MallUser u");
	}

	public List<MallRole> selectAllMallRole() {
		// TODO Auto-generated method stub
		return this.mallRoleDao.getList("select u from MallRole u");
	}

	public List<MallFunc> selectAllMallFunc() {
		// TODO Auto-generated method stub
		return this.mallFuncDao.getList("select u from MallFunc u");
	}

	public void deleteUserById(long id) {
		// TODO Auto-generated method stub
		MallUser mu = findUserById(id);
		List<MallUserRole> ll = findMallUserRole(id);
		for (MallUserRole mur : ll) {
			this.mallUserRoleDao.remove(mur);
		}
		this.mallUserDao.remove(mu);
	}

	public void deleteUser(MallUser mallUser) {
		// TODO Auto-generated method stub
		this.mallUserDao.remove(mallUser);
	}

	/**
	 * 删除角色 删除用户角色中间表 角色功能中间表 之后删除自己
	 * 
	 * @param mallRole
	 */
	public void deleteRole(MallRole mallRole) {
		String selectRoleFunc = " select rolefunc from  MallRoleFunc rolefunc where rolefunc.roleId ="
				+ mallRole.getRoleId();

		String selectUserRole = " select userrole from  MallUserRole userrole where userrole.roleId ="
				+ mallRole.getRoleId();

		List<MallRoleFunc> list = mallRoleFuncDao.getList(selectRoleFunc);
		for (MallRoleFunc rf : list) {
			// 删除角色功能表中的数据
			mallRoleFuncDao.remove(rf);
		}

		List<MallUserRole> list2 = mallUserRoleDao.getList(selectUserRole);
		for (MallUserRole ur : list2) {
			// 删除用户角色表中的数据
			mallUserRoleDao.remove(ur);
		}
		// 删除角色
		this.mallRoleDao.remove(mallRole);
	}

	/**
	 * 删除功能 同事删除子功能以及子功能相关角色功能中间表中记录
	 * 
	 * @param mallFunc
	 */
	public void deleteFunc(MallFunc mallFunc) {
		String deleteRoleFuncHql = "";
		List<MallFunc> funcs = new ArrayList<MallFunc>();
		String hql = "from MallFunc t where t.parentId = " + mallFunc.getFuncId();
		funcs = this.mallFuncDao.getList(hql);// 取子功能模块
		if (funcs != null && funcs.size() > 0)
			for (MallFunc fun : funcs) {
				// 删除现有角色功能表中有此子功能的记录
				deleteRoleFuncHql = "from MallRoleFunc rf where rf.funcId = " + fun.getFuncId();
				List<MallRoleFunc> rolefuncs = this.mallRoleFuncDao.getList(deleteRoleFuncHql);
				if (rolefuncs != null && rolefuncs.size() > 0) {
					for (int j = 0; j < rolefuncs.size(); j++) {
						mallRoleFuncDao.remove(rolefuncs.get(j));
					}
				}
				// 删除这个子功能
				this.mallFuncDao.remove(fun);
			}
		// 删除现有角色功能表中有此功能的记录
		deleteRoleFuncHql = "from MallRoleFunc rf where rf.funcId = " + mallFunc.getFuncId();
		List<MallRoleFunc> rolefuncs2 = this.mallRoleFuncDao.getList(deleteRoleFuncHql);
		if (rolefuncs2 != null && rolefuncs2.size() > 0) {
			for (int j = 0; j < rolefuncs2.size(); j++) {
				mallRoleFuncDao.remove(rolefuncs2.get(j));
			}
		}
		// 删除这个功能
		this.mallFuncDao.remove(mallFunc);
	}

	public void deleteRoleById(long id) {
		// TODO Auto-generated method stub
		this.mallRoleDao.remove(findRoleById(id));
	}

	public void deleteFuncById(long id) {
		// TODO Auto-generated method stub
		this.mallFuncDao.remove(findFuncById(id));
	}

}
