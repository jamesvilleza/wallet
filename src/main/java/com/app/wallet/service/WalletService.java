package com.app.wallet.service;

import com.app.wallet.config.AuthMessage;
import com.app.wallet.dto.TransactionDto;
import com.app.wallet.dto.SignupDto;
import com.app.wallet.dto.WalletDto;
import com.app.wallet.exceptions.CustomException;
import com.app.wallet.model.AuthenticationToken;
import com.app.wallet.model.User;
import com.app.wallet.model.Wallet;
import com.app.wallet.repository.UserRepository;
import com.app.wallet.repository.WalletRepository;
import java.util.Objects;
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

    public Wallet createWallet(SignupDto signup){
        Wallet wallet = new Wallet();
        User user = userRepository.findByEmail(signup.getEmail());
        wallet.setUser(user);
        walletRepository.save(wallet);
        return wallet;
    }

    public Wallet getWalletBalance(int userId, String token) throws CustomException {
        Wallet wallet = walletRepository.findWalletByUserId(userRepository.findById(userId));
        if(!Objects.nonNull(token)) {
            // token not present
            throw new CustomException(AuthMessage.AUTH_TOKEN_NOT_PRESENT);
        }
        return wallet;
    }

    public Wallet addMoney(TransactionDto walletDto) throws CustomException {
        User user = userRepository.findByEmail(walletDto.getEmail());
        Wallet wallet = walletRepository.findWalletByUserId(userRepository.findById(user.getId()));
        AuthenticationToken token = authenticationService.getToken(user);
        if(!Objects.nonNull(token)) {
            // token not present
            throw new CustomException(AuthMessage.AUTH_TOKEN_NOT_PRESENT);
        }
        wallet.setBalance(wallet.getBalance() + walletDto.getAmount());
        return wallet;
    }

    public Wallet payWithWallet(TransactionDto transaction){
        User user = userRepository.findByEmail(transaction.getEmail());
        Wallet wallet = walletRepository.findWalletByUserId(userRepository.findById(user.getId()));
        AuthenticationToken token = authenticationService.getToken(user);
        wallet.setBalance(wallet.getBalance() - transaction.getAmount());
        return wallet;
    }
}
