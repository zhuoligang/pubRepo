package com.lg.datadispose.module.constant;

public class AuthContants {

	/** jks部分 **/
	public static final String JKS_FILE = "kevin_key.jks";
	public static final String JKS_NAME = "kevin_key";
	public static final String JKS_PASSWORD = "123456";

	/** 浏览器的url部分 **/
	public static final String BROWSER_AUTHENTICATION_SUCCESS_URL = "oauth/authorize?client_id=client1&response_type=code&redirect_uri=";
	public static final String REFRESH_TOKEN_URL = "oauth/token?grant_type=refresh_token&refresh_token=";

	/**	APP的url部分 **/
	public static final String APP_AUTHENTICATION_SUCCESS_URL = "oauth/authorize?client_id=client2&response_type=code&redirect_uri=";
	
	/** clientId部分 **/
	public static final String BROWSER_CLIENT_ID = "client1";
	public static final String APP_CLIENT_ID = "client2";

	/** token部分 **/
	public static final String TOKEN_CODE_PARAMETER_NAME = "code";
	public static final String TOKEN_ACCESS_NAME = "_access_token";
	public static final String TOKEN_REFRESH_NAME = "_refresh_token";
	public static final String TOKEN_DISABLE_NAME = "_disable_token";

	/** 异常部分 **/
	public static final String KEYWORD_SUCCESS = "成功";
	public static final String LOGIN_FAILE = "账号或密码不正确";
	public static final String USER_NOT_EXIST = "用户不存在";
	public static final String VERIFY_CODE_ERROR = "验证码不正确";
	public static final String CODE_EXCEPTION = "code不能为空";
	public static final String REFRESH_TOKEN_EXPIRED = "刷新token已过期";
	public static final String REQUEST_PARAMETER_NOT_NULL = "请求参数不能为空";
	public static final String USER_IS_EXIST = "用户已存在";
	public static final String USER_SAVE_ERROR = "保存用户出现错误";
	public static final String USER_MULTIPLE_LOGIN = "用户多地登录";
//	public static final String UPDATE_PHONE_IS_EXIST = "修改的手机号已存在";

//	public static final String RESET_PASSWORD_ERROR = "修改密码出现错误";
//	public static final String ADD_PHONE_ERROR = "添加验证方式错误";
//	public static final String DEL_PHONE_ERROR = "删除验证方式错误";




	/**	CODE码部分 **/
	public static final int CODE_200 = 200;	// 成功
	public static final int CODE_500 = 500;	// 失败
	public static final int CODE_600 = 600;	// 登录失败或未登录
	public static final int CODE_603 = 603; // 验证码不正确
	public static final int CODE_604 = 604;	// 同一用户在多处登录
	public static final int CODE_605 = 605;	// 刷新token已过期
	
	/**	disable部分	**/
	public static final int DISABLE_CODE_0 = 0;
	public static final int DISABLE_CODE_1 = 1;
	
}
