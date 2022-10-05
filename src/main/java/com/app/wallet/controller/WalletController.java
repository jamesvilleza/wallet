package com.app.wallet.controller;

import com.app.wallet.dto.TransactionDto;
import com.app.wallet.exceptions.CustomException;
import com.app.wallet.model.Wallet;
import com.app.wallet.service.AuthenticationService;
import com.app.wallet.service.UserService;
import com.app.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @GetMapping("/myWallet")
    public ResponseEntity<Wallet> myWallet(@RequestHeader("Authorization") String authToken) throws NullPointerException, CustomException {

        Wallet wallet = walletService.getWalletBalance(authenticationService.getUser(authToken).getId(), authToken);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/addMoney")
    public ResponseEntity<Wallet> addMoney (@RequestHeader String authToken, @RequestBody Integer money) throws NullPointerException, CustomException {
        TransactionDto dto = new TransactionDto();
        Wallet wallet = walletService.addMoney(dto);
        return ResponseEntity.ok(wallet);
    }
}
