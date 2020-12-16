package com.lg.web.module.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
* @ClassName: GlobalExceptionHandler
* @Description: TODO(全局异常处理器)
* @author zlg
* @date 2019年5月29日下午3:40:07
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
		return new com.lg.web.module.bean.vo.ResultVO<String>(exception.getCode(), exception.getMessage());
	}

}
