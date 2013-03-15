package com.sohu.sur.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.sur.dao.admin.LotteryShowDao;
import com.sohu.sur.model.admin.LotteryShow;
import com.sohu.sur.util.PaginationSupport;

@Service
public class LotteryShowService {

    private static Logger logger = LoggerFactory.getLogger(LotteryShowService.class);

    @Autowired
    private LotteryShowDao lotteryShowDao;

    /**
     * 获得中奖博文列表
     * @param page
     * @param pageSize
     * @return
     */
    public PaginationSupport getAll(int page, int pageSize) {
        int begin = (page - 1) * pageSize;
        String params[] = new String[] {};
        String hql = " select l from LotteryShow l order by l.ctime desc";
        int rowCount = lotteryShowDao.getTotalCount(hql, params);
        List list = lotteryShowDao.getListByPosition(begin, pageSize, hql, params);
        PaginationSupport ps = new PaginationSupport(list, rowCount, begin, pageSize);
        return ps;
    }
    
    /**
     * 保存更新单个记录
     * @param lotteryShow
     */
    public void save(LotteryShow lotteryShow){
        lotteryShowDao.saveOrUpdate(lotteryShow);
    }
    
    /**
     * 获得单个记录
     * @param id
     * @return
     */
    public LotteryShow getById(Long id){
        LotteryShow lotteryShow = lotteryShowDao.get(id);
        return lotteryShow;
    }
    
    /**
     * 删除单个记录
     * @param id
     */
    public void delete(Long id){
        lotteryShowDao.remove(getById(id));
    }

}
