package com.ddup.springbootseed.service;

import com.ddup.springbootseed.model.user.Role;

/**
 * 角色Service接口
 *
 * @author dazzlzy
 * @date 2018/5/27
 */
public interface IRoleService {

    /**
     * 新增角色
     *
     * @param role 角色
     */
    void addRole(Role role);

}
