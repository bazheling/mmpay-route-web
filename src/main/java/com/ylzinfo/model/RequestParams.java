package com.ylzinfo.model;

import java.io.Serializable;
import java.util.Map;

public class RequestParams implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 应用id
	 */
	private String appId;

	/**
	 * 请求参数
	 */
	private Object param;

	/**
	 * 签名
	 */
	private String sign;

	/**
	 * 时间戳
	 */
	private String timestamp;

	/**
	 * 交易类型
	 */
	private String transType;

	/**
	 * 签名类型
	 */
	private String signType;

	/**
	 * 加密类型
	 */
	private String encryptType;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 密文
	 */
	private String encryptData;

	/**
	 * web透传
	 */
	private Map<String, String> extra;

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getEncryptType() {
		return encryptType;
	}

	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEncryptData() {
		return encryptData;
	}

	public void setEncryptData(String encryptData) {
		this.encryptData = encryptData;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public Map<String, String> getExtra() {
		return extra;
	}

	public void setExtra(Map<String, String> extra) {
		this.extra = extra;
	}
}
