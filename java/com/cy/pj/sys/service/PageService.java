package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;

public interface PageService<T> {

	
	/**分页查询 传入用户名 当前页 和总页数 返回 当前页数据*/
	PageObject<T> findPageObjects(String username,Integer pageCurrent);
}
