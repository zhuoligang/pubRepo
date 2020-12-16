package com.lg.web.module.websocket;

import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;

public class WebSocketCahce {
	// 使用集合来保存链接
	public static ConcurrentHashMap<String, Channel> clients = new ConcurrentHashMap<>();
}

