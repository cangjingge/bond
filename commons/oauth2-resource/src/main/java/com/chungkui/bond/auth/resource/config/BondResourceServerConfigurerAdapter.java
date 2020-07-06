package com.chungkui.bond.auth.resource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class BondResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    @Autowired
    protected ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;



    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    RemoteTokenServices remoteTokenServices(){
        return new RemoteTokenServices ();
    }
    /**
     * 默认的配置，对外暴露
     *
     * @param httpSecurity
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        // 允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        httpSecurity.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        registry.antMatchers("/sys/loadUserDetails").permitAll();
        registry.anyRequest().authenticated().and().csrf().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {

        resources
                .authenticationEntryPoint(resourceAuthExceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);


    }

}
