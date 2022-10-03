package com.app.wallet.controller;

import com.app.wallet.dto.WalletDto;
import com.app.wallet.model.Wallet;
import com.app.wallet.service.AuthenticationService;
import com.app.wallet.service.UserService;
import com.app.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/myWallet")
    public ResponseEntity<Wallet> myWallet(@RequestBody String authToken) throws NullPointerException {
        Wallet wallet = walletService.getWalletBalance(authenticationService.getUser(authToken).getId());
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/addMoney")
    public ResponseEntity<WalletDto> addMoney (@RequestBody String authToken, @RequestBody Integer money) throws NullPointerException {
        WalletDto wallet = walletService.addMoney(authenticationService.getUser(authToken).getId(), money);
        return ResponseEntity.ok(wallet);
    }
}
