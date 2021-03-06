package com.chungkui.bond.permission.server.upms.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chungkui.bond.commons.bean.RoleInfo;
import com.chungkui.bond.commons.bean.RolePermission;
import com.chungkui.bond.commons.bean.RoleRouter;
import com.chungkui.bond.permission.server.common.BaseController;

import com.chungkui.bond.permission.server.upms.remoteclient.ShiroApiClient;
import com.chungkui.bond.permission.server.upms.service.RoleInfoService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2019-2020, sunflow开发团队
 * 角色管理
 *
 * @author jwy
 * @fileName: RoleInfoController
 * @date: 23/05/2019 10:41
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("/role")
public class RoleInfoController extends BaseController {
    @Autowired
    ShiroApiClient shiroApiClient;
    @Autowired
    RoleInfoService roleInfoService;

    @GetMapping("/list")
    public Object list(Page iPage, RoleInfo roleInfo) {
        //获取所有角色
        //0.查询条件；1.list数据；2.分页数据；
        QueryWrapper<RoleInfo> queryWrapper = new QueryWrapper<RoleInfo>();
        queryWrapper.setEntity(roleInfo);
        queryWrapper.orderByAsc("role_code");
        IPage<RoleInfo> roleInfoList = roleInfoService.page(iPage, queryWrapper);
        return responseSuccess(roleInfoList);
    }

    @GetMapping("/all")
    public Object listAll() {
        return responseSuccess(roleInfoService.list());
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param roleCode
     * @return
     * @throws
     * @Author admin
     * @date 23/05/2019 11:08
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @GetMapping("/delete/{roleCode}")
    public Object delete(@PathVariable("roleCode") String roleCode, HttpServletRequest request, HttpServletResponse response) {
        roleInfoService.deleteRoleByCode(roleCode);
        shiroApiClient.updatePermission();
        return responseSuccess("删除成功");
    }

    @PostMapping("/save")
    public Object saveObject( RoleInfo fRoleinfo,   String permissions,   String routers, HttpServletRequest request, HttpServletResponse response) {
        RoleInfo roleinfo = roleInfoService.getById(fRoleinfo.getRoleCode());
        String msg = "新增角色成功！";
        if (roleinfo == null) {
            fRoleinfo.setCreateDate(new Date());
            fRoleinfo.setIsValid("T");
            fRoleinfo.setCreator(getLoginUser().getLoginName());
        } else {
            fRoleinfo.setUpdateDate(new Date());
            fRoleinfo.setUpdator(getLoginUser().getLoginName());
            msg = "权限更新成功！";
        }
        List<RolePermission> rolePermissions = new ArrayList<RolePermission>();
        if (!StringUtils.isBlank(permissions)) {
            for (String permission : permissions.split(",")) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setPermissionId(permission);
                rolePermission.setRoleCode(fRoleinfo.getRoleCode());
                rolePermissions.add(rolePermission);
            }
        }
        List<RoleRouter> roleRouters = new ArrayList<RoleRouter>();
        if (!StringUtils.isBlank(routers)) {
            for (String routerId : routers.split(",")) {
                RoleRouter roleRouter = new RoleRouter();
                roleRouter.setRoleCode(fRoleinfo.getRoleCode());
                roleRouter.setRouterId(routerId);
                roleRouters.add(roleRouter);
            }
        }
        roleInfoService.saveRoleSetting(fRoleinfo,rolePermissions,roleRouters);
        shiroApiClient.updatePermission();
        return responseSuccess(msg);
    }

    @GetMapping("/listPerAndRouter/{roleCode}")
    public Object listPerAndRouter(@PathVariable("roleCode") String roleCode, HttpServletRequest request, HttpServletResponse response) {
        List<String> routers = roleInfoService.listRoutersByRole(roleCode);
        List<String> permissions = roleInfoService.listPermissionByRole(roleCode);
        JSONObject obj = new JSONObject();
        obj.put("roleRouters", routers);
        obj.put("rolePermissions", permissions);
        return responseSuccess(obj);
    }

}
