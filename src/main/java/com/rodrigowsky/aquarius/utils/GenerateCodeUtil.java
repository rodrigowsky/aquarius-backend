package com.rodrigowsky.aquarius.utils;

import lombok.NoArgsConstructor;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@NoArgsConstructor
public class GenerateCodeUtil {

    public static Integer generateCode(){
        Integer code;

        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
             code = random.nextInt(9000) + 1000;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating the random code.");
        }

        return code;
    }
}
