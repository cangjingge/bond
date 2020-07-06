package com.chungkui.bond.authmanager.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chungkui.bond.authmanager.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    UserInfo findByLoginName(String loginName);
    int checkLoginNameUnique(UserInfo userInfo);
}
