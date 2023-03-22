package com.rodrigowsky.aquarius.services;

import com.rodrigowsky.aquarius.repositories.nosql.OtpRepository;
import com.rodrigowsky.aquarius.utils.GenerateCodeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

    private static final Logger logger = LogManager.getLogger(OtpService.class);


    @Autowired
    private OtpRepository otpRepository;

    //to check if otp code is present in redisDB
    //if yes then check if it's true or not and returns respective boolean
    public Integer getCode(String username){
        return otpRepository.getUserCode(username);
    }

    public boolean check(String username, Integer code) {
        logger.info("OTP SERVICE CHECK: ", username);
        Boolean userExists = otpRepository.userExists(username);
        logger.info("OTP SERVICE USER EXISTS: ", userExists);
        if (userExists != null && userExists) {
            logger.info("OTP SERVICE USER CODE: ", code);
            Integer otp = otpRepository.getUserCode(username);
            logger.info("OTP SERVICE REDIS CODE: ", otp);
            if (otp != null && code.equals(otp)) {
                return true;
            }
        }
        return false;
    }


    // method to create or renew otp codes in relation to usernames in redisDB
    public void renewOtp(UserDetails u) {
        //presents in util package returns a random otp generated code
        Integer code = GenerateCodeUtil.generateCode();
        logger.info("OTP SERVICE RENEW CODE USERNAME" + u.getUsername());
        Boolean userOtp = otpRepository.userExists(u.getUsername());
        //if username is already present in db renew its code
        // if not create a new key with username and set its code as value
        if (userOtp) {
            otpRepository.updateCode(u.getUsername(),code);
            logger.info("OTP SERVICE RENEW SUCCESS" + u.getUsername());
        } else {
            otpRepository.saveUser(u.getUsername(),code);
            logger.info("OTP SERVICE RENEW SAVE USER" + u.getUsername());
        }
    }

}
