package com.ddup.springbootseed.service.impl;

import com.ddup.common.exception.BusinessException;
import com.ddup.springbootseed.dao.user.RoleMapper;
import com.ddup.springbootseed.model.user.Role;
import com.ddup.springbootseed.service.IRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 角色Service实现类
 *
 * @author hwj
 * @date 2018/5/27
 */
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void addRole(Role role) {
        Date now = new Date(System.currentTimeMillis());
        validateRole(role);
        role.setCreateTime(now);
        role.setModifyTime(now);
        roleMapper.insert(role);
    }

    /**
     * 校验Role
     *
     * @param role 角色
     */
    private void validateRole(Role role) {
        if (role == null) {
            throw new BusinessException("角色不存在");
        }
        if (StringUtils.isBlank(role.getRoleName())) {
            throw new BusinessException("角色名不能为空");
        }
        if (StringUtils.isBlank(role.getRoleCode())) {
            throw new BusinessException("角色编码不能为空");
        }
    }
}
