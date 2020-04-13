package com.shenhua.base.utils.redis;

public interface RedisLock extends AutoCloseable {

	/**
	 * 释放分布式锁
	 */
	void unlock();
}
