package com.ylzinfo.exception;

/**
 * 通用错误码定义
 *
 * @author xiepy
 * @date 20200805
 */
public class MessageCode {

    // 报文合法性
    public final static String ERROR_REPORT_ENC_FAIL = "000001";
    public final static String ERROR_REPORT_ENC_FAIL_MSG = "报文加解密失败";

    public final static String ERROR_REQUEST_MSG_EMPTY = "000003";
    public final static String ERROR_REQUEST_MSG_EMPTY_MSG = "请求报文为空";

    public final static String ERROR_REQUEST_MSG_ILLEGAL = "000004";
    public final static String ERROR_REQUEST_MSG_ILLEGAL_MSG = "请求格式非法";

    public static final String ERROR_PARAM_ENC_TYPE_ILLEGAL = "000005";
    public static final String ERROR_PARAM_ENC_TYPE_ILLEGAL_MSG = "加密类型不支持";

    public final static String ERROR_TIMESTAMP_EMPTY = "000006";
    public final static String ERROR_TIMESTAMP_EMPTY_MSG = "请求时间戳为空";

    public final static String ERROR_NO_CONFIG = "010001";
    public final static String ERROR_NO_CONFIG_MSG = "该应用编码与医疗机构无配置信息";

    public final static String ERROR_CONFIG_STATUS_INVALID = "010002";
    public final static String ERROR_CONFIG_STATUS_INVALID_MSG = "该应用编码与医疗机构配置信息无效";

    public final static String ERROR_APP_ID_ILLEGAL = "010003";
    public final static String ERROR_APP_ID_ILLEGAL_MSG = "应用编号非法";


}
