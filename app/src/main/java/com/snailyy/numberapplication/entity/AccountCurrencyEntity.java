package com.snailyy.numberapplication.entity;

import java.io.Serializable;

public class AccountCurrencyEntity implements Serializable {

    /**
     * 货币名称
     */
    public String currency;
    /**
     * 总余额
     */
    public float balance;

    /**
     * 可用余额
     */
    public float available;

    public AccountCurrencyEntity(String currency, float balance, float available) {
        this.currency = currency;
        this.balance = balance;
        this.available = available;
    }

    public AccountCurrencyEntity() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getAvailable() {
        return available;
    }

    public void setAvailable(float available) {
        this.available = available;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountCurrencyEntity{");
        sb.append("currency='").append(currency).append('\'');
        sb.append(", balance=").append(balance);
        sb.append(", available=").append(available);
        sb.append('}');
        return sb.toString();
    }


}
