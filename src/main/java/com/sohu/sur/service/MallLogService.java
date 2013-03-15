package com.sohu.sur.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.sur.dao.admin.MallLogDao;
import com.sohu.sur.model.admin.MallLog;
import com.sohu.sur.util.PaginationSupport;
@Service
public class MallLogService {
	@Autowired
	private MallLogDao mallLogDao;
	/**
	 * 保存日志
	 * @param mallLog
	 */
	public void saveLog(MallLog mallLog) {
		this.mallLogDao.save(mallLog);
	}
	/**
	 * 查找用户操作 日志
	 * @param uid
	 * @param sDate
	 * @param eDate
	 * @param page
	 * @param pageSize
	 * @return
	 */

	public PaginationSupport getUserAllLog(String uid,Date sDate, Date eDate,int page, int pageSize) {
		int begin = (page - 1) * pageSize;
		List params = new ArrayList();
		String hql = "select log from MallLog log where 1 = 1 ";
		if (uid != null && !uid.equals("")) {
			hql += " and log.uid = ?";
			params.add(uid);
		}
		if (sDate != null) {
			hql += " and log.ctime> = ?";
			params.add(sDate);
		}
		if (eDate != null) {
			hql += " and log.ctime <= ?";
			params.add(eDate);
		}
		hql += " order by log.ctime desc";
		int rowCount = mallLogDao.getTotalCount(hql, params.toArray());
		List list = mallLogDao.getListByPosition(begin, pageSize, hql, params.toArray());
		PaginationSupport ps = new PaginationSupport(list, rowCount, begin, pageSize);
		return ps;
	}
	
}
