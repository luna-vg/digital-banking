package vn.com.bank.features;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AccountParameterizedTest {
    private Account account;
    private String accountNumber;
    private boolean expectedValidateAccount;
    private double balance;
    private boolean expectedPremium;

    public AccountParameterizedTest(String accountNumber, boolean expectedValidateAccount, double balance, boolean expectedPremium) {
        this.accountNumber = accountNumber;
        this.expectedValidateAccount = expectedValidateAccount;
        this.balance = balance;
        this.expectedPremium = expectedPremium;
    }

    @Before
    public void setup() {
        account = new Account();
        account.setBalance(balance);
    }

    @Parameterized.Parameters
    public static List<Object[]> testConditions() {
        return Arrays.asList(new Object[][] {
                {"123456", true, 1000000, false},
                {"58", false, 12000000, true},
                {"1611Q&L", false, 10000000, true}
        });
    }

    @Test
    public void validateAccount() {
        assertEquals(expectedValidateAccount, Account.validateAccount(accountNumber));
    }

    @Test
    public void isPremiumAccount() {
        assertEquals(expectedPremium, account.isPremiumAccount());
    }
}
