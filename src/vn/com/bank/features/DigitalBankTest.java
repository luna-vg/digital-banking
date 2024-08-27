package vn.com.bank.features;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DigitalBankTest {

    private DigitalBank digitalBank;
    private List<Customer> customers;
    private List<Account> accounts;
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    private SavingsAccount account1;
    private SavingsAccount account2;

    @Before
    public void setUp() throws CustomerIdNotValidException {
        customers = new ArrayList<>();
        accounts = new ArrayList<>();
        digitalBank = new DigitalBank("Commerzbank");
        customer1 = new Customer("021111111111", "Claire");
        customer2 = new Customer("036111111111", "Kevin");
        customer3 = new Customer("021111121910", "Evan");
        customers.add(customer1);
        customers.add(customer2);
        account1 = new SavingsAccount("021111111111", "111111", 10000000);
        account2 = new SavingsAccount("021111111123", "222222", 5000000);
        accounts.add(account1);
        accounts.add(account2);
    }

    @Test
    public void isCustomerExisted() {
        assertTrue(DigitalBank.isCustomerExisted(customers, customer1));
        assertFalse(DigitalBank.isCustomerExisted(customers, customer3));
    }

    @Test
    public void isAccountExisted() {
        assertTrue(DigitalBank.isAccountExisted(accounts, "111111"));
        assertFalse(DigitalBank.isAccountExisted(accounts, "555555"));
    }

    @Test
    public void getCustomerById() {
        assertEquals(customer1, DigitalBank.getCustomerById(customers, "021111111111"));
        assertNull(DigitalBank.getCustomerById(customers, "021111123989"));
    }
}