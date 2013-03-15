package com.sohu.sur.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.sur.dao.admin.GoodUserDao;
import com.sohu.sur.model.admin.GoodUser;

@Service
public class GoodUserService {
	private Logger logger = LoggerFactory.getLogger(GoodUserService.class);
	@Autowired
	private GoodUserDao goodUserDao;

	/**
	 * 批量导入优质用户
	 * 先删除对应level的旧数据
	 * @param list
	 */
	public void batchSave(List<GoodUser> list) {

		String level = list.get(0).getLevel();
		
		List<GoodUser> listOld = this.goodUserDao.getList("select u from GoodUser u where u.level = "+level);
		// 先删除旧优质用户
		if(listOld.size()>0)
		for (GoodUser gu : listOld) {
			this.goodUserDao.remove(gu);
		}
		logger.info("delete " + listOld.size());
		for (GoodUser gu : list) {
			this.goodUserDao.save(gu);
		}
		logger.info("add " + list.size());
	}

	/**
	 * 检查是否是优质用户
	 * @param name
	 * @param level 优质用户0 金牌用户1
	 * @return
	 */
	public boolean checkUser(String name,String level) {
		boolean flag = false;
		flag = this.goodUserDao.isGoodUser(name,level);
		return flag;
	}
}
