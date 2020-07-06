package com.chungkui.bond.authmanager.controller;

import com.chungkui.bond.authmanager.bean.UserInfo;
import com.chungkui.bond.authmanager.dock.UserDetailsDock;
import com.chungkui.bond.authmanager.service.UserInfoService;
import com.chungkui.bond.common.core.api.ResBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取用户详细信息：用户基本信息，角色信息，权限信息
     */
    @GetMapping("/loadUserDetails")
    public ResBus<UserDetailsDock> loadUserDetails(@RequestParam String username) {
        UserInfo userInfo = userInfoService.getUserByUsername(username);
        UserDetailsDock userDetailsDock = new UserDetailsDock();
        userDetailsDock.setUserInfo(userInfo);
        /*获取用户基本信息*/
        /*获取用户角色信息*/
        /*获取用户权限信息*/
        return ResBus.success(userDetailsDock);
    }
}
