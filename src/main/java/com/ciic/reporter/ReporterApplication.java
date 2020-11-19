package com.ciic.reporter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.ciic.reporter.**.mapper")
public class ReporterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReporterApplication.class, args);
    }

}
