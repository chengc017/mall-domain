package com.sohu.sur.dao.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sohu.sur.base.BaseHibernateDaoSupport;
import com.sohu.sur.model.admin.MallUser;
/**
 * 
 * @author xuewuhao
 *
 */
@Repository
public class MallUserDao extends BaseHibernateDaoSupport<MallUser> {

	/**
	 * login check
	 * @param name
	 * @param pwd
	 * @return
	 */
	public List<MallUser> checkMallUser(String name, String pwd) {
		List listParams = new ArrayList();
		if (name == null || pwd == null) {
			return null;
		}
		String hql = "select u from MallUser u where 1=1 ";
		if (name != null && !name.equals("")) {
			hql += " and u.name=?";
			listParams.add(name);
		}
		if (pwd != null && !pwd.equals("")) {
			hql += " and u.pwd=?";
			listParams.add(pwd);
		}
		List<MallUser> list = this.getAll(hql, listParams.toArray());
		return list;
	}
	
}
