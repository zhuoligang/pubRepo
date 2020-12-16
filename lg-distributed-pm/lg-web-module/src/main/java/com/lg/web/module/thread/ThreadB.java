package com.lg.web.module.thread;

import org.springframework.data.redis.core.RedisTemplate;

import com.lg.web.module.service.MsService;

public class ThreadB extends Thread {
    private MsService service;
    private RedisTemplate<String,Object> redisTemplate;
    private String key;
 
    public ThreadB(MsService service,RedisTemplate<String,Object> redisTemplate,String key) {
        this.service = service;
        this.redisTemplate=redisTemplate;
        this.key=key;
    }
 
    @Override
    public void run() {
        service.seckill(redisTemplate, key);
    }
}
