package com.Makushev.service.impl;

import com.Makushev.model.PaymentDetails;
import com.Makushev.model.User;
import com.Makushev.repository.PaymentDetailsRepository;
import com.Makushev.service.PaymentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    PaymentDetailsRepository paymentDetailsRepository;

    @Autowired
    public PaymentDetailsServiceImpl(PaymentDetailsRepository paymentDetailsRepository) {
        this.paymentDetailsRepository = paymentDetailsRepository;
    }



    @Override
    public PaymentDetails addPaymentDetails (String accountNumber,
                                             String accountHolderName,
                                             String ifsc,
                                             String bankName,
                                             User user)
    {

        PaymentDetails paymentDetails = new PaymentDetails();

        paymentDetails.setAccountNubmer(accountNumber);
        paymentDetails.setAccountHolderName(accountHolderName);
        paymentDetails.setIfsc(ifsc);
        paymentDetails.setBankName(bankName);
        paymentDetails.setUser(user);

        return paymentDetailsRepository.save(paymentDetails);
    }

    @Override
    public PaymentDetails getUsersPaymentDetails(User user) {

        return paymentDetailsRepository.findByUserId(user.getId());

    }
}
