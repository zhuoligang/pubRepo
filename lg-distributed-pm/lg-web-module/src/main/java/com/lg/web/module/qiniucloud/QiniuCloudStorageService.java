package com.lg.web.module.qiniucloud;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.lg.web.module.exception.BusinessException;
import com.lg.web.module.util.WebModuleUtil;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * 
 * @ClassName: QiniuCloudStorageService
 * @Description: TODO(七牛云存储)
 * @author zlg
 * @date 2019年6月11日下午5:47:40
 *
 */
@Service
public class QiniuCloudStorageService {

	public String upload(byte[] data, String path) {
		try {
			String token = Auth.create(QiniuConstant.QINIUACCESSKEY, QiniuConstant.QINIUSECRETKEY)
					.uploadToken(QiniuConstant.QINIUBUCKETNAME);
			//华南 Zone.zone2()
			UploadManager uploadManager = new UploadManager(new Configuration(Zone.autoZone()));
			Response res = uploadManager.put(data, path, token);
			if (!res.isOK()) {
				throw new RuntimeException("上传七牛出错：" + res.toString());
			}
		} catch (Exception e) {
			throw new BusinessException("上传文件失败，请核对七牛配置信息", e);
		}

		return QiniuConstant.QINIUDOMAIN + "/" + path;
	}

	public String upload(InputStream inputStream, String path) {
		try {
			byte[] data = IOUtils.toByteArray(inputStream);
			return this.upload(data, path);
		} catch (IOException e) {
			throw new BusinessException("上传文件失败", e);
		}
	}

	public String uploadSuffix(byte[] data, String suffix) {
		return upload(data, getPath(QiniuConstant.QINIUPREFIX, suffix));
	}

	public String uploadSuffix(InputStream inputStream, String suffix) {
		return upload(inputStream, getPath(QiniuConstant.QINIUPREFIX, suffix));
	}
	
	/**
	 * 文件路径
	 * 
	 * @param prefix
	 *            前缀
	 * @param suffix
	 *            后缀
	 * @return 返回上传路径
	 */
	public String getPath(String prefix, String suffix) {
		// 生成uuid
		String uuid = WebModuleUtil.createUUId();
		// 文件路径
		String path = WebModuleUtil.format(new Date(), "yyyyMMdd") + "/" + uuid;

		if (StringUtils.isNotBlank(prefix)) {
			path = prefix + "/" + path;
		}

		return path + suffix;
	}
}
