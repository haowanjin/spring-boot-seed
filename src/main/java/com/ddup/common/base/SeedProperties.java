package com.ddup.common.base;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author MrBird
 */
@Data
@SpringBootConfiguration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = SeedProperties.PROPERTIES_PREFIX)
public class SeedProperties {

    public static final String PROPERTIES_PREFIX = "seed";
    public static final String ENABLE_REDIS_CACHE = "seed.enable-redis-cache";

    /**
     * 批量插入提交commit数据量
     */
    private int maxBatchInsertNum = 1000;

    private ValidateCodeProperties code = new ValidateCodeProperties();
    /**
     * 是否开启Redis缓存，true开启，false关闭
     * 为false时，采用基于内存的ehcache缓存
     */
    private boolean enableRedisCache;
}
