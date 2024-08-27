package vn.com.bank.features;

public class CustomerIdNotValidException extends Exception {
    public CustomerIdNotValidException(String message) {
        super(message);
    }
}
