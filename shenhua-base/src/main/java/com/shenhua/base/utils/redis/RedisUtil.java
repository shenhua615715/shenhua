package com.shenhua.base.utils.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis cache 工具类 存放用户的FEE情况字段
 */
@Component
public class RedisUtil extends AbRedisConfiguration {
	@Resource(name = "redisTemplate")
	private StringRedisTemplate stringRedisTemplate;

	@Value("${jwt.refreshTokenExpireTime}")
	private int refreshTokenExpireTime;

	@Override
	public StringRedisTemplate getRedisTemplate() {
		return stringRedisTemplate;
	}

	/**
	 * 获取锁
	 */
	public RedisLock getLock(RedisUtil redisUtil, String key) {
		return redisUtil.getLock(AbRedisConfiguration.PRIFIX + "_" + key);
	}

	public int getRefreshTokenExpireTime() {
		return refreshTokenExpireTime;
	}

	/**
	 * 解锁
	 */
	public void unLock(RedisLock lock) {
		if (lock != null) {
			lock.unlock();
		}
	}
}
