package vn.com.bank.features;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AccountDao {
    private final static String FILE_PATH = "store/accounts.dat";
    private final static int MAX_THREAD = 5;
    private static ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);

    // ham luu lai danh sach tai khoan trong file
    public static void save(List<Account> accounts) {
        BinaryFileService.writeFile(FILE_PATH, accounts);
    }

    // ham lay ra danh sach tai khoan trong file
    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

    // ham cap nhat tai khoan
    public static void update(Account editAccount) {
        List<Account> accounts = list();
        boolean hasExist = false;
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);
        List<Future<Boolean>> futures = new ArrayList<>();
        for (Account account : accounts) {
            futures.add(executorService.submit(() -> account.getAccountNumber().equals(editAccount.getAccountNumber())));
        }
        try {
            for (Future<Boolean> future : futures) {
                if (future.get()) {
                    hasExist = true;
                    break;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        List<Account> updatedAccounts = new ArrayList<>();
        if (!hasExist) {
            updatedAccounts = accounts;
            updatedAccounts.add(editAccount);
        } else {
            for (Account account : accounts) {
                if (account.getAccountNumber().equals(editAccount.getAccountNumber())) {
                    updatedAccounts.add(editAccount);
                } else {
                    updatedAccounts.add(account);
                }
            }
        }
        save(updatedAccounts);
    }
}
