package com.chungkui.bond.oauth2.server.controller;

import com.chungkui.bond.common.core.api.ResBus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("sys")
public class MainController {
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * form版本登录入口页面
     *
     * @param modelAndView
     * @param error
     * @return
     */
    @GetMapping("/login")
    public ModelAndView require(ModelAndView modelAndView, @RequestParam(required = false) String error) {
        modelAndView.setViewName("ftl/login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    /**
     * 登出入口
     *
     * @param authHeader Authorization
     */
    @DeleteMapping("/logout")
    public Object logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StringUtils.isBlank(authHeader)) {
            return ResBus.success();
        }
        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, "").trim();
        return removeToken(tokenValue);
    }

    /**
     * 令牌管理调用
     *
     * @param token token
     */
    @DeleteMapping("/{token}")
    public Object removeToken(@PathVariable("token") String token) {
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        if (accessToken == null || StringUtils.isBlank(accessToken.getValue())) {
            return ResBus.success();
        }
        OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
        // 清空用户信息
        /*   cacheManager.getCache(CacheConstants.USER_DETAILS).evict(auth2Authentication.getName());*/
        // 清空access token
        tokenStore.removeAccessToken(accessToken);
        // 清空 refresh token
        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        tokenStore.removeRefreshToken(refreshToken);
        return ResBus.success();
    }
}
