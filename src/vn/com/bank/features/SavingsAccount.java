package vn.com.bank.features;

import java.io.Serializable;

public class SavingsAccount extends Account implements Serializable,ITransfer, IReport {
    private final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;

    private static final long serialVersionUID = 1;

    public SavingsAccount() {
        setAccountType("SAVINGS");
    }

    public SavingsAccount(String customerID, String accountNumber, double balance) {
        super(customerID, accountNumber, balance, "SAVINGS");
    }

    // ham kiem tra xem so tien rut/chuyen co hop le hay khong
    public boolean isAccepted(double amount) {
        if (amount >= 50000 && amount % 10000 == 0) {
            if (getBalance() - amount >= 50000) {
                if (!isPremiumAccount()) {
                    if (amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW) {
                        return true;
                    } else {
                        System.out.println("Doi voi tai khoan thuong khong duoc giao dich nhieu hon 5.000.000d");
                        return false;
                    }
                } else {
                    return true;
                }
            } else {
                System.out.println("So du con lai phai toi thieu la 50.000d");
                return false;
            }
        } else {
            System.out.println("So tien giao dich toi thieu la 50.000d va phai la boi so cua 10000");
            return false;
        }
    }

    // ham rut tien
    public boolean withdraw (double amount) {
        if (isAccepted(amount)) {
            createTransaction(amount, getDateAndTime(), true, "WITHDRAW");
            return true;
        }
        return false;
    }

    // ham chuyen tien
    @Override
    public void transfer(Account receiveAccount, double amount) {
        if (isAccepted(amount)) {
            createTransaction(-amount, getDateAndTime(), true, "TRANSFERS");
            receiveAccount.createTransaction(amount, getDateAndTime(), true, "TRANSFERS");
        }
    }

    // ham in ra bien lai giao dich
    @Override
    public void log(double amount, String type, Account receiveAccount) {
        System.out.println("+----------+--------------------------+------------+");
        System.out.printf("%30s%n", "BIEN LAI GIAO DICH SAVINGS");
        System.out.printf("NGAY G/D: %28s%n", getDateAndTime());
        System.out.printf("ATM ID: %30s%n", "DIGITAL-BANK-ATM 2022");
        System.out.printf("SO TK: %31s%n", getAccountNumber());
        if (type.equals("TRANSFERS")) {
            System.out.printf("SO TK NHAN: %26s%n", receiveAccount.getAccountNumber());
            System.out.printf("SO TIEN CHUYEN: %21sđ%n", String.format("%,.0f", amount));
            System.out.printf("SO DU: %30sđ%n", String.format("%,.0f", getBalance()));
        } else if (type.equals("WITHDRAW")) {
            System.out.printf("SO TIEN RUT: %24sđ%n", String.format("%,.0f", amount));
            System.out.printf("SO DU TK: %27sđ%n", String.format("%,.0f", getBalance()));
        }
        System.out.printf("PHI + VAT: %27s%n", "0đ");
        System.out.println("+----------+--------------------------+------------+");
    }

}
