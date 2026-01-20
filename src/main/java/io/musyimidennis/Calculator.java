package io.musyimidennis;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Calculator {
    private static Scanner scanner = new Scanner(System.in);

    // ============ MATHEMATICAL OPERATION FUNCTIONS ============

    /**
     * Function to perform addition
     * @param a - first number
     * @param b - second number
     * @return sum of a and b
     */
    public static double add(double a, double b) {
        return a + b;
    }

    /**
     * Function to perform subtraction
     * @param a - first number (minuend)
     * @param b - second number (subtrahend)
     * @return difference of a and b
     */
    public static double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Function to perform multiplication
     * @param a - first number
     * @param b - second number
     * @return product of a and b
     */
    public static double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Function to perform division
     * @param a - dividend
     * @param b - divisor
     * @return quotient of a divided by b, or NaN if b is zero
     */
    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return Double.NaN;
        }
        return a / b;
    }

    /**
     * Function to calculate power (exponentiation)
     * @param base - the base number
     * @param exponent - the power to raise the base to
     * @return base raised to the power of exponent
     */
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Function to calculate square root
     * @param number - the number to find square root of
     * @return square root of number, or NaN if number is negative
     */
    public static double squareRoot(double number) {
        if (number < 0) {
            System.out.println("Error: Cannot calculate square root of negative number!");
            return Double.NaN;
        }
        return Math.sqrt(number);
    }

    /**
     * Function to calculate modulus (remainder)
     * @param a - dividend
     * @param b - divisor
     * @return remainder when a is divided by b, or NaN if b is zero
     */
    public static double modulus(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot perform modulus with zero!");
            return Double.NaN;
        }
        return a % b;
    }

    // ============ INPUT FUNCTIONS ============

    /**
     * Function to get a single number from user
     * @param prompt - message to display to user
     * @return the number entered by user
     */
    public static double getNumber(String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }

    /**
     * Function to get two numbers from user
     * @return array containing two numbers [num1, num2]
     */
    public static double[] getTwoNumbers() {
        double[] numbers = new double[2];
        numbers[0] = getNumber("Enter first number: ");
        numbers[1] = getNumber("Enter second number: ");
        return numbers;
    }

    // ============ DISPLAY FUNCTIONS ============

    /**
     * Function to display the calculator menu
     */
    public static void displayMenu() {
        System.out.println("\n========== Calculator Menu ==========");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Power (^)");
        System.out.println("6. Square Root (√)");
        System.out.println("7. Modulus (%)");
        System.out.println("8. Exit");
        System.out.println("=====================================");
        System.out.print("Choose an operation: ");
    }

    /**
     * Function to display result with formatting
     * @param num1 - first operand
     * @param num2 - second operand
     * @param operator - operation symbol
     * @param result - calculated result
     */
    public static void displayResult(double num1, double num2, String operator, double result) {
        if (!Double.isNaN(result)) {
            System.out.printf("\nResult: %.2f %s %.2f = %.2f\n", num1, operator, num2, result);
        }
    }

    /**
     * Function to display single operand result
     * @param number - the operand
     * @param operation - description of operation
     * @param result - calculated result
     */
    public static void displaySingleResult(double number, String operation, double result) {
        if (!Double.isNaN(result)) {
            System.out.printf("\nResult: %s%.2f = %.2f\n", operation, number, result);
        }
    }

    // ============ OPERATION HANDLER FUNCTIONS ============

    /**
     * Function to handle addition operation
     * Gets two numbers, performs addition, and displays result
     */
    public static void handleAddition() {
        double[] numbers = getTwoNumbers();
        double result = add(numbers[0], numbers[1]);
        displayResult(numbers[0], numbers[1], "+", result);
    }

    /**
     * Function to handle subtraction operation
     * Gets two numbers, performs subtraction, and displays result
     */
    public static void handleSubtraction() {
        double[] numbers = getTwoNumbers();
        double result = subtract(numbers[0], numbers[1]);
        displayResult(numbers[0], numbers[1], "-", result);
    }

    /**
     * Function to handle multiplication operation
     * Gets two numbers, performs multiplication, and displays result
     */
    public static void handleMultiplication() {
        double[] numbers = getTwoNumbers();
        double result = multiply(numbers[0], numbers[1]);
        displayResult(numbers[0], numbers[1], "*", result);
    }

    /**
     * Function to handle division operation
     * Gets two numbers, performs division, and displays result
     */
    public static void handleDivision() {
        double num1 = getNumber("Enter dividend: ");
        double num2 = getNumber("Enter divisor: ");
        double result = divide(num1, num2);
        displayResult(num1, num2, "/", result);
    }

    /**
     * Function to handle power operation
     * Gets base and exponent, calculates power, and displays result
     */
    public static void handlePower() {
        double base = getNumber("Enter base: ");
        double exponent = getNumber("Enter exponent: ");
        double result = power(base, exponent);
        displayResult(base, exponent, "^", result);
    }

    /**
     * Function to handle square root operation
     * Gets a number, calculates square root, and displays result
     */
    public static void handleSquareRoot() {
        double number = getNumber("Enter number: ");
        double result = squareRoot(number);
        displaySingleResult(number, "√", result);
    }

    /**
     * Function to handle modulus operation
     * Gets two numbers, calculates remainder, and displays result
     */
    public static void handleModulus() {
        double[] numbers = getTwoNumbers();
        double result = modulus(numbers[0], numbers[1]);
        displayResult(numbers[0], numbers[1], "%", result);
    }

    // ============ MAIN CONTROL FUNCTION ============

    /**
     * Function to process user's menu choice
     * @param choice - the menu option selected by user
     * @return true if user wants to exit, false otherwise
     */
    public static boolean processChoice(int choice) {
        switch (choice) {
            case 1:
                handleAddition();
                return false;
            case 2:
                handleSubtraction();
                return false;
            case 3:
                handleMultiplication();
                return false;
            case 4:
                handleDivision();
                return false;
            case 5:
                handlePower();
                return false;
            case 6:
                handleSquareRoot();
                return false;
            case 7:
                handleModulus();
                return false;
            case 8:
                System.out.println("\nThank you for using the calculator. Goodbye!");
                return true;
            default:
                System.out.println("\nInvalid choice! Please select a valid option.");
                return false;
        }
    }

    /**
     * Main function to run the calculator program
     * Controls the program flow and menu loop
     */
    public static void runCalculator() {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║   Welcome to Simple Calculator    ║");
        System.out.println("╚═══════════════════════════════════╝");

        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            exit = processChoice(choice);
        }

        scanner.close();
    }

    // ============ MAIN METHOD ============

    /**
     * Entry point of the program
     * @param args - command line arguments (not used)
     */
    public static void main(String[] args) {
        runCalculator();
    }
}