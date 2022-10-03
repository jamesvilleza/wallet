package com.app.wallet.dto;


public class WalletDto {
    private Integer amount;

    public WalletDto(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
