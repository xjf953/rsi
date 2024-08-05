package com.shawn.votesystem.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * 密码加密工具类
 *
 * @author Colin.Guan
 * @date 2022/7/7 17:05
 * @since 1.0.0
 */
public class EncryptUtil {

    /** 系统秘钥 */
    private static final String RANDOM_SEY = "/vKXNeMcqOh5xulPdwbPncm7FqVBae85G/0G7iFjCjSXUtMzYrICcDUoCUHaqCUpG295nLsX13Q+2myQ/uhOMg==";

    /** AES256 */
    private static final String ENCRYPT_AES256_PREFIX = "eav1a$";

    /**
     * 根据系统秘钥加密
     *
     * @param encrypt
     * @date 2022-07-07 17:16
     * @since 1.0.0
     * @return java.lang.String
     * @throws
     */
    public static String encryptByRandomSey(String encrypt) {
        if (!"".equals(encrypt) && encrypt != null) {
            try {
                if (encrypt.startsWith(ENCRYPT_AES256_PREFIX)) {
                    return encrypt;
                }
                String randomSey = decryptRandomByBytes(RANDOM_SEY);
                String randomIv = randomSey.substring(8, 24);
                byte[] keyByte = randomSey.getBytes("UTF-8");
                byte[] ivByte = randomIv.getBytes("UTF-8");
                byte[] encryptByte = encrypt.getBytes("UTF-8");
                byte[] bytes = Base64.getEncoder().encode(encrypt(encryptByte, keyByte, ivByte));
                encrypt = ENCRYPT_AES256_PREFIX + new String(bytes, "UTF-8");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return encrypt;
    }

    /**
     * 根据系统秘钥解密
     *
     * @param decrypt
     * @date 2022-07-07 17:37
     * @since 1.0.0
     * @return java.lang.String
     * @throws
     */
    public static String decryptByRandomSey(String decrypt) {
        if (!"".equals(decrypt) && decrypt != null) {
            try {
                if (decrypt.startsWith(ENCRYPT_AES256_PREFIX)) {
                    String randomSey = decryptRandomByBytes(RANDOM_SEY);
                    String randomIv = randomSey.substring(8, 24);
                    decrypt = decrypt.replace(ENCRYPT_AES256_PREFIX, "");
                    byte[] decryptBytes = decrypt(Base64.getDecoder().decode(decrypt.getBytes("UTF-8")), randomSey.getBytes("UTF-8"), randomIv.getBytes("UTF-8"));
                    decrypt = new String(decryptBytes, "UTF-8");
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return decrypt;
    }

    /**
     * 处理秘钥
     *
     * @param randomSey
     * @date 2022-07-07 17:18
     * @since 1.0.0
     * @return java.lang.String
     * @throws
     */
    public static String decryptRandomByBytes(String randomSey) throws Exception {
        byte[] bufferTemp = Base64.getDecoder().decode(randomSey.getBytes("UTF-8"));
        List<Byte> list = new ArrayList();
        byte[] keys = new byte[16];
        int keyCount = 0;
        for(int i = 0; i < bufferTemp.length; ++i) {
            if (i % 2 == 1 && keyCount <= 15) {
                keys[keyCount] = bufferTemp[i];
                ++keyCount;
            } else {
                list.add(bufferTemp[i]);
            }
        }
        byte[] bufferArray = new byte[list.size()];
        for(int i = 0; i < list.size(); ++i) {
            bufferArray[i] = (Byte)list.get(i);
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(keys, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keys);
        cipher.init(2, keySpec, ivSpec);
        return new String(cipher.doFinal(bufferArray), "UTF-8");
    }

    /**
     * AES加密
     *
     * @param encrypt
     * @param keyByte
     * @param ivByte
     * @date 2022-07-07 17:28
     * @since 1.0.0
     * @return byte[]
     * @throws
     */
    public static byte[] encrypt(byte[] encrypt, byte[] keyByte, byte[] ivByte) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivByte);
        cipher.init(1, keySpec, ivSpec);
        return cipher.doFinal(encrypt);
    }

    /**
     * AES解密
     *
     * @param encrypt
     * @param key
     * @param initVector
     * @date 2022-07-07 17:37
     * @since 1.0.0
     * @return byte[]
     * @throws
     */
    public static byte[] decrypt(byte[] encrypt, byte[] key, byte[] initVector) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(initVector);
        cipher.init(2, keySpec, ivSpec);
        return cipher.doFinal(encrypt);
    }

}
