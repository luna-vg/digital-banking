package vn.com.bank.features;

import java.util.List;
import java.util.Scanner;

public class DigitalBank extends Bank {

    public DigitalBank(String bankName) {
        super(bankName);
    }

    // ham the hien thong tin cac khach hang
    public void showCustomers() {
        List<Customer> customers = CustomerDao.list();
        if (customers.isEmpty()) {
            System.out.println("Chua co khach hang nao trong danh sach");
        } else {
            customers.forEach(Customer::displayInformation);
        }
    }

    // ham them khach hang tu file duoc chi dinh vao danh sach khach hang
    public void addCustomers(String fileName) {
        List<Customer> updatedCustomers = CustomerDao.list();
        List<List<String>> customers = TextFileService.readFile(fileName);
        for (List<String> customer : customers) {
            Customer newCustomer;
            try {
                newCustomer = new Customer(customer);
                if (isCustomerExisted(CustomerDao.list(), newCustomer)) {
                    System.out.println("Khach hang " + newCustomer.getCustomerId() + " da ton tai, them khach hang khong thanh cong");
                } else {
                    updatedCustomers.add(newCustomer);
                    System.out.println("Da them khach hang " + newCustomer.getCustomerId() + " vao danh sach khach hang");
                }
            } catch (CustomerIdNotValidException e) {
                System.out.println("Khach hang " + customer.get(0) + " co so ID khong hop le, them khach hang khong thanh cong");
            }
        }
        CustomerDao.save(updatedCustomers);
    }

    // ham them tai khoan ATM cho khach hang
    public void addSavingAccount(Scanner scanner, String customerId) {
        List<Customer> customers = CustomerDao.list();
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                customer.input(scanner);
            }
        }
    }

    // ham rut tien cho khach hang
    public void withdraw(Scanner scanner, String customerId) {
        List<Customer> customers = CustomerDao.list();
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                customer.displayInformation();
                customer.withdraw(scanner);
            }
        }
    }

    // ham chuyen tien cho khach hang
    public void transfer(Scanner scanner, String customerId) {
        List<Customer> customers = CustomerDao.list();
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                customer.displayInformation();
                customer.transfers(scanner);
            }
        }
    }

    // ham kiem tra xem khach hang co ton tai khong
    public static boolean isCustomerExisted(List<Customer> customers, Customer newCustomer) {
        return customers.stream().anyMatch(customer -> customer.getCustomerId().equals(newCustomer.getCustomerId()));
    }

    // ham lay ra khach hang tu ma so khach hang nhap vao
    public Customer getCustomer(Scanner scanner) {
        List<Customer> customers = CustomerDao.list();
        if (customers.isEmpty()) {
            System.out.println("Chua co khach hang");
            return null;
        }
        String customerId;
        Customer customer;
        while (true) {
            System.out.print("Nhap ma so cua khach hang: ");
            customerId = scanner.nextLine();
            if (getCustomerById(customers, customerId) == null) {
                System.out.println("Khong tim thay khach hang " + customerId + ", tac vu khong thanh cong");
            } else {
                customer = getCustomerById(customers, customerId);
                break;
            }
        }
        return customer;
    }

    // ham kiem tra tai khoan ton tai chua
    public static boolean isAccountExisted(List<Account> accountList, String accountNumber) {
        return accountList.stream().anyMatch(account -> account.getAccountNumber().equals(accountNumber));
    }

    // ham lay ra khach hang trong danh sach duoc chi dinh tu so ID
    public static Customer getCustomerById(List<Customer> customerList, String customerId) {
        for (Customer customer : customerList) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

}
