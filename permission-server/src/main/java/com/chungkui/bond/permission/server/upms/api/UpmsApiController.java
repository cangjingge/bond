package com.chungkui.bond.permission.server.upms.api;

import com.chungkui.bond.commons.api.UpmsApi;
import com.chungkui.bond.commons.bean.*;
import com.chungkui.bond.commons.shiro.SecurityUserInfo;
import com.chungkui.bond.permission.server.common.BaseController;
import com.chungkui.bond.permission.server.upms.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Copyright (C), 2019/5/29, sunflow开发团队
 * 〈用户信息管理〉<br>
 * 〈功能详细描述〉
 *
 * @author jason
 * @fileName: UserInfoController.java
 * @date: 2019/5/29 20:45
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("/")
public class UpmsApiController extends BaseController<UserInfo> implements UpmsApi {
    private final static Logger LOG = LoggerFactory.getLogger(UpmsApiController.class);

    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    RouterService routerService;
    @Autowired
    UserDeptService userDeptService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleInfoService roleInfoService;
    @Override
    public List<Permission> listPermissionWithRoles() {
        return permissionService.listWithRoles();
    }

    @Override
    public Set<String> listRoleCodeByUser(@RequestBody UserInfo userInfo) {
        return roleInfoService.listRoleCodeByUser(userInfo);
    }

    @Override
    public List<RoleInfo> listRoleInfo() {
        return roleInfoService.list();
    }

    @Override
    public List<Router> buildMenuByUser(@RequestBody UserInfo shiroUser) {
        return routerService.buildMenuByUser(shiroUser);
    }

    @Override
    public UserInfo getUserDetailByLoginName(@RequestParam String loginName) {
        return null;
    }

    @Override
    public SecurityUserInfo getOrRegisterUser(@RequestBody  ThirdAuthUser user) {
        return null;
    }

    @Override
    public UserInfo getUserByLoginName(@RequestParam String loginName) {
        return userInfoService.getUserByLoginName(loginName);
    }
}
