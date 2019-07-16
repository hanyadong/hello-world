package com.cy.pj.sys.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.sys.pojo.SysUser;
import com.cy.pj.sys.pojo.SysUserDeptVo;

@Mapper
public interface SysUserDao extends pageDao<SysUserDeptVo>{

	//修改禁用状态
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	
	int insertObject(SysUser user);
	
	SysUserDeptVo findObjectById(Integer id);
	
	int updateObject(SysUser user);
	
	SysUser findOjbectByUserName(String username);
}
