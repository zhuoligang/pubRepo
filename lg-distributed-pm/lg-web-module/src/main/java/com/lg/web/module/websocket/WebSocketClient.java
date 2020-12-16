package com.lg.web.module.websocket;

import java.net.URI;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

/**
 * 
 * @ClassName: WebSocketClient
 * @Description: TODO(模拟服务器作为客户端与服务端之间建立通信链接)
 * @author zlg
 * @date 2019年7月23日上午10:59:17
 *
 */
public class WebSocketClient {

	static final String URL = "ws://localhost:7088/connect?paramKey=CNT_BT";

	public static void main(String[] args) throws Exception {
		URI uri = new URI(URL);
		String scheme = uri.getScheme() == null ? "ws" : uri.getScheme();
		final String host = uri.getHost() == null ? "127.0.0.1" : uri.getHost();
		final int port;
		if (uri.getPort() == -1) {
			if ("ws".equalsIgnoreCase(scheme)) {
				port = 80;
			} else if ("wss".equalsIgnoreCase(scheme)) {
				port = 443;
			} else {
				port = -1;
			}
		} else {
			port = uri.getPort();
		}

		if (!"ws".equalsIgnoreCase(scheme) && !"wss".equalsIgnoreCase(scheme)) {
			System.err.println("Only WS(S) is supported.");
			return;
		}

		final WebSocketClientHandler handler = new WebSocketClientHandler(WebSocketClientHandshakerFactory
				.newHandshaker(uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders()));

		Bootstrap bootstrap = new Bootstrap();
		EventLoopGroup group = new NioEventLoopGroup();
		bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline p = ch.pipeline();
				p.addLast(new HttpClientCodec(), new HttpObjectAggregator(8192), handler);
			}

		});
		Channel ch = bootstrap.connect(host, port).sync().channel();
		// System.out.println("测试链接将在10秒后断开。。。。");
		Thread.sleep(10 * 1000);
		System.out.println(ch.isRegistered());
		System.out.println(ch.isWritable());
		ch.writeAndFlush("这是一条测试消息: 测试链接将在10秒后断开。。。。");
		// ch.close();
	}
}
