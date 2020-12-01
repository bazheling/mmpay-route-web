package com.ylzinfo.model;

import java.io.Serializable;
import java.util.Map;

public class ResponseParams<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String respCode;

	private String respMsg;

	private T param;

	private String sign;

	private String timestamp;

	private Map<String, Object> pageParams;

	// 对账单明细
	private String billContent;

	public String getBillContent() {
		return billContent;
	}

	public void setBillContent(String billContent) {
		this.billContent = billContent;
	}

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
	 * 交易类型
	 */
	private String transType;

	/**
	 * appId
	 */
	private String appId;

	/**
	 * 额外的返回参数 (供controller处理的参数) add by xuwy
	 */
	private Map<String, Object> externalParam;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

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

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public T getParam() {
		return param;
	}

	public void setParam(T param) {
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

	public Map<String, Object> getPageParams() {
		return pageParams;
	}

	public void setPageParams(Map<String, Object> pageParams) {
		this.pageParams = pageParams;
	}

	public Map<String, Object> getExternalParam() {
		return externalParam;
	}

	public void setExternalParam(Map<String, Object> externalParam) {
		this.externalParam = externalParam;
	}
}
