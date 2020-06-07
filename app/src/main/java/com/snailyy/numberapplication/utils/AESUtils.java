package com.snailyy.numberapplication.utils;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtils {

    /**
     * AES密码器
     */
    private static Cipher cipher;

    // 字符串编码
    private static final String KEY_CHARSET = "UTF-8";

    // 算法方式
    private static final String KEY_ALGORITHM = "AES";

    // 算法/模式/填充
    private static final String CIPHER_ALGORITHM_CBC = "AES/ECB/PKCS5Padding";

    // 私钥大小128/192/256(bits)位 即：16/24/32bytes，暂时使用128，如果扩大需要更换java/jre里面的jar包
    private static final Integer PRIVATE_KEY_SIZE_BIT = 128;
    private static final Integer PRIVATE_KEY_SIZE_BYTE = 16;

    public static String SECRETKEY = "hbprojectlyj1111";


    /**
     * 加密
     *
     * @param secretKey 密钥：加密的规则 16位
     * @param plainText 明文：要加密的内容
     * @return cipherText
     * 密文：加密后的内容，如有异常返回空串：""
     */
    public static String encrypt(String plainText, String secretKey) {
        if (secretKey.length() != PRIVATE_KEY_SIZE_BYTE) {
            throw new RuntimeException("AESUtil:Invalid AES secretKey length (must be 16 bytes)");
        }

        // 密文字符串
        String cipherText = "";
        try {
            // 加密模式初始化参数
            initParam(secretKey, Cipher.ENCRYPT_MODE);
            // 获取加密内容的字节数组
            byte[] bytePlainText = plainText.getBytes(KEY_CHARSET);
            // 执行加密
            byte[] byteCipherText = cipher.doFinal(bytePlainText);
            cipherText = Base64.getEncoder().encodeToString(byteCipherText);
        } catch (Exception e) {
            throw new RuntimeException("AESUtil:encrypt fail!", e);
        }
        return cipherText;
    }

    /**
     * 加密
     *
     * @param secretKey 密钥：加密的规则 16位
     * @return cipherText
     * 密文：加密后的内容，如有异常返回空串：""
     */
    public static String encrypt(byte[] bytes, String secretKey) {
        // 密文字符串
        String cipherText = "";
        try {
            // 加密模式初始化参数
            initParam(secretKey, Cipher.ENCRYPT_MODE);
            // 执行加密
            byte[] byteCipherText = cipher.doFinal(bytes);
            cipherText = Base64.getEncoder().encodeToString(byteCipherText);
        } catch (Exception e) {
            throw new RuntimeException("AESUtil:encrypt fail!", e);
        }
        return cipherText;
    }


    /**
     * 解密
     *
     * @param secretKey  密钥：加密的规则 16位
     * @param cipherText 密文：加密后的内容，即需要解密的内容
     * @return plainText
     * 明文：解密后的内容即加密前的内容，如有异常返回空串：""
     */
    public static String decrypt(String cipherText, String secretKey) {
        if (secretKey.length() != PRIVATE_KEY_SIZE_BYTE) {
            throw new RuntimeException("AESUtil:Invalid AES secretKey length (must be 16 bytes)");
        }

        // 明文字符串
        String plainText = "";
        try {
            initParam(secretKey, Cipher.DECRYPT_MODE);
            // 将加密并编码后的内容解码成字节数组
            byte[] byteCipherText = Base64.getDecoder().decode(cipherText);
            // 解密
            byte[] bytePlainText = cipher.doFinal(byteCipherText);
            plainText = new String(bytePlainText, KEY_CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("AESUtil:decrypt fail!", e);
        }
        return plainText;
    }

    /**
     * 初始化参数
     *
     * @param secretKey 密钥：加密的规则 16位
     * @param mode      加密模式：加密or解密
     */
    public static void initParam(String secretKey, int mode) {
        try {
            // 防止Linux下生成随机key
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(secretKey.getBytes());
            // 获取key生成器
            KeyGenerator keygen = KeyGenerator.getInstance(KEY_ALGORITHM);
            keygen.init(PRIVATE_KEY_SIZE_BIT, secureRandom);

            // 获得原始对称密钥的字节数组
            byte[] raw = secretKey.getBytes();
            // 根据字节数组生成AES内部密钥
            SecretKeySpec key = new SecretKeySpec(raw, KEY_ALGORITHM);
            // 根据指定算法"AES/ECB/PKCS5Padding"实例化密码器
            cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            cipher.init(mode, key);
        } catch (Exception e) {
            throw new RuntimeException("AESUtil:initParam fail!", e);
        }
    }


    public static void main(String[] args) {
        String encrypt = AESUtils.encrypt("r4141414141141&&qe213111414", AESUtils.SECRETKEY);
        System.out.println(encrypt);
        String decrypt = AESUtils.decrypt(encrypt, AESUtils.SECRETKEY);
        System.out.println(decrypt);
    }

}