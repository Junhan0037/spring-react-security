package com.springreactsecurity.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class JspytGenerator {
    public static void main(String[] args) {
        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("yeboong1234!"); //암호화 키(password)
        jasypt.setAlgorithm("PBEWithMD5AndDES");

        String encryptedText1 = jasypt.encrypt("id123");    //암호화
        String plainText1 = jasypt.decrypt(encryptedText1);  //복호화

        System.out.println("encryptedText:  " + encryptedText1); //암호화된 값
        System.out.println("plainText:  " + plainText1);

        String encryptedText2 = jasypt.encrypt("id456");    //암호화
        String plainText2 = jasypt.decrypt(encryptedText2);  //복호화

        System.out.println("encryptedText:  " + encryptedText2); //암호화된 값
        System.out.println("plainText:  " + plainText2);
    }
}
