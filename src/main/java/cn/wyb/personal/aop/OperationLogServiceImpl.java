package cn.wyb.personal.aop;

import cn.wyb.personal.annotation.MethodLog;
import cn.wyb.personal.common.enums.OperationTypeEnum;
import cn.wyb.personal.common.utils.BeanConvertor;
import cn.wyb.personal.dao.OperationLogMapper;
import cn.wyb.personal.model.param.Syslog;
import cn.wyb.personal.model.po.ModulePO;
import cn.wyb.personal.model.po.OperationLogPO;
import cn.wyb.personal.model.po.UserPO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * OperationLogServiceImpl: 操作日志切面.
 *
 * @author wangyibin
 * @date 2018/8/21 14:31
 * @see
 */
@Aspect
@Service
public class OperationLogServiceImpl {

	@Autowired
	private OperationLogMapper operationLogDao;

	/**
	 * 切点
	 */
	@Pointcut("@annotation(cn.wyb.personal.annotation.MethodLog)")
	//@Pointcut(value = "execution(* cn.wyb.personal.controller.*.*(..))")
	public void methodCachePointcut() {
	}


	/**
	 * 切面
	 *
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around("methodCachePointcut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
		Calendar ca = Calendar.getInstance();
		String operDate = df.format(ca.getTime());
		String ip = getIp(request);
		Subject subject = SecurityUtils.getSubject();
		UserPO user = (UserPO) subject.getSession().getAttribute("user");
		String loginName;
		if (user != null) {
			loginName = user.getUsername();
		} else {
			loginName = "匿名用户";
		}
		String methodRemark = getMthodRemark(point);
		String methodName = point.getSignature().getName();
		String packages = point.getThis().getClass().getName();
		if (packages.indexOf("$$EnhancerByCGLIB$$") > -1) { // 如果是CGLIB动态生成的类
			try {
				packages = packages.substring(0, packages.indexOf("$$"));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		String operatingcontent = "";
		Object[] method_param = null;

		Object object;
		try {
			method_param = point.getArgs(); //获取方法参数
			// String param=(String) point.proceed(point.getArgs());
			object = point.proceed();
		} catch (Exception e) {
			// 异常处理记录日志..log.error(e);
			throw e;
		}
		Syslog sysLog = new Syslog();
		sysLog.setOptId(11);
		sysLog.setLoginId(user.getUsername());
		sysLog.setLoginName(loginName);
		sysLog.setIpAddress(ip);
		sysLog.setMethodName(packages + "." + methodName);
		sysLog.setMethodRemark(methodRemark);
		sysLog.setOptDate(operDate);

		/**
		 * 用户操作
		 */
		if (OperationTypeEnum.ADD.name().equals(methodRemark)) {
			HttpServletRequest req = (HttpServletRequest) method_param[0];
			sysLog.setOperatingContent("新增用户: 用户名为 " + req.getParameter("userName"));

		}
		if ("删除用户".equals(methodRemark)) {
			String loginId = (String) method_param[1];
			sysLog.setOperatingContent("删除用户: 用户id为 " + loginId);
		}
		if ("修改用户".equals(methodRemark)) {
			HttpServletRequest req = (HttpServletRequest) method_param[0];
			sysLog.setOperatingContent("修改用户: 用户名为 " + req.getParameter("loginName"));
		}

		/**
		 * 角色操作
		 */
		if ("新增角色".equals(methodRemark)) {
			HttpServletRequest req = (HttpServletRequest) method_param[0];
			sysLog.setOperatingContent("新增角色: 角色名为 " + req.getParameter("roleDesc"));
		}
		if ("删除角色".equals(methodRemark)) {
			String roleId = (String) method_param[1];
			sysLog.setOperatingContent("新增角色: 角色id为 " + roleId);
		}
		if ("修改角色".equals(methodRemark)) {
			HttpServletRequest req = (HttpServletRequest) method_param[0];
			sysLog.setOperatingContent("修改角色: 角色名为 " + req.getParameter("roleDesc"));
		}

		/**
		 * 菜单操作
		 */
		if ("新增菜单".equals(methodRemark)) {
			ModulePO menuModel = (ModulePO) method_param[0];
			sysLog.setOperatingContent("新增菜单: 菜单名为 " + menuModel.getMname());
		}
		if ("删除菜单".equals(methodRemark)) {
			String menuId = (String) method_param[0];
			sysLog.setOperatingContent("删除菜单: 菜单id为 " + menuId);
		}
		if ("修改菜单".equals(methodRemark)) {
			ModulePO menuModel = (ModulePO) method_param[0];
			sysLog.setOperatingContent("修改菜单: 菜单名为 " + menuModel.getMname());
		}

		/**
		 * 空参数
		 */
		if ("".equals(methodRemark) || null == methodRemark) {
			sysLog.setOperatingContent("操作参数: " + method_param[0]);
		}
		OperationLogPO log = BeanConvertor.copy(sysLog, OperationLogPO.class);
		operationLogDao.insert(log);
		System.out.println("日志实体：" + sysLog.getLoginName() + sysLog.getMethodRemark());
		return object;

	}

	/**
	 * 方法异常时调用
	 *
	 * @param ex
	 */
	public void afterThrowing(Exception ex) {
		System.out.println("afterThrowing");
		System.out.println(ex);
	}

	/**
	 * 获取方法中的中文备注
	 *
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	public static String getMthodRemark(ProceedingJoinPoint joinPoint) throws Exception {

		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();

		Class targetClass = Class.forName(targetName);
		Method[] method = targetClass.getMethods();
		String methode = "";
		for (Method m : method) {
			if (m.getName().equals(methodName)) {
				Class[] tmpCs = m.getParameterTypes();
				if (tmpCs.length == arguments.length) {
					MethodLog methodCache = m.getAnnotation(MethodLog.class);
					if (methodCache != null) {
						methode = methodCache.remark();
					}
					break;
				}
			}
		}
		return methode;
	}

	/**
	 * 获取请求ip
	 *
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip.substring(0, index);
			} else {
				return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr().equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
}
