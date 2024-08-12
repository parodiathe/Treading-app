package com.Makushev.controller;

import com.Makushev.model.PaymentDetails;
import com.Makushev.model.User;
import com.Makushev.service.PaymentDetailsService;
import com.Makushev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentDetailsController {

    private UserService userService;
    private PaymentDetailsService paymentDetailsService;

    @Autowired
    public PaymentDetailsController(UserService userService, PaymentDetailsService paymentDetailsService) {
        this.userService = userService;
        this.paymentDetailsService = paymentDetailsService;
    }

    @PostMapping("/payment-details")
    public ResponseEntity<PaymentDetails> addPaymentDetails(
            @RequestBody PaymentDetails paymentDetailsRequest,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        PaymentDetails paymentDetails = paymentDetailsService.addPaymentDetails(
                paymentDetailsRequest.getAccountNumber(),
                paymentDetailsRequest.getAccountHolderName(),
                paymentDetailsRequest.getIfsc(),
                paymentDetailsRequest.getBankName(),
                user
        );
        return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
    }

    @GetMapping("/payment-details")
    public ResponseEntity<PaymentDetails> getUsersPaymentDetails(
            @RequestHeader("Authorization") String jwt) throws Exception {

                User user = userService.findUserProfileByJwt(jwt);

                PaymentDetails paymentDetails = paymentDetailsService.getUsersPaymentDetails(user);

                return new ResponseEntity<>(paymentDetails, HttpStatus.CREATED);
    }
}
