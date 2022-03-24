package com.ddup.springbootseed.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author MrBird
 */
@Data
@Table(name = "sys_dept")
//@Excel("部门信息表")
public class Dept implements Serializable {

    public static final Long TOP_NODE = 0L;
    private static final long serialVersionUID = 5702271568363798328L;
    /**
     * 部门 ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private Long deptId;

    /**
     * 上级部门 ID
     */
    @Column(name = "PARENT_ID")
    private Long parentId;

    /**
     * 部门名称
     */
    @Column(name = "DEPT_NAME")
//    @NotBlank(message = "{required}")
//    @Size(max = 10, message = "{noMoreThan}")
//    @ExcelField(value = "部门名称")
    private String deptName;

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

    @Column(name = "MODIFY_TIME")
//    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private Date modifyTime;

}
