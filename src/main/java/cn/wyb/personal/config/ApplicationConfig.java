package cn.wyb.personal.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Lists;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Create Time: 2018年04月26日 18:19
 * C@author wyb
 **/
@Configuration
public class ApplicationConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource getDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public ServletRegistrationBean statViewServle() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		//白名单：
		servletRegistrationBean.addInitParameter("allow", "192.168.1.218,127.0.0.1");
		//IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的即提示:Sorry, you are not permitted to view this page.
		servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
		//登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername", "druid");
		servletRegistrationBean.addInitParameter("loginPassword", "12345678");
		//是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean statFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		//添加过滤规则.
		filterRegistrationBean.addUrlPatterns("/*");
		//添加不需要忽略的格式信息.
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

	@Bean
	PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public ServletRegistrationBean druidServlet(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean();
		registrationBean.setServlet(new StatViewServlet());
		registrationBean.addUrlMappings("/druid/*");
		Map<String, String> initParameters = new HashMap<>();
		//禁用HTML页面上的“Rest All”功能
		initParameters.put("resetEnable", "false");
		// ip白名单（没有配置或者为空，则允许所有访问）
		//initParameters.put("allow", "127.0.0.1");
		//++监控页面登录用户名
		initParameters.put("loginUsername", "admin");
		//++监控页面登录用户密码
		initParameters.put("loginPassword", "admin123");
		//initParameters.put("deny", ""); //ip黑名单
		//如果某个ip同时存在，deny优先于allow
		registrationBean.setInitParameters(initParameters);
		return registrationBean;
	}

	//配置数据库的基本链接信息
	//可以在application.properties中直接导入
	@Bean(name = "dataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource(){
		return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws IOException {
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		Resource[] resources = new PathMatchingResourcePatternResolver()
				.getResources("classpath:mapping/*.xml");
		bean.setMapperLocations(resources);
		return bean;
	}


	/**
	 * 文件上传配置
	 * @return
	 */
	/*@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//文件最大
		factory.setMaxFileSize("10240KB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}*/



}
