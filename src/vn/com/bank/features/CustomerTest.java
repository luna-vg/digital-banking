package vn.com.bank.features;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer customer;
    private SavingsAccount account1;
    private SavingsAccount account2;
    private List<Account> accounts;
    private Scanner scanner;

    @Before
    public void setUp() throws CustomerIdNotValidException {
        customer = new Customer("011111111111", "Dylan");
        List<Customer> customers = CustomerDao.list();
        customers.add(customer);
        CustomerDao.save(customers);
        String input = "876543\n10000000\n987654\n10000000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scanner = new Scanner(System.in);
        customer.input(scanner);
        customer.input(scanner);
        accounts = new ArrayList<>();
        account1 = new SavingsAccount("011111111111", "876543", 10000000);
        account2 = new SavingsAccount("011111111111", "987654", 10000000);
        accounts.add(account1);
        accounts.add(account2);
    }

    @Test
    public void getAccountByAccountNumber() {
        assertSame(account1, Customer.getAccountByAccountNumber(accounts, "876543"));
        assertNull(Customer.getAccountByAccountNumber(accounts, "777777"));
    }

    @Test
    public void isCustomerPremium() {
        assertTrue(customer.isCustomerPremium());
    }

    @Test
    public void getTotalBalance() {
        assertEquals(20000000, customer.getTotalAccountBalance(), 0);
    }

    @Test
    public void isAccepted() {
        assertTrue(account1.isAccepted(100000));
        assertFalse(account2.isAccepted(1000000000));
    }

    @Test
    public void withdraw() {
        account1.withdraw(2000000);
        assertEquals(8000000,account1.getBalance(),0);
    }

    @Test
    public void transfers() {
        account1.transfer(account2, 8000000);
        assertEquals(2000000, account1.getBalance(),0);
        assertEquals(18000000, account2.getBalance(),0);
    }

    @After
    public void teardown() {
        List<Customer> customers = CustomerDao.list();
        customers.removeIf(customer -> customer.getCustomerId().equals("011111111111"));
        CustomerDao.save(customers);
        List<Account> accounts = AccountDao.list();
        accounts.removeIf(account -> account.getAccountNumber().equals("876543") || account.getAccountNumber().equals("987654"));
        AccountDao.save(accounts);
        List<Transaction> transactions = TransactionDao.list();
        transactions.removeIf(transaction -> transaction.getAccountNumber().equals("876543") || transaction.getAccountNumber().equals("987654"));
        TransactionDao.save(transactions);
    }
}