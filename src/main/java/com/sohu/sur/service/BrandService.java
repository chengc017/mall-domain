package com.sohu.sur.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.sur.base.SearchCondition;
import com.sohu.sur.dao.admin.BrandDao;
import com.sohu.sur.dao.admin.SucLotteryGiftDao;
import com.sohu.sur.dto.BrandSearchCondition;
import com.sohu.sur.model.admin.SucBrand;
import com.sohu.sur.model.admin.SucLotteryGift;
import com.sohu.sur.util.EnumUtils;
import com.sohu.sur.util.Pagination;

/**
 * @Title: BrandService.java
 * @Package com.sohu.sur.service
 * @Description:
 * @author leiyangbj6779
 * @date 2011-12-1 下午5:44:08
 * @version V1.0
 */
@Service
public class BrandService {

	@Autowired
	private BrandDao brandDao;

	public List<SucBrand> getAllBrands(Pagination pagination) {
		String queryString = "from SucBrand where status = ? order by operateTime desc";
		List<SucBrand> result = null;
		if (pagination == null) {
			result = brandDao.getListByParams(queryString,
					new Object[] { EnumUtils.STATUS_NORMAL });
		} else {
			result = brandDao.getObjectList(queryString, pagination,
					new Object[] { EnumUtils.STATUS_NORMAL });
		}
		return result;
	}

	public List<SucBrand> getBrandByConditon(BrandSearchCondition condition,
			Pagination pagination) {
		if (condition == null) {
			return getAllBrands(pagination);
		}
		SearchCondition searchCondition = getSqlByBrandConditon(condition);
		List<SucBrand> result = brandDao.getObjectList(
				searchCondition.getQuerysql(), pagination,
				searchCondition.getParam());
		return result;
	}

	public void createBrand(SucBrand surBrand) {
		if (surBrand != null) {
			brandDao.save(surBrand);
		}
	}

	public SucBrand getBrandById(Long id) {
		if (id == null) {
			return null;
		}
		return brandDao.get(id);
	}

	public void updateBrand(SucBrand brand) {
		if (brand == null) {
			return;
		}
		brandDao.saveOrUpdate(brand);
	}

	/**
	 * 构造常用hibernate查询条件，querySql and param[]
	 * @param condition
	 * @return
	 */
	private SearchCondition getSqlByBrandConditon(BrandSearchCondition condition) {

		StringBuilder queryString = new StringBuilder("from SucBrand where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (condition != null) {
			if (condition.getId() != null) {
				queryString.append(" and id = ?");
				params.add(condition.getId());
			}
			if (!StringUtils.isEmpty(condition.getChName())) {
				queryString.append(" and chName like ?");
				params.add("%" + condition.getChName() + "%");
			}
			/** 默认是正常状态 */
			if (condition.getStatus() == null) {
				queryString.append(" and status = ?");
				params.add(EnumUtils.STATUS_NORMAL);
			} else {
				/** 如果非不确定状态根据状态查询，如果是不确定状态则不用拼接条件，默认查询所有 */
				if (!EnumUtils.STATUS_UNCERTAIN.equals(condition.getStatus())) {
					queryString.append(" and status = ?");
					params.add(condition.getStatus());
				}
			}
			queryString.append(" order by operateTime desc");
		}
		return new SearchCondition(queryString.toString(), params.toArray());
	}
}
