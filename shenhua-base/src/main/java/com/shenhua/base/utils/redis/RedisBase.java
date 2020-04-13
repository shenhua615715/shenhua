package com.shenhua.base.utils.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisBase {

	@Value("${redis.mymaster.masterName}")
	private String masterName;
	@Value("${redis.sentinel.hostName}")
	private String sentinelHostName;
	@Value("${redis.sentinel.hostName2}")
	private String sentinelHostName2;
	@Value("${redis.sentinel.port}")
	private int sentinelport;
	@Value("${redis.password}")
	private String password;
	@Value("${redis.database}")
	private int database;
	
	/**
	 * JedisPoolConfig 连接池
	 */
	public static JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(4000);
		jedisPoolConfig.setMaxIdle(500);
		jedisPoolConfig.setMinIdle(100);
		jedisPoolConfig.setMaxWaitMillis(2 * 1000L);
		jedisPoolConfig.setTimeBetweenEvictionRunsMillis(3000);
		jedisPoolConfig.setBlockWhenExhausted(true);
		jedisPoolConfig.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
		jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(1800000);
		jedisPoolConfig.setJmxEnabled(true);
		jedisPoolConfig.setTestOnBorrow(true);
		jedisPoolConfig.setTestOnReturn(true);
		jedisPoolConfig.setTestWhileIdle(true);
		jedisPoolConfig.setMinEvictableIdleTimeMillis(1800000);
		return jedisPoolConfig;
	}
	
	/**
     * 配置redis的哨兵
     */
    public RedisSentinelConfiguration sentinelConfiguration() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        Set<RedisNode> redisNodeSet = new HashSet<RedisNode>();
        redisNodeSet.add(new RedisNode(sentinelHostName, sentinelport));
        redisNodeSet.add(new RedisNode(sentinelHostName2, sentinelport));
        redisSentinelConfiguration.setSentinels(redisNodeSet);
        redisSentinelConfiguration.setMaster(masterName);
        redisSentinelConfiguration.setPassword(password);
        redisSentinelConfiguration.setDatabase(database);
        return redisSentinelConfiguration;
    }
	
}
