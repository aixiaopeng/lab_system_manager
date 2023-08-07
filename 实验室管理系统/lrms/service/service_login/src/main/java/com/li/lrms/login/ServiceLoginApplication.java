package com.li.lrms.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.li")
@SpringBootApplication

public class ServiceLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceLoginApplication.class,args);
    }
}
