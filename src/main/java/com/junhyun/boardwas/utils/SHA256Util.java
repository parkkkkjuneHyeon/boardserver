package com.junhyun.boardwas.utils;


import lombok.extern.log4j.Log4j2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Log4j2
public class SHA256Util {
    private static final String ALGORITHM = "SHA-256";
    public static String encryptSHA256(String password) {
        String sha;
        MessageDigest messageDigest;

        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM);
            byte[] digest = messageDigest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            sha = hexString.toString();

        }catch (NoSuchAlgorithmException e) {
            log.error("encryptSHA256 ERROR: {}", e.getMessage());
            sha = null;
        }
        return sha;
    }
}
