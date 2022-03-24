package com.ddup.springbootseed.controller;

import com.ddup.common.base.BaseResult;
import com.ddup.common.base.BaseResultGenerator;
import com.ddup.common.base.MenuTree;
import com.ddup.common.controller.BaseController;
import com.ddup.common.exception.BusinessException;
import com.ddup.springbootseed.model.Menu;
import com.ddup.springbootseed.service.IMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author: haowanjin
 * @Description 菜单 Controller
 * @create: 2022/3/23 14:27
 */
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {
    @Autowired
    private IMenuService menuService;

    @GetMapping("{username}")
    public BaseResult<MenuTree<Menu>> getUserMenus(@NotBlank(message = "{required}") @PathVariable String username) throws BusinessException {
        if (!StringUtils.equalsIgnoreCase(username, getCurrentUser().getUsername())) {
            throw new BusinessException("您无权获取别人的菜单");
        }
        return BaseResultGenerator.success(menuService.findUserMenus(username));
    }
    @GetMapping("tree")
//    @ControllerEndpoint(exceptionMessage = "获取菜单树失败")
    public BaseResult<List<MenuTree<Menu>>> getMenuTree(Menu menu) {
        List<MenuTree<Menu>> menuTrees = menuService.findMenus(menu).getChilds();
        return BaseResultGenerator.success(menuTrees);
    }

}
