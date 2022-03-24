package com.ddup.springbootseed.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MrBird
 */
@Data
@Table(name = "sys_menu")
//@Excel("菜单信息表")
public class Menu implements Serializable {

    /**
     * 菜单
     */
    public static final String TYPE_MENU = "0";
    /**
     * 按钮
     */
    public static final String TYPE_BUTTON = "1";
    public static final Long TOP_NODE = 0L;
    private static final long serialVersionUID = 8571011372410167901L;
    /**
     * 菜单/按钮ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MENU_ID")
    private Long menuId;

    /**
     * 上级菜单ID
     */
    @Column(name = "PARENT_ID")
    private Long parentId;

    /**
     * 菜单/按钮名称
     */
    @Column(name = "MENU_NAME")
    @NotBlank(message = "{required}")
    @Size(max = 10, message = "{noMoreThan}")
//    @ExcelField(value = "名称")
    private String menuName;

    /**
     * 菜单URL
     */
    @Column(name = "URL")
    @Size(max = 50, message = "{noMoreThan}")
//    @ExcelField(value = "URL")
    private String url;

    /**
     * 权限标识
     */
    @Column(name = "PERMS")
    @Size(max = 50, message = "{noMoreThan}")
//    @ExcelField(value = "权限")
    private String perms;

    /**
     * 图标
     */
    @Column(name = "ICON")
    @Size(max = 50, message = "{noMoreThan}")
//    @ExcelField(value = "图标")
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    @Column(name = "TYPE")
    @NotBlank(message = "{required}")
//    @ExcelField(value = "类型", writeConverterExp = "0=按钮,1=菜单")
    private String type;

    /**
     * 排序
     */
    @Column(name = "ORDER_NUM")
    private Long orderNum;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
//    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "MODIFY_TIME")
//    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private Date modifyTime;


}
