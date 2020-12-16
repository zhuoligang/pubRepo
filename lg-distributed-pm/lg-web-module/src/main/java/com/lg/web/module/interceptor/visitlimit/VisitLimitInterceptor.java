package com.lg.web.module.interceptor.visitlimit;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lg.web.module.annotation.VisitLimit;
import com.lg.web.module.conf.RedisCache;
import com.lg.web.module.constant.WebConstant;
import com.lg.web.module.exception.BusinessException;
import com.lg.web.module.util.WebModuleUtil;

@Component
public class VisitLimitInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private RedisCache redisService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			if (!method.isAnnotationPresent(VisitLimit.class)) {
				return true;
			}
			VisitLimit accessLimit = method.getAnnotation(VisitLimit.class);
			if (accessLimit == null) {
				return true;
			}
			int limit = accessLimit.limit();
			long sec = accessLimit.sec();
			String key = "visit_limit:" + WebModuleUtil.getIpAddr(request) + request.getRequestURI();
			Integer maxLimit = null;
			Object value = redisService.getCache(key);
			if (value != null) {
				maxLimit = Integer.valueOf(String.valueOf(value));
			}
			if (maxLimit == null) {
				redisService.putCacheWithExpireTime(key, "1", sec);
			} else if (maxLimit < limit) {
				Integer i = maxLimit + 1;
				redisService.putCacheWithExpireTime(key, i.toString(), sec);
			} else {
				// output(response, "请求太频繁!");
				// return false;
				throw new BusinessException(WebConstant.CODE_500, "请求太频繁!");
			}
		}
		return true;
	}

	public void output(HttpServletResponse response, String msg) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.write(msg.getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			outputStream.flush();
			outputStream.close();
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}
}
