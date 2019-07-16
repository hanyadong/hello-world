package com.cy.pj.sys.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.sys.dao.SysMenusDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.pojo.SysUser;

@Service
public class ShiroUserRealm extends AuthorizingRealm {
	
	private SysUserDao userDao;
	private SysUserRoleDao userRoleDao;
	private SysRoleMenuDao roleMenuDao;
	private SysMenusDao menuDao;
	@Autowired
	public ShiroUserRealm(SysUserDao userDao,SysUserRoleDao userRoleDao,
			SysRoleMenuDao roleMenuDao,SysMenusDao menuDao) {
		this.userDao=userDao;
		this.userRoleDao=userRoleDao;
		this.roleMenuDao=roleMenuDao;
		this.menuDao=menuDao;
	}

	/**
	 * 设置凭证匹配器(与用户添加操作使用相同的加密算法)
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		//设置加密算法
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		hashedCredentialsMatcher.setHashIterations(1);
		super.setCredentialsMatcher(hashedCredentialsMatcher);
	}

	/**
 	通过此方法完成认证数据的获取及封装,系统 底层会将认证数据传递认证管理器，由认证管理器完成认证操作。
	 */
	/** 负责认证的*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.获取用户名(用户页面输入)
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		//2.基于用户名查询用户信息
		SysUser user = userDao.findOjbectByUserName(username);
		if (user == null)
			throw new UnknownAccountException("用户名不存在");
		if (user.getValid() == 0)
			throw new LockedAccountException("此用户被禁用");
		//封装用户信息
		@SuppressWarnings("static-access")
		ByteSource bs = new ByteSource.Util().bytes(user.getSalt());
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				user, user.getPassword(), bs, getName());//realName
		return info;
	}

	/** 负责授权的*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取用户ID
		SysUser user = (SysUser)principals;
		Integer userId = user.getId();
		//基于用户ID获取角色ID
		List<Integer> roleIds = userRoleDao.findObjectByUserId(userId);
		verifyListParam(roleIds);
		//基于角色ID获取菜单ID
		Integer[] array = {};
		List<Integer> menuIds = roleMenuDao.findMenuIdsByRoleIds(roleIds.toArray(array));
		verifyListParam(menuIds);
		//基于菜单ID获取权限集合
		List<String> Permissions = menuDao.findPermissions(menuIds.toArray(array));
		verifyListParam(Permissions);
		//返回封装的权限集合信息
		Set<String> permissInfo = new HashSet<>();
		for (String p : Permissions) {
			if(!StringUtils.isEmpty(p))
				permissInfo.add(p);
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(permissInfo);
		return info;
	}

	private void verifyListParam(@SuppressWarnings("rawtypes") List list) {
		if (list == null || list.size() == 0)
			throw new ServiceException("没有相关权限");
	}
	
	
	
}
