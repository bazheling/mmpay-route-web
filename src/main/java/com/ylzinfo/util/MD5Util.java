package com.ylzinfo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MD5工具类 功能描述： 2014年1月10日
 * 
 * @author zwsun
 * 
 */
public class MD5Util {

	/**
	 * 适用于上G大的文件
	 * 
	 * @param file
	 * @return 加密的密文
	 * @throws IOException
	 */
	public static String encrypt(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		MessageDigest messagedigest = null;
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.out.println("初始化失败，MessageDigest不支持MD5Util，原因是：" + nsaex.getMessage());
		}
		in.close();
		messagedigest.update(byteBuffer);
		return DataFormater.byte2hex(messagedigest.digest());
	}

	/**
	 * MD5加密
	 * 
	 * @param s
	 *            明文
	 * @return 密文
	 * @throws UnsupportedEncodingException
	 */
	public static String encrypt(String s) {
		try {
			return encrypt(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("密码验证出异常了");
			return "";
		}
	}

	/**
	 * MD5加密
	 * 
	 * @param bytes
	 *            明文
	 * @return 密文
	 */
	public static String encrypt(byte[] bytes) {
		MessageDigest messagedigest = null;
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			System.out.println("初始化失败，MessageDigest不支持MD5Util，原因是：" + nsaex.getMessage());
		}
		messagedigest.update(bytes);
		return DataFormater.byte2hex(messagedigest.digest());
	}

	/**
	 * 密码校验
	 * 
	 * @param password
	 *            明文密码
	 * @param md5PwdStr
	 *            密文
	 * @return 相等：true，不相等：false
	 */
	public static boolean checkPassword(String password, String md5PwdStr) {
		String s = "";
		s = encrypt(password);
		return s.equals(md5PwdStr);
	}

	/**
	 * MD5 加密 16进制
	 */
	public static String md5HexEncrypt(String str, String charset) {
		return hexEncrypt(str, charset, "MD5");
	}

	/**
	 * 加密 16进制
	 * @param str
	 * @param charset
	 * @param algorithm
	 * @return
	 */
	private static String hexEncrypt(String str, String charset, String algorithm) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.reset();
			messageDigest.update(str.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!"+e.getMessage());
			return "";
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupport Charset!"+e.getMessage());
			return "";
		}

		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

	/**
	 * (微信授权社保卡查询接口)对参数列表做签名
	 * @param allParam
	 * @param key
	 * @param secret
	 * @return
	 */
	public static String signMap(TreeMap<String, String> allParam, String key) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> e : allParam.entrySet()) {
			sb.append(e.getKey());
			sb.append('=');
			sb.append(e.getValue());
			sb.append('&');
		}
		sb.setLength(sb.length() - 1); //去除多余的&，不会存在空param列表，无须判断
		sb.append("&key="+key);
		String toSignStr = sb.toString();
		
		return md5HexEncrypt(toSignStr, "UTF-8");
	}
}
