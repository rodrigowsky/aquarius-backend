package com.rodrigowsky.aquarius.services;

import com.rodrigowsky.aquarius.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpEmailService implements OtpEmail{

    @Autowired
    private OtpService otpService;

    @Override
    public void sendEmail(CustomUserDetails u) {
        Integer code = otpService.getCode(u.getUsername());
        //System.out.println(code);
    }
}
