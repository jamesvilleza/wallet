package com.app.wallet.dto;

public class TransactionDto {
    private int amount;

    private String email;
    private String token;

    public int getAmount() {
        return amount;
    }

    public void setAmountToAdd(int amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
