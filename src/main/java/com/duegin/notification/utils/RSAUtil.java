package com.duegin.notification.utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtil {

    /**
     * 生成RSA密钥对
     */
    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);  // 2048位密钥长度
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 公钥加密
     */
    public static String encryptWithPublicKey(String data, String publicKeyStr) throws Exception {
        PublicKey publicKey = getPublicKeyFromBase64(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 私钥解密
     */
    public static String decryptWithPrivateKey(String encryptedData, String privateKeyStr) throws Exception {
        PrivateKey privateKey = getPrivateKeyFromBase64(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    // 公钥转换（Base64编码）
    public static PublicKey getPublicKeyFromBase64(String base64PublicKey) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(base64PublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    // 私钥转换（Base64编码）
    public static PrivateKey getPrivateKeyFromBase64(String base64PrivateKey) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(base64PrivateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    // 从公钥获取Base64编码
    public static String getPublicKeyBase64(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    // 从私钥获取Base64编码
    public static String getPrivateKeyBase64(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public static void main(String[] args) throws Exception {
        // 生成RSA密钥对
        KeyPair keyPair = generateRSAKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        // 获取公钥和私钥的Base64编码
        String publicKeyBase64 = getPublicKeyBase64(publicKey);
        String privateKeyBase64 = getPrivateKeyBase64(privateKey);

        System.out.println("Public Key: " + publicKeyBase64);
        System.out.println("Private Key: " + privateKeyBase64);

        // 加密数据
        String originalData = "Hello, RSA!";
        String encryptedData = encryptWithPublicKey(originalData, publicKeyBase64);
        System.out.println("Encrypted Data: " + encryptedData);

        // 解密数据
        String decryptedData = decryptWithPrivateKey(encryptedData, privateKeyBase64);
        System.out.println("Decrypted Data: " + decryptedData);
    }
}
