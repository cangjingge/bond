package com.chungkui.bond.oauth2.server.config;

import com.chungkui.bond.oauth2.server.handler.AuthenticationFaillHandler;
import com.chungkui.bond.oauth2.server.handler.AuthenticationPassedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * @author lengleng
 * @date 2019/2/1 认证相关配置
 */
@Primary
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*登录页面地址*/
        http.formLogin().loginPage("/sys/login")
                /*登录入口*/
                .loginProcessingUrl("/sys/login")
                .failureHandler(authenticationFailureHandler())
                .successHandler(authenticationPassedHandler())
                .and().authorizeRequests()
                /*/认证相关的请求直接允许*/
                .antMatchers("/sys/**").permitAll()
                /*其余请求需要登录*/
                .anyRequest().authenticated().and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    AuthenticationPassedHandler authenticationPassedHandler() {
        return new AuthenticationPassedHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFaillHandler();
    }

    /**
     * 密码加密
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
