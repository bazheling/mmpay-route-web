package com.ylzinfo.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 类功能说明
 * 
 * @author sun
 * @version 1.0.0
 */

public class AESUtil {
    public static final String VIPARA = "0102030405060708";
    public static final String BM = "UTF-8";

    public static String encrypt(String content, String password) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
        SecretKeySpec key = getKey(password);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(content.getBytes(BM));
        String encryptResultStr = DataFormater.parseByte2HexStr(encryptedData);

        return encryptResultStr; // 加密
    }

    public static String encrypt(String content, String password, String vi) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(vi.getBytes());
        SecretKeySpec key = getKey(password);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(content.getBytes(BM));
        String encryptResultStr = DataFormater.parseByte2HexStr(encryptedData);

        return encryptResultStr; // 加密
    }

    public static String decrypt(String content, String password, String vi) throws Exception {
        content = new String(content.getBytes("UTF-8"));
        byte[] decryptFrom = DataFormater.parseHexStr2Byte(content);

        IvParameterSpec zeroIv = new IvParameterSpec(vi.getBytes(BM));
        SecretKeySpec key = getKey(password);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte[] decryptedData = cipher.doFinal(decryptFrom);

        return new String(decryptedData, BM);
    }

    public static String decrypt(String content, String password) throws Exception {
        content = new String(content.getBytes("UTF-8"));
        byte[] decryptFrom = DataFormater.parseHexStr2Byte(content);

        IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
        SecretKeySpec key = getKey(password);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte[] decryptedData = cipher.doFinal(decryptFrom);

        return new String(decryptedData, BM);
    }

    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes();
        // 创建一个空的16位字节数组（默认值为0）
        byte[] arrB = new byte[16];

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
        return skeySpec;
    }

    /**
     * 用于对接微信平台AES加密
     * 
     * @param password
     * @param strIn
     * @return
     */
    public static String encryptBase64(String password, String strIn) {
        try {
            SecretKeySpec skeySpec = getKey(password);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(strIn.getBytes("UTF-8"));

            return Base64.encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用于对接微信平台AES解密
     * 
     * @param password
     * @param strIn
     * @return
     * @throws Exception
     */
    public static String decryptBase64(String password, String strIn) throws Exception {
        try {
            SecretKeySpec skeySpec = getKey(password);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decode(strIn);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "UTF-8");
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void main(String[] args) throws Exception {
        String password = "1BL74DHMK0017518A8C00000991AE20A";
        // String str = encrypt("q!w@e3r$", password);
        // System.out.println("--" + str);
        String str1 = encrypt("1234567890123456", password);
        System.out.println("--" + str1);
        String oldStr = decrypt("2DDAB3AA6875DE4871F10481E88AF369DBFB4FD95B1411559EFFE98B29FF809FED9C9C1C51C128D77702AF276119579FDFA8C9A80ACE70AFFB079424D63E3FD9CF078185F56C6044F6E45658E3A1332FF42B9D40589A30C55D0F3625BC9E3680", password);
        System.out.println("====" + oldStr);
    }

}
