package com.ylzinfo.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class MmpRouteConfig implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.app_id
     *
     * @mbg.generated
     */
    private String appId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.app_secret
     *
     * @mbg.generated
     */
    private String appSecret;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.app_name
     *
     * @mbg.generated
     */
    private String appName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.mmp_web_url
     *
     * @mbg.generated
     */
    private String mmpWebUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.app_public_key
     *
     * @mbg.generated
     */
    private String appPublicKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.mmp_private_key
     *
     * @mbg.generated
     */
    private String mmpPrivateKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.app_flag
     *
     * @mbg.generated
     */
    private String appFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.app_channel
     *
     * @mbg.generated
     */
    private String appChannel;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.med_org_no
     *
     * @mbg.generated
     */
    private String medOrgNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mmp_route_config
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.id
     *
     * @return the value of mmp_route_config.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.id
     *
     * @param id the value for mmp_route_config.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.app_id
     *
     * @return the value of mmp_route_config.app_id
     *
     * @mbg.generated
     */
    public String getAppId() {
        return appId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.app_id
     *
     * @param appId the value for mmp_route_config.app_id
     *
     * @mbg.generated
     */
    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.app_secret
     *
     * @return the value of mmp_route_config.app_secret
     *
     * @mbg.generated
     */
    public String getAppSecret() {
        return appSecret;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.app_secret
     *
     * @param appSecret the value for mmp_route_config.app_secret
     *
     * @mbg.generated
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.app_name
     *
     * @return the value of mmp_route_config.app_name
     *
     * @mbg.generated
     */
    public String getAppName() {
        return appName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.app_name
     *
     * @param appName the value for mmp_route_config.app_name
     *
     * @mbg.generated
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.mmp_web_ip
     *
     * @return the value of mmp_route_config.mmp_web_ip
     *
     * @mbg.generated
     */
    public String getMmpWebUrl() {
        return mmpWebUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.mmp_web_url
     *
     * @param mmpWebUrl the value for mmp_route_config.mmp_web_url
     *
     * @mbg.generated
     */
    public void setMmpWebUrl(String mmpWebUrl) {
        this.mmpWebUrl = mmpWebUrl == null ? null : mmpWebUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.app_public_key
     *
     * @return the value of mmp_route_config.app_public_key
     *
     * @mbg.generated
     */
    public String getAppPublicKey() {
        return appPublicKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.app_public_key
     *
     * @param appPublicKey the value for mmp_route_config.app_public_key
     *
     * @mbg.generated
     */
    public void setAppPublicKey(String appPublicKey) {
        this.appPublicKey = appPublicKey == null ? null : appPublicKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.mmp_private_key
     *
     * @return the value of mmp_route_config.mmp_private_key
     *
     * @mbg.generated
     */
    public String getMmpPrivateKey() {
        return mmpPrivateKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.mmp_private_key
     *
     * @param mmpPrivateKey the value for mmp_route_config.mmp_private_key
     *
     * @mbg.generated
     */
    public void setMmpPrivateKey(String mmpPrivateKey) {
        this.mmpPrivateKey = mmpPrivateKey == null ? null : mmpPrivateKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.app_flag
     *
     * @return the value of mmp_route_config.app_flag
     *
     * @mbg.generated
     */
    public String getAppFlag() {
        return appFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.app_flag
     *
     * @param appFlag the value for mmp_route_config.app_flag
     *
     * @mbg.generated
     */
    public void setAppFlag(String appFlag) {
        this.appFlag = appFlag == null ? null : appFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.app_channel
     *
     * @return the value of mmp_route_config.app_channel
     *
     * @mbg.generated
     */
    public String getAppChannel() {
        return appChannel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.app_channel
     *
     * @param appChannel the value for mmp_route_config.app_channel
     *
     * @mbg.generated
     */
    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel == null ? null : appChannel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.med_org_no
     *
     * @return the value of mmp_route_config.med_org_no
     *
     * @mbg.generated
     */
    public String getMedOrgNo() {
        return medOrgNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.med_org_no
     *
     * @param medOrgNo the value for mmp_route_config.med_org_no
     *
     * @mbg.generated
     */
    public void setMedOrgNo(String medOrgNo) {
        this.medOrgNo = medOrgNo == null ? null : medOrgNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.status
     *
     * @return the value of mmp_route_config.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.status
     *
     * @param status the value for mmp_route_config.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.gmt_create
     *
     * @return the value of mmp_route_config.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.gmt_create
     *
     * @param gmtCreate the value for mmp_route_config.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmp_route_config.gmt_modified
     *
     * @return the value of mmp_route_config.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmp_route_config.gmt_modified
     *
     * @param gmtModified the value for mmp_route_config.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmp_route_config
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appId=").append(appId);
        sb.append(", appSecret=").append(appSecret);
        sb.append(", appName=").append(appName);
        sb.append(", mmpWebUrl=").append(mmpWebUrl);
        sb.append(", appPublicKey=").append(appPublicKey);
        sb.append(", mmpPrivateKey=").append(mmpPrivateKey);
        sb.append(", appFlag=").append(appFlag);
        sb.append(", appChannel=").append(appChannel);
        sb.append(", medOrgNo=").append(medOrgNo);
        sb.append(", status=").append(status);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}