package com.app.wallet.service;

import com.app.wallet.dto.SignupDto;
import com.app.wallet.dto.WalletDto;
import com.app.wallet.model.User;
import com.app.wallet.model.Wallet;
import com.app.wallet.repository.UserRepository;
import com.app.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    public Wallet createWallet(SignupDto signup, int amount){
        Wallet wallet = new Wallet();
        User user = userRepository.findByEmail(signup.getEmail());
        wallet.setUser(user);
        wallet.setBalance(amount);
        return wallet;
    }

    public Wallet getWalletBalance(int userId){
        Wallet wallet = walletRepository.findWalletByUserId(userRepository.findById(userId));
        return wallet;
    }

    public WalletDto addMoney(int userId, int money){
        Wallet wallet = walletRepository.findWalletByUserId(userRepository.findById(userId));
        wallet.setBalance(wallet.getBalance() + money);
        return new WalletDto(wallet.getBalance());
    }

    public WalletDto payWithWallet(int userId, int transaction){
        Wallet wallet = walletRepository.findWalletByUserId(userRepository.findById(userId));
        wallet.setBalance(wallet.getBalance() - transaction);
        return new WalletDto(wallet.getBalance());
    }
}
