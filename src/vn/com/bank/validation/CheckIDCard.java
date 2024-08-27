package vn.com.bank.validation;

import java.util.Random;
import java.util.Scanner;

public class CheckIDCard {
    public static void showTitle(String author, String version) {
        System.out.println("+----------+--------------------------+------------+");
        System.out.println("| NGAN HANG SO | " + author + "@v" + version + "                    |");
    }
    public static void showMenu() {
        System.out.println("+----------+--------------------------+------------+");
        System.out.println("| 1. Nhap CCCD                                     |");
        System.out.println("| 0. Thoat                                         |");
        System.out.println("+----------+--------------------------+------------+");
        System.out.print("Chuc nang: ");
    }

    // tao ma xac thuc co 6 ki tu gom ca chu va so
    public static String createSixRandomCharacters() {
        Random rand = new Random();
        String lowerCaseLetter = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseLetter = lowerCaseLetter.toUpperCase();
        String number = "0123456789";
        String allLettersAndNumbers = lowerCaseLetter + upperCaseLetter + number;
        String randomSixCharacters = "";
        for (int i = 0; i < 6; i++) {
            int randomInt = rand.nextInt(allLettersAndNumbers.length());
            randomSixCharacters += (Character.toString(allLettersAndNumbers.charAt(randomInt)));
        }
        return randomSixCharacters;
    }

    // tao ma xac thuc gom 3 chu so tu 100 den 999
    public static String createThreeRandomNumbers() {
        Random rand = new Random();
        String numbersWithZero = "0123456789";
        String numbersWithoutZero = "123456789";
        String firstNumber = Character.toString(numbersWithoutZero.charAt(rand.nextInt(9)));
        String secondNumber = Character.toString(numbersWithZero.charAt(rand.nextInt(10)));
        String thirdNumber = Character.toString(numbersWithZero.charAt(rand.nextInt(10)));
        String randomThreeNumbers = firstNumber + secondNumber + thirdNumber;
        return randomThreeNumbers;
    }

    public static String createSixRandomNumbers() {
        Random rand = new Random();
        String numbersWithZero = "0123456789";
        String numbersWithoutZero = "123456789";
        String firstNumber = Character.toString(numbersWithoutZero.charAt(rand.nextInt(9)));
        String secondNumber = Character.toString(numbersWithZero.charAt(rand.nextInt(10)));
        String thirdNumber = Character.toString(numbersWithZero.charAt(rand.nextInt(10)));
        String fourthNumber = Character.toString(numbersWithZero.charAt(rand.nextInt(10)));
        String fifthNumber = Character.toString(numbersWithZero.charAt(rand.nextInt(10)));
        String sixthNumber = Character.toString(numbersWithZero.charAt(rand.nextInt(10)));
        String randomSixNumbers = firstNumber + secondNumber + thirdNumber + fourthNumber + fifthNumber + sixthNumber;
        return randomSixNumbers;
    }

    public static boolean includeOnlyNumbers (String passedString) {
        try {
            Long tryString = Long.parseLong(passedString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // dieu kien de CCCD la hop le: gom 12 ki tu deu phai la chu so
    public static boolean isValidID (String id) {
        if (includeOnlyNumbers(id) && id.length() == 12) {
            return true;
        } else {
            return false;
        }
    }

    public static void showSubMenu() {
        System.out.println("      | 1. Kiem tra noi sinh");
        System.out.println("      | 2. Kiem tra tuoi, gioi tinh");
        System.out.println("      | 3. Kiem tra so ngau nhien");
        System.out.println("      | 0. Thoat");
        System.out.print("Chuc nang: ");
    }

    // ham su dung ba chu so dau tien cua CCCD de xac dinh noi sinh
    public static String showBirthPlace (String firstThreeNumbersFromID) {
        switch (firstThreeNumbersFromID) {
            case "001":
                return "Ha Noi";
            case "002":
                return "Ha Giang";
            case "004":
                return "Cao Bang";
            case "006":
                return "Bac Kan";
            case "008":
                return "Tuyen Quang";
            case "010":
                return "Lao Cai";
            case "011":
                return "Dien Bien";
            case "012":
                return "Lai Chau";
            case "014":
                return "Son La";
            case "015":
                return "Yen Bai";
            case "017":
                return "Hoa Binh";
            case "019":
                return "Thai Nguyen";
            case "020":
                return "Lang Son";
            case "022":
                return "Quang Ninh";
            case "024":
                return "Bac Giang";
            case "025":
                return "Phu Tho";
            case "026":
                return "Vinh Phuc";
            case "027":
                return "Bac Ninh";
            case "030":
                return "Hai Duong";
            case "031":
                return "Hai Phong";
            case "033":
                return "Hung Yen";
            case "034":
                return "Thai Binh";
            case "035":
                return "Ha Nam";
            case "036":
                return "Nam Dinh";
            case "037":
                return "Ninh Binh";
            case "038":
                return "Thanh Hoa";
            case "040":
                return "Nghe An";
            case "042":
                return "Ha Tinh";
            case "044":
                return "Quang Binh";
            case "045":
                return "Quang Tri";
            case "046":
                return "Thua Thien Hue";
            case "048":
                return "Da Nang";
            case "049":
                return "Quang Nam";
            case "051":
                return "Quang Ngai";
            case "052":
                return "Binh Dinh";
            case "054":
                return "Phu Yen";
            case "056":
                return "Khanh Hoa";
            case "058":
                return "Ninh Thuan";
            case "060":
                return "Binh Thuan";
            case "062":
                return "Kon Tum";
            case "064":
                return "Gia Lai";
            case "066":
                return "Dak Lak";
            case "067":
                return "Dak Nong";
            case "068":
                return "Lam Dong";
            case "070":
                return "Binh Phuoc";
            case "072":
                return "Tay Ninh";
            case "074":
                return "Binh Duong";
            case "075":
                return "Dong Nai";
            case "077":
                return "Ba Ria - Vung Tau";
            case "079":
                return "Ho Chi Minh";
            case "080":
                return "Long An";
            case "082":
                return "Tien Giang";
            case "083":
                return "Ben Tre";
            case "084":
                return "Tra Vinh";
            case "086":
                return "Vinh Long";
            case "087":
                return "Dong Thap";
            case "089":
                return "An Giang";
            case "091":
                return "Kien Giang";
            case "092":
                return "Can Tho";
            case "093":
                return "Hau Giang";
            case "094":
                return "Soc Trang";
            case "095":
                return "Bac Lieu";
            case "096":
                return "Ca Mau";
            default:
                return "So CCCD co the da nhap sai.";
        }
    }

    // ham the hien gioi tinh va nam sinh dua tren chu so tu 4 den 6 trong CCCD
    public static void showSexAndBirthYear (String fourthNumberFromID, String nextTwoNumbersFromID) {
        if (includeOnlyNumbers(nextTwoNumbersFromID)) {
            int nextTwoNumbers = Integer.parseInt(nextTwoNumbersFromID);
            // chap nhan nam sinh tu 1900 den 2023
            switch (fourthNumberFromID) {
                case "0":
                    System.out.println("Nam | 19" + nextTwoNumbersFromID);
                    break;
                case "1":
                    System.out.println("Nu | 19" + nextTwoNumbersFromID);
                    break;
                case "2":
                    if (nextTwoNumbers >= 0 && nextTwoNumbers <= 23) {
                        System.out.println("Nam | 20" + nextTwoNumbersFromID);
                    } else {
                        System.out.println("So CCCD co the da nhap sai.");
                    }
                    break;
                case "3":
                    if (nextTwoNumbers >= 0 && nextTwoNumbers <= 23) {
                        System.out.println("Nu | 20" + nextTwoNumbersFromID);
                    } else {
                        System.out.println("So CCCD co the da nhap sai.");
                    }
                    break;
                default:
                    System.out.println("So CCCD co the da nhap sai.");
            }
        }
    }

    public static void main(String[] args) {
        final String AUTHOR = "FX20043";
        final String VERSION = "1.0.0";
        showTitle(AUTHOR,VERSION);
        showMenu();
        Scanner sc = new Scanner(System.in);
        while (true) {
            boolean continueProgram = true;
            try {
                int n = sc.nextInt();
                sc.nextLine();
                if (n == 0) {
                    break;
                } else if (n == 1) {
                    String randomStringToCheck = "";
                    System.out.print("Vui long chon '1' de nhap ma xac thuc gom 3 chu so tu 100 den 999 hoac chon '2' de nhap ma xac thuc gom 6 ki tu bao gom ca chu va so: ");
                    String typeOfVerification = "";
                    while (true) {
                        typeOfVerification = sc.nextLine();
                        if (typeOfVerification.equals("1")) {
                            randomStringToCheck = createThreeRandomNumbers();
                            break;
                        } else if (typeOfVerification.equals("2")) {
                            randomStringToCheck = createSixRandomCharacters();
                            break;
                        } else {
                            System.out.print("Vui long nhap lai: ");
                        }
                    }
                    System.out.println("Nhap ma xac thuc: " + randomStringToCheck);
                    String check = sc.nextLine();
                    while (continueProgram) {
                        if (check.equals(randomStringToCheck)) {
                            System.out.print("Vui long nhap so CCCD: ");
                            String soCccd = sc.nextLine();
                            while (continueProgram) {
                                if (isValidID(soCccd)) {
                                    while (continueProgram) {
                                        showSubMenu();
                                        try {
                                            int m = sc.nextInt();
                                            sc.nextLine();
                                            if (m == 1) {
                                                String firstThreeNumbers = soCccd.substring(0, 3);
                                                System.out.println("Noi sinh: " + showBirthPlace(firstThreeNumbers));
                                            } else if (m == 2) {
                                                System.out.print("Gioi tinh: ");
                                                String nextOneNumber = soCccd.substring(3,4);
                                                String nextTwoNumbers = soCccd.substring(4,6);
                                                showSexAndBirthYear(nextOneNumber,nextTwoNumbers);
                                            } else if (m == 3) {
                                                String lastSixNumbers = soCccd.substring(6);
                                                System.out.println("So ngau nhien: " + lastSixNumbers);
                                            // nguoi dung nhap '0' se tro lai menu chinh
                                            } else if (m == 0) {
                                                continueProgram = false;
                                                showMenu();
                                                break;
                                            } else {
                                                System.out.println("Vui long chi nhap chuc nang tu 0 den 3!");
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Vui long chi nhap chuc nang tu 0 den 3!");
                                            sc.nextLine();
                                        }
                                    }
                                // nguoi dung nhap 'No' se tro lai menu chinh
                                } else if (soCccd.equals("No")) {
                                    continueProgram = false;
                                    showMenu();
                                    break;
                                } else {
                                    System.out.print("So CCCD khong hop le. Vui long nhap lai hoac 'No' de thoat: ");
                                    soCccd = sc.nextLine();
                                }
                            }
                        } else {
                            System.out.println("Ma xac thuc khong dung. Vui long thu lai.");
                            if (typeOfVerification.equals("1")) {
                                randomStringToCheck = createThreeRandomNumbers();
                            } else {
                                randomStringToCheck = createSixRandomCharacters();
                            }
                            System.out.println("Nhap ma xac thuc: " + randomStringToCheck);
                            check = sc.nextLine();
                        }
                    }
                } else {
                    System.out.println("Vui long chi nhap chuc nang 0 hoac 1!");
                    System.out.print("Chuc nang: ");
                }
            } catch (Exception e) {
                System.out.println("Vui long chi nhap chuc nang 0 hoac 1!");
                System.out.print("Chuc nang: ");
                sc.nextLine();
            }
        }
    }
}