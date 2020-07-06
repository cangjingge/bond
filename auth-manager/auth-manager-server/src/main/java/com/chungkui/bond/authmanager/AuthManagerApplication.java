package com.chungkui.bond.authmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(scanBasePackages = "com.chungkui.bond")
@EnableResourceServer
@EnableFeignClients
/*
@EnableGlobalMethodSecurity(prePostEnabled = true)
*/

public class AuthManagerApplication {
    public static void main(String[] args) {

        SpringApplication.run(AuthManagerApplication.class, args);
    }

}

