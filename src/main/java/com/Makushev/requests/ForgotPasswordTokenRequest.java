package com.Makushev.requests;

import com.Makushev.domain.VerificationType;
import lombok.Data;

@Data
public class ForgotPasswordTokenRequest {

    private String sendTo;
    private VerificationType verificationType;

}
