package com.lg.web.module.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @ClassName: ImageUtil
 * @Description: TODO(图片处理工具)
 * @author zlg
 * @date 2019年6月10日下午4:43:57
 *
 */
public class ImageUtil {
	public static void main(String[] args) {
		String str = "123.jpg";
		System.out.println(isImageSuffixs(str));
	}

	/**
	 * 
	* @Title: chooseImage
	* @Description: TODO(验证图片是否满足需求【图片类型、图片大小】)
	* @param @param image
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean chooseImage(MultipartFile image) {
		boolean bool = false;
		String inputName = image.getContentType();
		if (isImageSuffixs(inputName) && image.getSize() < 10485760) {// 10M = 10 * 1024 *1024 = 10485760k
			bool = true;
		}
		return bool;
	}
	
	/**
	 * 
	* @Title: chooseImage
	* @Description: TODO(验证图片是否满足需求【图片类型、图片大小】)
	* @param @param map
	* @param @return    设定文件
	* @return int    0:满足，1：图片类型不满足，2：图片大小不满足
	* @throws
	 */
	public static int chooseImages(Map<String, MultipartFile> map) {
		for (Entry<String, MultipartFile> entry : map.entrySet()) {
			MultipartFile image = entry.getValue();
			
			String inputName = image.getContentType();
			if (!isImageSuffixs(inputName)) {
				return 1;
			}
			if (image.getSize() >= 10485760) {// 10M = 10 * 1024 *1024 = 10485760k
				return 2;
			}
		}
		return 0;
	}

	/**
	 * 验证图片后缀
	 * 
	 * @param suffixs
	 * @return
	 */
	public static boolean isImageSuffixs(String inputName) {
		String suffixs = inputName.substring(inputName.lastIndexOf("/") + 1);
		String regex = "^(jpg|jpeg|png|JPG|PNG)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(suffixs);
		return matcher.matches();
	}

	/**
	 * 重新生成图片，验证图片是否有效
	 * 
	 * @param inputStream
	 * @param fileName
	 * @param uploadDir
	 * @return
	 * @throws Exception
	 */
	public static boolean recastImg(MultipartFile file, String fileName, String uploadDir) throws Exception {
		if(file == null || file.getSize() <= 0){
			return false;
		}
		InputStream inputStream = file.getInputStream();
		BufferedImage src = ImageIO.read(inputStream);
		// 无效图片
		if (src == null) {
			return false;
		}
		// 得到源图宽
		int old_w = src.getWidth();
		// 得到源图长
		int old_h = src.getHeight();
		// 验证图片长宽是否有效
		if (0 == old_w || 0 == old_h) {
			return false;
		}
		BufferedImage newImg = null;
		// 判断输入图片的类型
		switch (src.getType()) {
		case 13:
			// png,gif
			newImg = new BufferedImage(old_w, old_h, BufferedImage.TYPE_4BYTE_ABGR);
			break;
		default:
			newImg = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
			break;
		}
		Graphics2D gd = newImg.createGraphics();
		// 从原图上取颜色绘制新图
		gd.drawImage(src, 0, 0, old_w, old_h, null);
		gd.dispose();
		// 根据图片尺寸压缩比得到新图的尺寸
		newImg.getGraphics().drawImage(src.getScaledInstance(old_w, old_h, Image.SCALE_SMOOTH), 0, 0, null);
		File newFile = new File(uploadDir);
		String endName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		return ImageIO.write(newImg, endName, newFile);
	}
}
