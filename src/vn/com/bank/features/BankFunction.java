package vn.com.bank.features;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class BankFunction {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank("Commerzbank");

    // ham the hien tieu de
    public static void showTitle(String author, String version) {
        System.out.println("+----------+--------------------------+------------+");
        System.out.println("| NGAN HANG SO | " + author + "@v" + version + "                    |");
    }

    // ham the hien menu
    public static void showMenu() {
        System.out.println("+----------+--------------------------+------------+");
        System.out.println(" 1. Xem danh sach khach hang");
        System.out.println(" 2. Nhap danh sach khach hang");
        System.out.println(" 3. Them tai khoan ATM");
        System.out.println(" 4. Chuyen tien");
        System.out.println(" 5. Rut tien");
        System.out.println(" 6. Tra cuu lich su giao dich");
        System.out.println(" 0. Thoat");
        System.out.println("+----------+--------------------------+------------+");
        System.out.print("Chuc nang: ");
    }

    public static void createsFile(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void createsDirectory(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        final String AUTHOR = "FX20043";
        final String VERSION = "4.0.0";
        showTitle(AUTHOR, VERSION);
        Path dir = FileSystems.getDefault().getPath("store");
        Path path1 = FileSystems.getDefault().getPath("store/customers.dat");
        Path path2 = FileSystems.getDefault().getPath("store/accounts.dat");
        Path path3 = FileSystems.getDefault().getPath("store/transactions.dat");
        Path path4 = FileSystems.getDefault().getPath("store/customers.txt");
        createsDirectory(dir);
        createsFile(path1);
        createsFile(path2);
        createsFile(path3);
        createsFile(path4);

        while (true) {
            showMenu();
            try {
                int n = scanner.nextInt();
                scanner.nextLine();
                if (n == 0) { // thoat ra khoi chuong trinh
                    break;
                } else if (n == 1) { // in ra danh sach khach hang
                    activeBank.showCustomers();
                } else if (n == 2) { // chuc nang them khach hang
                    System.out.print("Nhap duong dan den tep: ");
                    String fileName = scanner.nextLine();
                    activeBank.addCustomers(fileName);
                } else if (n == 3) { // chuc nang them tai khoan ATM
                    Customer customer = activeBank.getCustomer(scanner);
                    if (customer != null) {
                        activeBank.addSavingAccount(scanner, customer.getCustomerId());
                    }
                } else if (n == 4) { // chuc nang chuyen tien
                    Customer customer = activeBank.getCustomer(scanner);
                    if (customer != null) {
                        activeBank.transfer(scanner, customer.getCustomerId());
                    }
                } else if (n == 5) { // chuc nang rut tien
                    Customer customer = activeBank.getCustomer(scanner);
                    if (customer != null) {
                        activeBank.withdraw(scanner, customer.getCustomerId());
                    }
                } else if (n == 6) { // in lich su giao dich
                    Customer customer = activeBank.getCustomer(scanner);
                    if (customer != null) {
                        customer.displayTransactionInformation();
                    }
                } else {
                    System.out.println("Vui long chi nhap chuc nang tu 0 den 6!");
                }
            } catch(Exception e) {
                System.out.println("Vui long chi nhap chuc nang tu 0 den 6!");
                scanner.nextLine();
            }
        }
    }
}
