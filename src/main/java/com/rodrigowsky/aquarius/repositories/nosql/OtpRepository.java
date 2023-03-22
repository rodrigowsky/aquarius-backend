package com.rodrigowsky.aquarius.repositories.nosql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component

public class OtpRepository {

    private static final Logger logger = LogManager.getLogger(OtpRepository.class);


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveUser(String username, Integer code) {
        logger.info("OTP REPO SAVE USER:" + username);
        redisTemplate.opsForValue().set(username, code, Duration.ofMinutes(3));
    }

    public Integer getUserCode(String username) {
        String valueFromRedis = redisTemplate.opsForValue().get(username).toString();
        if (valueFromRedis != null) {
            try {
                Integer codeValue = Integer.parseInt(valueFromRedis);
                return codeValue;
            } catch (NumberFormatException e) {
                // handle the case where the value is not a valid integer
                logger.error("OTP REPO GET USER CODE: codeValue Error");
            }
        }
        // handle the case where the key does not exist in Redis or the value is not a valid integer
        return null;
    }

    public void updateCode(String username, Integer newCode) {
        String key = username;
        logger.info("OTP REPO CODE UPDATE: "  + " - USERNAME: " + key);
        redisTemplate.opsForValue().set(key, newCode);
    }


    public boolean userExists(String username) {
        //safety for case of return null
//        Boolean keyExists = redisTemplate.hasKey(username);
//        if (keyExists != null && keyExists) {
//            logger.info("OTP REPO USER EXISTS: true - USERNAME: {}", username);
//            return true;
//        } else {
//            logger.info("OTP REPO USER EXISTS: false - USERNAME: {}", username);
//            return false;
//        }
        Object value = redisTemplate.opsForValue().get(username);
        return value != null;
    }


}
