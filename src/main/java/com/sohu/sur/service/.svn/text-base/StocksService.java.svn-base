package com.sohu.sur.service;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.sur.base.exception.SucGiftException;
import com.sohu.sur.dao.admin.StocksDao;
import com.sohu.sur.model.admin.SucStocks;
import com.sohu.sur.util.Pagination;

/**
 * @Title: StocksService.java
 * @Package com.sohu.sur.service
 * @Description: TODO
 * @author leiyangbj6779
 * @date 2011-12-8 下午6:21:48
 * @version V1.0
 */
@Service
public class StocksService {
	protected final Log log = LogFactory.getLog(getClass());
	@Autowired
	private StocksDao stocksDao;

	public List<SucStocks> getStocksList(String querySql, Object[] params,
			Pagination pagination) {
		return stocksDao.getObjectList(querySql, pagination, params);
	}

	/**
	 * 获取指定的礼品对象
	 * 
	 */
	public SucStocks getStocks(Long giftId, String cardId) {
		SucStocks stocks = null;
		try {
			String queryString = "from SucStocks where gift_id = ? and card_id = ? ";
			Object[] params = { giftId, cardId };

			List<SucStocks> stockList = stocksDao.getListByParams(queryString,
					params);
			if (stockList != null && stockList.size() >= 1) {
				stocks = stockList.get(0);
			}

		} catch (Exception e) {
			log.error(e.getCause(), e);
		}
		return stocks;
	}

	/**
	 * 添加库存
	 * 
	 */
	public void createStocks(SucStocks stocks) throws SucGiftException {
		try {
			stocksDao.save(stocks);
		} catch (Exception e) {
			log.error(e.getCause(), e);
			throw new SucGiftException(e);
		}
	}

	/**
	 * 保存编辑后的礼品对象
	 * 
	 * @see com.sohu.suc.gift.service.GiftService#updateGift(com.sohu.suc.gift.model.SucGift)
	 */
	public void updateStocks(SucStocks stocks) throws SucGiftException {
		try {
			stocks.setUpdateTime(new Date());
			stocksDao.saveOrUpdate(stocks);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new SucGiftException(e);
		}
	}

	public StocksDao getStocksDao() {
		return stocksDao;
	}

	public void setStocksDao(StocksDao stocksDao) {
		this.stocksDao = stocksDao;
	}

	/**
     * 获取指定的礼品对象的库存， 同时更新库存状态
     * @param giftId
     * @return SucStocks
     */
    public SucStocks useStocks(Long giftId) {
        SucStocks stocks = null;
        synchronized(giftId){
            try
            {           
                String queryString = "from SucStocks where gift_id = ? and status = 0 ";
                Object[] params = {giftId};
                
                Pagination pagination = new Pagination(1,1);  
                
                List<SucStocks> stockslist = stocksDao.getObjectList(queryString, pagination, params);
                if (stockslist != null && stockslist.size()>0)
                     stocks = (SucStocks)stockslist.get(0);
                if (stocks != null && stocks.getStatus().intValue() == 0) {
                    stocks.setStatus(new Integer(1));
                    stocks.setUpdateTime(new Date());
                    stocksDao.saveOrUpdate(stocks);
                }
                
            } catch (Exception e)
            {
                log.error(e.getMessage(),e);
                stocks = null;
            }
        }//
        
        return stocks;
        
    }
    
}
