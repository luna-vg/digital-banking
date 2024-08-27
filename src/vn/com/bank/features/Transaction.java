package vn.com.bank.features;

import vn.com.bank.validation.CheckIDCard;

import java.io.Serializable;

public class Transaction implements Serializable {
    private final String id = CheckIDCard.createSixRandomCharacters();
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;
    private String type;
    private static final long serialVersionUID = 1;

    public Transaction(String accountNumber, double amount, String time, boolean status, String type) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
        this.status = status;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public boolean getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

}
