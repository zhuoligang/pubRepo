package com.lg.web.module.qiniucloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QiniuConstant {

	// 七牛绑定的域名
	public static String QINIUDOMAIN;

	@Value("${url.qiniu.qiniuDomain}")
	public void setQINIUDOMAIN(String qINIUDOMAIN) {
		QINIUDOMAIN = qINIUDOMAIN;
	}

	// 七牛路径前缀
	public static String QINIUPREFIX;

	@Value("${url.qiniu.qiniuPrefix}")
	public void setQINIUPREFIX(String qINIUPREFIX) {
		QINIUPREFIX = qINIUPREFIX;
	}

	// 七牛ACCESS_KEY
	public static String QINIUACCESSKEY;

	@Value("${url.qiniu.qiniuAccessKey}")
	public void setQINIUACCESSKEY(String qINIUACCESSKEY) {
		QINIUACCESSKEY = qINIUACCESSKEY;
	}

	// 七牛SECRET_KEY
	public static String QINIUSECRETKEY;

	@Value("${url.qiniu.qiniuSecretKey}")
	public void setQINIUSECRETKEY(String qINIUSECRETKEY) {
		QINIUSECRETKEY = qINIUSECRETKEY;
	}

	// 七牛存储空间名
	public static String QINIUBUCKETNAME;

	@Value("${url.qiniu.qiniuBucketName}")
	public void setQINIUBUCKETNAME(String qINIUBUCKETNAME) {
		QINIUBUCKETNAME = qINIUBUCKETNAME;
	}

}
