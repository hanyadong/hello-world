package com.cy.pj.sys.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 封装数据库中数据或用户提交数据
 * @author will.han
 * member variable :id,username,operation,method,params,time,ip,createdTime
 */
@Data //lombok的注解 时pojo对象可以省略get() set()方法 lombok会自动为对象生成getset方法
public class SysLog implements Serializable{
	private static final long serialVersionUID = -6100771814310224896L;
	private Integer id;
	//用户名
	private String username;
	//用户操作
	private String operation;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//执行时长(毫秒)
	private Long time;
	//IP地址
	private String ip;
	//创建时间
	private Date createdTime;

	@Override
	public String toString() {
		return "SysLog [id=" + id + ", username=" + username + ", operation=" + operation + ", method=" + method
				+ ", params=" + params + ", time=" + time + ", ip=" + ip + ", createdTime=" + createdTime + "]";
	}

}
