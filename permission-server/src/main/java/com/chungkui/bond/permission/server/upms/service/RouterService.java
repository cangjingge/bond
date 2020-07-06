package com.chungkui.bond.permission.server.upms.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chungkui.bond.commons.bean.Router;
import com.chungkui.bond.commons.shiro.SecurityUserInfo;

import java.util.List;

public interface RouterService extends IService<Router> {

    List<Router> list2tree();

    List<Router> listByUser(SecurityUserInfo securityUserInfo);

    List<Router> list2tree(SecurityUserInfo securityUserInfo);

    List<Router> buildMenuByUser(SecurityUserInfo securityUserInfo);
}
