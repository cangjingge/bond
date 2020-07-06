package com.chungkui.bond.permission.server.upms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chungkui.bond.commons.bean.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {
    boolean saveUserRoles(String userId, List<UserRole> userRoleList, String optUserId);
}
