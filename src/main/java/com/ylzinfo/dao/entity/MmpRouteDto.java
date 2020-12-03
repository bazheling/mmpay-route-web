package com.ylzinfo.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class MmpRouteDto implements Serializable {

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.app_id
     *
     * @mbg.generated
     */
    private String appId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.app_secret
     *
     * @mbg.generated
     */
    private String appSecret;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmp_route_config.app_name
     *
     * @mbg.generated
     */
    private String mmpWebUrl;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getMmpWebUrl() {
        return mmpWebUrl;
    }

    public void setMmpWebUrl(String mmpWebUrl) {
        this.mmpWebUrl = mmpWebUrl;
    }
}