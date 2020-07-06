package com.chungkui.bond.authmanager.dock;

import com.chungkui.bond.authmanager.bean.Permission;
import com.chungkui.bond.authmanager.bean.RoleInfo;
import com.chungkui.bond.authmanager.bean.UserInfo;

import java.util.List;

/**
 * 用户权限信息传输载体
 */
public class UserDetailsDock {

    private UserInfo userInfo;
    private List<RoleInfo> roleInfos;
    private List<Permission> permissions;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<RoleInfo> getRoleInfos() {
        return roleInfos;
    }

    public void setRoleInfos(List<RoleInfo> roleInfos) {
        this.roleInfos = roleInfos;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
