package vn.com.bank.features;

import java.util.List;

public class CustomerDao {
    private final static String FILE_PATH = "store/customers.dat";
    public static void save(List<Customer> customers) {
        BinaryFileService.writeFile(FILE_PATH, customers);
    }

    public static List<Customer> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }
}
