package com.chungkui.bond.authmanager.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Copyright (C), 2019/5/29, sunflow开发团队
 * 〈用户信息表〉<br>
 * 〈功能详细描述〉
 *
 * @author jason
 * @fileName: UserInfo.java
 * @date: 2019/5/29 20:17
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@TableName("supms_user_info")
public
class UserInfo implements Serializable {
    private static final long serialVersionUID = -1L;
    @TableId(type = IdType.AUTO)
    private String id;
    private String username;
    private String password;
    private String name;
    private String introduction;
    private String avatar;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
