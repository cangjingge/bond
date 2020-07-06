package com.chungkui.bond.authmanager.client;


import com.baomidou.mybatisplus.extension.api.R;
import com.chungkui.bond.authmanager.dock.UserDetailsDock;
import com.chungkui.bond.common.core.api.ResBus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Component
@FeignClient(contextId = "userInfoClient", value = "auth-manager-server")
public interface UserInfoClient {

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @return R
     */
    @GetMapping("/sys/loadUserDetails")
    ResBus<UserDetailsDock> loadUserDetails(@RequestParam("username") String username);

}
