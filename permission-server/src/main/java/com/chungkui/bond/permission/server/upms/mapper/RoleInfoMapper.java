package com.chungkui.bond.permission.server.upms.mapper;


import com.chungkui.bond.commons.bean.RoleInfo;
import com.chungkui.bond.commons.shiro.SecurityUserInfo;
import com.chungkui.bond.permission.server.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface RoleInfoMapper extends SuperMapper<RoleInfo> {
    Set<String> listRoleCodeByUser(SecurityUserInfo userInfo);
    List<String> listRoutersByRole(String roleCode);
    List<String> listPermissionByRole(String roleCode);
}