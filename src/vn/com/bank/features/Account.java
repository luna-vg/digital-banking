package vn.com.bank.features;

import vn.com.bank.validation.CheckIDCard;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Account implements Serializable {
    private static final long serialVersionUID = 1;
    private String customerID;
    private String accountNumber;
    private double balance;
    private String accountType;

    public Account(String customerID, String accountNumber, double balance, String accountType) {
        this.customerID = customerID;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
    }

    public Account() {}

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        // so du phai lon hon 50.000d
        if (balance >= 50000) {
            this.balance = balance;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getBalance() {
        return balance;
    }

    // ham phan loai tai khoan, tai khoan la premium neu so du lon hon hoac bang 10.000.000d
    public boolean isPremiumAccount() {
        return this.balance >= 10000000;
    }

    // ham lay ngay va gio hien tai theo format chi dinh
    public static String getDateAndTime() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(date);
    }

    // ham kiem tra tinh hop le cua STK
    public static boolean validateAccount(String accountNumber) {
        return accountNumber.length() == 6 && CheckIDCard.includeOnlyNumbers(accountNumber);
    }

    // ham lay ra cac giao dich cua tai khoan
    public List<Transaction> getTransactions() {
        return TransactionDao.list().stream().filter(transaction -> transaction.getAccountNumber().equals(this.accountNumber)).collect(Collectors.toList());
    }

    // ham lay ra khach hang so huu tai khoan
    public Customer getCustomer() {
        List<Customer> customers = CustomerDao.list();
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(this.customerID)) {
                return customer;
            }
        }
        return null;
    }

    // ham hien thi lich su giao dich
    public void displayTransactionsList() {
        for (Transaction transaction : getTransactions()) {
            if (transaction.getStatus()) {
                System.out.printf("[GD %s] %6s | %-10s | %15sđ | %19s%n", transaction.getId(), transaction.getAccountNumber(), transaction.getType(), String.format("%,.0f", transaction.getAmount()), transaction.getTime());
            }
        }
    }

    // tao ra giao dich moi va cap nhat lai tat ca cac files
    public void createTransaction(double amount, String time, boolean status, String type) {
        if (status) {
            if (type.equals("DEPOSIT")) {
                balance += amount;
            } else if (type.equals("WITHDRAW")) {
                balance -= amount;
            } else if (type.equals("TRANSFERS")){
                balance += amount;
            } else {
                System.out.println("Khong hop le");
            }
        }
        Transaction newTransaction = new Transaction(this.accountNumber, amount, time, status, type);
        List<Transaction> transactions = TransactionDao.list();
        transactions.add(newTransaction);
        TransactionDao.save(transactions);
        AccountDao.update(this);
    }

}
