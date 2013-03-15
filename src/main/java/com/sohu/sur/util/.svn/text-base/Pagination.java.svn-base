package com.sohu.sur.util;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

	public Pagination(int pageNO) {
		this.pageNO = 1;
		pageCount = 15;
		recordSum = -1;
		pageSum = 1;
		prePageNO = 1;
		nextPageNO = 1;
		needRecordSum = true;
		this.pageNO = pageNO;
	}

	public Pagination() {
		this.pageNO = 1;
		pageCount = 15;
		recordSum = -1;
		pageSum = 1;
		prePageNO = 1;
		nextPageNO = 1;
		needRecordSum = true;
	}

	public Pagination(int pageNO, int pageCount) {
		this.pageNO = 1;
		this.pageCount = 15;
		recordSum = -1;
		pageSum = 1;
		prePageNO = 1;
		nextPageNO = 1;
		needRecordSum = true;
		this.pageNO = pageNO;
		this.pageCount = pageCount;
	}
    
	public Pagination(int pageNO, int pageCount,int sum) {
		recordSum = sum;
		pageSum = 1;
		prePageNO = 1;
		nextPageNO = 1;
		needRecordSum = true;
		this.pageNO = pageNO;
		this.pageCount = pageCount;
	}
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNO() {
		return pageNO;
	}

	public void setPageNO(int pageNO) {
		if (recordSum > -1 && pageNO > getPageSum())
			this.pageNO = getPageSum();
		else
			this.pageNO = pageNO;
	}

	public int getPageSum() {
		return (recordSum - 1) / pageCount + 1;
	}

	public int getStartIndex() {
		int i = (pageNO - 1) * pageCount + 1;
		return i <= 0 ? 0 : i;
	}

	public int getEndIndex() {
		int i = pageNO * pageCount - 1;
		return i <= recordSum ? i : recordSum;
	}

	public int getRecordSum() {
		return recordSum;
	}

	public void setRecordSum(int recordSum) {
		this.recordSum = recordSum;
	}

	public int getNextPageNO() {
		return pageNO >= getPageSum() ? getPageSum() : pageNO + 1;
	}

	public int getPrePageNO() {
		return pageNO <= 1 ? 1 : pageNO - 1;
	}

	public boolean isNeedRecordSum() {
		return needRecordSum;
	}
	
	/**
	 * @param needRecordSum the needRecordSum to set
	 */
	public void setNeedRecordSum(boolean needRecordSum) {
		this.needRecordSum = needRecordSum;
	}
	
	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public Pagination(int pageNO, int pageCount, boolean needRecordSum) {
		this.pageNO = 1;
		this.pageCount = 15;
		recordSum = -1;
		pageSum = 1;
		prePageNO = 1;
		nextPageNO = 1;
		this.needRecordSum = true;
		this.pageNO = pageNO;
		this.pageCount = pageCount;
		this.needRecordSum = needRecordSum;
	}
	
	
	
	
	/**
	 * 生成导航数字
	 * @param count 	分页导航下面显示的导航数据的个数（比如：当count为5时将在下面显示 1，2，3，4，5个数字的导航，当当前的页数大于等于5时将显示后5~9页的导航）
	 * @return
	 */
    public List getPageNoList(int count){
    	
    	List noList = new ArrayList();
    	
    	if(count == 0)
    		count = 1;
    	
    	if(count < 0)
    		count = 5;//默认显示5个导航数字
    	
    	if(getPageSum() < count)
    	{
    		for(int i=0; i< getPageSum(); i++)    		
    			noList.add(i+1);
    		
    	}else if(pageNO <= getPageSum())
    	{
    		int nextStartNo = 1;
    		
        	if(pageNO > 3)
        	{
        		nextStartNo = getPrePageNO() - 1;
        	}
    		
    		int nextEnd = nextStartNo + count -1;
    		
    		//当到达最后5页时，每点击下一页移动一页
    		if(pageNO >= (count -1) && pageNO >= getPageSum() - (count -1))
    		{   
    			
    			nextStartNo = pageNO - 1;
    			
    			if(pageNO == getPageSum() - (count -1))
    				nextEnd = getPageSum() - 1; 
    			else
    				nextEnd = getPageSum();    			
    			
    		}
    		
    		for(int i=nextStartNo; i <= nextEnd; i++)
    		{
    			noList.add(i);
    		}
    	}		
    	 
        return noList;	
    }
    
	private int pageNO;
	private int pageCount;
	private int recordSum;
	private int pageSum;
	private int prePageNO;
	private int nextPageNO;
	private boolean needRecordSum;
	private String orderField;//排序: 字段名称
	
}
