package cn.wyb.personal.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wyb.personal.model.po.ModulePO;
import cn.wyb.personal.model.po.RolePO;
import cn.wyb.personal.model.po.UserPO;
import cn.wyb.personal.service.user.UserService;

public class AuthRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	//认证.登录
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;//获取用户输入的token
		String username = utoken.getUsername();
		UserPO user = userService.findUserByUderName(username);
		if (user == null) {
			throw new AccountException("该用户不存在");
		}
		ByteSource source = ByteSource.Util.bytes(username);
		return new SimpleAuthenticationInfo(user, user.getPassword(), source, this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
	}

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		UserPO user = (UserPO) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
		List<String> permissions = new ArrayList<>();
		Set<String> roleSet = new HashSet<>();
		Set<RolePO> roles = user.getRoles();
		if (roles.size() > 0) {
			for (RolePO role : roles) {
				roleSet.add(role.getRname());
				Set<ModulePO> modules = role.getModules();
				if (modules.size() > 0) {
					for (ModulePO module : modules) {
						permissions.add(module.getMname());
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissions);//将权限放入shiro中.
		info.setRoles(roleSet);
		return info;
	}

}