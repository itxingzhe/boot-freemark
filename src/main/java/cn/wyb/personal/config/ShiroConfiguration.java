package cn.wyb.personal.config;

import cn.wyb.personal.shiro.AuthRealm;
import cn.wyb.personal.shiro.CredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {

	//配置自定义的权限登录器
	@Bean(name = "authRealm")
	public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
		AuthRealm authRealm = new AuthRealm();
		authRealm.setCredentialsMatcher(matcher);
		return authRealm;
	}

	//配置核心安全事务管理器
	@Bean(name = "securityManager")
	public DefaultSecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
		System.err.println("--------------shiro已经加载----------------");
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		return manager;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultSecurityManager manager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		//配置登录的url和登录成功的url
		bean.setLoginUrl("/user/toLogin");
		bean.setSuccessUrl("/");
		bean.setUnauthorizedUrl("/");
		//配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put("/jsp/login.jsp*", "anon"); //表示可以匿名访问
		filterChainDefinitionMap.put("/user/doLogin", "anon");
		filterChainDefinitionMap.put("/css/*", "anon");
		filterChainDefinitionMap.put("/js/*", "anon");
		filterChainDefinitionMap.put("/img/*", "anon");
		filterChainDefinitionMap.put("/jars/**", "anon");
		filterChainDefinitionMap.put("/js/*", "anon");
		filterChainDefinitionMap.put("/user/logout*", "anon");
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/jsp/error.jsp*", "anon");
		filterChainDefinitionMap.put("/jsp/index.jsp*", "authc");
		filterChainDefinitionMap.put("/*", "authc");//表示需要认证才可以访问
		filterChainDefinitionMap.put("/**", "authc");//表示需要认证才可以访问
		filterChainDefinitionMap.put("/*.*", "authc");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	//配置自定义的密码比较器
	@Bean(name = "credentialsMatcher")
	public CredentialsMatcher credentialsMatcher() {
		return new CredentialsMatcher();
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultSecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;
	}
}