package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.sys.pojo.SysRole;
import com.cy.pj.sys.pojo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao extends pageDao<SysRole>{
	
	@Delete("delete from sys_roles where id =#{id}")
	int deleteObject(Integer id);
	
	int insertObject(SysRole entity);
	
	SysRoleMenuVo findObjectById(Integer id);
	
	int updateObject(SysRole entity);
	
	@Select("select id, name from sys_roles")
	List<Map<String,Object>> findObjects();
}
