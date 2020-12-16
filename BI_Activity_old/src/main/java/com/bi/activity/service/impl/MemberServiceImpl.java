/**
 * MemberServiceImpl.java
 * com.bi.activity.service.impl
 *
 * Function： TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   ver1.0  2018年9月4日 		zhuoligang
 *
 * Copyright (c) 2018, b-i All Rights Reserved.
*/

package com.bi.activity.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bi.activity.cache.RedisCache;
import com.bi.activity.dto.BaseAutowired;
import com.bi.activity.entity.Member;
import com.bi.activity.service.MemberService;

/**
 * ClassName:MemberServiceImpl（Describe this Class）
 * @author   zhuoligang
 * @version  Ver 1.0
 * @Date	 2018年9月4日		下午1:14:28
 * @see 	 
 */
@Service
public class MemberServiceImpl extends BaseAutowired implements MemberService{
	
	@Autowired
	private RedisCache cache;
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public Member findMember(Map<String, Object> map) {
        int id = Integer.parseInt(map.get("id").toString());
        Member member = memberDao.selectByPrimaryKey(id);
        return member;
        
    }

	@Override
	public Member getCacheMember(Map<String, Object> map) {
		Member member = null;
		int id = Integer.parseInt(map.get("id").toString());
		if(id != 0){
			String cache_key = RedisCache.CAHCENAME + "|CacheMember|" + id; 
			member = cache.getCache(cache_key, Member.class);
		}
		return member;
	}

	@Override
	public boolean putCacheMember(Map<String, Object> map) {
		boolean bool = false;
		Member result_cache = null;
		if(map.get("id") == null || "".equals(map.get("id"))){
			return bool;
		}
        int id = Integer.parseInt(map.get("id").toString());
		//创建一个独有的缓存key
		String cache_key = RedisCache.CAHCENAME + "|CacheMember|" + id;
		
		boolean flag = false;
		if(map.get("flag") != null && !"".equals(map.get("flag"))){
			flag = Boolean.parseBoolean(map.get("flag").toString());
		}
		if(!flag){
			result_cache = findMember(map);
			LOG.info("put cache with key:" + cache_key);
		}else{
			// 先去缓存中取
			result_cache = cache.getCache(cache_key,Member.class);
			if(result_cache == null){
				result_cache = findMember(map);
				LOG.info("put cache with key:" + cache_key);
			}else{
				LOG.info("get cache with key:" + cache_key);
			}
		}
		if(result_cache != null){//标记为在线状态
			cache.putCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
			bool =  true ;
		}
		return bool;
	}

	@Override
	public boolean deleteCacheMember(Map<String, Object> map) {
		boolean bool = false;
        int id = Integer.parseInt(map.get("id").toString());
		if(id != 0){//逻辑删除
			String cache_key = RedisCache.CAHCENAME + "|CacheMember|" + id; 
			cache.deleteCache(cache_key);
			LOG.info("delete cache with key:" + cache_key);
			bool = true;
		}
		return bool;
	}


}

