package com.ddup.common.base;

import com.ddup.common.exception.BusinessException;
import com.ddup.common.redis.RedisClientTemplate;
import com.ddup.common.utils.HttpContextUtil;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 验证码服务，
 * 如果febs.enable-redis-cache=true，则存redis，否则存session
 *
 * @author MrBird
 */
@Service
@RequiredArgsConstructor
public class ValidateCodeService {

    private final SeedProperties properties;
    private RedisClientTemplate redisService;
    @Value("${" + SeedProperties.ENABLE_REDIS_CACHE + "}")
    private boolean enableRedisCache;

    @Autowired(required = false)
    public void setRedisService(RedisClientTemplate redisService) {
        this.redisService = redisService;
    }

    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String key = session.getId();
        ValidateCodeProperties code = properties.getCode();
        setHeader(response, code.getType());

        Captcha captcha = createCaptcha(code);
        String codeKey = ParamConst.VALIDATE_CODE_PREFIX + key;
        String codeValue = StringUtils.lowerCase(captcha.text());
        if (enableRedisCache) {
            redisService.set(codeKey, codeValue, (int) code.getTime().getSeconds());
        } else {
            session.setAttribute(codeKey, codeValue);
            LocalDateTime expireTime = LocalDateTime.now().plusSeconds(code.getTime().getSeconds());
            session.setAttribute(ParamConst.VALIDATE_CODE_TIME_PREFIX + key, expireTime);
        }
        captcha.out(response.getOutputStream());
    }

    public void check(String key, String value) throws BusinessException {
        if (StringUtils.isBlank(value)) {
            throw new BusinessException("请输入验证码");
        }
        String codeKey = ParamConst.VALIDATE_CODE_PREFIX + key;
        if (enableRedisCache) {
            Object codeInRedis = redisService.get(codeKey);
            if (codeInRedis == null) {
                throw new BusinessException("验证码已过期");
            }
            if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInRedis))) {
                throw new BusinessException("验证码不正确");
            }
        } else {
            HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
            HttpSession session = request.getSession();
            String validateCodeTimeSessionKey = ParamConst.VALIDATE_CODE_TIME_PREFIX + key;
            Object codeInSession = session.getAttribute(codeKey);
            LocalDateTime timeInSession = (LocalDateTime) session.getAttribute(validateCodeTimeSessionKey);
            try {
                if (LocalDateTime.now().isAfter(timeInSession)) {
                    throw new BusinessException("验证码已过期");
                }
                if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInSession))) {
                    throw new BusinessException("验证码不正确");
                }
            } finally {
                session.removeAttribute(codeKey);
                session.removeAttribute(validateCodeTimeSessionKey);
            }
        }
    }

    private Captcha createCaptcha(ValidateCodeProperties code) {
        Captcha captcha;
        if (StringUtils.equalsIgnoreCase(code.getType(), ImageType.GIF)) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, ImageType.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
