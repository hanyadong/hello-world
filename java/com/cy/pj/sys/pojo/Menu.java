package com.cy.pj.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;
//id,name,parent,type,sort,url,permission
@Data
@ToString
public class Menu implements Serializable{
	private static final long serialVersionUID = -6450184336330076292L;
	private Integer id;
	private String name;
	private Integer type;
	private Integer sort;
	private String url;
	private String permission;
	private String note;
	private Integer parentId;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;
}
