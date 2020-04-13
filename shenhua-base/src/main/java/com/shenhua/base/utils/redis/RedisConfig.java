package com.shenhua.base.utils.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig extends RedisBase{
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
	@Value("${redis.database}")
	private int database;
    @Value("${redis.sentinelMode}")
    private boolean sentinelMode;
	
    /**
     * 实例化 RedisTemplate 对象
     */
	@Bean(name = "redisTemplate")
    public StringRedisTemplate functionDomainRedisTemplate() {
		StringRedisTemplate stringRedisTemplate = initDomainRedisTemplate();
        return stringRedisTemplate;
    }
    
    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     */
    private StringRedisTemplate initDomainRedisTemplate() {
        System.out.println("database==="+database);
    	StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
    	stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
    	stringRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
    	stringRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    	stringRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
    	stringRedisTemplate.setEnableTransactionSupport(false);
    	stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());
    	return stringRedisTemplate;
    }
	
    /**
     * 配置工厂
     */
    public JedisConnectionFactory jedisConnectionFactory() {
        //哨兵模式
        if(sentinelMode){
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(sentinelConfiguration(), jedisPoolConfig());
            jedisConnectionFactory.setDatabase(Integer.valueOf(database));
            jedisConnectionFactory.afterPropertiesSet();
            return jedisConnectionFactory;
        }
        //单机模式
        else{
            JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig());
            jedisConnectionFactory.setDatabase(database);
            jedisConnectionFactory.setHostName(host);
            jedisConnectionFactory.setPort(port);
            jedisConnectionFactory.setPassword(password);
            return jedisConnectionFactory;
        }
    }
    
}
