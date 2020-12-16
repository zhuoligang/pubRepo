package com.lg.web.module.conf;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lg.web.module.util.Json2Obj;

/**
 * 
 * @ClassName: RedisCache
 * @Description: TODO(redis缓存)
 * @author zlg
 * @date 2019年5月29日下午3:39:28
 *
 */
@Component
public class RedisCache {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public RedisTemplate<String, Object> getRedisTemplate() {
		return this.redisTemplate;
	}

	public String getCache(final String key) {
		//如果返回null，说明key不存在；如果返回""，说明key存在，值为""----业务上需要判断
		return redisTemplate.opsForValue().get(key)==null ? "":redisTemplate.opsForValue().get(key).toString();
	}

	public <T> T getCache(final String key, Class<T> targetClass) {
		byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
			@Override
			public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.get(key.getBytes());
			}
		});
		if (result == null) {
			return null;
		}
		String str = new String(result);
		return Json2Obj.j2o(str, targetClass);
	}

	/**
	 * 设置指定 key 的值
	 * 
	 * @param key
	 * @param value
	 */
	public void setCache(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public <T> void putCacheWithExpireTime(String key, String value, final long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = value.getBytes();
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
	}

	public <T> void putCacheWithExpireTime(String key, T value, final long expireTime) {
		final byte[] bkey = key.getBytes();
		String value_ = JSON.toJSONString(value);
		final byte[] bvalue = value_.getBytes();
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
	}

	/**
	 * 精确删除key
	 * 
	 * @param key
	 */
	public void deleteCache(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 模糊删除key
	 * 
	 * @param pattern
	 */
	public void deleteCacheWithPattern(String pattern) {
		Set<String> keys = redisTemplate.keys(pattern);
		redisTemplate.delete(keys);
	}

	/**
	 * 清空所有缓存
	 */
	public void clearCache(String pattern) {
		deleteCacheWithPattern(pattern + "|*");
	}
	
    /**
     * 设置过期时间
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 返回 key 的剩余的过期时间
     *
     * @param key
     * @param unit
     * @return
     */
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }
}
