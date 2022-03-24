package com.ddup.common.aop;

import com.alibaba.fastjson.JSON;
import com.ddup.common.base.BaseResultGenerator;
import com.ddup.common.enums.BaseResultEnum;
import com.ddup.common.exception.BusinessException;
import com.google.common.base.Stopwatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: haowanjin
 * @Description TODO
 * @create: 2022/3/21 11:17
 */
@Aspect
@Component
public class ControllerLogAspect {
    Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Around("execution(* com.ddup.springbootseed.controller.*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Object result;
        try {
            logger.info("执行Controller开始: {} \n入参：{}", pjp.getSignature(), pjp.getArgs());
            result = pjp.proceed(pjp.getArgs());
            logger.info("执行Controller结束: {} \n出参：{}", pjp.getSignature(), JSON.toJSONString(result));
            logger.info("耗时：{} (毫秒).\n\n", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));

        } catch (BusinessException e) {
            logger.error("执行异常方法：{}\n,参数：{}\n,错误信息：{}", pjp.getSignature(), pjp.getArgs(), e);
            result = BaseResultGenerator.failure(e.getErrorMessage());
        } catch (Throwable throwable) {
            result = handlerException(pjp, throwable);
        }
        return result;
    }

    private Object handlerException(ProceedingJoinPoint pjp, Throwable e) {
        BaseResultEnum result = BaseResultEnum.SERVER_ERROR;
        logger.error("执行异常方法：{}\n,参数：{}\n,错误信息：{}", pjp.getSignature(), pjp.getArgs(), e);
        return BaseResultGenerator.failure(result);
    }
}
