package com.bi.activity.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.bi.activity.util.ProtoStuffSerializerUtil;


/**
 * redis缓存
 * 
 * @author zlg
 * 
 */
@Component
public class RedisCache {

  public final static String CAHCENAME = "bi_activity_";// 缓存名
  public final static String CURREN_NEWEJIEORDER_FLAG = "this_is_bi_activity_orders ";
  public final static int CAHCETIME = 604800;// 默认缓存时间 7天

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  public <T> boolean putCache(String key, T obj) {
    final byte[] bkey = key.getBytes();
    final byte[] bvalue = ProtoStuffSerializerUtil.serialize(obj);
    boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.setNX(bkey, bvalue);
      }
    });
    return result;
  }

  public <T> void putCacheWithExpireTime(String key, T obj, final long expireTime) {
    final byte[] bkey = key.getBytes();
    final byte[] bvalue = ProtoStuffSerializerUtil.serialize(obj);
    redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        connection.setEx(bkey, expireTime, bvalue);
        return true;
      }
    });
  }

  public <T> boolean putListCache(String key, List<T> objList) {
    final byte[] bkey = key.getBytes();
    final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
    boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.setNX(bkey, bvalue);
      }
    });
    return result;
  }

  // 存入消息
  public <T> boolean putHashCache(String key, HashMap<String, T> object) {
    final byte[] bkey = key.getBytes();

    final HashMap<byte[], byte[]> ham = new HashMap<>();

    for (Entry<String, T> entry : object.entrySet()) {

      ham.put(ProtoStuffSerializerUtil.serialize(entry.getKey()),
          ProtoStuffSerializerUtil.serialize(entry.getValue()));

    }
    boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        connection.hMSet(bkey, ham);
        return true;
      }
    });
    return result;
  }

  // 获取消息
  public <T> HashMap<String, T> getHashCache(final String key, Class<T> targetClass) {
    final byte[] bkey = key.getBytes();

    HashMap<String, T> ham = new HashMap<>();

    Map<byte[], byte[]> result = redisTemplate.execute(new RedisCallback<Map<byte[], byte[]>>() {
      @Override
      public Map<byte[], byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.hGetAll(bkey);

      }
    });
    if (result == null) {
      return null;
    }
    System.out.println(key + "--------------");
    for (Entry<byte[], byte[]> entry : result.entrySet()) {


      // ProtoStuffSerializerUtil.deserialize(entry.getKey(),
      // String.class);
      // ProtoStuffSerializerUtil.deserialize(entry.getValue(),
      // targetClass);
      System.out.println(key + "--------------"
          + ProtoStuffSerializerUtil.deserialize(entry.getKey(), String.class));

      System.out.println(key + "--------------"
          + ProtoStuffSerializerUtil.deserialize(entry.getValue(), targetClass));
      ham.put(ProtoStuffSerializerUtil.deserialize(entry.getKey(), String.class),
          ProtoStuffSerializerUtil.deserialize(entry.getValue(), targetClass));

      System.out.println(ham.values());

    }

    return ham;
  }


  // 删除消息
  public Long deleteHashCache(final String key, final String field) {
    final byte[] bkey = key.getBytes();

    return redisTemplate.execute(new RedisCallback<Long>() {
      @Override
      public Long doInRedis(RedisConnection connection) throws DataAccessException {
        Map<byte[], byte[]> result = connection.hGetAll(bkey);

        for (Entry<byte[], byte[]> entry : result.entrySet()) {
          if (field.equals(ProtoStuffSerializerUtil.deserialize(entry.getKey(), String.class))) {
            connection.hDel(bkey, entry.getKey());
          }
        }

        return 0L;
      }
    });
  }

  public <T> boolean putListCacheWithExpireTime(String key, List<T> objList, final long expireTime) {
    final byte[] bkey = key.getBytes();
    final byte[] bvalue = ProtoStuffSerializerUtil.serializeList(objList);
    boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
      @Override
      public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
        connection.setEx(bkey, expireTime, bvalue);
        return true;
      }
    });
    return result;
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
    return ProtoStuffSerializerUtil.deserialize(result, targetClass);
  }

  public <T> List<T> getListCache(final String key, Class<T> targetClass) {
    byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
      @Override
      public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
        return connection.get(key.getBytes());
      }
    });
    if (result == null) {
      return null;
    }
    return ProtoStuffSerializerUtil.deserializeList(result, targetClass);
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
  public void clearCache() {
    deleteCacheWithPattern(RedisCache.CAHCENAME + "|*");
  }
}
