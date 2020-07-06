package com.chungkui.bond.commons.api;

import com.chungkui.bond.commons.bean.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;

public interface ShiroApi {
    @GetMapping
    void removeSessionByLoginId(String id);

    @GetMapping
    void updatePermission();

    @GetMapping
    /**
     * @Dec
     */
    UserInfo getUserDetailByLoginName(String loginName);


}
