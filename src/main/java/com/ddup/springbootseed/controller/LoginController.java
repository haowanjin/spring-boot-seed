package com.ddup.springbootseed.controller;

import com.ddup.common.base.BaseResult;
import com.ddup.common.base.BaseResultGenerator;
import com.ddup.common.base.ValidateCodeService;
import com.ddup.common.exception.BusinessException;
import com.ddup.springbootseed.model.User;
import com.ddup.springbootseed.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

/**
 * @author: haowanjin
 * @Description
 * @create: 2022/3/21 11:50
 */
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ValidateCodeService validateCodeService;


    @RequestMapping("login")
    public BaseResult<String> doLogin(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password,
            @NotBlank(message = "{required}") String verifyCode,
            boolean rememberMe, HttpServletRequest request)throws BusinessException {
        Subject subject = SecurityUtils.getSubject();
        validateCodeService.check(request.getSession().getId(), verifyCode);
        User existUser = userService.queryByIdOrName(null, username);
        if (existUser == null) {
            throw new BusinessException("用户不存在或用户名密码错误");
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        //进行验证，这里可以捕获异常，然后返回对应信息
        subject.login(token);
        return BaseResultGenerator.success("index");
    }

    @GetMapping("index/{username}")
    public BaseResult index(@NotBlank(message = "{required}") @PathVariable String username) {
        // 更新登录时间
       /* userService.updateLoginTime(username);
        // 获取首页数据
        Map<String, Object> data = loginLogService.retrieveIndexPageData(username);*/
        return BaseResultGenerator.success(null);
    }


    @GetMapping("images/captcha")
//    @Limit(key = "get_captcha", period = 60, count = 10, name = "获取验证码", prefix = "limit")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, BusinessException {
        validateCodeService.create(request, response);
    }
}
