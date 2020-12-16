package com.lg.datadispose.module.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lg.datadispose.module.bean.vo.ResultVO;

/**
 * 全局异常处理器
 * 
 * @author Administrator
 *
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 自定义异常
	 * 
	 * @param request
	 * @param exception
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = BusinessException.class)
	public Object businessHandler(HttpServletRequest request, BusinessException exception, Model model)
			throws Exception {
		return new ResultVO<Object>(exception.getCode(), exception.getMessage(), exception.getData());
	}
	
}
