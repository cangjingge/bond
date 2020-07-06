package com.chungkui.bond.oauth2.server.service;

import com.chungkui.bond.authmanager.client.UserInfoClient;
import com.chungkui.bond.authmanager.dock.UserDetailsDock;
import com.chungkui.bond.common.core.api.ResBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("iUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserInfoClient userInfoClient;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResBus<UserDetailsDock> resBus = userInfoClient.loadUserDetails(username);
        UserDetailsDock userDetailsDock = resBus.getData();
        return User.withUsername(username)
                .password(passwordEncoder.encode("123456"))
                .authorities("ROLE_ADMIN") //权限
                .build();//构建一个User对象
    }
}
