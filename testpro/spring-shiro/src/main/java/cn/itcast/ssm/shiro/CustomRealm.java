package cn.itcast.ssm.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.po.ActiveUser;
import cn.itcast.ssm.po.SysPermission;
import cn.itcast.ssm.po.SysUser;
import cn.itcast.ssm.service.SysService;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private SysService sysService;

	@Override
	public void setName(String name) {
		super.setName("CustomRealm");
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// token是用户输入的（包括用户名和密码）
		// token中取出用户输入的用户名
		String userCode = (String) token.getPrincipal();
		// 根据用户输入的用户名(userCode)从数据库中获取用户密码
		// 从数据库查询
		SysUser user = null;
		try {
			user = sysService.findSysUserByUserCode(userCode);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (user == null)
			return null;

		// 查询到的密码(加密过后的密码)
		String password = user.getPassword();

		// 盐salt，凭证加密的时候盐会自动加在密码的前面
		String salt = user.getSalt();

		ActiveUser activeUser = new ActiveUser();
		activeUser.setUserid(user.getId());
		activeUser.setUsercode(user.getUsercode());
		activeUser.setUsername(user.getUsername());
		// ...

		// 根据id取菜单
		List<SysPermission> menuList = null;
		try {
			menuList = sysService.findMenuListByUserId(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将用户菜单设置到activeUser
		activeUser.setMenus(menuList);

		// 将用户身份acriveUser设置到SimpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, password,
				ByteSource.Util.bytes(salt.getBytes()), this.getName());
		return simpleAuthenticationInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		// 从principal获取主身份信息
		// 将getPrimaryPrincipal方法返回值转成真实身份类型，
		// 也就是上面doGetAuthenticationInfo方法认证通过后填充到SimpleAuthenticationInfo中的身份信息
		ActiveUser activeUser = (ActiveUser) principal.getPrimaryPrincipal();
		// 根据身份信息获取权限信息
		// 从数据库获取到权限信息
		List<SysPermission> permissionList = null;
		try {
			permissionList = sysService.findPermissionListByUserId(activeUser.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> permissoins = new ArrayList<String>();
		for (SysPermission p : permissionList)
			permissoins.add(p.getPercode());

		// 查询到权限数据，返回授权信息
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 将上面查询到的权限信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(permissoins);

		return simpleAuthorizationInfo;
	}

	// 清除授权信息缓存
	public void clearCache() {
		PrincipalCollection ps = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(ps);
	}

}
