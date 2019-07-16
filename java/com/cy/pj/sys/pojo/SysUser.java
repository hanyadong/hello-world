package com.cy.pj.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SysUser implements Serializable{

	private static final long serialVersionUID = -5332901958127143702L;
	private Integer id;
	@NotBlank(message = "用户名不能为空")
	private String username;
	@NotBlank(message = "密码不能为空")
	private String password;
	private String salt;//盐值
	@Email
	@NotBlank(message = "email can not be null")
	private String email;
	private String mobile;
	private Integer valid=1;
	@NotNull(message = "部门id不能为null")
	private Integer deptId;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;

}
