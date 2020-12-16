package com.bi.activity.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
 * ClassName:BaseResult ajax 请求的返回类型封装JSON结果
 * @author   zhuoligang
 * @version  Ver 1.0
 * @Date	 2018	2018年9月4日		下午1:26:50
 * @see 	 @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "BaseResult<T>对象", description = "请求的返回类型封装JSON结果BaseResult<T>")
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = -4185151304730685014L;

    /**
     * 执行结果 默认 0失败 1是成功
     */
    @ApiModelProperty(value = "执行结果 默认 0失败 1是成功")
    private int code;

    /**
     * 返回前端的消息
     */
    @ApiModelProperty(value = "返回前端的消息")
    private String msg;

    /**
     * 返回的结果集
     */
    @ApiModelProperty(value = "返回的结果集")
    private T data;

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }
}
