package com.chungkui.bond.permission.server.upms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chungkui.bond.commons.bean.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<Permission> listWithRoles();
}
