package com.chungkui.bond.commons.api;

import com.chungkui.bond.commons.bean.*;
import com.chungkui.bond.commons.shiro.SecurityUserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

public interface UpmsApi {
    @GetMapping("/listPermissionWithRoles")
    List<Permission> listPermissionWithRoles();

    @PostMapping("/listRoleCodeByUser")
    Set<String> listRoleCodeByUser(@RequestBody UserInfo userInfo);

    @GetMapping("/listRoleInfo")
    List<RoleInfo> listRoleInfo();

    /**
     * 移除
     *
     * @param shiroUser
     * @return
     */
    @PostMapping("/getUserDetailByLoginName")
    List<Router> buildMenuByUser(@RequestBody UserInfo shiroUser);

    @PostMapping
    /**
     * @Dec
     */
    UserInfo getUserDetailByLoginName(@RequestParam String loginName);

    @PostMapping("/getOrRegisterUser")
    SecurityUserInfo getOrRegisterUser(@RequestBody ThirdAuthUser user);

    /**
     * @param loginName
     * @return
     */
    @PostMapping("/getUserByLoginName")
    UserInfo getUserByLoginName(@RequestParam String loginName);
}
