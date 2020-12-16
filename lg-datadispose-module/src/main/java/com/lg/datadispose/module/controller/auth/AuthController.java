package com.lg.datadispose.module.controller.auth;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lg.datadispose.module.bean.dto.ResultDTO;
import com.lg.datadispose.module.bean.dto.TokenDTO;
import com.lg.datadispose.module.bean.dto.TokenResultDTO;
import com.lg.datadispose.module.conf.RedisCache;
import com.lg.datadispose.module.constant.AuthContants;
import com.lg.datadispose.module.domain.User;
import com.lg.datadispose.module.exception.BusinessException;
import com.lg.datadispose.module.service.UserService;
import com.lg.datadispose.module.util.IpUtil;
import com.lg.datadispose.module.util.TokenUtil;

/**
 * 
* @ClassName: AuthController
* @Description: TODO(认证授权服务端)
* @author zlg
* @date 2019年9月23日下午3:49:42
*
 */
@Controller
public class AuthController {

	private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Value(value = "${prefix.auth}")
	private String authPrefix;

	@Value(value = "${oauth.tokenUrl}")
	private String tokenUrl;

	@Qualifier(value = "browserRestTemplate")
	@Autowired
	private RestTemplate browserRestTemplate;

	@Qualifier(value = "appRestTemplate")
	@Autowired
	private RestTemplate appRestTemplate;
	@Autowired
	private UserService userService;
	@Autowired
	private RedisCache redisCache;

	/**
	 * 注册
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/register")
	public ResultDTO<String> register(@RequestBody User user) {
		logger.info("&AuthenticationCenter&AuthController&register&ApiStatistics");
		String result = this.userService.saveUser(user);
		return new ResultDTO<String>(AuthContants.CODE_200, AuthContants.KEYWORD_SUCCESS, result);
	}
	
	/**
	 * 获取token
	 * 
	 * @param request
	 * @param attributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/token")
	public ResultDTO<TokenResultDTO> token(HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attributes) {
		logger.info("&AuthenticationCenter&AuthController&token&ApiStatistics");
		String code = request.getParameter(AuthContants.TOKEN_CODE_PARAMETER_NAME);
		if (StringUtils.isEmpty(code)) {
			throw new BusinessException(AuthContants.CODE_EXCEPTION);
		}
		// 发送请求token
		String deviceType = request.getHeader("deviceType");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		TokenResultDTO tokenResultDTO = null;
		if ("browser".equals(deviceType)) {
			tokenResultDTO = this.browserRestTemplate.postForObject(
					this.authPrefix + "oauth/token?client_id=" + AuthContants.BROWSER_CLIENT_ID
							+ "&grant_type=authorization_code&redirect_uri=" + this.tokenUrl + "&code=" + code,
					entity, TokenResultDTO.class);
		} else if ("app".equals(deviceType)) {
			tokenResultDTO = this.appRestTemplate.postForObject(
					this.authPrefix + "oauth/token?client_id=" + AuthContants.APP_CLIENT_ID
							+ "&grant_type=authorization_code&redirect_uri=" + this.tokenUrl + "&code=" + code,
					entity, TokenResultDTO.class);
		}
		return new ResultDTO<TokenResultDTO>(AuthContants.CODE_200, AuthContants.KEYWORD_SUCCESS,
				new TokenResultDTO(tokenResultDTO.getAccess_token(), tokenResultDTO.getRefresh_token(),
						tokenResultDTO.getUserId(), tokenResultDTO.getExpires_in()));
	}

	/**
	 * 重新获取token
	 * 
	 * @param refreshToken
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/reacquire")
	public ResultDTO<?> reacquire(@RequestBody Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("&AuthenticationCenter&AuthController&reacquire&ApiStatistics");
		// 解析refresh_token
		TokenDTO refreshTokenDTO = TokenUtil.resolveAccessToken(map.get("refreshToken"));
		Long refreshTokenExp = refreshTokenDTO.getExp() * 1000;
		Long now = System.currentTimeMillis();
		if (refreshTokenExp < now) {
			return new ResultDTO<>(AuthContants.CODE_605, AuthContants.REFRESH_TOKEN_EXPIRED);
		}
		// 重新请求access token
		String deviceType = request.getHeader("deviceType");
		// 重设redis缓存token
		String keyStr = "token_safe:" + deviceType + "-" + map.get("userId");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		TokenResultDTO tokenResultDTO = null;
		if ("browser".equals(deviceType)) {
			tokenResultDTO = this.browserRestTemplate.postForObject(
					this.authPrefix + AuthContants.REFRESH_TOKEN_URL + map.get("refreshToken"), entity,
					TokenResultDTO.class);
			this.redisCache.putCacheWithExpireTime(keyStr,
					"Bearer " + tokenResultDTO.getAccess_token() + "-" + IpUtil.getRealIpAddr(request), 86400);
		} else if ("app".equals(deviceType)) {
			tokenResultDTO = this.appRestTemplate.postForObject(
					this.authPrefix + AuthContants.REFRESH_TOKEN_URL + map.get("refreshToken"), entity,
					TokenResultDTO.class);
			this.redisCache.putCacheWithExpireTime(keyStr,
					"Bearer " + tokenResultDTO.getAccess_token() + "-" + IpUtil.getRealIpAddr(request), 3888000);
		}
		// 重设tokenCookie
		// String domain = request.getHeader("x-forwarded-host");
		// if(domain != null && !"".equals(domain)){
		// domain = domain.substring(domain.indexOf(".") + 1);
		// Cookie toeknCookie = new Cookie("token",
		// tokenResultDTO.getAccess_token());
		// toeknCookie.setPath("/");
		// toeknCookie.setDomain(domain);
		// toeknCookie.setMaxAge(-1);
		// response.addCookie(toeknCookie);
		// }

		// 返回
		return new ResultDTO<TokenResultDTO>(AuthContants.CODE_200, AuthContants.KEYWORD_SUCCESS, new TokenResultDTO(
				tokenResultDTO.getAccess_token(), tokenResultDTO.getRefresh_token(), tokenResultDTO.getUserId()));
	}
	
	/**
	 * session过期
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/expiredSession")
	public ResultDTO<?> expiredSession() {
		logger.info("&AuthenticationCenter&AuthController&expiredSession&ApiStatistics");
		return new ResultDTO<>(AuthContants.CODE_604, AuthContants.USER_MULTIPLE_LOGIN);
	}
}
