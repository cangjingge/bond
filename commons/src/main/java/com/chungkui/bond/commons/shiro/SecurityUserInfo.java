package com.chungkui.bond.commons.shiro;

import java.util.Set;

/**
 * 用户信息接口
 * Created by jason on 2019/2/2 10:22
 * @author jason
 */
public interface SecurityUserInfo {
    String getId();
    String getLoginName();
    String getPwd();
    Set<String>getRoles();
}
