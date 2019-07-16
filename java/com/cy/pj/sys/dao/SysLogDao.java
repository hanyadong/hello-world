package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.pojo.SysLog;

@Mapper
public interface SysLogDao extends pageDao<SysLog>{
	
	int deleteObjects(@Param("ids")Integer... ids);
}
