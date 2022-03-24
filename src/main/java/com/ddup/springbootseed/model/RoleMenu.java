package com.ddup.springbootseed.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author MrBird
 */
@Data
@Table(name = "sys_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -5200596408874170216L;
    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 菜单/按钮ID
     */
    @Column(name = "menu_id")
    private Long menuId;


}
