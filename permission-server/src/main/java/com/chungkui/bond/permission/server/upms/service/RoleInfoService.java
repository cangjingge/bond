package com.chungkui.bond.permission.server.upms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chungkui.bond.commons.bean.RoleInfo;
import com.chungkui.bond.commons.bean.RolePermission;
import com.chungkui.bond.commons.bean.RoleRouter;
import com.chungkui.bond.commons.shiro.SecurityUserInfo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;


/**
 * Copyright (C), 2019/5/29, sunflow开发团队
 * 〈角色信息service〉<br>
 * 〈功能详细描述〉
 *
 * @author Jwy
 * @fileName: RoleInfoService.java
 * @date: 2019/5/29 20:49
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface RoleInfoService extends IService<RoleInfo> {
    /**
     * 保存角色的权限和菜单
     *
     * @param permissions
     * @param routers
     * @return
     */
    Boolean savePermissionsAndRouters(String roleCode, List<RolePermission> permissions, List<RoleRouter> routers);

    boolean deleteRolePerAndRouter(String roleCode);

    Set<String> listRoleCodeByUser(SecurityUserInfo userInfo);

    List<SimpleGrantedAuthority> listAuthorityCodeByUser(SecurityUserInfo userInfo);

    List<String> listRoutersByRole(String roleCode);

    List<String> listPermissionByRole(String roleCode);

    int deleteRoleByCode(String roleCode);

    boolean saveRoleSetting(RoleInfo fRoleinfo, List<RolePermission> permissions, List<RoleRouter> routers);
}
