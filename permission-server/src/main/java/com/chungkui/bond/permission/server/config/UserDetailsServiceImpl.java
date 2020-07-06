package com.chungkui.bond.permission.server.config;

import com.chungkui.bond.commons.bean.UserInfo;
import com.chungkui.bond.permission.server.bean.IUserDetail;
import com.chungkui.bond.permission.server.upms.service.RoleInfoService;
import com.chungkui.bond.permission.server.upms.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("IUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private RoleInfoService roleInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoService.getUserByLoginName(s);
        return new IUserDetail(userInfo.getLoginName(), passwordEncoder.encode(userInfo.getPwd()), roleInfoService.listAuthorityCodeByUser(userInfo));
    }
}
