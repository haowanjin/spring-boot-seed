package com.ddup.springbootseed.service;

import com.ddup.SpringBootSeedApplicationTests;
import com.ddup.springbootseed.model.User;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * IShiroService 单元测试
 *
 * @author hwj
 * @date 2018/5/27
 */
@Slf4j
public class IShiroServiceTest extends SpringBootSeedApplicationTests {

    @Autowired
    private IShiroService shiroService;

    @Test
    public void login() {
        User user = shiroService.login(null, "admin", "123456");
        TestCase.assertNotNull(user);
        log.info("----------------------------------------------------------------------------");
        log.info("User: {}", user);
        log.info("----------------------------------------------------------------------------");
    }
}