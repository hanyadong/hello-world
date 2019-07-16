package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleDao {

	@Delete("delete from sys_user_roles where id =#{id}")
	int deleteObjectByRoleId(Integer id);
	
	/**
	  * 插入用户角色表关系
	 */
	int insertObject(@Param("userId")Integer userId, @Param("roleIds")Integer[] roleIds	);
	
	/**
	 * 基于用户Id查找角色信息
	 */
	List<Integer> findObjectByUserId(Integer userId);
	
	int deleteObjectByUserId(Integer userId);
}
