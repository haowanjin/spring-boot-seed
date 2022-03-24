package com.ddup.springbootseed.service.impl;

import com.ddup.common.base.MenuTree;
import com.ddup.common.utils.TreeUtil;
import com.ddup.springbootseed.mapper.MenuMapper;
import com.ddup.springbootseed.model.Menu;
import com.ddup.springbootseed.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: haowanjin
 * @Description 菜单 Service
 * @create: 2022/3/23 14:18
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<Menu> findUserPermissions(String username) {
        return menuMapper.findUserPermissions(username);
    }

    @Override
    public MenuTree<Menu> findUserMenus(String username) {
        List<Menu> menus = menuMapper.findUserMenus(username);
        List<MenuTree<Menu>> menuTrees = convertMenus(menus);
        return TreeUtil.buildMenuTree(menuTrees);
    }

    @Override
    public MenuTree<Menu> findMenus(Menu menu) {
        List<Menu> menus = menuMapper.selectByMenuName(menu);
        List<MenuTree<Menu>> menuTrees = convertMenus(menus);
        return TreeUtil.buildMenuTree(menuTrees);
    }

    @Override
    public List<Menu> findMenuList(Menu menu) {
        return null;
    }

    @Override
    public void createMenu(Menu menu) {

    }

    @Override
    public void updateMenu(Menu menu) {

    }

    @Override
    public void deleteMenus(String menuIds) {

    }


    private List<MenuTree<Menu>> convertMenus(List<Menu> menus) {
        List<MenuTree<Menu>> trees = new ArrayList<>();
        menus.forEach(menu -> {
            MenuTree<Menu> tree = new MenuTree<>();
            tree.setId(String.valueOf(menu.getMenuId()));
            tree.setParentId(String.valueOf(menu.getParentId()));
            tree.setTitle(menu.getMenuName());
            tree.setIcon(menu.getIcon());
            tree.setHref(menu.getUrl());
            tree.setData(menu);
            trees.add(tree);
        });
        return trees;
    }

}
