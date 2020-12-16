package com.lg.datadispose.module.exception;

/**
 * 
 * @ClassName: BusinessException
 * @Description: TODO(自定义异常类)
 * @author zlg
 * @date 2019年8月2日下午2:58:09
 *
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;

	private String message;

	private Object data;

	public BusinessException() {
		super();
	}

	public BusinessException(String msg) {
		super(msg);
		this.message = msg;
	}

	public BusinessException(int code, String msg) {
		super(msg);
		this.code = code;
		this.message = msg;
	}

	public BusinessException(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
		this.message = msg;
	}

	public BusinessException(Throwable cause, String msg) {
		super(msg, cause);
		this.message = msg;
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
