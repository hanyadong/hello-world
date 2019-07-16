package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.pojo.Menu;

@Mapper
public interface SysMenusDao {
	
	List<Menu> findMenuObjects();
	
	List<Map<String,Object>> findMapObjects();
	
	List<Node> findZtreeMenuNodes();
	
	int insertObject(Menu entity);
	
	int updateObject(Menu entity);
	
	int doDeleteObject(@Param("ids")Integer... ids);
	
	List<String> findPermissions(@Param("menuIds")Integer[] menuIds);
}
