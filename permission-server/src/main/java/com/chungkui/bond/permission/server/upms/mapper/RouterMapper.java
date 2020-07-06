package com.chungkui.bond.permission.server.upms.mapper;


import com.chungkui.bond.commons.bean.Router;

import com.chungkui.bond.permission.server.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface RouterMapper extends SuperMapper<Router> {
    List<Router> list();
    List<Router>listByRole(Set<String> set);
}
