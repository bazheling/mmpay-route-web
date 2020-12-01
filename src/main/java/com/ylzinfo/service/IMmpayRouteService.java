package com.ylzinfo.service;

import com.ylzinfo.dao.entity.MmpRouteConfig;
import com.ylzinfo.model.RequestParams;

/**
 * mmp路由接口
 *
 * @author xiepy
 * @date 20200805
 */
public interface IMmpayRouteService {

    /**
     * 查询路由到web地址
     *
     * @param appId
     * @param medOrgNo
     * @return
     */
    MmpRouteConfig queryMmpayRouteConfig(String appId, String medOrgNo);


    /**
     * 根据appId获取配置信息
     *
     * @param appId
     * @return
     */
    MmpRouteConfig queryByAppId(String appId);


}
