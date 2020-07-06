package com.chungkui.bond.permission.server.upms.mapper;



import com.chungkui.bond.commons.bean.Permission;
import com.chungkui.bond.permission.server.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper extends SuperMapper<Permission> {
    List<Permission> listWithRoles();
}
