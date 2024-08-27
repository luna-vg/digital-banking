package vn.com.bank.features;

import java.util.UUID;

public class Bank {
    private final String bankId;
    private final String bankName;

    public Bank(String bankName) {
        this.bankId = String.valueOf(UUID.randomUUID());
        this.bankName = bankName;
    }
}
