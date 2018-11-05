package com.koolearn.wordfight.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;
import java.util.UUID;

/**
 *
 * @author chenzhongyong
 * AES128 算法
 *
 * CBC 模式
 *
 * PKCS7Padding 填充模式
 *
 * CBC模式需要添加一个参数iv--对称解密算法初始向量 iv
 *
 * 介于java 不支持PKCS7Padding，只支持PKCS5Padding 但是PKCS7Padding 和 PKCS5Padding 没有什么区别
 * 要实现在java端用PKCS7Padding填充，需要用到bouncycastle组件来实现
 */
public class WechatDecriptUtils {
    // 算法名称
    static  final String KEY_ALGORITHM = "AES";
    // 加解密算法/模式/填充方式
    static  final String algorithmStr = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;
    boolean isInited = false;
 
    public static void init(byte[] keyBytes) {
 
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr, "BC");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }
    /**
     * 加密方法
     *      ---自定义对称解密算法初始向量 iv
     * @param content
     *              要加密的字符串
     * @param keyBytes
     *              加密密钥
     * @param ivs
     *         自定义对称解密算法初始向量 iv
     * @return 加密的结果
     */
    public static byte[] encryptOfDiyIV(byte[] content, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }
    /**
     * 解密方法
     *
     * @param encryptedData
     *            要解密的字符串
     * @param keyBytes
     *            解密密钥
     * @param ivs
     *         自定义对称解密算法初始向量 iv
     * @return
     */
    public static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes,byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

    public static void main(String[] args) throws Exception {
        String  encryptedData = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZMQmRzooG2xrDcvSnxIMXFufNstNGTyaGS9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+3hVbJSRgv+4lGOETKUQz6OYStslQ142dNCuabNPGBzlooOmB231qMM85d2/fV6ChevvXvQP8Hkue1poOFtnEtpyxVLW1zAo6/1Xx1COxFvrc2d7UL/lmHInNlxuacJXwu0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn/Hz7saL8xz+W//FRAUid1OksQaQx4CMs8LOddcQhULW4ucetDf96JcR3g0gfRK4PC7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns/8wR2SiRS7MNACwTyrGvt9ts8p12PKFdlqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYVoKlaRv85IfVunYzO0IKXsyl7JCUjCpoG20f0a04COwfneQAGGwd5oa+T8yO5hzuyDb/XcxxmK01EpqOyuxINew==";
        String iv="r7BXXKkLb8qrSNn05n0qiA==";
        String sessionKey = "tiihtNczf5v6AKRyjwEUhQ==";

        String appId = "wx4f4bc4dec97d474b";


        JSONObject decryptedData = getDecryptedData(sessionKey, encryptedData, iv);

        String unionId = decryptedData.getString("unionId");
        System.out.println("unionId = " + unionId);

        System.out.println(decryptedData);
    }

    public static JSONObject getDecryptedData(String sessionKey, String  encryptedData , String iv){
        JSONObject dataObj = new JSONObject();
        if (StringUtils.isNotEmpty(sessionKey) && StringUtils.isNotEmpty(encryptedData) && StringUtils.isNotEmpty(iv)) {
            byte[] sessionKeyBy = (new Base64()).decode(sessionKey.getBytes());
            byte[] encryptedDataBy = (new Base64()).decode(encryptedData.getBytes());
            byte[] ivBy = (new Base64()).decode(iv.getBytes());
            byte[] dec = WechatDecriptUtils.decryptOfDiyIV(encryptedDataBy, sessionKeyBy,ivBy);
            String decryptedDataStr = new String(dec);

            dataObj = JSONObject.parseObject(decryptedDataStr);
        }

        return dataObj;
    }

    public static String uuid() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

}