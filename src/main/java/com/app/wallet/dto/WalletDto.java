package com.app.wallet.dto;


public class WalletDto {
    private String token;

    public WalletDto(Integer amount, String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
