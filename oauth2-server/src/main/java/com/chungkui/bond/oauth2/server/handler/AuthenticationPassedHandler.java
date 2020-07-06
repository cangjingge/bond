package com.chungkui.bond.oauth2.server.handler;

import com.alibaba.fastjson.JSON;
import com.chungkui.bond.common.core.api.ResBus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationPassedHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json; charset=UTF-8");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        try {
            response.getWriter().write(JSON.toJSONString(ResBus.success(null,"认证通过")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
