package com.lg.web.module.websocket;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.OnBinary;
import org.yeauty.annotation.OnClose;
import org.yeauty.annotation.OnError;
import org.yeauty.annotation.OnEvent;
import org.yeauty.annotation.OnMessage;
import org.yeauty.annotation.OnOpen;
import org.yeauty.annotation.ServerEndpoint;
import org.yeauty.pojo.ParameterMap;
import org.yeauty.pojo.Session;

import com.lg.web.module.util.ZipUtil;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;

@ServerEndpoint(prefix = "netty-websocket")
@Component
public class WebsocketServer {
	private final static Logger logger = LoggerFactory.getLogger(WebsocketServer.class);

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static AtomicInteger onlineCount = new AtomicInteger(0);

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<WebsocketServer> webSocketSet = new CopyOnWriteArraySet<WebsocketServer>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	private String sid = null;

	@OnOpen
	public void onOpen(Session session, HttpHeaders headers, ParameterMap parameterMap) throws IOException {
		this.session = session;
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		logger.info("有新连接加入！当前在线数为" + getOnlineCount());
		String paramValue = URLDecoder.decode(parameterMap.getParameter("paramKey"), "UTF-8");
		if (paramValue != null) {
			this.sid = paramValue;

			// String msKey = "netty-websocket:" + paramValue;
			// WebSocketCahce.clients.put(msKey, session.channel());

			System.out.println("paramValue is : " + paramValue);
		}
	}

	@OnClose
	public void onClose(Session session) throws IOException {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		WebSocketCahce.clients.remove(this.sid);
		logger.info("有一连接关闭！当前在线数为" + getOnlineCount());
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		throwable.printStackTrace();
	}

	@OnMessage
	public void onMessage(Session session, String message) {
		// session.sendText();
		// System.out.println(sid +" send message :" + message);
		if (message != null && !"".equals(message)) {
			try {
				sendMsg(sid + ": " + message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@OnBinary
	public void onBinary(Session session, byte[] bytes) {
		byte[] data = ZipUtil.unGZip(bytes);
		System.out.println("unBZip2->" + new String(data));
		System.out.println(data);
		for (byte b : bytes) {
			System.out.println(b);
		}
		session.sendBinary(bytes);
	}

	@OnEvent
	public void onEvent(Session session, Object evt) {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
			switch (idleStateEvent.state()) {
			case READER_IDLE:
				System.out.println("read idle");
				break;
			case WRITER_IDLE:
				System.out.println("write idle");
				break;
			case ALL_IDLE:
				System.out.println("all idle");
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 
	 * @Title: sendMessage @Description: TODO(消息发送) @param @param
	 *         message @param @throws IOException 设定文件 @return void 返回类型 @throws
	 */
	public void sendMessage(String message) throws IOException {
		this.session.sendText(message);
	}

	/**
	 * 
	 * @Title: sendInfo @Description: TODO(定向消息) @param @param
	 *         message @param @param sid @param @throws IOException 设定文件 @return
	 *         void 返回类型 @throws
	 */
	public static void sendInfo(String message, String sid) throws IOException {
		if (sid == null || "".equals(sid)) {
			logger.error("定向消息推送错误--没有传入推送对象。");
		}
		for (WebsocketServer item : webSocketSet) {
			try {
				// if(sid == null || "".equals(sid)) {
				// item.sendMessage(message);
				// }else
				if (item.sid.equals(sid)) {
					item.sendMessage(message);
				}
			} catch (IOException e) {
				logger.error("定向消息推送失败", e);
				continue;
			}
		}
	}

	/**
	 * 
	 * @Title: sendMsg @Description: TODO(广播消息) @param @param
	 *         message @param @throws IOException 设定文件 @return void 返回类型 @throws
	 */
	public static void sendMsg(String message) throws IOException {
		for (WebsocketServer item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				logger.error("广播消息失败", e);
				continue;
			}
		}
		logger.info("-------------------广播消息： " + message);
	}

	public AtomicInteger getOnlineCount() {
		synchronized (this) {
			return onlineCount;
		}
	}

	public static void addOnlineCount() {
		onlineCount.getAndIncrement();
	}

	public static void subOnlineCount() {
		onlineCount.getAndDecrement();
	}

	public String getAddress(Session session) {
		String remoteAddress = session.remoteAddress().toString();
		String ipAddress = remoteAddress.substring(1, remoteAddress.lastIndexOf(":"));
		return ipAddress;
	}
}
