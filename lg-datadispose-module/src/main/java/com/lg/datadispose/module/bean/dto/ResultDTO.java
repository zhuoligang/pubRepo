package com.lg.datadispose.module.bean.dto;

/**
 * 
* @ClassName: ResultDTO
* @Description: TODO(前端返回vo类)
* @author zlg
* @date 2019年9月20日下午5:38:06
*
* @param <T>
 */
public class ResultDTO<T> {

	/**
	 * 状态码
	 */
	private Integer code;
	
	/**
	 * 信息
	 */
	private String message;
	
	/**
	 * 返回数据
	 */
	private T data;

	public ResultDTO() {}
	
	public ResultDTO(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public ResultDTO(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
