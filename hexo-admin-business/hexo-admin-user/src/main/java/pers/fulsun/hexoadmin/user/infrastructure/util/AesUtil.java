package pers.fulsun.hexoadmin.user.infrastructure.util;

import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES 加密工具类 使用 AES/CBC/PKCS5Padding 模式
 */
public class AesUtil {
    
    private static final String ALGORITHM = "AES";
    
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    
    private static final int KEY_SIZE = 256; // 128, 192 or 256
    
    private static String key = "nSvYcupxmacthQ5VBbYwlFkHm8+4EYwK5w43OlN/Rs8=";
    
    /**
     * 生成 AES 密钥
     *
     * @return 返回Base64编码的密钥字符串
     */
    public static String generateKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
            keyGenerator.init(KEY_SIZE, new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate AES key", e);
        }
    }
    
    /**
     * 加密
     *
     * @param plaintext 明文
     * @param key       Base64编码的密钥
     * @return Base64编码的加密结果
     */
    public static String encrypt(String plaintext, String key) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(key);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);
            
            // 生成随机IV
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            
            byte[] encrypted = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            
            // 将IV和加密数据合并，IV放在前面
            byte[] combined = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);
            
            return Base64.getEncoder().encodeToString(combined);
        } catch (Exception e) {
            throw new RuntimeException("AES encryption failed", e);
        }
    }
    
    /**
     * 解密
     *
     * @param ciphertext Base64编码的密文
     * @param key        Base64编码的密钥
     * @return 解密后的明文
     */
    public static String decrypt(String ciphertext, String key) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(key);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);
            
            byte[] combined = Base64.getDecoder().decode(ciphertext);
            
            // 提取IV (前16字节)
            byte[] iv = new byte[16];
            System.arraycopy(combined, 0, iv, 0, iv.length);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            
            // 提取实际加密数据
            byte[] encrypted = new byte[combined.length - iv.length];
            System.arraycopy(combined, iv.length, encrypted, 0, encrypted.length);
            
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            
            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("AES decryption failed", e);
        }
    }
    
    
    public static String encrypt(String content) {
        // 判空修改
        if (StringUtils.isBlank(content)) {
            return content;
        }
        
        return encrypt(content, key);
    }
    
    public static String decrypt(String content) {
        // 判空修改
        if (StringUtils.isBlank(content)) {
            return content;
        }
        
        return decrypt(content, key);
    }
    
    
}