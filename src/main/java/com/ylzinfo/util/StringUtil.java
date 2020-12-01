package com.ylzinfo.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 
 * 
 */
public final class StringUtil {
    public static final int XFF00 = 0xff00;
    public static final int XF0 = 0xf0;
    public static final int X0F = 0x0f;
    public static final int XFF = 0xff;
    public static final int X000000FF = 0x000000ff;
    public static final int X0000FF00 = 0x0000ff00;
    public static final int SIXTEEN = 16;
  

    /**
     * 十六进制字符数组
     * 
     * @author 冯俊峰
     * @Date 2014年1月4日
     */
    public static final char[] HEX_CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
 
    /**
     * 生成随机的字符串
     * 
     * @author songjie 2013年11月28日 上午10:16:16
     * @param length
     *            ：生成的字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }
    /**
     * 生成随机的字符串
     * 
     * @author  2013年11月28日 上午10:16:16
     * @param length
     *            ：生成的字符串的长度
     * @return
     */
    public static String getRandomNumber(int length) {
        String base = "0123456789";

        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        return sb.toString();
    }
    /**
     * 将字符串按指定的"分割符"分割成数值数组
     * 
     * @param s
     * @param delim
     * @return
     */
    public static int[] splitToIntArray(String s, String delim) {
        String[] stringValueArray = split(s, delim);
        int[] intValueArray = new int[stringValueArray.length];
        for (int i = 0; i < intValueArray.length; i++) {
            intValueArray[i] = Integer.parseInt(stringValueArray[i]);
        }
        return intValueArray;
    }

    /**
     * 分割字串
     * 
     * @param source
     *            原始字符
     * @param div
     *            分割符
     * @return 字符串数组
     */
    public static String[] split(String source, String div) {
        int arynum = 0;
        int intIdx = 0;
        int intIdex = 0;
        int div_length = div.length();
        if (source.compareTo("") != 0) {
            if (source.indexOf(div) != -1) {
                intIdx = source.indexOf(div);
                for (int intCount = 1;; intCount++) {
                    if (source.indexOf(div, intIdx + div_length) != -1) {
                        intIdx = source.indexOf(div, intIdx + div_length);
                        arynum = intCount;
                    } else {
                        arynum += 2;
                        break;
                    }
                }
            } else {
                arynum = 1;
            }
        } else {
            arynum = 0;

        }
        intIdx = 0;
        intIdex = 0;
        String[] returnStr = new String[arynum];

        if (source.compareTo("") != 0) {
            if (source.indexOf(div) != -1) {
                intIdx = (int) source.indexOf(div);
                returnStr[0] = (String) source.substring(0, intIdx);
                for (int intCount = 1;; intCount++) {
                    if (source.indexOf(div, intIdx + div_length) != -1) {
                        intIdex = (int) source.indexOf(div, intIdx + div_length);
                        returnStr[intCount] = (String) source.substring(intIdx + div_length,
                                intIdex);
                        intIdx = (int) source.indexOf(div, intIdx + div_length);
                    } else {
                        returnStr[intCount] = (String) source.substring(intIdx + div_length,
                                source.length());
                        break;
                    }
                }
            } else {
                returnStr[0] = (String) source.substring(0, source.length());
                return returnStr;
            }
        } else {
            return returnStr;
        }
        return returnStr;
    }

    /**
     * 将数字字符串转化为int型
     * 
     * @param srcInt
     * @return int
     */
    public static int doNullInt(String srcInt) {
        if (srcInt == null || "".equals(srcInt)) {
            return 0;
        }
        return Integer.parseInt(srcInt);
    }

    /**
     * 
     * @param obj
     * @return
     */
    public static int doNullInt(Object obj) {
        String srcInt = doNullStr(obj);
        if (srcInt == null || "".equals(srcInt)) {
            return 0;
        }
        return Integer.parseInt(srcInt);
    }

    /**
     * 将数字字符串转化为long型
     * 
     * @param srcInt
     * @return
     */
    public static long doNullLong(String srcInt) {
        if (srcInt == null || "".equals(srcInt)) {
            return 0;
        }
        return Long.parseLong(srcInt);
    }

    /**
     * 将对象转化为long型
     * 
     * @param obj
     * @return
     */
    public static long doNullLong(Object obj) {
        String srcInt = doNullStr(obj);
        if (srcInt == null || "".equals(srcInt)) {
            return 0;
        }
        return Long.parseLong(srcInt);
    }

    /**
     * 转化为字符串
     * 
     * @param obj
     *            Object
     * @return String
     */
    public static String doNullStr(Object obj) {
        String str = "";
        if (obj != null) {
            str = String.valueOf(obj);
            if (str.equals("null")) {
                str = "";
            }
        }
        return str;
    }

    /**
     * 转换为整型
     * 
     * @param obj
     * @return
     */
    public static Integer doNullInteger(Object obj) {
        String str = doNullStr(obj);
        if (isEmpty(str)) {
            str = "0";
        } else {
            int i = str.indexOf(".");
            if (i > 0) {
                str = str.substring(0, i);
            }
        }
        return Integer.valueOf(str);
    }

    /**
     * 验证字符串数组是否为空
     * 
     * @param string
     * @return
     */
    public static boolean isEmpty(String[] string) {
        return string == null || string.length == 0;
    }

    /**
     * 验证字符串是否为空
     * 
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if (string == null || "".equals(string.trim()) || "null".equals(string.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 验证字符串是非空
     * 
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * @param o
     * @return
     * @Adder by zhys 2011-8-9 上午09:50:56
     * @Description 验证BEAN是否为空
     */
    public static boolean isEmpty(Object o) {
        return o == null || "".equals(o);
    }

    /**
     * 对字符串进行操作，对于小于指定长度的字符，在其右方按特定字符进行补足。
     * 
     * <pre>
     * 示例：
     * 	String stringUtils = &quot;abc&quot;;
     * 	log.info(StringUtils.padRight(stringUtils, 5, '0'));
     * 输出结果为：abc00；
     * </pre>
     * 
     * @param value
     *            输入值
     * @param totalWidth
     *            总长度
     * @param paddingChar
     *            不足时填充的字符
     * @return 重新计算后的字符。
     */
    public static String padRight(String value, int totalWidth, char paddingChar) {
        String temp = value;
        if (value.length() > totalWidth) {
            return value;
        } else {
            while (temp.length() < totalWidth) {
                temp = temp + paddingChar;
            }
            return temp;
        }
    }

    /**
     * 对字符串进行操作，对于小于指定长度的字符，在其左方按特定字符进行补足。 示例：
     * 
     * <pre>
     * String stringUtils = &quot;abc&quot;;
     * log.info(StringUtils.padLeft(stringUtils, 5, '0'));
     * </pre>
     * 
     * 输出结果为：00abc；
     * 
     * @param value
     *            输入值
     * @param totalWidth
     *            总长度
     * @param paddingChar
     *            不足时填充的字符
     * @return 重新计算后的字符。
     */
    public static String padLeft(String value, int totalWidth, char paddingChar) {
        String temp = value;
        if (value.length() > totalWidth) {
            return value;
        } else {
            while (temp.length() < totalWidth) {
                temp = paddingChar + temp;
            }
            return temp;
        }
    }

    /**
     * java trim()重写，去除字符串前后空格 add 2009-5-5 by sumfing
     * */
    public static String reTrimByString(String value) {
        String reValue;
        if (value == null || value.equals("")) {
            reValue = "";
        } else {
            reValue = value.trim();
        }
        return reValue;
    }

    /**
     * 去除前后空格
     * 
     * @param obj
     * @return
     */
    public static String reTrimByObject(Object obj) {
        String reValue;
        if (obj == null || obj.equals("")) {
            reValue = "";
        } else {
            reValue = String.valueOf(obj).trim();
        }
        return reValue;
    }

    /**
     * 类似String.indexOf() 返回字符串在数组中的位置
     * 
     * @param strArr
     * @param str
     * @return
     */
    public static int indexOfStringArray(String[] strArr, String str) {
        int index = -1;
        if (strArr != null && str != null) {
            for (int i = 0; i < strArr.length; i++) {
                if (str.equals(strArr[i])) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    /**
     * 替换特定的字符串，替换位置为第一次遇到的
     * 
     * @param whole
     *            完整的字符串
     * @param strold
     *            要被替换的字符串
     * @param strnew
     *            替换的字符串
     * @return
     */
    public static String replaceFirst(String whole, String strold, String strnew) {

        if (whole.indexOf(strold) > -1 && strnew != null) {
            String whole_one = whole.substring(0, whole.indexOf(strold));
            String whole_two = whole.substring(whole.indexOf(strold) + strold.length());
            whole = whole_one + strnew + whole_two;
        }

        return whole;
    }

    /**
     * 将String数组转换为Long类型数组
     * 
     * @param strs
     * @return
     */
    public static Long[] convertionToLong(String[] strs) {
        Long[] longs = null;
        if (!isEmpty(strs)) {
            longs = new Long[strs.length];
            for (int i = 0; i < strs.length; i++) {
                String str = strs[i];
                long thelong = Long.valueOf(str);
                longs[i] = thelong;
            }
        }
        return longs;
    }

    /**
     * 将String转换为Long类型数组
     * 
     * @param strs
     * @param splitChar
     *            分割字符
     * @return
     */
    public static Long[] convertionToLongArr(String strs, String splitChar) {
        if (isEmpty(splitChar)) {
            splitChar = ",";
        }
        Long[] result = null;
        if (!StringUtil.isEmpty(strs)) {
            String[] ids = strs.split(splitChar);
            result = new Long[ids.length];
            for (int i = 0; i < ids.length; i++) {
                result[i] = new Long(ids[i]);
            }
        }
        return result;
    }

    /**
     * @Description: 将字符串按指定分割字符串 转为数组
     * @Author: luofuyong @Date: 2012-4-5
     * @LastEditTime:
     * @param str
     *            原字符串
     * @param div
     *            分隔符
     * @return
     */
    public static String[] decodeStringToArray(String str, String div) {
        ArrayList<String> array = new ArrayList<String>();
        StringTokenizer fenxi = new StringTokenizer(str, div);
        while (fenxi.hasMoreTokens()) {
            String s1 = fenxi.nextToken();
            array.add(s1);
        }
        String[] result = new String[array.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = array.get(i);
        }
        return result;
    }

    /**
     * Long[] 转成 String
     * 
     * @param l
     * @param splitChar
     * @return
     */
    public static String convertionLongToString(Long[] l, String splitChar) {
        String result = null;
        if (l != null) {
            result = Arrays.toString(l);
            result = result.substring(1, result.length() - 1);
            if (!StringUtil.isEmpty(splitChar)) {
                result = result.replaceAll(",", splitChar);
            }
        }
        return result;
    }

    /**
     * @Description: 将对象数组转换成按指定字符分割的字符串
     * @Author: luofuyong @Date: 2012-4-1
     * @LastEditTime:
     * @return
     */
    public static String convertionObjectArrayToStr(Object[] strings, String regx) {
        String result = null;
        if (regx != null) {
            result = Arrays.toString(strings);
            result = result.substring(1, result.length() - 1);
            if (!StringUtil.isEmpty(regx)) {
                result = result.replaceAll(",", regx);
            }
        }
        return result;
    }

    /**
     * 字符编码转换
     * 
     * @param str
     * @return
     * @throws Exception
     */
    public static String charEncoding(String str) {
        try {
            str = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            str = null;
        }
        return str;
    }

    /**
     * 将字符串str里的某些字符sregex转义成指定的字符sreplace
     * 
     * @param str
     * @param sregex
     * @param sreplace
     * @return String
     */
    public static String getStrTransMean(String str, String sregex, String sreplace) {
        if (!StringUtil.isEmpty(str)) {
            str = str.replaceAll(sregex, sreplace);
        }
        return str;
    }

    /**
     * 将字符串中的特殊字符去掉
     */
    public static String replaceSpecialChar(String s) {
        return s.replaceAll("/|\\\\|\\$|#|&|%|\\*|\\^|;|,|<|>|&|'|\"", "");
    }

    /**
     * 将字符串中的特殊字符去掉
     */
    public static String replaceSpecialCode(String s) {
        if (null != s && !"".equals(s)) {
            return s.replaceAll("<|>|\"|%|;|\\(|\\)|&|'|\\+|\\\\", "");
        } else {
            return s;
        }
    }

    /**
     * @param map
     * @return
     * @Adder by zhys 2011-7-7 上午09:19:09
     * @Description 遍历所有MAP的Value组合成String
     */
    public static String getMapValue(Map<String, Object> map) {
        StringBuffer str = new StringBuffer();
        Iterator<Object> it = map.values().iterator();
        while (it.hasNext()) {
            String val = String.valueOf(it.next());
            str.append(val);
        }
        return str.toString();
    }

    /**
     * 
     * @author
     * @Date 2014年1月10日
     * @param s
     * @param length
     * @return
     */
    public static String bSubstring(String s, int length) {
        byte[] bytes;
        try {
            bytes = s.getBytes("Unicode");
            int n = 0; // 表示当前的字节数
            int i = 2; // 要截取的字节数，从第3个字节开始
            for (; i < bytes.length && n < length; i++) {
                // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
                if (i % 2 == 1) {
                    n++; // 在UCS2第二个字节时n加1
                } else {
                    // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                    if (bytes[i] != 0) {
                        n++;
                    }
                }
            }
            // 如果i为奇数时，处理成偶数
            if (i % 2 == 1) {
                // 该UCS2字符是汉字时，去掉这个截一半的汉字
                if (bytes[i - 1] != 0) {
                    i = i - 1;
                } else {
                	// 该UCS2字符是字母或数字，则保留该字符
                    i = i + 1;
                }
            }
            return new String(bytes, 0, i, "Unicode");
        } catch (Exception e) {
            return new String("");
        }
    }

    /**
     * @Description getString
     * @author 冯俊峰
     * @Date 2013年12月9日
     * 
     * @param o
     * @return
     */
    public static String getString(Object o) {
        return o == null ? "" : o.toString();
    }

    /**
     * 加载资源文件
     */
    public static Properties getProperties(String filename) {
        Properties properties = new Properties();
        InputStream in = null;
        in = StringUtil.class.getClassLoader().getResourceAsStream(filename);
        try {
            properties.load(in);
        } catch (IOException e) {
            return properties;
        }
        return properties;
    }

    /**
     * 日期转换为字符串
     * 
     * @param date
     *            Date类型的日期
     * @param format
     *            转换格式
     * @return String 转换后的日期字符串
     */
    public static String dateTostr(Date date, String format) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String dateStr = null;
        dateStr = dateFormat.format(date);
        return dateStr;
    }

    /**
     * @Description:转码[ISO-8859-1 -> UTF-8] 不同的平台需要不同的转码
     * @Author: luofuyong @Date: 2012-3-29
     * @LastEditTime:
     * @param obj
     * @return
     */
    public static String iso8859ToUTF8(Object obj) {
        try {
            if (obj == null) {
                return "";
            } else {
                return new String(obj.toString().getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @Description: 转码[UTF-8 -> ISO-8859-1] 不同的平台需要不同的转码
     * @Author: luofuyong @Date: 2012-3-29
     * @LastEditTime:
     * @param obj
     * @return
     */
    public static String utf8ToIso8859(Object obj) {
        try {
            if (obj == null) {
                return "";
            } else {
                return new String(obj.toString().getBytes("iso-8859-1"), "UTF-8");
            }
        } catch (Exception e) {
            return "";
        }
    }

	/**
	 * 金额转换
	 * @param object
	 * @return
	 */
	public static BigDecimal getStrBigDecimal(String str) {
		return (StringUtil.isEmpty(str) ? null : new BigDecimal(str));
	}

	/**
	 * 截取字符串
	 * @param str
	 * @param size
	 * @return
	 */
	public static String cutString(String str, int size) {
		return (isNotEmpty(str) && str.length() > 100 ? str.substring(0, 100) : str);
	}
}
