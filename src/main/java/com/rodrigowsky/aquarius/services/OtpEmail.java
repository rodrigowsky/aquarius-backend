package com.rodrigowsky.aquarius.services;

import com.rodrigowsky.aquarius.model.CustomUserDetails;

public interface OtpEmail {

    public void sendEmail(CustomUserDetails u);
}
