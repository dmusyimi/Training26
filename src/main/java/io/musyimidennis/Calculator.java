package io.musyimidennis;
import java.util.Scanner;

public class Calculator {
    private Scanner scanner;

    public Calculator() {
        this.scanner = new Scanner(System.in);
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return Double.NaN;
        }
        return a / b;
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public double squareRoot(double number) {
        if (number < 0) {
            System.out.println("Error: Cannot calculate square root of negative number!");
            return Double.NaN;
        }
        return Math.sqrt(number);
    }

    public double modulus(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot perform modulus with zero!");
            return Double.NaN;
        }
        return a % b;
    }

    public void showMenu() {
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

    public void start() {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║   Welcome to Simple Calculator    ║");
        System.out.println("╚═══════════════════════════════════╝");

        boolean exit = false;

        while (!exit) {
            showMenu();
            int choice = scanner.nextInt();

            double num1, num2, result;

            switch (choice) {
                case 1:
                    System.out.print("Enter first number: ");
                    num1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    num2 = scanner.nextDouble();
                    result = add(num1, num2);
                    System.out.printf("\nResult: %.2f + %.2f = %.2f\n", num1, num2, result);
                    break;

                case 2:
                    System.out.print("Enter first number: ");
                    num1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    num2 = scanner.nextDouble();
                    result = subtract(num1, num2);
                    System.out.printf("\nResult: %.2f - %.2f = %.2f\n", num1, num2, result);
                    break;

                case 3:
                    System.out.print("Enter first number: ");
                    num1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    num2 = scanner.nextDouble();
                    result = multiply(num1, num2);
                    System.out.printf("\nResult: %.2f * %.2f = %.2f\n", num1, num2, result);
                    break;

                case 4:
                    System.out.print("Enter dividend: ");
                    num1 = scanner.nextDouble();
                    System.out.print("Enter divisor: ");
                    num2 = scanner.nextDouble();
                    result = divide(num1, num2);
                    if (!Double.isNaN(result)) {
                        System.out.printf("\nResult: %.2f / %.2f = %.2f\n", num1, num2, result);
                    }
                    break;

                case 5:
                    System.out.print("Enter base: ");
                    num1 = scanner.nextDouble();
                    System.out.print("Enter exponent: ");
                    num2 = scanner.nextDouble();
                    result = power(num1, num2);
                    System.out.printf("\nResult: %.2f ^ %.2f = %.2f\n", num1, num2, result);
                    break;

                case 6:
                    System.out.print("Enter number: ");
                    num1 = scanner.nextDouble();
                    result = squareRoot(num1);
                    if (!Double.isNaN(result)) {
                        System.out.printf("\nResult: √%.2f = %.2f\n", num1, result);
                    }
                    break;

                case 7:
                    System.out.print("Enter first number: ");
                    num1 = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    num2 = scanner.nextDouble();
                    result = modulus(num1, num2);
                    if (!Double.isNaN(result)) {
                        System.out.printf("\nResult: %.2f %% %.2f = %.2f\n", num1, num2, result);
                    }
                    break;

                case 8:
                    System.out.println("\nThank you for using the calculator. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("\nInvalid choice! Please select a valid option.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.start();
    }
}