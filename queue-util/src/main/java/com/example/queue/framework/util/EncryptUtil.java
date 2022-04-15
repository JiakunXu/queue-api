package com.example.queue.framework.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import org.apache.http.Consts;

/**
 * @author JiakunXu
 */
public class EncryptUtil {

    public static String encryptMD5(String data) throws IOException {
        byte[] bytes = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes(Consts.UTF_8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse);
        }

        return byte2hex(bytes);
    }

    public static String encryptSHA(String data) throws IOException {
        byte[] bytes = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            bytes = md.digest(data.getBytes(Consts.UTF_8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse);
        }

        return byte2hex(bytes);
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }

        return sign.toString();
    }

}
