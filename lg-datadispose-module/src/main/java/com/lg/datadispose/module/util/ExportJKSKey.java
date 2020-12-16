package com.lg.datadispose.module.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import sun.misc.BASE64Encoder;

/**
 * 
 * @ClassName: ExportJKSKey
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author zlg
 * @date 2019年9月23日上午9:26:44
 *
 */
public class ExportJKSKey {
	/** jks部分 **/
	private final static String JKS_FILE = "zlg_key.jks";
	private final static String JKS_NAME = "zlg_key";
	private final static String JKS_PASSWORD = "123456";
	private final static String keyStoreType = "JKS";

	private File keystoreFile;
	private File exportedFile;

	public static KeyPair getPrivateKey(KeyStore keystore, String JKS_NAME, char[] JKS_PASSWORD) {
		try {
			Key key = keystore.getKey(JKS_NAME, JKS_PASSWORD);
			if (key instanceof PrivateKey) {
				Certificate cert = keystore.getCertificate(JKS_NAME);
				PublicKey publicKey = cert.getPublicKey();
				return new KeyPair(publicKey, (PrivateKey) key);
			}
		} catch (UnrecoverableKeyException e) {
		} catch (NoSuchAlgorithmException e) {
		} catch (KeyStoreException e) {
		}
		return null;
	}

	public void export() throws Exception {
		KeyStore keystore = KeyStore.getInstance(keyStoreType);
		BASE64Encoder encoder = new BASE64Encoder();
		keystore.load(new FileInputStream(keystoreFile), JKS_PASSWORD.toCharArray());
		KeyPair keyPair = getPrivateKey(keystore, JKS_NAME, JKS_PASSWORD.toCharArray());
		PublicKey publicKey = keyPair.getPublic();
		String encoded = encoder.encode(publicKey.getEncoded());
		FileWriter fw = new FileWriter(exportedFile);
		fw.write("—–BEGIN PUBLIC KEY—–\n");
		fw.write(encoded);
		fw.write("\n");
		fw.write("—–END PUBLIC KEY—–");
		fw.close();
	}

	public static void main(String args[]) throws Exception {
		ExportJKSKey export = new ExportJKSKey();
		export.keystoreFile = new File("D:/zlgstudy/jks/" + JKS_FILE);
		export.exportedFile = new File("D:/zlgstudy/jks/" + "public_key.txt");
		export.export();
//		export.accessTokenConverter();
	}

	/*
	 * 客户端
	 */
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
		};
		Resource resource = new ClassPathResource("public_key.txt");
		String publicKey = null;
		try {
			publicKey = inputStream2String(resource.getInputStream());
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		accessTokenConverter.setVerifierKey(publicKey);
		return accessTokenConverter;
	}

	private String inputStream2String(InputStream is) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}
}
