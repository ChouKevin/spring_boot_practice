package com.example.demo.Auth;

import java.security.SecureRandom;
import java.util.Base64;

import org.junit.jupiter.api.Test;

public class JWTServiceTest {

    @Test
    public void test() {
        // 定義要生成的密碼長度
        int length = 64;

        // 生成安全的隨機數
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);

        // 將隨機數轉換為 Base64 字串
        String randomPassword = Base64.getEncoder().encodeToString(randomBytes);

        System.out.println("Random Password: " + randomPassword);
    }

    @Test
    public void test2() {
        long t1 = System.currentTimeMillis();
        Long sum1 = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum1 = i;
        }
        System.out.println("total:" + sum1);
        System.out.println("my1 time-consuming: " 
                + (System.currentTimeMillis() - t1) + " ms");
        long t2 = System.currentTimeMillis();
        long sum2 = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum2  = i;
        }
        System.out.println("total:" + sum2);
        System.out.println("my2 time-consuming: " 
                + (System.currentTimeMillis() - t2) + " ms");
    }
}
