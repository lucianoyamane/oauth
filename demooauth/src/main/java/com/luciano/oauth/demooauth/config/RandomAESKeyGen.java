package com.luciano.oauth.demooauth.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomAESKeyGen {

    public static String generate(final int keyLen) throws NoSuchAlgorithmException {

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keyLen);
        SecretKey secretKey = keyGen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        return DatatypeConverter.printHexBinary(encoded).toLowerCase();
    }

    public static String generate2(final int keyLen) throws NoSuchAlgorithmException {

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[keyLen/8];
        random.nextBytes(bytes);
        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }

    public static void main(String[] args) {

        String password = "authorization_code_secret";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        System.out.print(encodedPassword);


        String key = null;
        for(int i=0; i< 5; ++i) {
            try {
                key = RandomAESKeyGen.generate(128);
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Exception caught");
                e.printStackTrace();
            }
            System.out.println(key);
        }
        System.out.println("==================");

        for(int i=0; i< 5; ++i) {
            try {
                key = RandomAESKeyGen.generate(256);
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Exception caught");
                e.printStackTrace();
            }
            System.out.println(key);
        }
        System.out.println("==================");

        for(int i=0; i< 5; ++i) {
            try {
                key = RandomAESKeyGen.generate2(128);
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Exception caught");
                e.printStackTrace();
            }
            System.out.println(key);
        }
        System.out.println("==================");

        for(int i=0; i< 5; ++i) {
            try {
                key = RandomAESKeyGen.generate2(256);
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Exception caught");
                e.printStackTrace();
            }
            System.out.println(key);
        }
    }
}
