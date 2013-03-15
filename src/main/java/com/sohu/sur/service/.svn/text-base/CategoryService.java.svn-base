package com.sohu.sur.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sohu.sur.base.exception.SucCatetoryException;
import com.sohu.sur.dao.admin.CategoryDao;
import com.sohu.sur.dto.CategorySearchCondition;
import com.sohu.sur.model.admin.MallUser;
import com.sohu.sur.model.admin.SucCategory;
import com.sohu.sur.util.CatetoryConstants;
import com.sohu.sur.util.EnumUtils;
import com.sohu.sur.util.Pagination;

/**   
 * @Title: CategoryServiceImpl.java
 * @Package com.sohu.sur.service.impl
 * @Description: TODO
 * @author leiyangbj6779
 * @date 2011-11-28 下午4:17:21
 * @version V1.0   
 */
@Service
public class CategoryService {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private CategoryDao categoryDao;

	 
	public List<SucCategory> getSucCategoryList(Long parentId,
			Pagination pagination) {
		List<SucCategory> childrenList = null;
		try {
			CategorySearchCondition condition = new CategorySearchCondition();
			condition.setParenetSortId(parentId);
			condition.setStatus(EnumUtils.status.正常.ordinal());
			childrenList = getSucCategoryList(condition, pagination);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return childrenList;
	}

	 
	public List<SucCategory> getSucCategoryList(CategorySearchCondition condition,
							Pagination pagination) throws SucCatetoryException {
		List<SucCategory> childrenList = null;
		try {
			StringBuffer queryString = new StringBuffer(
					"from SucCategory where 1=1 ");
			List<Object> params = new ArrayList<Object>();
			if ( condition.getId()!= null) {
				queryString.append(" and id = ? ");
				params.add(condition.getId());
			}
			if (condition.getParenetSortId() != null) {
				queryString.append(" and parenetSortId = ? ");
				params.add(condition.getParenetSortId());
			}
			if (!StringUtils.isEmpty(condition.getName())) {
				queryString.append(" and name like ? ");
				params.add("%" + condition.getName() + "%");
			}

			/** 默认是正常状态 */
			if (condition.getStatus() == null) {
				queryString.append(" and status = ? ");
				params.add(EnumUtils.status.正常.ordinal());
			} else {
				/** 如果非不确定状态根据状态查询，如果是不确定状态则不用拼接条件，默认查询所有 */
				if (!EnumUtils.STATUS_UNCERTAIN.equals(condition.getStatus())) {
					queryString.append(" and status = ? ");
					params.add(condition.getStatus());
				}
			}
			 childrenList = categoryDao.getObjectList(queryString.toString(), pagination,
			 params.toArray());

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SucCatetoryException(e);
		}

		return childrenList;
	}

	 
	public List<SucCategory> getRootList() {
		String queryString = "from SucCategory where parenetSortId = ? and status = ?";
		Object[] params = { CatetoryConstants.ROOT_ITEM_ID,
				EnumUtils.status.正常.ordinal() };
		return categoryDao.getListByParams(queryString, params);
	}

	 
	public List<SucCategory> getCatetoryList(String queryString, Object[] params) {
		return categoryDao.getListByParams(queryString,params);
	}

	 
	public void createCatetoryItem(SucCategory catetoryItem)
			throws SucCatetoryException {
		try {
			checkCategoryItem(catetoryItem);
			SucCategory newCategroy = packageCategory(catetoryItem);
			categoryDao.save(newCategroy);
		} catch (SucCatetoryException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SucCatetoryException(e);
		}
	}

	 
	public SucCategory getCatetoryItem(Long catetoryId) {
		return categoryDao.get(catetoryId);
	}

	 
	public void updateCatetoryItem(SucCategory catetoryItem)
			throws SucCatetoryException {
		try {
			nullCheck(catetoryItem, "类别信息不能为空！");

			nullCheck(catetoryItem.getName(), "类别的“名称”不能为空！");

			catetoryItem.setStatus(EnumUtils.status.正常.ordinal());

			long categoryId = catetoryItem.getId();
			catetoryItem.setId(null);

			SucCategory oldCatetoryItem = getCatetoryItem(categoryId);
			if (oldCatetoryItem.getIsLeafBoolean() != catetoryItem
					.getIsLeafBoolean()) {
				// 如果原结点为非叶子，新节点为叶子时，需要判断原来的节点下面是否有子节点
				if (catetoryItem.getIsLeafBoolean()) {
					List childList = getSucCategoryList(categoryId, null);

					if (childList != null && childList.size() != 0) {
						throw new SucCatetoryException(
								"该类别是中间节点，并且其下面有子节点，不能将该分类调整为叶子节点！");
					}
				} else {
					// 如果原结点为叶子，新节点为非叶子时，需要判断原来的节点下面是否数据
					boolean isUsed = isUsedCatetory(categoryId);

					if (isUsed) {
						throw new SucCatetoryException(
								"该类别正在被其他数据使用，不允许将该类别调整为非叶子节点！<br /> 如果想调整为非叶子节点，请先删除该类别下的所有数据！");
					}
				}
			}

			String newName = java.net.URLDecoder.decode(catetoryItem.getName(),
					"UTF-8");

			boolean flag = existsName(catetoryItem.getParenetSortId(), newName,
					categoryId);
			if (flag) {
				throw new SucCatetoryException("已经存在了该名称的类别，同一分类下不能有同名的类别！");
			}

			// 处理关键字字段
			String keyWords = catetoryItem.getKeyWords();
			if (!StringUtils.isEmpty(keyWords)) {
				oldCatetoryItem.setKeyWords(java.net.URLDecoder.decode(
						keyWords, "UTF-8"));
			}

			// 处理描述字段
			String desc = catetoryItem.getDescr();
			if (!StringUtils.isEmpty(desc)) {
				oldCatetoryItem.setDescr(java.net.URLDecoder.decode(desc,
						"UTF-8"));
			}

			oldCatetoryItem.setName(newName);
			oldCatetoryItem.setIsLeaf(catetoryItem.getIsLeaf());
			oldCatetoryItem.setPrority(catetoryItem.getPrority());
			oldCatetoryItem.setSortId(catetoryItem.getSortId());
			oldCatetoryItem.setCreateUser(catetoryItem.getCreateUser());
			oldCatetoryItem.setId(categoryId);
			categoryDao.saveOrUpdate(oldCatetoryItem);

		} catch (SucCatetoryException e) {
			log.error(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SucCatetoryException(e);
		}
	}

	 
	public String deleteCatetoryItem(Long catetoryId, MallUser user,
			Integer opStatus) throws SucCatetoryException {
		try {
			SucCategory catetoryItem = getCatetoryItem(catetoryId);
			
			if (catetoryItem == null)
				return "操作的类别已经不存在，请确认执行了正确的操作！";
			if (opStatus == null)
				return "请确认执行了正确的操作！";
			
			if (EnumUtils.status.删除.ordinal() == opStatus) {
				// 删除操作
				if (!catetoryItem.getIsLeafBoolean()) {
					List childList = getSucCategoryList(catetoryId, null);
					// 判断是否是叶子节点，如果是非叶子节点并且其下面有子节点，则不允许删除
					if (childList != null && childList.size() != 0) {
						return "该类别是中间节点，并且其下面有子节点，只有先删除其所有子节点及其数据才能删除该节点！";
					}
					// 仅仅是非叶子节点,并且非叶子节点下没有子节点，则运行直接删除
					// 执行删除操作
					updateCatetoryItem(catetoryId,
							EnumUtils.status.删除.ordinal(), user.getName());
					return "类别删除成功！";
				}
				// 当待删除的节点为叶子节点是，需要查询该类别是否正在被其他数据使用
				boolean isUsed = isUsedCatetory(catetoryId);
				if (isUsed) {
					return "该类别正在被其他数据使用，不允许删除该类别！<br /> 如果想删除该类别，请先删除该类别下的所有数据！";
				} else {
					updateCatetoryItem(catetoryId,
							EnumUtils.status.删除.ordinal(), user.getName());
					return "类别删除成功！";
				}
			} else if (EnumUtils.status.正常.ordinal() == opStatus) {
				updateCatetoryItem(catetoryId, opStatus, user.getName());
				return "操作成功！";
			} else {
				return "操作类型错误，请确认执行了正确的操作！";
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SucCatetoryException(e);
		}
	}
	
	
	private boolean existsName(Long parentId, String name, Long id){
		StringBuilder queryString = new StringBuilder("from SucCategory where parenetSortId = ? and name = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(parentId);
		params.add(name.trim());
		
		if(id != null)
		{
			queryString.append(" and id != ?");
			params.add(id);
		}
		List<SucCategory> result = categoryDao.getListByParams(queryString.toString(), params.toArray());
		
		if(result != null && !result.isEmpty())
		{
			return true;
		}
		
		return false;
	}
	
	private boolean isUsedCatetory( Long catetoryId ){
		return false;
	}
	
	/**
	 * 删除指定的类别
	 * 
	 * @param catetoryId
	 */
	private void updateCatetoryItem(Long catetoryId, Integer status, String userName)
	{
		//开始删除
		String updateString = "update SucCategory set status = ?, createUser = ? where id = ? ";
		Object[] params = {status, userName, catetoryId};
		categoryDao.updateByParams(updateString, params);
	}
	
	private void checkCategoryItem(SucCategory catetoryItem)
			throws SucCatetoryException, UnsupportedEncodingException {
		
		nullCheck(catetoryItem, "请填写类别信息！");
		
		Long parentId = catetoryItem.getParenetSortId();
		
		nullCheck(parentId, "父类别不存在，请确认执行了正常的操作！");
		
		nullCheck(catetoryItem.getName(), "类别的“名称”不能为空！");
		
		if (parentId.longValue() != CatetoryConstants.ROOT_ITEM_ID) {
			SucCategory parentCatetoryItem = getCatetoryItem(parentId);
			nullCheck(parentCatetoryItem, "父类别不存在，无法完成类别的创建！");
			if (parentCatetoryItem.getIsLeafBoolean()) {
				throw new SucCatetoryException("父类别是叶子节点，不允许在叶子节点创建子类别！");
			}
		}
		
		String newName = java.net.URLDecoder.decode(catetoryItem.getName(),"UTF-8");
		
		if (existsName(parentId, newName, null)) {
			throw new SucCatetoryException("已经存在了该名称的类别，同一分类下不能有同名的类别！");
		}
	}

	private SucCategory packageCategory(SucCategory catetoryItem)
			throws UnsupportedEncodingException {
		Date currentDate = new Date();
		catetoryItem.setStatus(EnumUtils.status.正常.ordinal());
		catetoryItem.setCreateTime(currentDate);			
		
		//处理关键字字段
		String keyWords = catetoryItem.getKeyWords();
		if(!StringUtils.isEmpty(keyWords))
		{
			catetoryItem.setKeyWords(java.net.URLDecoder.decode(keyWords, "UTF-8"));
		}
		
		//处理描述字段
		String desc = catetoryItem.getDescr();
		if(!StringUtils.isEmpty(desc))
		{
			catetoryItem.setDescr(java.net.URLDecoder.decode(desc, "UTF-8"));
		}
		String newName = java.net.URLDecoder.decode(catetoryItem.getName(),
				"UTF-8");
		catetoryItem.setName(newName);
		catetoryItem.setOperationTime(currentDate);
		return catetoryItem;
	}
	
	/**
	 * 如果obj为空，则抛出相应异常
	 * @param obj
	 * @param msg
	 * @throws SucCatetoryException
	 */
	private void nullCheck(Object obj , String msg)
			throws SucCatetoryException {
		if(obj == null){
			throw new SucCatetoryException(msg);
		}else {
			if(obj instanceof String && StringUtils.isEmpty((String)obj)){
				throw new SucCatetoryException(msg);
			}
			if(obj instanceof List && ((List) obj).size() == 0){
				throw new SucCatetoryException(msg);
			}
		}
	}


}
