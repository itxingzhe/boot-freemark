package cn.wyb.personal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.wyb.personal.model.po.UserPO;
import cn.wyb.personal.service.user.UserService;

/**
 * UserActionInterceptor: 拦截器 判断用户角色变更.
 *
 * @author wangyibin
 * @date 2018/7/26 13:21
 * @see
 */
public class UserActionInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(UserActionInterceptor.class);

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String contextPath = request.getServletPath();
		logger.debug("请求到达后台方法之前调用（controller之前）URL:" + contextPath);
		// 1. SecurityUtils获取session中的用户信息
		// HttpSession session=request.getSession();

		UserPO user = (UserPO) SecurityUtils.getSubject().getPrincipal();
		if (user != null && StringUtils.isNotEmpty(user.getUsername())
				&& null != user.getVersion()) {
			// 2. 获取数据库中的用户数据
			UserPO dataUser = this.userService.findUserByUderName(user.getUsername());
			// 3. 对比session中用户的version和数据库中的是否一致
			if (dataUser != null
					&& null != dataUser.getVersion()
					&& String.valueOf(user.getVersion()).equals(
					String.valueOf(dataUser.getVersion()))) {
				// 3.1 一样，放行
				return true;
			} else {
				// 3.2 不一样，这里统一做退出登录处理；
				SecurityUtils.getSubject().logout();
			}
		}
		return false;
	}
}
