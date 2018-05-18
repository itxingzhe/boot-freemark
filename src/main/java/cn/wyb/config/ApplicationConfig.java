package cn.wyb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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

/*    @Bean
    @Primary
    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml"));
        bean.setConfigLocation(new ClassPathResource("config/mybatis-page.xml"));
        return bean.getObject();
    }*/


}
