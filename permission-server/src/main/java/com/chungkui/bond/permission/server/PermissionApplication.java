package com.chungkui.bond.permission.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Copyright (C), 2019/5/29, sunflow开发团队
 * 〈upms服务启动入口〉<br>
 * 〈功能详细描述〉
 *
 * @author jason
 * @fileName: Application.java
 * @date: 2019/5/29 20:51
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@SpringBootApplication
@EnableFeignClients
@EnableAuthorizationServer
/*
@EnableCaching
*/
public class PermissionApplication {
    public static void main(String[] args) {
        SpringApplication.run(PermissionApplication.class, args);
    }


}
