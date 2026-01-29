package Random_Assignments;

import java.util.Scanner;

public class Calculator {
    private static Scanner scanner = new Scanner(System.in);

    // Constants for overflow/underflow detection
    private static final double MAX_SAFE_VALUE = 1e308;
    private static final double MIN_SAFE_VALUE = -1e308;
    private static final double EPSILON = 1e-15;

    // ============ CUSTOM EXCEPTION CLASSES ============

    /**
     * Custom exception for division by zero
     */
    public static class DivisionByZeroException extends Exception {
        public DivisionByZeroException(String message) {
            super(message);
        }
    }

    /**
     * Custom exception for invalid number format
     */
    public static class InvalidNumberFormatException extends Exception {
        public InvalidNumberFormatException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * Custom exception for arithmetic overflow/underflow
     */
    public static class ArithmeticOverflowException extends Exception {
        public ArithmeticOverflowException(String message) {
            super(message);
        }
    }

    /**
     * Custom exception for invalid operator
     */
    public static class InvalidOperatorException extends Exception {
        public InvalidOperatorException(String message) {
            super(message);
        }
    }

    /**
     * Custom exception for business rule violations
     */
    public static class CalculatorBusinessRuleException extends Exception {
        public CalculatorBusinessRuleException(String message) {
            super(message);
        }
    }

    // ============ UTILITY METHODS FOR VALIDATION ============

    /**
     * Validates if a result causes overflow or underflow
     * @param result - the calculation result
     * @throws ArithmeticOverflowException if overflow/underflow detected
     */
    private static void validateResult(double result) throws ArithmeticOverflowException {
        if (Double.isInfinite(result)) {
            throw new ArithmeticOverflowException("Result caused arithmetic overflow/underflow: " + result);
        }
        if (Double.isNaN(result)) {
            throw new ArithmeticOverflowException("Result is not a valid number (NaN)");
        }
    }

    /**
     * Validates business rules for calculator operations
     * @param operation - the operation being performed
     * @param operands - the operands involved
     * @throws CalculatorBusinessRuleException if business rules violated
     */
    private static void validateBusinessRules(String operation, double... operands) throws CalculatorBusinessRuleException {
        // Business Rule 1: No operation with extremely large numbers (beyond safe range)
        for (double operand : operands) {
            if (Math.abs(operand) > MAX_SAFE_VALUE) {
                throw new CalculatorBusinessRuleException("Operand too large for safe calculation: " + operand);
            }
        }

        // Business Rule 2: Square root of negative numbers not allowed
        if ("sqrt".equals(operation) && operands.length > 0 && operands[0] < 0) {
            throw new CalculatorBusinessRuleException("Cannot calculate square root of negative number: " + operands[0]);
        }

        // Business Rule 3: Power operation with extremely large exponents
        if ("power".equals(operation) && operands.length >= 2) {
            if (Math.abs(operands[1]) > 1000) {
                throw new CalculatorBusinessRuleException("Exponent too large for safe calculation: " + operands[1]);
            }
        }
    }

    // ============ MATHEMATICAL OPERATION FUNCTIONS WITH EXCEPTION HANDLING ============

    /**
     * Function to perform addition with exception handling
     * @param a - first number
     * @param b - second number
     * @return sum of a and b
     * @throws ArithmeticOverflowException if result overflows
     * @throws CalculatorBusinessRuleException if business rules violated
     */
    public static double add(double a, double b) throws ArithmeticOverflowException, CalculatorBusinessRuleException {
        validateBusinessRules("add", a, b);

        // Check for potential overflow before calculation
        if ((a > 0 && b > 0 && a > MAX_SAFE_VALUE - b) ||
            (a < 0 && b < 0 && a < MIN_SAFE_VALUE - b)) {
            throw new ArithmeticOverflowException("Addition would cause overflow: " + a + " + " + b);
        }

        double result = a + b;
        validateResult(result);
        return result;
    }

    /**
     * Function to perform subtraction with exception handling
     * @param a - first number (minuend)
     * @param b - second number (subtrahend)
     * @return difference of a and b
     * @throws ArithmeticOverflowException if result overflows
     * @throws CalculatorBusinessRuleException if business rules violated
     */
    public static double subtract(double a, double b) throws ArithmeticOverflowException, CalculatorBusinessRuleException {
        validateBusinessRules("subtract", a, b);

        // Check for potential overflow before calculation
        if ((a > 0 && b < 0 && a > MAX_SAFE_VALUE + b) ||
            (a < 0 && b > 0 && a < MIN_SAFE_VALUE + b)) {
            throw new ArithmeticOverflowException("Subtraction would cause overflow: " + a + " - " + b);
        }

        double result = a - b;
        validateResult(result);
        return result;
    }

    /**
     * Function to perform multiplication with exception handling
     * @param a - first number
     * @param b - second number
     * @return product of a and b
     * @throws ArithmeticOverflowException if result overflows
     * @throws CalculatorBusinessRuleException if business rules violated
     */
    public static double multiply(double a, double b) throws ArithmeticOverflowException, CalculatorBusinessRuleException {
        validateBusinessRules("multiply", a, b);

        // Check for potential overflow before calculation
        if (a != 0 && b != 0 && Math.abs(a) > MAX_SAFE_VALUE / Math.abs(b)) {
            throw new ArithmeticOverflowException("Multiplication would cause overflow: " + a + " * " + b);
        }

        double result = a * b;
        validateResult(result);
        return result;
    }

    /**
     * Function to perform division with exception handling
     * @param a - dividend
     * @param b - divisor
     * @return quotient of a divided by b
     * @throws DivisionByZeroException if divisor is zero
     * @throws ArithmeticOverflowException if result overflows
     * @throws CalculatorBusinessRuleException if business rules violated
     */
    public static double divide(double a, double b) throws DivisionByZeroException, ArithmeticOverflowException, CalculatorBusinessRuleException {
        if (Math.abs(b) < EPSILON) {
            throw new DivisionByZeroException("Cannot divide by zero: " + a + " / " + b);
        }

        validateBusinessRules("divide", a, b);

        double result = a / b;
        validateResult(result);
        return result;
    }

    /**
     * Function to calculate power with exception handling
     * @param base - the base number
     * @param exponent - the power to raise the base to
     * @return base raised to the power of exponent
     * @throws ArithmeticOverflowException if result overflows
     * @throws CalculatorBusinessRuleException if business rules violated
     */
    public static double power(double base, double exponent) throws ArithmeticOverflowException, CalculatorBusinessRuleException {
        validateBusinessRules("power", base, exponent);

        double result = Math.pow(base, exponent);
        validateResult(result);
        return result;
    }

    /**
     * Function to calculate square root with exception handling
     * @param number - the number to find square root of
     * @return square root of number
     * @throws CalculatorBusinessRuleException if number is negative
     * @throws ArithmeticOverflowException if result is invalid
     */
    public static double squareRoot(double number) throws CalculatorBusinessRuleException, ArithmeticOverflowException {
        validateBusinessRules("sqrt", number);

        double result = Math.sqrt(number);
        validateResult(result);
        return result;
    }

    /**
     * Function to calculate modulus with exception handling
     * @param a - dividend
     * @param b - divisor
     * @return remainder when a is divided by b
     * @throws DivisionByZeroException if divisor is zero
     * @throws ArithmeticOverflowException if result is invalid
     * @throws CalculatorBusinessRuleException if business rules violated
     */
    public static double modulus(double a, double b) throws DivisionByZeroException, ArithmeticOverflowException, CalculatorBusinessRuleException {
        if (Math.abs(b) < EPSILON) {
            throw new DivisionByZeroException("Cannot perform modulus with zero divisor: " + a + " % " + b);
        }

        validateBusinessRules("modulus", a, b);

        double result = a % b;
        validateResult(result);
        return result;
    }

    // ============ INPUT FUNCTIONS WITH EXCEPTION HANDLING ============

    /**
     * Function to get a single number from user with exception handling
     * @param prompt - message to display to user
     * @return the number entered by user
     * @throws InvalidNumberFormatException if input is not a valid number
     */
    public static double getNumber(String prompt) throws InvalidNumberFormatException {
        System.out.print(prompt);
        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                throw new InvalidNumberFormatException("Empty input provided", new IllegalArgumentException());
            }
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new InvalidNumberFormatException("Invalid number format. Please enter a valid number.", e);
        }
    }

    /**
     * Function to get two numbers from user with exception handling
     * @return array containing two numbers [num1, num2]
     * @throws InvalidNumberFormatException if inputs are not valid numbers
     */
    public static double[] getTwoNumbers() throws InvalidNumberFormatException {
        double[] numbers = new double[2];
        numbers[0] = getNumber("Enter first number: ");
        numbers[1] = getNumber("Enter second number: ");
        return numbers;
    }

    /**
     * Function to get menu choice with exception handling
     * @return valid menu choice
     * @throws InvalidOperatorException if choice is not valid
     */
    public static int getMenuChoice() throws InvalidOperatorException {
        try {
            System.out.print("Choose an operation: ");
            String input = scanner.nextLine().trim();
            int choice = Integer.parseInt(input);

            if (choice < 1 || choice > 8) {
                throw new InvalidOperatorException("Invalid menu choice: " + choice + ". Please choose between 1-8.");
            }

            return choice;
        } catch (NumberFormatException e) {
            throw new InvalidOperatorException("Invalid input format. Please enter a number between 1-8.");
        }
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
    }

    /**
     * Function to display result with formatting
     * @param num1 - first operand
     * @param num2 - second operand
     * @param operator - operation symbol
     * @param result - calculated result
     */
    public static void displayResult(double num1, double num2, String operator, double result) {
        System.out.printf("\nResult: %.6f %s %.6f = %.6f\n", num1, operator, num2, result);
    }

    /**
     * Function to display single operand result
     * @param number - the operand
     * @param operation - description of operation
     * @param result - calculated result
     */
    public static void displaySingleResult(double number, String operation, double result) {
        System.out.printf("\nResult: %s%.6f = %.6f\n", operation, number, result);
    }

    /**
     * Function to display error messages in a formatted way
     * @param errorType - type of error
     * @param message - error message
     */
    public static void displayError(String errorType, String message) {
        System.err.println("\n❌ " + errorType + ": " + message);
    }

    // ============ OPERATION HANDLER FUNCTIONS WITH EXCEPTION HANDLING ============

    /**
     * Function to handle addition operation with exception handling
     */
    public static void handleAddition() {
        try {
            double[] numbers = getTwoNumbers();
            double result = add(numbers[0], numbers[1]);
            displayResult(numbers[0], numbers[1], "+", result);
        } catch (InvalidNumberFormatException e) {
            displayError("Invalid Input", e.getMessage());
        } catch (ArithmeticOverflowException e) {
            displayError("Arithmetic Overflow", e.getMessage());
        } catch (CalculatorBusinessRuleException e) {
            displayError("Business Rule Violation", e.getMessage());
        } catch (Exception e) {
            displayError("Unexpected Error", "An unexpected error occurred during addition: " + e.getMessage());
        }
    }

    /**
     * Function to handle subtraction operation with exception handling
     */
    public static void handleSubtraction() {
        try {
            double[] numbers = getTwoNumbers();
            double result = subtract(numbers[0], numbers[1]);
            displayResult(numbers[0], numbers[1], "-", result);
        } catch (InvalidNumberFormatException e) {
            displayError("Invalid Input", e.getMessage());
        } catch (ArithmeticOverflowException e) {
            displayError("Arithmetic Overflow", e.getMessage());
        } catch (CalculatorBusinessRuleException e) {
            displayError("Business Rule Violation", e.getMessage());
        } catch (Exception e) {
            displayError("Unexpected Error", "An unexpected error occurred during subtraction: " + e.getMessage());
        }
    }

    /**
     * Function to handle multiplication operation with exception handling
     */
    public static void handleMultiplication() {
        try {
            double[] numbers = getTwoNumbers();
            double result = multiply(numbers[0], numbers[1]);
            displayResult(numbers[0], numbers[1], "*", result);
        } catch (InvalidNumberFormatException e) {
            displayError("Invalid Input", e.getMessage());
        } catch (ArithmeticOverflowException e) {
            displayError("Arithmetic Overflow", e.getMessage());
        } catch (CalculatorBusinessRuleException e) {
            displayError("Business Rule Violation", e.getMessage());
        } catch (Exception e) {
            displayError("Unexpected Error", "An unexpected error occurred during multiplication: " + e.getMessage());
        }
    }

    /**
     * Function to handle division operation with exception handling
     */
    public static void handleDivision() {
        try {
            double num1 = getNumber("Enter dividend: ");
            double num2 = getNumber("Enter divisor: ");
            double result = divide(num1, num2);
            displayResult(num1, num2, "/", result);
        } catch (InvalidNumberFormatException e) {
            displayError("Invalid Input", e.getMessage());
        } catch (DivisionByZeroException e) {
            displayError("Division by Zero", e.getMessage());
        } catch (ArithmeticOverflowException e) {
            displayError("Arithmetic Overflow", e.getMessage());
        } catch (CalculatorBusinessRuleException e) {
            displayError("Business Rule Violation", e.getMessage());
        } catch (Exception e) {
            displayError("Unexpected Error", "An unexpected error occurred during division: " + e.getMessage());
        }
    }

    /**
     * Function to handle power operation with exception handling
     */
    public static void handlePower() {
        try {
            double base = getNumber("Enter base: ");
            double exponent = getNumber("Enter exponent: ");
            double result = power(base, exponent);
            displayResult(base, exponent, "^", result);
        } catch (InvalidNumberFormatException e) {
            displayError("Invalid Input", e.getMessage());
        } catch (ArithmeticOverflowException e) {
            displayError("Arithmetic Overflow", e.getMessage());
        } catch (CalculatorBusinessRuleException e) {
            displayError("Business Rule Violation", e.getMessage());
        } catch (Exception e) {
            displayError("Unexpected Error", "An unexpected error occurred during power calculation: " + e.getMessage());
        }
    }

    /**
     * Function to handle square root operation with exception handling
     */
    public static void handleSquareRoot() {
        try {
            double number = getNumber("Enter number: ");
            double result = squareRoot(number);
            displaySingleResult(number, "√", result);
        } catch (InvalidNumberFormatException e) {
            displayError("Invalid Input", e.getMessage());
        } catch (ArithmeticOverflowException e) {
            displayError("Arithmetic Error", e.getMessage());
        } catch (CalculatorBusinessRuleException e) {
            displayError("Business Rule Violation", e.getMessage());
        } catch (Exception e) {
            displayError("Unexpected Error", "An unexpected error occurred during square root calculation: " + e.getMessage());
        }
    }

    /**
     * Function to handle modulus operation with exception handling
     */
    public static void handleModulus() {
        try {
            double[] numbers = getTwoNumbers();
            double result = modulus(numbers[0], numbers[1]);
            displayResult(numbers[0], numbers[1], "%", result);
        } catch (InvalidNumberFormatException e) {
            displayError("Invalid Input", e.getMessage());
        } catch (DivisionByZeroException e) {
            displayError("Division by Zero", e.getMessage());
        } catch (ArithmeticOverflowException e) {
            displayError("Arithmetic Overflow", e.getMessage());
        } catch (CalculatorBusinessRuleException e) {
            displayError("Business Rule Violation", e.getMessage());
        } catch (Exception e) {
            displayError("Unexpected Error", "An unexpected error occurred during modulus calculation: " + e.getMessage());
        }
    }

    // ============ MAIN CONTROL FUNCTION WITH EXCEPTION HANDLING ============

    /**
     * Function to process user's menu choice with exception handling
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
                System.out.println("\n✅ Thank you for using the calculator. Goodbye!");
                return true;
            default:
                displayError("Invalid Choice", "Please select a valid option (1-8).");
                return false;
        }
    }

    /**
     * Main function to run the calculator program with comprehensive exception handling
     */
    public static void runCalculator() {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║   Welcome to Enhanced Calculator v2.0     ║");
        System.out.println("║   With Comprehensive Exception Handling   ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        boolean exit = false;

        while (!exit) {
            try {
                displayMenu();
                int choice = getMenuChoice();
                exit = processChoice(choice);
            } catch (InvalidOperatorException e) {
                displayError("Invalid Operator", e.getMessage());
            } catch (Exception e) {
                displayError("System Error", "An unexpected system error occurred: " + e.getMessage());
                System.out.println("Please try again or contact support if the problem persists.");
            }
        }

        scanner.close();
    }

    // ============ MAIN METHOD ============

    /**
     * Entry point of the program
     * @param args - command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            runCalculator();
        } catch (Exception e) {
            System.err.println("💥 Critical Error: Failed to start calculator - " + e.getMessage());
            e.printStackTrace();
        }
    }
}