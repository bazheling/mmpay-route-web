package com.ylzinfo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.ylzinfo.enums.SignType;
import com.ylzinfo.exception.BusinessException;
import com.ylzinfo.model.RequestParams;
import com.ylzinfo.model.ResponseParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.Map.Entry;

public class Signature {
    private static List<String> ignoreSign = new ArrayList<String>();

    private static Logger logger = LoggerFactory.getLogger(Signature.class);

    static {
        ignoreSign.add("sign");
        ignoreSign.add("encryptData");
        ignoreSign.add("extenalMap");
        ignoreSign.add("pageParams");
        ignoreSign.add("externalNo");
        ignoreSign.add("billContent");
    }

    /**
     * 请求签名
     *
     * @param requestParams
     * @param appKey
     * @return
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static String createSign(RequestParams requestParams, String appKey) {
        return createSign(JSON.parseObject(JSON.toJSONString(requestParams)), appKey);
    }

    /**
     * 响应签名
     *
     * @param responseParams
     * @param appKey
     * @return
     * @throws SecurityException
     * @throws IllegalArgumentException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static String createSign(ResponseParams<?> responseParams, String appKey) {
        return createSign(JSON.parseObject(JSON.toJSONString(responseParams)), appKey);
    }

    /**
     * 创建签名
     *
     * @param appKey
     * @return
     */
    public static String createSign(JSONObject jsonObject, String appKey) {
        // 签名数据集合
        Map<String, String> signMap = new TreeMap<String, String>();
        Set<Entry<String, Object>> entrys = jsonObject.entrySet();

        // 获取签名键值
        for (Entry<String, Object> entry : entrys) {
            // 非空 且 非过滤签名组合
            if (!StringUtil.isEmpty(entry.getValue()) && !ignoreSign.contains(entry.getKey())) {
                signMap.put(entry.getKey(), getValue(entry.getValue()));
            }
        }

        // 创建签名
        String sign = getSign(signMap, appKey);
        return sign;
    }

    /**
     * 取值
     *
     * @param value
     * @return
     */
    private static String getValue(Object value) {
        if (value instanceof String) {
            return getObjString(value);
        } else {
            return treeJsonParam(value);
        }
    }

    /**
     * 跳转签名
     *
     * @param appKey
     * @return
     */
    public static String createSign(String content, String appKey, String signType) {
        String result = "";

        if (StringUtil.isEmpty(signType) || SignType.MD5.toString().equals(signType)) {
            content = content + appKey;
            System.out.println("Sign Before MD5:" + content);
            result = MD5Util.encrypt(content);
            System.out.println("Sign Result MD5:" + result);
        } else if (SignType.RSA.equals(signType)) {
            System.out.println("Sign Before RSA:" + result);
            result = RSAUtil.sign(content, appKey, "UTF-8");
            System.out.println("Sign Result RSA:" + result);
        }

        return result;
    }

    /**
     * 对MAP签名 过滤空值 拼接&key=***
     *
     * @param map
     * @param key
     * @return
     */
    public static String getSign(Map<String, String> map, String key) {
        if (map == null) {
            return "";
        }
        String result = getSignContent(map);
        String signType = map.get("signType");
        System.out.println("signType:" + signType);

        if (StringUtil.isEmpty(signType) || SignType.MD5.toString().equals(signType)) {
            result += "&key=" + key;
            System.out.println("Sign Before MD5:" + result);
            result = MD5Util.encrypt(result).toUpperCase();
            System.out.println("Sign Result MD5:" + result);
        } else if (SignType.RSA.toString().equals(signType)) {
            if (StringUtil.isEmpty(key)) {
                throw new BusinessException("签名密钥为空");
            }

            logger.info("Sign Before RSA:{}", result);
            //System.out.println("Sign Before RSA:" + result);
            result = RSAUtil.sign(result, key, "UTF-8");
            //System.out.println("Sign Result RSA:" + result);
            logger.info("Sign Result RSA:{}", result);
        }

        return result;
    }

    private static String getSignContent(Map<String, String> map) {
        ArrayList<String> list = new ArrayList<String>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (StringUtil.isNotEmpty(getObjString(entry.getValue()))) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }

        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.substring(0, sb.length() - 1);
        System.out.println(result);
        return result;
    }

    /**
     * 对象转换为字符串
     *
     * @param object
     * @return
     */
    public static String getObjString(Object object) {
        return (object == null ? "" : (String) object);
    }

    /**
     * 转换PARAM
     *
     * @return
     */
    private static String treeJsonParam(Object value) {
        String jsoNParam = null;

        if (value instanceof Map<?, ?>) {
            Map<String, Object> treeNestedMap = new TreeMap<String, Object>();

            Map<?, ?> nestedMap = (Map<?, ?>) value;

            for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
                treeNestedMap.put((String) nestedEntry.getKey(), nestedEntry.getValue());
            }

            jsoNParam = JSONObject.toJSONString(treeParams(treeNestedMap));
        } else if (value instanceof JSONObject) {
            Map<String, Object> jsonMap = new TreeMap<String, Object>();

            JSONObject nestedMap = (JSONObject) value;

            for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
                jsonMap.put((String) nestedEntry.getKey(), nestedEntry.getValue());
            }

            jsoNParam = JSONObject.toJSONString(treeParams(jsonMap));
        } else if (value instanceof ArrayList<?>) {
            ArrayList<?> ar = (ArrayList<?>) value;
            jsoNParam = JSONObject.toJSONString(treeList((ar)));
        } else if (value instanceof JSONArray) {
            JSONArray jarr = (JSONArray) value;
            jsoNParam = JSONObject.toJSONString(treeJsonArray((jarr)));
        } else if (value != null && value.getClass().getPackage().getName().startsWith("com.ylzinfo.mmpay.sdk.domain")) {
            jsoNParam = JSONObject.toJSONString(treeParams(JSONObject.parseObject(JSON.toJSONString(value))));
        } else if (value == null) {

        } else {
            jsoNParam = value.toString();
        }

        return jsoNParam;
    }

    /**
     * 获取响应签名参数
     *
     * @param responseParams
     * @deprecated 改自动过滤获取拼装
     */
    protected static Map<String, String> signParams(ResponseParams<?> responseParams) {
        Map<String, String> signMap = new TreeMap<String, String>();
        signMap.put("respCode", responseParams.getRespCode());
        signMap.put("respMsg", responseParams.getRespMsg());
        signMap.put("signType", responseParams.getSignType());
        signMap.put("encryptType", responseParams.getEncryptType());
        signMap.put("timestamp", responseParams.getTimestamp());
        signMap.put("param", treeJsonParam(responseParams.getParam()));
        return signMap;
    }

    /**
     * 签名集合算法 -- 排序
     *
     * @param params
     * @return
     */
    private static Map<String, Object> treeParams(Map<String, Object> params) {
        if (params == null) {
            return new TreeMap<String, Object>();
        }

        Map<String, Object> treeParams = new TreeMap<String, Object>();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map<?, ?>) {
                Map<String, Object> treeNestedMap = new TreeMap<String, Object>();

                Map<?, ?> nestedMap = (Map<?, ?>) value;

                for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
                    treeNestedMap.put((String) nestedEntry.getKey(), nestedEntry.getValue());
                }

                treeParams.put(key, treeParams(treeNestedMap));
            } else if (value instanceof JSONObject) {
                Map<String, Object> treeNestedMap = new TreeMap<String, Object>();

                JSONObject nestedMap = (JSONObject) value;

                for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
                    treeNestedMap.put(key, nestedEntry.getValue());
                }

                treeParams.put(key, treeParams(treeNestedMap));
            } else if (value instanceof ArrayList<?>) {
                ArrayList<?> ar = (ArrayList<?>) value;
                treeParams.put(key, treeList(ar));
            } else if (value instanceof JSONArray) {
                JSONArray ar = (JSONArray) value;
                treeParams.put(key, treeJsonArray(ar));
            } else if ("".equals(value)) {
                // flatParams.put(key, "");
            } else if (value == null) {
                // flatParams.put(key, "");
            } else if (value != null && value.getClass().getPackage().getName().startsWith("com.ylzinfo.mmpay.sdk.domain")) { // 实体类
                treeParams.put(key, treeParams(JSONObject.parseObject(JSON.toJSONString(value))));
            } else {
                treeParams.put(key, value.toString());
            }
        }

        return treeParams;
    }

    /**
     * JsonArray排序
     *
     * @return
     */
    private static JSONArray treeJsonArray(JSONArray jarr) {
        if (jarr == null || jarr.size() == 0) {
            return null;
        }

        JSONArray jsonArray = new JSONArray();

        int size = jarr.size();

        for (int i = 0; i < size; i++) {
            Object value = jarr.get(i);

            if (value instanceof Map<?, ?>) {
                Map<String, Object> treeNestedMap = new TreeMap<String, Object>();

                Map<?, ?> nestedMap = (Map<?, ?>) value;

                for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
                    treeNestedMap.put((String) nestedEntry.getKey(), nestedEntry.getValue());
                }

                jsonArray.add(i, treeParams(treeNestedMap));
            } else if (value instanceof JSONObject) {
                Map<String, Object> treeNestedMap = new TreeMap<String, Object>();

                JSONObject nestedMap = (JSONObject) value;

                for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
                    treeNestedMap.put((String) nestedEntry.getKey(), nestedEntry.getValue());
                }

                jsonArray.add(i, treeParams(treeNestedMap));
            } else if (value instanceof ArrayList<?>) {
                ArrayList<?> ar = (ArrayList<?>) value;
                jsonArray.add(i, treeList(ar));
            } else if (value instanceof JSONArray) {
                JSONArray ar = (JSONArray) value;
                jsonArray.add(i, treeJsonArray(ar));
            } else if ("".equals(value)) {
                // flatParams.put(key, "");
            } else if (value != null && value.getClass().getPackage().getName().startsWith("com.ylzinfo.mmpay.sdk.domain")) { // 实体类
                jsonArray.add(i, treeParams(JSONObject.parseObject(JSON.toJSONString(value))));
            } else {
                jsonArray.add(i, value.toString());
            }
        }

        return jsonArray;
    }

    /**
     * List排序
     *
     * @return
     */
    private static JSONArray treeList(ArrayList<?> list) {
        if (list == null || list.size() == 0) {
            return null;
        }

        JSONArray jsonArray = new JSONArray();
        int size = list.size();

        for (int i = 0; i < size; i++) {
            jsonArray.add(i, list.get(i));
        }

        return treeJsonArray(jsonArray);
    }

    /**
     * RSA校验
     *
     * @param signOld
     * @return
     */
    public static boolean verifyRSASign(JSONObject jsonObject, String publicKey, String signOld) {
        // 签名数据集合
        Map<String, String> signMap = new TreeMap<String, String>();
        Set<Entry<String, Object>> entrys = jsonObject.entrySet();

        // 获取签名键值
        for (Entry<String, Object> entry : entrys) {
            // 非空 且 非过滤签名组合
            if (!StringUtil.isEmpty(entry.getValue()) && !ignoreSign.contains(entry.getKey())) {
                signMap.put(entry.getKey(), getValue(entry.getValue()));
            }
        }

        // 创建签名
        return RSAUtil.verify(getSignContent(signMap), signOld, publicKey, "UTF-8");
    }

}
