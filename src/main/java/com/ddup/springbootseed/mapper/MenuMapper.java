package com.ddup.springbootseed.mapper;

import com.ddup.common.base.MenuTree;
import com.ddup.springbootseed.model.Menu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author: haowanjin
 * @Description TODO
 * @create: 2022/3/23 13:50
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 查找用户权限集
     *
     * @param username 用户名
     * @return 用户权限集合
     */
    List<Menu> findUserPermissions(@Param("username") String username);

    /**
     * 查找用户菜单集合
     *
     * @param username 用户名
     * @return 用户菜单集合
     */
    List<Menu> findUserMenus(@Param("username")String username);

    /**
     * 根据 menuName 查询菜单
     * @param menu
     * @return
     */
    List<Menu> selectByMenuName(@Param("menu") Menu menu);
}
