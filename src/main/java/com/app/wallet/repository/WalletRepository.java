package com.app.wallet.repository;

import com.app.wallet.model.User;
import com.app.wallet.model.Wallet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findWalletByUserId(Optional<User> user);
}
