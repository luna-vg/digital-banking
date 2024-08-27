package vn.com.bank.features;

import vn.com.bank.validation.CheckIDCard;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1;
    private final String name;
    private final String customerId;

    public Customer(String customerId, String name) throws CustomerIdNotValidException {
        this.name = name;
        if (CheckIDCard.isValidID(customerId)) {
            this.customerId = customerId;
        } else {
            throw new CustomerIdNotValidException("So ID khong hop le");
        }
    }

    public Customer(List<String> values) throws CustomerIdNotValidException {
        this(values.get(0), values.get(1));
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    // ham lay ra cac tai khoan cua khach hang
    public List<Account> getAccounts() {
        return AccountDao.list().stream()
                .filter(account -> {
                    Customer customer = account.getCustomer();
                    if (customer != null) {
                        return customer.getCustomerId().equals(this.customerId);
                    }
                    return false;
                })
                .collect(Collectors.toList());

    }

    // ham phan loai khach hang
    public boolean isCustomerPremium() {
        return getAccounts().stream().anyMatch(Account::isPremiumAccount);
    }

    // ham lay ra tai khoan tu danh sach duoc chi dinh
    public static Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    // ham tinh tong so du cua cac tai khoan cua khach hang
    public double getTotalAccountBalance() {
        double totalBalance = 0;
        for (Account account : getAccounts()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    // ham the hien thong tin khach hang
    public void displayInformation() {
        System.out.printf("%18s | %-29s | %7s | %19s%n", this.customerId, this.name, (isCustomerPremium() ? "Premium" : "Normal"), (getAccounts().size() == 0 ? "No accounts" : (String.format("%,.0f", getTotalAccountBalance()) + "đ")));
        if (getAccounts().size() >= 1) {
            for (int i = 1; i <= getAccounts().size(); i++) {
                System.out.printf("%s%17s | %-29s | %28sđ%n", i, getAccounts().get(i - 1).getAccountNumber(), ((getAccounts().get(i - 1)).getAccountType()), String.format("%,.0f", getAccounts().get(i - 1).getBalance()));
            }
        }
    }

    // ham the hien lich su giao dich cua khach hang
    public void displayTransactionInformation() {
        displayInformation();
        getAccounts().forEach(Account::displayTransactionsList);
    }

    // ham rut tien cho khach hang
    public void withdraw(Scanner scanner) {
        List<Account> accounts = getAccounts();
        if (!accounts.isEmpty()) {
            Account account;
            double amount;
            do {
                System.out.print("Nhap so tai khoan: ");
                account = getAccountByAccountNumber(accounts, scanner.nextLine());
            } while (account == null);
            if (account instanceof SavingsAccount) {
                while (true) {
                    try {
                        System.out.print("Nhap so tien rut hoac '0' de thoat: ");
                        String answer = scanner.nextLine();
                        if (answer.equals("0")) {
                            amount = 0;
                            break;
                        }
                        amount = Double.parseDouble(answer);
                        if (((SavingsAccount) account).isAccepted(amount)) {
                            break;
                        }
                    } catch (Exception e) {
                        System.out.print("Khong hop le. ");
                    }
                }
                if (amount != 0) {
                    ((SavingsAccount) account).withdraw(amount);
                    System.out.println("Rut tien thanh cong, bien lai giao dich:");
                    ((SavingsAccount) account).log(amount, "WITHDRAW", null);
                }
            }
        } else {
            System.out.println("Khach hang chua co tai khoan nao, tac vu khong thanh cong");
        }
    }

    // ham chuyen tien
    public void transfers (Scanner scanner) {
        List<Account> accounts = getAccounts();
        if (!accounts.isEmpty()) {
            Account sendAccount;
            Account receiveAccount = null;
            double amount;
            do {
                System.out.print("Nhap so tai khoan chuyen: ");
                sendAccount = getAccountByAccountNumber(accounts, scanner.nextLine());
            } while (sendAccount == null);
            if (sendAccount instanceof SavingsAccount) {
                do {
                    System.out.print("Nhap so tai khoan nhan hoac 'No' de thoat: ");
                    String accountNumber = scanner.nextLine();
                    if (accountNumber.equals("No")) {
                        break;
                    } else {
                        receiveAccount = getAccountByAccountNumber(AccountDao.list(), accountNumber);
                    }
                } while (receiveAccount == null || receiveAccount.getAccountNumber().equals(sendAccount.getAccountNumber()));
                if (receiveAccount != null) {
                    System.out.println("Gui tien den tai khoan: " + receiveAccount.getAccountNumber() + " | " + receiveAccount.getCustomer().getName());
                    while (true) {
                        try {
                            System.out.print("Nhap so tien chuyen hoac '0' de thoat: ");
                            String answer = scanner.nextLine();
                            if (answer.equals("0")) {
                                amount = 0;
                                break;
                            }
                            amount = Double.parseDouble(answer);
                            if (((SavingsAccount) sendAccount).isAccepted(amount)) {
                                break;
                            }
                        } catch (Exception e) {
                            System.out.print("Chua hop le. ");
                        }
                    }
                    if (amount != 0) {
                        String answer;
                        System.out.print("Xac nhan thuc hien chuyen " + (String.format("%,.0f", amount) + "đ") + " tu tai khoan [" + sendAccount.getAccountNumber() + "] den tai khoan [" + receiveAccount.getAccountNumber() + "] (Y/N): ");
                        while (true) {
                            answer = scanner.nextLine();
                            if (answer.equals("N")) {
                                System.out.println("Khong thuc hien chuyen tien");
                                break;
                            } else if (answer.equals("Y")) {
                                ((SavingsAccount) sendAccount).transfer(receiveAccount, amount);
                                System.out.println("Chuyen tien thanh cong, bien lai giao dich:");
                                ((SavingsAccount) sendAccount).log(amount, "TRANSFERS", receiveAccount);
                                break;
                            } else {
                                System.out.print("Khong hop le. Nhap lai: ");
                            }
                        }
                    }
                }
            }
        } else {
            System.out.println("Khach hang chua co tai khoan nao");
        }
    }

    // ham tao tai khoan ATM moi cho khach hang
    public void input(Scanner scanner) {
        String accountNumber;
        do {
            System.out.print("Nhap so tai khoan gom 6 chu so: ");
            accountNumber = scanner.nextLine();
            if (DigitalBank.isAccountExisted(AccountDao.list(), accountNumber)) {
                System.out.print("STK da ton tai. ");
            } else if (!Account.validateAccount(accountNumber)) {
                System.out.print("STK khong hop le. ");
            }
        } while (!Account.validateAccount(accountNumber) | DigitalBank.isAccountExisted(AccountDao.list(), accountNumber));
        double amount;
        while (true) {
            try {
                System.out.print("Nhap so du tai khoan >= 50000đ: ");
                amount = scanner.nextDouble();
                scanner.nextLine();
                if (amount >= 50000) {
                    break;
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
        }
        SavingsAccount newAccount = new SavingsAccount();
        newAccount.setAccountNumber(accountNumber);
        newAccount.setCustomerID(this.customerId);
        newAccount.createTransaction(amount, Account.getDateAndTime(), true, "DEPOSIT");
        System.out.println("Tao tai khoan thanh cong");
    }

}
