package com.ylzinfo.service.impl;

import com.alibaba.fastjson.JSON;
import com.ylzinfo.dao.entity.MmpRouteConfig;
import com.ylzinfo.dao.mappers.MmpRouteConfigMapper;
import com.ylzinfo.exception.BusinessException;
import com.ylzinfo.exception.MessageCode;
import com.ylzinfo.model.RequestParams;
import com.ylzinfo.service.IMmpayRouteService;
import com.ylzinfo.util.SecurityUtil;
import com.ylzinfo.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询mmp路由配置实现类
 *
 * @author xiepy
 * @date 20200805
 */
public class MmpayRouteServiceImpl implements IMmpayRouteService {

    private static final Logger logger = LoggerFactory.getLogger(MmpayRouteServiceImpl.class);

    @Autowired
    private MmpRouteConfigMapper mmpRouteConfigMapper;

    public final static Byte INVALID_STATUS = 0;

    @Override
    public MmpRouteConfig queryMmpayRouteConfig(String appId, String medOrgNo) {
        logger.info("查询路由地址入参，appId:{},medOrgNo:{}", appId, medOrgNo);

        MmpRouteConfig mmpRouteConfig = mmpRouteConfigMapper.selectByMedOrgNo(appId, medOrgNo);
        if (mmpRouteConfig == null) {
            mmpRouteConfig = mmpRouteConfigMapper.selectByPrimaryKey(0L);

            return mmpRouteConfig;
        }

        if (INVALID_STATUS.equals(mmpRouteConfig.getStatus())) {
            throw new BusinessException(MessageCode.ERROR_CONFIG_STATUS_INVALID, MessageCode.ERROR_CONFIG_STATUS_INVALID_MSG);
        }


        return mmpRouteConfig;
    }

    @Override
    public MmpRouteConfig queryByAppId(String appId) {
        MmpRouteConfig mmpRouteConfig = mmpRouteConfigMapper.selectByAppId(appId);
        if (mmpRouteConfig == null) {
            throw new BusinessException(MessageCode.ERROR_APP_ID_ILLEGAL, MessageCode.ERROR_APP_ID_ILLEGAL_MSG);

        }
        return mmpRouteConfig;
    }


}
