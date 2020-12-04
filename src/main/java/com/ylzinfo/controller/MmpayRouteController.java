package com.ylzinfo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ylzinfo.MmpayRouteProperties;
import com.ylzinfo.dao.entity.MmpRouteConfig;
import com.ylzinfo.enums.EncType;
import com.ylzinfo.enums.URLType;
import com.ylzinfo.exception.BusinessException;
import com.ylzinfo.exception.MessageCode;
import com.ylzinfo.model.RequestParams;
import com.ylzinfo.model.ResponseParams;
import com.ylzinfo.service.IMmpayRouteService;
import com.ylzinfo.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author lcl
 * @date 2020/12/1
 */

@Controller
@EnableAutoConfiguration
@RequestMapping("/mmpay-web")
public class MmpayRouteController {

    private static final Logger LOG = LoggerFactory.getLogger(MmpayRouteController.class);


    @RequestMapping(value = "gateway", method = RequestMethod.POST)
    @ResponseBody
    public ResponseParams<Object> route(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        ResponseParams<Object> responseParams = new ResponseParams<Object>();
        RequestParams requestParams;

        try {
            inputStream = request.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            String requestMessage = StreamUtil.readInputStream(inputStreamReader);
            LOG.info("接收到商户请求，请求报文为：{}", requestMessage);
            requestParams = parseRequestParam(requestMessage);

            String signType = "MD5";
            String encryptType = "DES";
            responseParams.setAppId(requestParams.getAppId());
            responseParams.setTransType(requestParams.getTransType());
            responseParams.setVersion(requestParams.getVersion());
            responseParams.setSignType(signType);
            responseParams.setEncryptType(encryptType);
            responseParams.setTimestamp(requestParams.getTimestamp());

            RequestParams reqParams = decryptData(requestParams, requestParams.getAppId());
            JSONObject param = JSONObject.parseObject(JSON.toJSONString(reqParams.getParam()));
            if (param == null) {
                throw new BusinessException(MessageCode.ERROR_REQUEST_MSG_EMPTY, MessageCode.ERROR_REQUEST_MSG_EMPTY_MSG);

            }
            IMmpayRouteService mmpayRouteService = AppUtil.getService(Boolean.valueOf(MmpayRouteProperties.getAppWay));
            MmpRouteConfig mmpRouteConfig = mmpayRouteService.queryMmpayRouteConfig(requestParams.getAppId(), param.getString("medOrgNo"));
            String resStr = HttpUtil.request(mmpRouteConfig.getMmpWebUrl(), requestMessage, URLType.HTTP);
            responseParams = JSON.parseObject(resStr, ResponseParams.class);
        } catch (BusinessException e) {
            responseParams.setRespCode(e.getErrorCode());
            responseParams.setRespMsg(e.getMessage());
            LOG.error("SDK支付出现异常", e);
        } catch (Exception e) {
            responseParams.setRespCode(MessageCode.ERROR_UNKOWN);
            responseParams.setRespMsg(MessageCode.ERROR_UNKOWN_MS);
            LOG.error("SDK支付出现异常", e);
        }
        LOG.info("接收到商户请求，响应报文为：{}", responseParams);
        return responseParams;
    }


    private RequestParams parseRequestParam(String requestMessage) {
        /*
         * 校验请求报文
         */
        if (StringUtil.isEmpty(requestMessage)) {
            throw new BusinessException(MessageCode.ERROR_REQUEST_MSG_EMPTY, MessageCode.ERROR_REQUEST_MSG_EMPTY_MSG);
        }

        RequestParams requestParams = null;

        try {
            requestParams = JSONObject.parseObject(requestMessage, RequestParams.class);
        } catch (Exception e) {
            LOG.error("报文格式非法{}", requestMessage);
            requestParams = null;
        }

        if (requestParams == null) {
            throw new BusinessException(MessageCode.ERROR_REQUEST_MSG_ILLEGAL, MessageCode.ERROR_REQUEST_MSG_ILLEGAL_MSG);
        }

        return requestParams;
    }

    public RequestParams decryptData(RequestParams requestParams, String appId) throws IOException {
        IMmpayRouteService mmpayRouteService = AppUtil.getService(Boolean.valueOf(MmpayRouteProperties.getAppWay));
        MmpRouteConfig mmpRouteConfig = mmpayRouteService.queryByAppId(appId);
        String appSecret = mmpRouteConfig.getAppSecret();

        /*
         * 获取校验时间戳
         */
        String timestamp = requestParams.getTimestamp();

        if (StringUtil.isEmpty(timestamp)) {
            throw new BusinessException(MessageCode.ERROR_TIMESTAMP_EMPTY, MessageCode.ERROR_TIMESTAMP_EMPTY_MSG);
        }

        requestParams.setParam(null);

        /*

         * 解密密文
         */
        String encryptType = requestParams.getEncryptType();

        Boolean isEncryptType = !(StringUtil.isEmpty(encryptType) || EncType.AES.toString().equals(encryptType)
                || EncType.Plain.toString().equals(encryptType) || EncType.DES.toString().equals(encryptType));

        if (isEncryptType) {
            throw new BusinessException(MessageCode.ERROR_PARAM_ENC_TYPE_ILLEGAL, MessageCode.ERROR_PARAM_ENC_TYPE_ILLEGAL_MSG);
        }

        try {
            String encryptData = requestParams.getEncryptData();
            LOG.info("请求密文:{}", encryptData);
            String decryptData = SecurityUtil.decrypt(encryptData, requestParams.getEncryptType(), appSecret, appId);
            LOG.info("请求明文:{}", decryptData);
            requestParams.setParam(JSON.parseObject(decryptData, Object.class));
        } catch (Exception e) {
            LOG.error("解密失败:", e);
            throw new BusinessException(MessageCode.ERROR_REPORT_ENC_FAIL, MessageCode.ERROR_REPORT_ENC_FAIL_MSG);
        }


        return requestParams;
    }


}