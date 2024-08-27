package vn.com.bank.features;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFileService {
    private static final String COMMA_DELIMITER = ",";

    // ham doc du lieu tu file duoc chi dinh
    public static List<List<String>> readFile(String fileName) {
        List<List<String>> objects = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            scanner.useDelimiter(COMMA_DELIMITER);
            try {
                while (scanner.hasNextLine()) {
                    List<String> object = new ArrayList<>();
                    String input = scanner.nextLine();
                    String[] data = input.split(COMMA_DELIMITER);
                    String cccd = data[0];
                    String name = data[1];
                    object.add(cccd);
                    object.add(name);
                    objects.add(object);
                }
            } catch (Exception e) {
                System.out.println("Du lieu trong tep co the chua thoa man cu phap: '[soID],[ten]'. Them khach hang khong thanh cong");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Tep khong ton tai");
        }
        return objects;
    }
}
