package util;

import java.util.Scanner;

public class InputValidator {

    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null.");
        }
        this.scanner = scanner;
    }

    public int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public int readPositiveInt(String message) {
        while (true) {
            int number = readInt(message);

            if (number > 0) {
                return number;
            }

            System.out.println("Please enter a positive integer.");
        }
    }

    public String readNonEmptyString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public int readMenuChoice(String message, int min, int max) {
        while (true) {
            int choice = readInt(message);

            if (choice >= min && choice <= max) {
                return choice;
            }

            System.out.println(
                "Invalid choice. Enter a number between "
                + min + " and " + max + "."
            );
        }
    }
}

