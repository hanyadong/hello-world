package com.cy.pj.sys.service;

import java.util.List;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.dao.pageDao;

public class DefaultPageService<T> implements PageService<T> {

	private pageDao<T> sysDao;
	public DefaultPageService(pageDao<T> sysDao) {
		this.sysDao = sysDao;
	}

	@Override
	public PageObject<T> findPageObjects(String username, Integer pageCurrent) {
		//1.判定参数有效性 合法性
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("当前页码不正确");
		}
		//2.基于用户名查询总记录数 并进行判定
		int count = sysDao.getRowCount(username);
		if(count == 0) {
			throw new ServiceException("系统没有查询到对应记录");
		}
		int pageSize = 3;
		int pageStart = (pageCurrent-1)*pageSize;
		//3.当总数不为0时执行查询并返回结果
		List<T> list = sysDao.findPageObjects(username, pageStart, pageSize);
		//4.对查询结果进行封装并返回
		PageObject<T> po = new PageObject<>();
		po.setPageCount((int) Math.ceil(count/pageSize));
		po.setPageCurrent(pageCurrent);
		po.setRecords(list);
		po.setRowsCount(count);
		return po;
	}

}
