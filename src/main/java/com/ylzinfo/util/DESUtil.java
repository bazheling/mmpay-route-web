package com.ylzinfo.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class DESUtil {


    private static final byte[] IV = {1, 2, 3, 4, 5, 6, 7, 8};
    public static final String BM = "UTF-8";

    /**
     * 加密
     *
     * @param encryptString 加密字符串
     * @param encryptKey    密钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String encryptString, String encryptKey)
            throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(IV);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes(BM));
        return DataFormater.parseByte2HexStr(encryptedData); // 加密
    }

    /**
     * 加密
     *
     * @param encryptString 加密字符串
     * @param encryptKey    密钥
     * @return
     * @throws Exception
     */
    public static String encryptWithBase64(String encryptString, String encryptKey)
            throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(IV);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes(BM));
        return Base64.encode(encryptedData); // 加密
    }

    /**
     * 解密
     *
     * @param decryptString 解密字符串
     * @param decryptKey    密钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String decryptString, String decryptKey)
            throws Exception {
        byte[] byteMi = DataFormater.parseHexStr2Byte(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(IV);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData, BM);
    }

    /**
     * 解密
     *
     * @param decryptString 解密字符串
     * @param decryptKey    密钥
     * @return
     * @throws Exception
     */
    public static String decryptWithBase64(String decryptString, String decryptKey)
            throws Exception {
        byte[] byteMi = Base64.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(IV);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);
        return new String(decryptedData, BM);
    }

    public static void main(String[] args) throws Exception {
        String encrypt = encrypt("中文", "12345678");
        System.out.println(encrypt);
        System.out.println(decrypt(encrypt, "12345678"));
    }
}
