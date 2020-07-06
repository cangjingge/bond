package com.chungkui.bond.permission.server.upms.remoteclient;


import com.chungkui.bond.commons.api.ShiroApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "shiro-gateway")
public interface ShiroApiClient extends ShiroApi {
}
