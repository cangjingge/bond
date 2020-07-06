package com.chungkui.bond.oauth2.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.chungkui.bond")
public class Oauth2ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }
}
