package com.cy.pj.sys.service;

import java.util.List;
import java.util.Map;

import com.cy.pj.sys.pojo.SysDept;

public interface SysDeptService {

	List<Map<String,Object>> findObjects();
	
	int deleteObjectById(Integer id);
	
	List<Map<String,Object>> doFindZTreeNodes();
	
	int insertObject(SysDept dept);
	
	int updateObject(SysDept dept);
}
