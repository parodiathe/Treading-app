package com.Makushev.requests;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String otp;
    private String password;
}
