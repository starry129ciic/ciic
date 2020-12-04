package com.ciic.reporter.common.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
 
@SpringBootApplication
public class exampleApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(exampleApplication.class, args);
    }
 
    /**
     * 配置跨域访问的过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean registerFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.addUrlPatterns("/*");
        bean.setFilter(new CrosFilter());
        return bean;
    }
}