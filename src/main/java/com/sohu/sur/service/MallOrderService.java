package com.sohu.sur.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.sur.base.exception.SucScoreException;
import com.sohu.sur.dao.order.MallOrderDao;
import com.sohu.sur.dto.OrderSearchCondition;
import com.sohu.sur.model.admin.MallOrder;
import com.sohu.sur.util.Pagination;
import com.sohu.sur.util.PaginationSupport;
import com.sohu.sur.util.PaybackUtil;
import com.sohu.sur.util.gift.TopGift;

/**
 * 
 * 订单管理实现类
 * 
 * @author timzhao
 */
@Service
public class MallOrderService {

    private static Logger logger = LoggerFactory.getLogger(MallOrderService.class);

    @Autowired
    private MallOrderDao mallOrderDao;
    
    private PaybackUtil paybackUtil;

    /**
     * 查找所有订单
     * 
     * @param page
     * @param pageSize
     * @return
     */
    public PaginationSupport getOrderAll(int page, int pageSize) {
        int begin = (page - 1) * pageSize;
        String params[] = new String[] {};
        String hql = "select o from MallOrder o where 1=1 order by o.createTime desc";
        int rowCount = mallOrderDao.getTotalCount(hql, params);
        List list = mallOrderDao.getListByPosition(begin, pageSize, hql, params);
        PaginationSupport ps = new PaginationSupport(list, rowCount, begin, pageSize);
        return ps;
    }

    /**
     * 搜索订单
     * 
     * @param condition
     * @param page
     * @param pageSize
     * @return
     */
    public PaginationSupport getOrderCondition(OrderSearchCondition condition, int page, int pageSize) {
        int begin = (page - 1) * pageSize;
        List params = new ArrayList();
        String hql = "select o from MallOrder o where 1=1 ";
        if (condition != null) {
            if (condition.getDealId() != null && !condition.getDealId().equals("")) {
                hql += " and o.dealId= ? ";
                params.add(condition.getDealId());
            }

            if (condition.getGiftName() != null && !condition.getGiftName().equals("")) {
                hql += " and o.giftName like ? ";
                params.add("%" + condition.getGiftName() + "%");
            }

            if (condition.getUserId() != null && !condition.getUserId().equals("")) {
                hql += " and o.userId like ? ";
                params.add("%" + condition.getUserId() + "%");
            }

            if (condition.getUserName() != null && !condition.getUserName().equals("")) {
                hql += " and o.receiveUserName like ? ";
                params.add("%" + condition.getUserName() + "%");
            }

            if (condition.getUserTel() != null && !condition.getUserTel().equals("")) {
                hql += " and o.receiveTel like ? ";
                params.add("%" + condition.getUserTel() + "%");
            }

            if (condition.getStatus() != 6) {
                hql += " and o.status= ? ";
                params.add(condition.getStatus());
            }

            if (condition.getGiftType() != 3) {
                hql += " and o.giftType= ? ";
                params.add(condition.getGiftType());
            }

            if (!condition.getType().equals("3")) {
                hql += " and o.type= ?";
                params.add(condition.getType());
            }

            if (condition.getStartTime() != null && !condition.getStartTime().equals("")) {
                hql += " and o.createTime>= ? ";
                params.add(condition.getStartTime());
            }

            if (condition.getEndTime() != null && !condition.getEndTime().equals("")) {
                hql += " and o.createTime<= ? ";
                params.add(condition.getEndTime());
            }
        }
        hql += " order by o.createTime desc";

        int rowCount = mallOrderDao.getTotalCount(hql, params.toArray());
        List list = mallOrderDao.getListByPosition(begin, pageSize, hql, params.toArray());
        PaginationSupport ps = new PaginationSupport(list, rowCount, begin, pageSize);
        return ps;
    }
    /**
     * 搜索订单 导出
     * @param condition
     * @return
     */
    public List getOrderConditionExcel(OrderSearchCondition condition) {
        List params = new ArrayList();
        String hql = "select o from MallOrder o where 1=1 ";
        if (condition != null) {
            if (condition.getDealId() != null && !condition.getDealId().equals("")) {
                hql += " and o.dealId= ? ";
                params.add(condition.getDealId());
            }

            if (condition.getGiftName() != null && !condition.getGiftName().equals("")) {
                hql += " and o.giftName like ? ";
                params.add("%" + condition.getGiftName() + "%");
            }

            if (condition.getUserId() != null && !condition.getUserId().equals("")) {
                hql += " and o.userId like ? ";
                params.add("%" + condition.getUserId() + "%");
            }

            if (condition.getUserName() != null && !condition.getUserName().equals("")) {
                hql += " and o.receiveUserName like ? ";
                params.add("%" + condition.getUserName() + "%");
            }

            if (condition.getUserTel() != null && !condition.getUserTel().equals("")) {
                hql += " and o.receiveTel like ? ";
                params.add("%" + condition.getUserTel() + "%");
            }

            if (condition.getStatus() != 6) {
                hql += " and o.status= ? ";
                params.add(condition.getStatus());
            }

            if (condition.getGiftType() != 2) {
                hql += " and o.giftType= ? ";
                params.add(condition.getGiftType());
            }

            if (!condition.getType().equals("3")) {
                hql += " and o.type= ?";
                params.add(condition.getType());
            }

            if (condition.getStartTime() != null && !condition.getStartTime().equals("")) {
                hql += " and o.createTime>= ? ";
                params.add(condition.getStartTime());
            }

            if (condition.getEndTime() != null && !condition.getEndTime().equals("")) {
                hql += " and o.createTime<= ? ";
                params.add(condition.getEndTime());
            }
        }
        hql += " order by o.createTime desc";
        List list   =  new ArrayList<MallOrder>();
        List<MallOrder> listTemp = mallOrderDao.getAll(hql, params.toArray());
        logger.info("原list的size"+listTemp.size());
        Map<String,MallOrder> map = new HashMap<String,MallOrder>();
        String key = "";
        MallOrder mallOrderTemp = null;
        for(MallOrder mallOrder:listTemp){
        	
        	if(mallOrder.getReceiveUserName()==null||mallOrder.getReceiveUserName().length()==0||mallOrder.getReceiveTel()==null||mallOrder.getReceiveTel().length()==0||mallOrder.getReceiveAddr()==null||mallOrder.getReceiveAddr().length()==0){
        		logger.info("订单"+mallOrder.getDealId()+"无效");
        		continue;
        	}
        	
        	key = mallOrder.getReceiveUserName().trim()+mallOrder.getReceiveTel().trim()+mallOrder.getReceiveAddr().trim();
        	if(map.containsKey(key)){
        		mallOrderTemp = map.get(key);
        		logger.info("合并:"+mallOrderTemp.getDealId()+" , "+mallOrder.getDealId());
        		mallOrderTemp.setGiftName(mallOrderTemp.getGiftName()+" , "+mallOrder.getGiftName());
        		map.put(key, mallOrderTemp);
        	}else{
        		map.put(key, mallOrder);
        	}
        }
        for(Map.Entry entry:map.entrySet()){
        	MallOrder mo = (MallOrder)entry.getValue();
        	Object[] obj = {mo.getReceiveUserName(),mo.getDealId(),mo.getReceiveTel(),mo.getReceiveAddr(),mo.getGiftName(),mo.getDescr()}; 
        	list.add(obj); 
        } 
        logger.info("list的size"+list.size());
        return list;
    }

    /**
     * 查看指定订单详情
     * 
     * @param id
     * @return
     */
    public List<MallOrder> findOrderListById(long id) {

        String hql = "select o from MallOrder o where o.id=" + id;
        List<MallOrder> list = mallOrderDao.getList(hql);
        return list;
    }

    /**
     * 查找订单
     * 
     * @param id
     * @return
     */
    public MallOrder findOrderById(long id) {
        return mallOrderDao.get(id);
    }

    /**
     * 保存更新信息
     * 
     * @param m
     */
    public void saveMallOrder(MallOrder m) {
        this.mallOrderDao.saveOrUpdate(m);
    }

    public void payback(MallOrder m) {

        String uid = m.getUserId();
        Integer value = m.getSaleValue();
        String actionCode = "payback";
        try {
            paybackUtil.adjustUserBonus(uid, value, "payback", actionCode);
        } catch (SucScoreException e) {
            logger.error(e.getMessage(), e);
        }

    }

    
    /**
     * 根据订单号查找订单详情
     * @param dealid
     * @return
     */
    public MallOrder findOrderByDealId(String dealid) {
        MallOrder order = null;

        try {
            String queryString = "from MallOrder where dealId=?";
            Object[] params = { dealid };
            List list=null; 
            list=mallOrderDao.getListByParams(queryString.toString(), params);
            if (list!=null&&list.size()>0)
                order = (MallOrder)list.get(0);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return order;
    }
    
    
    
    /**
     * 个人用户查看礼品兑换记录
     * 
     * @see com.sohu.suc.gift.service.GiftDetailService#getGiftDetailList(java.lang.Long, java.lang.String, com.sohu.suc.util.Pagination)
     */
    public List<MallOrder> getGiftDetailList(Long regionId, String userId, Pagination pagination) 
    {
        List result = null;
        
        try
        {
            
            String queryString = "from MallOrder where regionId = ? and userId = ? order by createTime desc";
            Object[] params = {regionId, userId};
            
            result = mallOrderDao.getObjectList(queryString, pagination, params);
            
        } catch (Exception e)
        {
            logger.error(e.getMessage(),e);
        }
        
        return result;
    }
    
    /**
     * 个人用户查看礼品兑换记录
     * @param regionId
     * @param userId
     * @param type
     * @see com.sohu.suc.gift.service.GiftDetailService#getGiftDetailListByParam(java.lang.Long, java.lang.String, java.lang.String, com.sohu.suc.util.Pagination)
     */
    public List<MallOrder> getGiftDetailListByParam(Long regionId, String userId, String type, Pagination pagination) 
    {
        List result = null;
        
        try
        {
            
            String queryString = "from MallOrder where regionId = ? and userId = ? and type = ? order by createTime desc";
            Object[] params = {regionId, userId, type};
            
            result = mallOrderDao.getObjectList(queryString, pagination, params);
            
        } catch (Exception e)
        {
            logger.error(e.getMessage(),e);
        }
        
        return result;
    }
    
    /**
     * 查找最新订单  且订单状态不是已取消或者已作废
     * @param type
     * @param regionId
     * @param pagination
     * @return
     */
    public List<MallOrder> getTopGiftDetailList(String type, Long regionId, Pagination pagination) {
        List result = null;

        try {

            String queryString = "from MallOrder where regionId = ? and type = ?  and status != 5 and status!=6  order by createTime desc";
            Object[] params = { regionId, type };

            result = mallOrderDao.getObjectList(queryString, pagination, params);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return result;
    }

    /**
     * 查找最热兑换列表
     * @param regionId
     * @param pagination
     * @return
     */
    public List<TopGift> getTopGiftList(Long regionId, Pagination pagination) {

        List<TopGift> result = new ArrayList<TopGift>();

        List details = null;

        try {

            String queryString = "select b.id, b.name, b.view_name, a.sale_value, b.logo, a.hitcount FROM (SELECT gift_Id, sale_value, SUM(count) hitcount FROM SUC_GIFT_DETAIL WHERE type = 1 GROUP BY gift_Id ORDER BY hitcount DESC) a, SUC_GIFT b WHERE a.gift_id = b.id and b.is_shelf = 1 ORDER BY hitcount DESC LIMIT 0 ,10 ";
            
            details = mallOrderDao.executeList(queryString);
            
            for(Object obj: details){
                TopGift topGift = new TopGift();
                Object[] objs = (Object[])obj;
                topGift.setId((BigInteger)objs[0]);
                topGift.setName((String)objs[1]);
                topGift.setView_name((String)objs[2]);
                topGift.setType(((Integer)objs[3]).toString());
                topGift.setLogo((String)objs[4]);
                result.add(topGift);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return result;

    }
    
    /**
     * 博客首页列表
     * @param regionId
     * @param pagination
     * @return
     */
    public List<MallOrder> getBlogGiftDetailList(Long regionId,
            Pagination pagination) {
        List result = null;
        
        try
        {
            
            String queryString = "from MallOrder where regionId = ? and status != 5 and status!=6  order by createTime desc";
            Object[] params = {regionId};
            
            result = mallOrderDao.getObjectList(queryString, pagination, params);
            
        } catch (Exception e)
        {
            logger.error(e.getMessage(),e);
        }
        
        return result;
    }
    
    /**
     * 商品兑换人数
     * @param productid
     * @return
     */
    public java.math.BigDecimal getExchangeCount(Long productid){
    	java.math.BigDecimal ret = new java.math.BigDecimal("0");
        List details = null;        
        try
        {
            
            String queryString = "SELECT sum(COUNT) FROM suc_gift_detail d WHERE d.gift_Id ="+productid;
            details = mallOrderDao.executeList(queryString);
            for(Object obj: details){
            	if(obj!=null){
            		ret = (java.math.BigDecimal)obj;
            	}
                logger.info("-------->" + ret);
            }           
        } catch (Exception e)
        {
            logger.info(e.getMessage(),e);
        }
        return ret;
    }
}
