package com.lg.datadispose.module.conf;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lg.datadispose.module.util.Json2Obj;

/**
 * redis缓存
 * 
 * @author zlg
 * 
 */
@Component
public class RedisCache {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * 设置指定 key 的值
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 写入缓存设置时效时间
	 *
	 * @param key
	 * @param value
	 * @param expireTime
	 *            有效时间，单位秒
	 * @return
	 */
	public boolean set(final String key, String value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<String, String> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public <T> void putCacheWithExpireTime(String key, String obj, final long expireTime) {
		final byte[] bkey = key.getBytes();
		final byte[] bvalue = obj.getBytes();
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
	}

	public <T> void putCacheWithExpireTime(String key, T obj, final long expireTime) {
		final byte[] bkey = key.getBytes();
		String value = JSON.toJSONString(obj);
		final byte[] bvalue = value.getBytes();
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				connection.setEx(bkey, expireTime, bvalue);
				return true;
			}
		});
	}

	public String getCache(final String key) {
		// 如果返回null，说明key不存在；如果返回""，说明key存在，值为""----业务上需要判断
		return redisTemplate.opsForValue().get(key);
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
	
	//模糊查询key
	public Set<String> getCacheWithPattern(String key){
		Set<String> keys = redisTemplate.keys(key + "*");
		return keys;
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
	public void clearCache(String key) {
		deleteCacheWithPattern(key + "|*");
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
	 * 获取指定 key 的值
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		Object v = redisTemplate.opsForValue().get(key);
		if (v == null) {
			return null;
		}
		return v.toString();
	}

	/**
	 * 获取存储在哈希表中指定字段的值
	 *
	 * @param key
	 * @param field
	 * @return
	 */
	public Object hGet(String key, String field) {
		return redisTemplate.opsForHash().get(key, field);
	}

	public void hPut(String key, String hashKey, String value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	/**
	 * 获取所有给定字段的值
	 *
	 * @param key
	 * @return
	 */
	public Map<Object, Object> hGetAll(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	public void hPutAll(String key, Map<String, String> maps) {
		redisTemplate.opsForHash().putAll(key, maps);
	}

}
