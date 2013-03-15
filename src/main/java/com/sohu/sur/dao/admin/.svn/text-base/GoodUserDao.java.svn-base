package com.sohu.sur.dao.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sohu.sur.base.BaseHibernateDaoSupport;
import com.sohu.sur.model.admin.GoodUser;

/**
 * 可中奖用户实体
 * 
 * @author xuewuhao
 * 
 */
@Repository
public class GoodUserDao extends BaseHibernateDaoSupport<GoodUser> {

	/**
	 * 检查是否是优质或者金牌用户
	 * @param name
	 * @param level 优质用户0 金牌用户1
	 * @return
	 */
	public boolean isGoodUser(String name,String level) {
		boolean flag = false;
		List listParams = new ArrayList();
		if (name == null) {
			return flag;
		}
		String hql = "select u from GoodUser u where 1=1 ";
		if (name != null && !name.equals("")) {
			hql += " and u.name=?";
			listParams.add(name);
		}
		if (level != null && !level.equals("")) {
			hql += " and u.level=?";
			listParams.add(level);
		}
		List<GoodUser> list = this.getAll(hql, listParams.toArray());
		if (list.size() > 0) {
			flag = true;
		}
		return flag;
	}

}
