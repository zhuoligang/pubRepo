package com.lg.web.module.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 
* @ClassName: ZipUtil
* @Description: TODO(压缩工具类)
* @author zlg
* @date 2019年7月24日上午11:02:02
*
 */
public class ZipUtil {

	/**
	 * 
	* @Title: gZip
	* @Description: TODO(字符串的压缩)
	* @param @param str
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String gZip(String str){
		String resultStr = null;
		if (null == str || str.length() <= 0) {
			return resultStr;
		}
		if (str != null && !"".equals(str)) {
			try {
				// 创建一个新的 byte 数组输出流
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				// 使用默认缓冲区大小创建新的输出流
				GZIPOutputStream gzip = new GZIPOutputStream(out);
				// 将 b.length 个字节写入此输出流
				gzip.write(str.getBytes());
				gzip.finish();
				gzip.close();
				// 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
				resultStr = out.toString("ISO-8859-1");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultStr;
	}

	/***
	 * 压缩GZip
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] gZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(bos);
			gzip.write(data);
			gzip.finish();
			gzip.close();
			b = bos.toByteArray();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	/***
	 * 解压GZip
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] unGZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];
			int num = -1;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
			baos.close();
			gzip.close();
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	/***
	 * 压缩Zip
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] zip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ZipOutputStream zip = new ZipOutputStream(bos);
			ZipEntry entry = new ZipEntry("zip");
			entry.setSize(data.length);
			zip.putNextEntry(entry);
			zip.write(data);
			zip.closeEntry();
			zip.close();
			b = bos.toByteArray();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	/***
	 * 解压Zip
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] unZip(byte[] data) {
		byte[] b = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			ZipInputStream zip = new ZipInputStream(bis);
			while (zip.getNextEntry() != null) {
				byte[] buf = new byte[1024];
				int num = -1;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((num = zip.read(buf, 0, buf.length)) != -1) {
					baos.write(buf, 0, num);
				}
				b = baos.toByteArray();
				baos.flush();
				baos.close();
			}
			zip.close();
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public static String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2){
				sb.append(0);
			}
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String str = "pro/idcardimages";
		System.out.println(str.substring(0, str.indexOf("/") + 1));
		// String s = "this is a test";
		//
		// byte[] b1 = zip(s.getBytes());
		// System.out.println("zip:" + bytesToHexString(b1));
		// byte[] b2 = unZip(b1);
		// System.out.println("unZip:" + new String(b2));
		//
		// byte[] b5 = gZip(s.getBytes());
		// System.out.println("bZip2:" + bytesToHexString(b5));
		// byte[] b6 = unGZip(b5);
		// System.out.println("unBZip2:" + new String(b6));

	}
}
