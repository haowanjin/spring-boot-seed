package com.ddup.springbootseed.mapper;

import com.ddup.common.base.BaseMapper;
import com.ddup.springbootseed.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author hwj
 * @date 2018/5/19
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据条件查询用户集合
     *
     * @param user 查询条件
     * @return 用户集合
     */
    List<User> selectUser(@Param("user") User user);

    /**
     * 根据用户ID或者用户名称查询用户
     *
     * @param id   用户ID
     * @param name 用户名
     * @return 一个用户
     */
    User selectUserByIdOrName(@Param("id") Long id, @Param("name") String name);

    /**
     * 根据 unionId 查询用户
     * @param unionId
     * @return
     */
    User selectUserByUnionId(@Param("unionId") Long unionId);

    User selectUserByMobile(@Param("mobile") Long mobile);

    /**
     * 根据 openId 查询用户
     * @param openId
     * @return
     */
    User selectUserByOpenId(@Param("openId") Long openId);

    /**
     * 根据用户ID集合查询用户集合
     *
     * @param ids 用户ID集合
     * @return 用户集合
     */
    List<User> selectUserByIds(@Param("ids") List<Long> ids);


}