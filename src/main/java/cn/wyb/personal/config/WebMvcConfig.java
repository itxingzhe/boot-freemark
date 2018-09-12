package cn.wyb.personal.config;

import cn.wyb.personal.interceptor.UserActionInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

	@Bean
	public UserActionInterceptor userActionInterceptor() {
		return new UserActionInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		logger.info(">>>>>>>>>>>>>>>>> webInterceptor <<<<<<<<<<<<<<<<<");
		registry.addInterceptor(userActionInterceptor()).excludePathPatterns("/", "/user/toLogin", "/user/doLogin",
				"/user/toRegister", "/user/logout", "/css/**", "/js/**", "/img/**", "/jars/**", "/error*");
	}

}