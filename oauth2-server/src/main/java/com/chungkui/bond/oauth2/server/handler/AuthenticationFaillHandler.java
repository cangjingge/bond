package com.chungkui.bond.oauth2.server.handler;


import com.alibaba.fastjson.JSON;
import com.chungkui.bond.common.core.api.ResBus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class AuthenticationFaillHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) {
        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        try {
            response.getWriter().write(JSON.toJSONString(ResBus.failed(403,"认证失败")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
