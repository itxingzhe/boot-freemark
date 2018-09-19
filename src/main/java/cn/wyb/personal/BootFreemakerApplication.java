package cn.wyb.personal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Create Time: 2018年04月26日 13:03
 * C@author wyb
 **/
@SpringBootApplication
//@ServletComponentScan
@MapperScan("cn.wyb.personal.dao")
public class BootFreemakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootFreemakerApplication.class,args);
    }
}
