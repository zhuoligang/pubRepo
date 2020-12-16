package com.bi.activity.util;

/**
 * 
 * @author zlg
 *	图片上传工具类，
 *	包含操作后的返回码，参数和执行操作后的消息提示
 * @param <T>
 */
public class ResultData<T> {
	/**
	 * 参数
	 */
	private T data;
	
	/**
	 * 运行码
	 */
	private int code =200;
	
	/**
	 * 消息提示
	 */
	private String msg;
	
	/**
	 * 是否成功
	 */
	private Boolean success = true;
	
	/**
	 * 以下是get。set方法
	 */
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getCode() {
		
		return code;
	}
	public void setCode(int code) {
		if(200 != code){
			success = false;
		}
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}