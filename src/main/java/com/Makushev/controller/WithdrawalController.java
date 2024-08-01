package com.Makushev.controller;

import com.Makushev.model.User;
import com.Makushev.model.Wallet;
import com.Makushev.model.WalletTransaction;
import com.Makushev.model.Withdrawal;
import com.Makushev.service.UserService;
import com.Makushev.service.WalletService;
import com.Makushev.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WithdrawalController {

    private WithdrawalService withdrawalService;
    private WalletService walletService;
    private UserService userService;

    @Autowired
    public WithdrawalController(WithdrawalService withdrawalService, WalletService walletService, UserService userService) {
        this.withdrawalService = withdrawalService;
        this.walletService = walletService;
        this.userService = userService;
    }


    public ResponseEntity<?> withdrawalRequest(
            @PathVariable Long amount,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        Wallet userWallet = walletService.getUserWallet(user);

        Withdrawal withdrawal = withdrawalService.requestyWithdrawal(amount, user);
        walletService.addBalanceToWallet(userWallet, -withdrawal.getAmount());

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @PatchMapping("/api/admin/withdrawal/{id}/processed/{accept}")
    public ResponseEntity<?> processedWithdrawal(
            @PathVariable Long id,
            @PathVariable boolean accept,
            @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserProfileByJwt(jwt);

        Withdrawal withdrawal = withdrawalService.processedWithwithdrawal(id, accept);

        Wallet userWallet = walletService.getUserWallet(user);

        if(!accept){
            walletService.addBalanceToWallet(userWallet, withdrawal.getAmount());
        }

        return new ResponseEntity<>(withdrawal, HttpStatus.OK)

    }

    @GetMapping("/api/withdrawal")
    public ResponseEntity<List<Withdrawal>> getWithdrawalHistory(
            @RequestHeader("Authorization") String jwt) throws Exception {


        User user = userService.findUserProfileByJwt(jwt);

        List<Withdrawal> withdrawal = withdrawalService.getUsersWithdrawalHistory(user);

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping("/api/admin/withdrawal")
    public ResponseEntity<List<Withdrawal>> getAllWithdrawalRequest(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        List<Withdrawal> withdrawal = withdrawalService.getAllWithdrawalRequest();

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);

    }
}
