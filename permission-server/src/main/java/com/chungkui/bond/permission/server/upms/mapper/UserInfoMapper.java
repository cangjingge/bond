package com.chungkui.bond.permission.server.upms.mapper;


import com.chungkui.bond.commons.bean.UserInfo;
import com.chungkui.bond.permission.server.mapper.SuperMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends SuperMapper<UserInfo> {
    UserInfo findByLoginName(String loginName);
    int checkLoginNameUnique(UserInfo userInfo);
}
