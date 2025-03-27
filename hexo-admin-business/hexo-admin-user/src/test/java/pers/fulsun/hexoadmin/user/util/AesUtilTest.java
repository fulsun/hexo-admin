package pers.fulsun.hexoadmin.user.util;


import org.junit.jupiter.api.Test;
import pers.fulsun.hexoadmin.user.infrastructure.util.AesUtil;

public class AesUtilTest {
    
    @Test
    public void testEncryptAndDecrypt() {
        // 示例用法
        String key = AesUtil.generateKey();
        System.out.println("Generated Key: " + key);
        
        String plaintext = "Hello, AES Encryption!";
        System.out.println("Original Text: " + plaintext);
        
        String encrypted = AesUtil.encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encrypted);
        
        String decrypted = AesUtil.decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);
        
        String encrypt = AesUtil.encrypt(plaintext);
        System.out.println("Encrypted Text: " + encrypt);
        System.out.println("Decrypted Text: " + AesUtil.decrypt(encrypt));
    }
}
