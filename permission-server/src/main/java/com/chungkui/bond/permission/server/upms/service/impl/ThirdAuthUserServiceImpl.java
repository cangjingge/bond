package com.chungkui.bond.permission.server.upms.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chungkui.bond.commons.bean.ThirdAuthUser;
import com.chungkui.bond.commons.bean.UserInfo;
import com.chungkui.bond.commons.bean.UserRole;
import com.chungkui.bond.commons.shiro.SecurityUserInfo;
import com.chungkui.bond.permission.server.upms.mapper.ThirdAuthUserMapper;
import com.chungkui.bond.permission.server.upms.remoteclient.ShiroApiClient;
import com.chungkui.bond.permission.server.upms.service.ThirdAuthUserService;
import com.chungkui.bond.permission.server.upms.service.UserInfoService;
import com.chungkui.bond.permission.server.upms.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright (C), 2019/5/29, sunflow开发团队
 * 〈第三方权限登陆信息表〉<br>
 * 〈第三方权限登陆信息表 用于记录第三方系统登陆本系统中记录和自己用户关联的信息。 服务实现类〉
 *
 * @author jwy
 * @fileName: ThirdAuthUserServiceImpl.java
 * @date: 2019/5/29 20:47
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class ThirdAuthUserServiceImpl extends ServiceImpl<ThirdAuthUserMapper, ThirdAuthUser> implements ThirdAuthUserService {

    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    ShiroApiClient shiroApiClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SecurityUserInfo getOrRegisterUser(ThirdAuthUser user) {
        String thirdId = user.getSource() + "_" + user.getId();
        ThirdAuthUser dbAuthUser = super.getById(thirdId);
        if (dbAuthUser != null) {
            return shiroApiClient.getUserDetailByLoginName(dbAuthUser.getLoginName());
        } else {
            //注册新用户
            UserInfo userInfo = new UserInfo();
            userInfo.setLoginName(user.getSource() + user.getUsername());

            userInfo.setName(user.getUsername());
            userInfo.setPwd("00000");
            userInfo.setAvatar(user.getAvatar());
            userInfoService.save(userInfo);
            user.setUserId(userInfo.getId());
            /*保证id不会重复*/
            user.setId(thirdId);
            user.setLoginName(user.getSource() + user.getUsername());
            UserRole userRole=new UserRole();
            userRole.setRoleCode("thirdPart");
            userRole.setUserId(userInfo.getId());
            userRoleService.save(userRole);
            save(user);
            return shiroApiClient.getUserDetailByLoginName(userInfo.getLoginName());
        }
    }

}
