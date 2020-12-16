package com.lg.web.module.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.lg.web.module.bean.vo.ResultVO;
import com.lg.web.module.conf.RedisCache;
import com.lg.web.module.constant.WebConstant;
import com.lg.web.module.util.IPUtils;
import com.lg.web.module.util.SpringContextUtil;

//@Component
public class VerifyFilter extends AbstractAuthenticationProcessingFilter {
	
	private RedisCache redisCache = (RedisCache) SpringContextUtil.getBean(RedisCache.class);
	
	public VerifyFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		return null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");

		ServletRequest requestWrapper = new RequestReaderHttpServletRequestWrapper((HttpServletRequest) request);

		ResultVO<String> resultVO = null;
		try {
			HttpServletRequest req = (HttpServletRequest) requestWrapper;
			HttpServletResponse res = (HttpServletResponse) response;
			
//			String ip = IPUtils.getIpAddr(req);
//			List<String> IP_INTERNAL = this.safetyService.getIP_INTERNAL();
//			if (!IP_INTERNAL.contains(ip)) {//校验ip白名单
//				resultVO = new ResultVO<String>(300, WebConstant.FAILURE,"ip未授权");
//				logger.error("ip未授权");
//				response.getWriter().write(JSONObject.toJSONString(resultVO));
//				return;	
//			}
			if (!requiresAuthentication(req, res)) {
				chain.doFilter(requestWrapper, response);
				return;
			}
			//header中必须包含userId、Authorization
			String userId = req.getHeader("userId");
			String token = req.getHeader("Authorization");
			if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(token)) {
				resultVO = new ResultVO<String>(501, WebConstant.FAILURE,"请登录。");
				logger.error("请登录。");
				response.getWriter().write(JSONObject.toJSONString(resultVO));
				return;	
			}
//			BibrThirdUser bibrThirdUser = this.safetyService.queryUserByUserId(userId);
//			if(bibrThirdUser == null){
//				resultVO = new ResultVO<String>(500, WebConstant.FAILURE,
//						"用户不在体系内");
//				logger.error("用户不在体系内");
//				response.getWriter().write(JSONObject.toJSONString(resultVO));
//				return;	
//			}
//			else if(bibrThirdUser.getUserLevel() != 1){
//				resultVO = new ResultVO<String>(500, WebConstant.FAILURE,
//						"用户没有该操作的权限");
//				logger.error("用户没有该操作的权限");
//				response.getWriter().write(JSONObject.toJSONString(resultVO));
//				return;	
//			}
			// 如果是携带token的资源请求，需要验证用户是否在redis中存在禁用信息
			String keyStr = "freeze_main:" + userId;
			String freezeMainFlag = this.redisCache.getCache(keyStr);
			if (freezeMainFlag != null && !"".equals(freezeMainFlag)) {// 如果查到禁用信息，通知前端做下线处理
				logger.info("get cache :" + keyStr + "value :" + freezeMainFlag);
				resultVO = new ResultVO<String>(501, WebConstant.FAILURE,
						"用户已被冻结，如有疑问，请联客服人员！");
				logger.error("用户已被冻结，如有疑问，请联客服人员！");
				response.getWriter().write(JSONObject.toJSONString(resultVO));
				return;	
			}
			// 生成重复登录检测的键
			String deviceType = req.getHeader("deviceType");
			String keyToken = "token_safe:" + deviceType + "-" + userId;
			String tokenValue = this.redisCache.getCache(keyToken);
			logger.info("get cache :" + keyToken + "value :" + tokenValue);
			if (StringUtils.isEmpty(tokenValue)) {
				resultVO = new ResultVO<String>(501, WebConstant.FAILURE,"请登录。");
				logger.error("请登录。");
				response.getWriter().write(JSONObject.toJSONString(resultVO));
				return;	
			}
			// 截取token
			String tokenFlag = tokenValue.substring(0, tokenValue.lastIndexOf("-"));
			if (!token.equals(tokenFlag)) {// 临时处理，如果token没有对应上，再判断访问是否来自同一个ip
				// 截取此次登录ip
				String ipFlag = tokenValue.substring(tokenValue.lastIndexOf("-") + 1, tokenValue.length());
				// 获取IP-----如果是同一个ip地址，不弹出提示消息，只是退出登录
				String ip = IPUtils.getIpAddr(req);
				if (ip.equals(ipFlag)) {
					resultVO = new ResultVO<String>(501, WebConstant.FAILURE,"登录已失效，请重新登录。");
					logger.error("登录已失效，请重新登录。");
				} else {
					resultVO = new ResultVO<String>(501, WebConstant.FAILURE,
							"您的帐号已在其他地方登录，请确认是否是本人操作，如非本人操作，请及时修改密码。");
					logger.error("您的帐号已在其他地方登录，请确认是否是本人操作，如非本人操作，请及时修改密码。");
				}
				response.getWriter().write(JSONObject.toJSONString(resultVO));
				return;	
			}
			
		} catch (Exception e) {
			resultVO = new ResultVO<String>(WebConstant.CODE_500, WebConstant.FAILURE, e.getMessage());
			logger.error(e.getMessage(),e);
			response.getWriter().write(JSONObject.toJSONString(resultVO));
			return;
		}
		chain.doFilter(requestWrapper, response);
	}

}
