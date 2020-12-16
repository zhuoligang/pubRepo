package com.lg.web.module.service;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lg.web.module.bean.po.CenterUserMain;
import com.lg.web.module.bean.po.SpecialCertificationInfo;
import com.lg.web.module.bean.po.Users;
import com.lg.web.module.conf.RedisCache;
import com.lg.web.module.constant.WebConstant;
import com.lg.web.module.dao.CenterUserMainMapper;
import com.lg.web.module.dao.SpecialCertificationInfoMapper;
import com.lg.web.module.dao.UsersMapper;
import com.lg.web.module.exception.BusinessException;
import com.lg.web.module.qiniucloud.QiniuUploadPool;
import com.lg.web.module.util.ImageUtil;
import com.lg.web.module.util.ZipUtil;
import com.lg.web.module.websocket.WebSocketCahce;
import com.lg.web.module.websocket.WebSocketClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.codec.string.StringEncoder;

@Service
public class UsersService {
	private final static Logger logger = LoggerFactory.getLogger(UsersService.class);

	@Autowired
	private UsersMapper usersMapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private CenterUserMainMapper centerUserMainMapper;
	@Autowired
	private QiniuUploadPool qiniuUploadPool;

	@Autowired
	private SpecialCertificationInfoMapper specialCertificationInfoMapper;

	public Users selectUser(Map<String, Object> map) {
		try {
			if (map == null || (map.get("id") == null || "".equals(map.get("id")))) {
				throw new BusinessException(WebConstant.CODE_500, WebConstant.FILL_IN_REQUIRED);
			}
			Integer id = Integer.parseInt(map.get("id").toString());
			return this.usersMapper.selectByPrimaryKey(id);
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
			logger.error("selectUser?" + JSONObject.toJSONString(map));
			throw new BusinessException(WebConstant.CODE_500, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("selectUser?" + JSONObject.toJSONString(map));
			throw new BusinessException(WebConstant.CODE_500, e.getMessage());
		}
	}

	public String set2Redis(Map<String, Object> map) {
		if (map == null
				// || (map.get("msKey") == null || "".equals(map.get("msKey")))
				|| (map.get("msValue") == null || "".equals(map.get("msValue")))) {
			throw new BusinessException(WebConstant.CODE_500, WebConstant.FILL_IN_REQUIRED);
		}
		try {
			// String msKey = "MS:" + map.get("msKey").toString();
			String msKey = "pronum";
			String msValue = map.get("msValue").toString();
			this.redisCache.setCache(msKey, msValue);
			logger.info("put redis key ->" + msKey + ",value ->" + msValue);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(WebConstant.CODE_500, e.getMessage());
		}
		return "写入成功";
	}

	/**
	 * 
	 * @Title: findCenterUserMainById @Description:
	 *         TODO(精确查询一个用户的全部信息) @param @param map @param @return 设定文件 @return
	 *         CenterUserMain 返回类型 @throws
	 */

	public CenterUserMain findCenterUserMain(Map<String, String> map) {
		CenterUserMain centerUserMain = null;
		if (map == null || (map.get("uuid") == null && map.get("memberId") == null)) {
			throw new BusinessException(WebConstant.CODE_500, WebConstant.FILL_IN_REQUIRED);
		}
		if (map.get("uuid") != null) {
			centerUserMain = this.centerUserMainMapper.selectByPrimaryKey(map.get("uuid"));
			return centerUserMain;
		}
		if (map.get("memberId") != null) {
			centerUserMain = this.centerUserMainMapper.selectByMemberId(map.get("memberId"));
			return centerUserMain;
		}
		return centerUserMain;
	}

	@Transactional(rollbackFor = Exception.class)
	public String certificationImages(String memberId, MultipartFile idcardPicFront, MultipartFile idcardPicBack,
			MultipartFile idcardPicOnhand) throws IOException {
		String resultStr = "上传失败，请联系管理员。";
		if ((memberId == null || "".equals(memberId)) || (idcardPicFront == null || "".equals(idcardPicFront))
				|| (idcardPicBack == null || "".equals(idcardPicBack))
				|| (idcardPicOnhand == null || "".equals(idcardPicOnhand))) {
			throw new BusinessException(WebConstant.CODE_500, WebConstant.FILL_IN_REQUIRED);
		}
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		CenterUserMain centerUserMain = findCenterUserMain(map);
		if (centerUserMain == null) {
			throw new BusinessException(WebConstant.CODE_500, "用户不存在");
		}
		if ((centerUserMain.getIdcard() == null || "".equals(centerUserMain.getIdcard()))
				|| (centerUserMain.getName() == null || "".equals(centerUserMain.getName()))) {
			throw new BusinessException(WebConstant.CODE_500, "请先进行普通实名认证，才能使用本功能。");
		}
		if (centerUserMain.getIdcardPicCheckId() > 2 && centerUserMain.getIdcardPicCheckType() == 1) {
			throw new BusinessException(WebConstant.CODE_500, "您已通过高级认证，不能重复认证。");
		}
		map.put("status", "0");
		List<SpecialCertificationInfo> sc = this.specialCertificationInfoMapper.selectByMap(map);
		if (sc != null && sc.size() > 0) {
			throw new BusinessException(WebConstant.CODE_500, "您还有审核中的申请，不能重复提交申请。");
		}
		try {
			if (!ImageUtil.chooseImage(idcardPicFront) || !ImageUtil.chooseImage(idcardPicBack)
					|| !ImageUtil.chooseImage(idcardPicOnhand)) {
				logger.error("上传的图片不满足要求，请上传jpg、jpeg、png格式并且单张不超过10M的图片");
				throw new BusinessException(WebConstant.CODE_500, "上传的图片不满足要求，请上传jpg、jpeg、png格式并且单张不超过10M的图片");
			}
			Map<String, byte[]> mapCheck = new HashMap<>();
			mapCheck.put("idcardPicFront", idcardPicFront.getBytes());
			mapCheck.put("idcardPicBack", idcardPicBack.getBytes());
			mapCheck.put("idcardPicOnhand", idcardPicOnhand.getBytes());

			this.qiniuUploadPool.executeAsync(centerUserMain, mapCheck);

			resultStr = "上传成功，请耐心等待后台审核。";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(WebConstant.CODE_500, e.getMessage());
		}
		return resultStr;
	}

	public void testNettyWs(Map<String, Object> map) {
		try {
			String URL = "ws://localhost:7088/connect?paramKey=";
			URL += map.get("name");
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
					p.addLast(new StringEncoder(Charset.forName("UTF-8")), new HttpClientCodec(),
							new HttpObjectAggregator(8192), handler);
				}

			});
			Channel ch = bootstrap.connect(host, port).sync().channel();

			// System.out.println("测试链接将在10秒后断开。。。。");
			// Thread.sleep(10 * 1000);
			// ch.close();
			String msKey = "netty-websocket:" + map.get("name");

			// 方案1
			// ServletContext servletContext =
			// SpringContextUtil.getServletContext();
			// servletContext.setAttribute(msKey, ch);
			// 方案2
			WebSocketCahce.clients.put(msKey, ch);

			logger.info("put servletContext key ->" + msKey + ",value ->" + ch);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(WebConstant.CODE_500, "建立链接错误！");
		}
	}

	public void testNettyWs2(Map<String, Object> map) {
		try {
			Channel ch = null;
			String msKey = "netty-websocket:" + map.get("name");
			// 方案1
			// ServletContext servletContext =
			// SpringContextUtil.getServletContext();
			// ch = (Channel) servletContext.getAttribute(msKey);
			// 方案2
			ch = WebSocketCahce.clients.get(msKey);
			if (ch == null) {
				throw new BusinessException(WebConstant.CODE_500, "链接不存在或者已经关闭。。");
			}

			logger.info("get servletContext key ->" + msKey + ",value ->" + ch);
			ch.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testNettyWs3(Map<String, Object> map) {
		try {
			Channel ch = null;
			String msKey = "netty-websocket:" + map.get("name");
			// 方案1String
//			 ServletContext servletContext =
//			 SpringContextUtil.getServletContext();
//			 ch = (Channel) servletContext.getAttribute(msKey);
			// 方案2
			ch = WebSocketCahce.clients.get(msKey);

//			logger.info("get servletContext key ->" + msKey + ",value ->" + ch);

//			 System.out.println(ch.isRegistered());
//			 System.out.println(ch.isWritable());

			String string = (String) map.get("msg");
			//写字符串
			ChannelFuture lastWriteFuture = ch.writeAndFlush(new TextWebSocketFrame(string));
			
//			byte[] data = ZipUtil.gZip(string.getBytes());
//			System.out.println("bZip2->" + ZipUtil.bytesToHexString(data));
			//压缩字符串，或者做数据加密
//			ChannelFuture lastWriteFuture = ch.writeAndFlush(new TextWebSocketFrame(ZipUtil.bytesToHexString(data)));


			// 等到所有消息都被刷新后再关闭频道。
			if (lastWriteFuture != null) {
				lastWriteFuture.sync();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
