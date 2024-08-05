package com.shawn.votesystem.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用 String的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // hash的 key也采用 String的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        // value序列化方式采用 FastJson序列化方式
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer(Object.class));
        // hash的 value序列化方式采用 FastJson序列化方式
        redisTemplate.setHashValueSerializer(new FastJsonRedisSerializer(Object.class));
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
