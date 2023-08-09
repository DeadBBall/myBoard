package com.myboard.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String hashWithSHA256(String password){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("비밀번호 암호화에 실패하였습니다.");
        }
        messageDigest.update(password.getBytes());
        byte[] bytes = messageDigest.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }

    public static Boolean comparePasswords(String hashedPassword, String password) {
        if(hashedPassword.equals(hashWithSHA256(password))) {
            return true;
        }
        return false;
    }
}
