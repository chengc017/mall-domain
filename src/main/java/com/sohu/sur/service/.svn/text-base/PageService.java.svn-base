package com.sohu.sur.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sohu.sur.dao.admin.PageDao;
import com.sohu.sur.dao.admin.RegionDao;
import com.sohu.sur.model.admin.Page;
import com.sohu.sur.model.admin.Region;
import com.sohu.sur.model.admin.SucStocks;
import com.sohu.sur.util.Pagination;

/**
 * @Title: PageService.java
 * @Package com.sohu.sur.service
 * @Description: TODO
 * @author leiyangbj6779
 * @date 2011-12-14 上午11:35:31
 * @version V1.0
 */
@Service
public class PageService {
	@Autowired
	private PageDao pageDao;
	@Autowired
	private RegionDao regionDao;

	public List<Page> getPageListByRegion(Region region, Pagination pagination,
			int status, int type, Long bId /** Long renderId, */) {
		if (region == null) {
			List<Object> params = new ArrayList<Object>();
			StringBuffer querySql = new StringBuffer(
					" from Page where 1 = 1   ");
			if (status != -1) {
				querySql.append(" and status = ? ");
				params.add(status);
			} else {
				querySql.append(" and status <> ? ");
				params.add(2);
			}
			if (type != -1) {
				querySql.append(" and type = ? ");
				params.add(type);
			}
			if (bId != null && bId.longValue() != -1) {
				querySql.append(" and businessId =? ");
				params.add(bId);
			}

			List<Page> pageList = pageDao.getObjectList(querySql.toString(),pagination,
					params.toArray());
			return pageList;
		} else {
			// TODO 需要对region进行处理
			return null;
		}

	}

	public List<Region> getRegionList(Pagination pagination, int status) {
		String queryHql = " from Region where status = ?  order by updateTime desc ";

		Object[] params = { new Integer(status) };

		return regionDao.getListByParams(queryHql, params);
	}
	
	public Region getRegion(Long id){
		return regionDao.get(id);
	}
	
	public void savePage(Page page){
		pageDao.save(page);
	}
	
	public void deployPage(Page page){
		/**同一个url 其他的page修改成回收状态*/
		String updateSql = " update Page set status = ? where url = ? and status = ? and id <> ?";
		Object[] params = new Object[]{ Page.STATUS_RECALL,page.getUrl(),Page.STATUS_DEPLOY, page.getId()};
		pageDao.updateByParams(updateSql, params);
		if (page.getStatus() != Page.STATUS_DEPLOY) {
			page.setStatus(Page.STATUS_DEPLOY);
		}
		pageDao.saveOrUpdate(page);
	}
	
	public void updatePage(Page oldPage){
		pageDao.saveOrUpdate(oldPage);
	}
	
	public Page getPage(Long id){
		return pageDao.get(id);
	}

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}
}
