package com.ddup.springbootseed.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * 用户
 *
 * @author hwj
 * @date 2018/5/19
 */
@Data
@Table(name = "sys_user")
public class User {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 启用状态
     */
    @Column(name = "state_code")
    private Integer stateCode;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String username;

    @Column(name = "dept_id")
    private Long deptId;
    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;
    /**
     * 性别
     */
    @Column(name = "sex")
    private Integer sex;
    /**
     * openId
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * unionId
     */
    @Column(name = "union_id")
    private String unionId;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * Email
     */
    private String email;

    /**
     * 座机
     */
    private String phone;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 电话
     */
    private String avatar;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 角色列表
     */
    @Transient
    private List<Role> roles;

    /**
     * 权限列表
     */
    @Transient
    private List<Menu> menus;

}