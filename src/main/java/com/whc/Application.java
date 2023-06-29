package com.whc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: hc.wan
 * @CreateTime: 2023-06-29 13:22
 * @Description:
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.whc")
public class Application {

    public static void main(String[] args){
        try {
            ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("server start successfully！");
    }
}
