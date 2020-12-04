package com.ylzinfo.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ylzinfo.MmpayRouteProperties;
import com.ylzinfo.dao.entity.MmpRouteConfig;
import com.ylzinfo.dao.entity.MmpRouteDto;
import com.ylzinfo.exception.BusinessException;
import com.ylzinfo.exception.MessageCode;
import com.ylzinfo.service.IMmpayRouteService;
import com.ylzinfo.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


/**
 * @author lcl
 * @date 2020/12/3
 */
public class MmpayRouteJsonServiceImpl implements IMmpayRouteService {

    private static final Logger logger = LoggerFactory.getLogger(MmpayRouteJsonServiceImpl.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public final static Byte INVALID_STATUS = 0;

    @Override
    public MmpRouteConfig queryMmpayRouteConfig(String appId, String medOrgNo) throws IOException {
        logger.info("查询路由地址入参，appId:{},medOrgNo:{}", appId, medOrgNo);

        if (StringUtil.isNotEmpty(medOrgNo)) {
            appId = medOrgNo;
        }

        MmpRouteConfig mmpRouteConfig = queryByAppId(appId);

        if (INVALID_STATUS.equals(mmpRouteConfig.getStatus())) {
            throw new BusinessException(MessageCode.ERROR_CONFIG_STATUS_INVALID, MessageCode.ERROR_CONFIG_STATUS_INVALID_MSG);
        }


        return mmpRouteConfig;
    }

    @Override
    public MmpRouteConfig queryByAppId(String appId) throws IOException {
        MmpRouteConfig mmpRouteConfig = new MmpRouteConfig();
        List<MmpRouteDto> list = getJson();
        for (int i = 0; i < list.size(); i++) {
            if (appId.equals(list.get(i).getAppId())) {
                mmpRouteConfig.setAppId(appId);
                mmpRouteConfig.setAppSecret(list.get(i).getAppSecret());
                mmpRouteConfig.setMmpWebUrl(list.get(i).getMmpWebUrl());
                return mmpRouteConfig;
            }
        }
        throw new BusinessException(MessageCode.ERROR_APP_ID_ILLEGAL, MessageCode.ERROR_APP_ID_ILLEGAL_MSG);

    }

    public List<MmpRouteDto> getJson() throws IOException {
        File file = new File(MmpayRouteProperties.jsonPath);
        InputStreamReader in = new InputStreamReader(new FileInputStream(file), "UTF-8");

        List<MmpRouteDto> mockCardList = MAPPER.readValue(in, new TypeReference<List<MmpRouteDto>>() {
        });

        return mockCardList;
    }


}
