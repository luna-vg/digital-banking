package vn.com.bank.features;

public interface IReport {
    void log(double amount, String type, Account receiveAccount);
}
