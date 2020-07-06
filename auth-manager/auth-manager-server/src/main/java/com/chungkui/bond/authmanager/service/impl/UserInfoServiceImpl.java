package com.chungkui.bond.authmanager.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chungkui.bond.authmanager.bean.UserDept;
import com.chungkui.bond.authmanager.bean.UserInfo;
import com.chungkui.bond.authmanager.bean.UserRole;
import com.chungkui.bond.authmanager.mapper.UserInfoMapper;
import com.chungkui.bond.authmanager.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C), 2019/6/22, sunflow开发团队
 * 〈用户管理〉<br>
 * 〈功能详细描述〉
 *
 * @author jason
 * @fileName: UserInfoServiceImpl.java
 * @date: 2019/6/22 12:02
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {


    /**
     * 校验用户名称是否唯一
     *
     * @param userInfo 用户名
     * @return 操作结果
     */
    @Override
    public boolean checkLoginNameUnique(UserInfo userInfo) {
        int count = baseMapper.checkLoginNameUnique(userInfo);
        return count == 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdateUser(String oldLoginName, UserInfo userInfo, List<UserDept> userDeptList) {
        saveOrUpdate(userInfo);
    /*    QueryWrapper<UserDept> userDeptWrapper = new QueryWrapper<>();
        userDeptWrapper.eq("user_id", userInfo.getId());
        userDeptService.remove(userDeptWrapper);
        if (userDeptList != null) {
            for (UserDept dept : userDeptList) {
                dept.setUserId(userInfo.getId());
            }
            userDeptService.saveBatch(userDeptList);
        }*/
        return true;
    }

    @Override
    public UserInfo getUserByUsername(String loginName) {
        QueryWrapper<UserInfo> userInfoWrapper = new QueryWrapper<UserInfo>();
        userInfoWrapper.eq("username", loginName);
        return super.getOne(userInfoWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeUserInfo(String loginName, String id) {
        QueryWrapper<UserRole> userRoleWrapper = new QueryWrapper<>();
        userRoleWrapper.eq("user_id", id);
        /* userRoleService.remove(userRoleWrapper);*/
        QueryWrapper<UserInfo> userInfoWrapper = new QueryWrapper<>();
        userInfoWrapper.eq("username", loginName);
        return super.remove(userInfoWrapper);
    }

}
