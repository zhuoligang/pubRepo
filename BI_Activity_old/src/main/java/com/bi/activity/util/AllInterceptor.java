package com.bi.activity.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.bi.activity.dto.BaseResult;

/**
 * 
 * ClassName:AllInterceptor 增加一个通行码验证所有接口调用
 * @author   zhuoligang
 * @version  Ver 1.0
 * @Date	 2018	2018年9月4日		下午4:02:37
 * @see
 */
public class AllInterceptor implements HandlerInterceptor {


    /**
     * Handler执行完成之后调用这个方法
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // System.out.println("接口完成调用");
    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // System.out.println("接口之后调用");

    }

    /**
     * Handler执行之前调用这个方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
    	String token = request.getHeader("Authorization");

        if (request.getRequestURI().contains("swagger") || request.getRequestURI().contains("api-docs")
                || request.getRequestURI().contains("getListToRisk") || request.getRequestURI().contains("wmessage.ws")
                || request.getRequestURI().contains("main") || request.getRequestURI().contains("index.html")
                || request.getRequestURI().contains("static") || request.getRequestURI().contains("resource")
                || request.getRequestURI().contains("plug") || request.getRequestURI().contains("ssoToCompliance")) {
            // swagger请求或风控请求或登录请求，直接通过
            return true;
        } else {
            //承兑接口调用拦截
            if(request.getRequestURI().contains("accept")){
            	if (!"1qaz#EDC".equals(token)) {
                    // 通行码错误
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().print(JSONObject.toJSONString(new BaseResult<Object>(99, "通行码错误", null)));
                    return false;
            	}else{
            		return true;
            	}
            }
        	
            if (!"zlg123".equals(token)) {
                // 通行码错误
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().print(JSONObject.toJSONString(new BaseResult<Object>(99, "通行码错误", null)));
                return false;
            } else {
                return true;
            }
        }

    }
}
