import java.util.Scanner;
import java.util.Random;

public class PasswordGenerator {
    public static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    public static final String DIGIT = "0123456789";
    public static final String SYMBOL = "@#$%&*=";

    public static String generatePassword(int length, boolean useUpper, boolean useLower, boolean useDigits, boolean useSymbol) {
        StringBuilder allowedChars = new StringBuilder();
        if (useUpper) {
            allowedChars.append(UPPER);
        }
        if (useLower) {
            allowedChars.append(LOWER);
        }
        if (useDigits) {
            allowedChars.append(DIGIT);
        }
        if (useSymbol) {
            allowedChars.append(SYMBOL);
        }
        if(allowedChars.length() == 0) {
            throw new IllegalArgumentException("Error: At least one character type must be selected");
        }

        Random random = new Random();
        StringBuilder password = new StringBuilder();
        String allowed = allowedChars.toString();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowed.length());  // Randomly choose an index and get the character at that index from allowed characters
            password.append(allowed.charAt(index));
        }
        return password.toString();   // Change StringBuilder to String and return
    }

    public static String strength(String password) {
        int length = password.length();
        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean symbol = false;

        int score = 0;

        for(int i=0; i<length; i++) {
            char s = password.charAt(i);
            if (UPPER.indexOf(s) >= 0) upper = true;
            if (LOWER.indexOf(s) >= 0) lower = true;
            if (DIGIT.indexOf(s) >= 0) digit = true;
            if (SYMBOL.indexOf(s) >= 0) symbol = true;
        }
            if(length >= 8) score++;
            if(length >= 12) score++;
            if(upper) score++;
            if(lower) score++;
            if(digit) score++;
            if(symbol) score++;

        if(score < 3) {
            return "Weak";
        } else if(score == 3 || score == 4) {
            return "Medium";
        } else  {
            return "Strong";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(">> Password Generator <<");
        
        System.out.print("Enter password length: ");
        int length = sc.nextInt();

        if(length < 6) {
            System.out.println("Error: Password length must be >= 6");
            sc.close();
            return;
        }

        System.out.print("Include uppercase letters? (y/n): ");
        boolean useUpper = sc.next().equalsIgnoreCase("y");

        System.out.print("Include lowercase letters? (y/n): ");
        boolean useLower = sc.next().equalsIgnoreCase("y");

        System.out.print("Include digits? (y/n): ");
        boolean useDigits = sc.next().equalsIgnoreCase("y");

        System.out.print("Include symbols? (y/n): ");
        boolean useSymbol = sc.next().equalsIgnoreCase("y");

        try {   // Generate password and display strength
            String password = generatePassword(length, useUpper, useLower, useDigits, useSymbol);
            System.out.println("Generated Password: " + password);
            System.out.println("Password Strength: " + strength(password));
        }
         catch (IllegalArgumentException e) {   // Handle exception for invalid outputs
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
