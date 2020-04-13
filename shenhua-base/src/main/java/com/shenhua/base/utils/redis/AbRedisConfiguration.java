package com.shenhua.base.utils.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shenhua.base.utils.SerializableUtil;
import com.shenhua.base.utils.StringUtils;
import com.shenhua.base.utils.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AbRedisConfiguration {
	protected StringRedisTemplate redisTemplate;
	private static Logger log = LoggerFactory.getLogger(AbRedisConfiguration.class);
	public static final String PRIFIX = "shenhua";
	/**
	 * json解析对象
	 */
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}
	
	/** 
     * 获取redis的分布式锁，内部实现使用了redis的setnx。如果锁定失败返回null，如果锁定成功则返回RedisLock对象，调用方需要调用RedisLock.unlock()方法来释放锁 
     * <span style="color:red;">此方法在获取失败时会自动重试指定的次数,由于多次等待会阻塞当前线程，请尽量避免使用此方法</span> 
     * @param key 要锁定的key 
     * @param expireSeconds key的失效时间 
     * @param maxRetryTimes 最大重试次数,如果获取锁失败，会自动尝试重新获取锁； 
     * @param retryIntervalTimeMillis 每次重试之前sleep等待的毫秒数 
     * @return 获得的锁对象（如果为null表示获取锁失败），后续可以调用该对象的unlock方法来释放锁. 
     */  
	private static final String COMPARE_AND_DELETE =  
            "if redis.call('get',KEYS[1]) == ARGV[1]\n" +  
            "then\n" +  
            "    return redis.call('del',KEYS[1])\n" +  
            "else\n" +  
            "    return 0\n" +  
            "end";
    private static final long expireSeconds = 3; // 锁失效时间
    private static final int maxRetryTimes = 1; // 重试次数
    private static final long retryIntervalTimeMillis = 1;

    public RedisLock getLock(final String key){  
    	final String value = "-1";
    	int maxTimes = maxRetryTimes + 1;  
        for(int i = 0;i < maxTimes; i++) {  
            String status = getRedisTemplate().execute(new RedisCallback<String>() {
                @Override
                public String doInRedis(RedisConnection connection) throws DataAccessException {
                    Jedis jedis = (Jedis) connection.getNativeConnection();
					/*SetParams params = SetParams.setParams();
					params.nx();
					params.px(expireSeconds);
					String status = jedis.set(key,value,params);*/
					String status = jedis.set(key, value, "nx", "ex", expireSeconds);
					return status;
                }  
            });  
            if ("OK".equals(status)) {//抢到锁  
                return new RedisLockInner(getRedisTemplate(), key, value);  
            }  
            if(retryIntervalTimeMillis > 0) {  
                try {  
                    Thread.sleep(retryIntervalTimeMillis);  
                } catch (InterruptedException e) {  
                    break;  
                }  
            }  
            if(Thread.currentThread().isInterrupted()){  
                break;  
            }  
        }  
        return null;  
    }  
    private class RedisLockInner implements RedisLock{  
        private StringRedisTemplate stringRedisTemplate;
        private String key;
        private String expectedValue;
        protected RedisLockInner(StringRedisTemplate stringRedisTemplate, String key, String expectedValue){
            this.stringRedisTemplate = stringRedisTemplate;
            this.key = key;  
            this.expectedValue = expectedValue;  
        }  
        /** 
         * 释放redis分布式锁 
         */  
        @Override  
        public void unlock(){  
            List<String> keys = Collections.singletonList(key);
            stringRedisTemplate.execute(new DefaultRedisScript<>(COMPARE_AND_DELETE, String.class), keys, expectedValue);
        }
        @Override  
        public void close() throws Exception {  
            this.unlock();  
        }  
    }  
	
	/**
	 * 缓存的个数
	 */
	public int size(){
		int keySize = getRedisTemplate().execute(new RedisCallback<Integer>() {
			public Integer doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.keys("*".getBytes()).size();
			}
		});
		return keySize;
	}

	/**
	 * 清空当前数据库数据
	 */
	public void flushDB(){
		getRedisTemplate().execute(new RedisCallback<Object>() {
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				log.info("进行flushDb.");
				connection.flushDb();
				log.info("进行flushDb_完成.");
				return "ok";
			}
		});
	}
	
	/**
	 * 清空所有数据库数据
	 */
	public void flushAll(){
		getRedisTemplate().execute(new RedisCallback<Object>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
            	log.info("进行flushAll.");
                connection.flushAll();
                log.info("进行flushAll完成.");
                return "ok";
            }
        });
	}
	
	/**
	 * 指定缓存失效时间
	 * @param key 键
	 * @param time 时间(秒)
	 */
	public boolean expire(String key, long time) {
		try {
			if (time > 0) {
				getRedisTemplate().expire(PRIFIX + "_" + key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			log.error("设置缓存失效时间异常:{}", e.getMessage());
			return false;
		}
	}

	/**
	 * 判断key是否存在
	 * @param key 键
	 * @return true 存在 false不存在
	 */
	public boolean hasKey(String key) {
		try {
			return getRedisTemplate().hasKey(PRIFIX + "_" + key);
		} catch (Exception e) {
			log.error("REDIS_判断key是否存在异常:{}", e.getMessage());
			return false;
		}
	}

	public void del(String key,boolean isLog) {
		long start = System.currentTimeMillis();
		getRedisTemplate().delete(PRIFIX + "_" + key);
		if(isLog){
			log.info("REDIS_删除key:{},prefix:{},耗时:{}ms.", key, PRIFIX, (System.currentTimeMillis() - start));
		}
	}
	
	// ============================String对象存储=============================
	/**
	 * 普通缓存获取
	 */
	public Object get(String key,boolean isLog) {
		long start = System.currentTimeMillis();
		try {
			if (hasKey(key)) {
				String rtMsg = String.valueOf(getRedisTemplate().opsForValue().get(PRIFIX + "_" + key));
				if(isLog){
					log.info("REDIS_查询key:{},prefix:{},耗时:{}ms.数据:{}", key, PRIFIX, (System.currentTimeMillis() - start),rtMsg);
				}
				return rtMsg;
			}
		} catch (Exception e) {
			log.error("REDIS_查询key:{},prefix:{},耗时:{}ms.查询失败{}", key, PRIFIX, (System.currentTimeMillis() - start),e.getMessage());
		}
		return "";
	}

	/**
	 * 普通缓存放入
	 */
	public boolean set(String key, Object value,boolean isLog) {
		long start = System.currentTimeMillis();
		try {
			getRedisTemplate().opsForValue().set(PRIFIX + "_" + key, String.valueOf(value));
			if(isLog){
				log.info("REDIS_存储key:{},prefix:{},耗时:{}ms.value:{}", key, PRIFIX, (System.currentTimeMillis() - start),value);
			}
			return true;
		} catch (Exception e) {
			log.error("REDIS_存储key:{},prefix:{},耗时:{}ms.value:{},设置失败:{}", key, PRIFIX,(System.currentTimeMillis() - start), value, e.getMessage());
			return false;
		}

	}

	/**
	 * 普通缓存放入并设置时间
	 */
	public boolean set(String key, Object value, long time,boolean isLog) {
		long start = System.currentTimeMillis();
		try {
			if (time > 0) {
				getRedisTemplate().opsForValue().set(PRIFIX + "_" + key, String.valueOf(value), time, TimeUnit.SECONDS);
			} else {
				set(key, value, isLog);
			}
			if(isLog){
				log.info("REDIS_存储key:{},prefix:{},过期时间:{}s,耗时:{}ms.value:{}", key, PRIFIX, time, (System.currentTimeMillis() - start), value);
			}
			return true;
		} catch (Exception e) {
			log.error("REDIS_存储key:{},prefix:{},过期时间:{}s,耗时:{}ms.value:{},且设置失败:{}.", key, PRIFIX, time,(System.currentTimeMillis() - start), value, e.getMessage());
			return false;
		}
	}


	// ============================String对象存储=============================
	/**
	 * 普通缓存获取
	 */
	public <T> T getObject(String key,Class<T> targetClass, boolean isLog) {
		long start = System.currentTimeMillis();
		try {
			if (hasKey(key)) {
				String json = String.valueOf(getRedisTemplate().opsForValue().get(PRIFIX + "_" + key));
				if(isLog){
					log.info("REDIS_查询key:{},prefix:{},耗时:{}ms.数据:{}", key, PRIFIX, (System.currentTimeMillis() - start),json);
				}
				return objectMapper.readValue(json,targetClass);
			}
		} catch (Exception e) {
			log.error("REDIS_查询key:{},prefix:{},耗时:{}ms.查询失败{}", key, PRIFIX, (System.currentTimeMillis() - start),e.getMessage());
		}
		return null;
	}

	/**
	 * 普通缓存放入并设置时间
	 */
	public boolean setObject(String key, Object value) {
		return setObject(key, value,-1,false);
	}
	/**
	 * 普通缓存放入并设置时间
	 */
	public boolean setObject(String key, Object value,long time,boolean isLog) {
		long start = System.currentTimeMillis();
		try {
			if (time > 0) {
				getRedisTemplate().opsForValue().set(PRIFIX + "_" + key, objectMapper.writeValueAsString(value), time, TimeUnit.SECONDS);
			} else {
				set(key, value, isLog);
			}
			if(isLog){
				log.info("REDIS_存储key:{},prefix:{},过期时间:{}s,耗时:{}ms.value:{}", key, PRIFIX, time, (System.currentTimeMillis() - start), value);
			}
			return true;
		} catch (Exception e) {
			log.error("REDIS_存储key:{},prefix:{},过期时间:{}s,耗时:{}ms.value:{},且设置失败:{}.", key, PRIFIX, time,(System.currentTimeMillis() - start), value, e.getMessage());
			return false;
		}
	}
	
}
