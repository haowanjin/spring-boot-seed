package com.ddup.springbootseed.controller;

import com.alibaba.fastjson.JSON;
import com.ddup.common.base.ParamConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandleController {

    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception ex) {
        log.error(JSON.toJSONString(ex));
        return "redirect:" + ParamConst.VIEW_PREFIX + "403";
    }

    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
        log.error("授权出错：{}", ex);
        return "redirect:/" + ParamConst.VIEW_PREFIX + "403";
    }

}