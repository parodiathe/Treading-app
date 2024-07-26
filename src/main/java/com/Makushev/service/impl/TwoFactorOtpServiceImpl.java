package com.Makushev.service.impl;

import com.Makushev.model.TwoFactorOTP;
import com.Makushev.model.User;
import com.Makushev.service.TwoFactorOtpService;

public class TwoFactorOtpServiceImpl implements TwoFactorOtpService {

    @Override
    public TwoFactorOTP createTwoFactorOtp(User user, String otp, String jwt) {
        return null;
    }

    @Override
    public TwoFactorOTP findByUser(Long user) {
        return null;
    }

    @Override
    public TwoFactorOTP findById(String id) {
        return null;
    }

    @Override
    public boolean verifyTwoFactorOtp(TwoFactorOTP twoFactorOTP, String otp) {
        return false;
    }

    @Override
    public void deleteTwoFactorOtp(TwoFactorOTP twoFactorOTP) {

    }
}
