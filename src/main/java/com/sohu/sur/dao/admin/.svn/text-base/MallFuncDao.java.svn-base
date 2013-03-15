package com.sohu.sur.dao.admin;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sohu.sur.base.BaseHibernateDaoSupport;
import com.sohu.sur.model.admin.MallFunc;

@Repository
public class MallFuncDao extends BaseHibernateDaoSupport<MallFunc>{
	/**
	 * login check
	 * @param name
	 * @param pwd
	 * @return
	 */
	public List<MallFunc> findFuncbyPid(long pid) {
		String hql = "select u from MallFunc u where u.parentId = "+pid;
		List<MallFunc> list = this.getList(hql);
		return list;
	}
}
