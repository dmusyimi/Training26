package Assessment2_DennisMusyimi.java;

import java.util.Scanner;


class Calculator {

    // Method Overloading - add() with different parameters

    // 1. Add two integers
    public int add(int a, int b) {
        return a + b;
    }

    // 2. Add two doubles
    public double add(double a, double b) {
        return a + b;
    }

    // 3. Add three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // 4. Concatenate two strings
    public String add(String a, String b) {
        return a + b;
    }

    // Additional overloaded methods for demonstration
    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    // Display calculator type
    public void displayInfo() {
        System.out.println("This is a basic Calculator");
    }
}

// Derived Class: ScientificCalculator
class ScientificCalculator extends Calculator {

    // Override add(int a, int b) to include a log message
    @Override
    public int add(int a, int b) {
        System.out.println("[LOG] ScientificCalculator: Adding " + a + " + " + b);
        int result = super.add(a, b);
        System.out.println("[LOG] Result: " + result);
        return result;
    }

    // Override add(double a, double b) with logging
    @Override
    public double add(double a, double b) {
        System.out.println("[LOG] ScientificCalculator: Adding " + a + " + " + b);
        double result = super.add(a, b);
        System.out.println("[LOG] Result: " + result);
        return result;
    }

    // New method: power
    public double power(double base, double exponent) {
        System.out.println("[LOG] Calculating " + base + "^" + exponent);
        double result = Math.pow(base, exponent);
        System.out.println("[LOG] Result: " + result);
        return result;
    }

    // New method: squareRoot
    public double squareRoot(double num) {
        if (num < 0) {
            throw new ArithmeticException("Cannot calculate square root of negative number");
        }
        System.out.println("[LOG] Calculating square root of " + num);
        double result = Math.sqrt(num);
        System.out.println("[LOG] Result: " + result);
        return result;
    }

    // Additional scientific methods
    public double sine(double angle) {
        return Math.sin(Math.toRadians(angle));
    }

    public double cosine(double angle) {
        return Math.cos(Math.toRadians(angle));
    }

    public double logarithm(double num) {
        if (num <= 0) {
            throw new ArithmeticException("Cannot calculate logarithm of non-positive number");
        }
        return Math.log(num);
    }

    // Override displayInfo
    @Override
    public void displayInfo() {
        System.out.println("This is a Scientific Calculator with advanced features");
    }
}

// Main class to demonstrate overloading and overriding
public class Quiz3_Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("====================================");
            System.out.println("  CALCULATOR DEMONSTRATION PROGRAM  ");
            System.out.println("====================================\n");

            // ===== PART 1: Demonstrate Method Overloading =====
            System.out.println("===== PART 1: METHOD OVERLOADING =====");
            System.out.println("Method Overloading: Multiple methods with the same name but different parameters\n");

            Calculator calc = new Calculator();
            calc.displayInfo();
            System.out.println();

            // Overloading Example 1: add(int, int)
            System.out.println("1. add(int a, int b):");
            System.out.print("   Enter first integer: ");
            int int1 = scanner.nextInt();
            System.out.print("   Enter second integer: ");
            int int2 = scanner.nextInt();
            System.out.println("   Result: " + calc.add(int1, int2));
            System.out.println();

            // Overloading Example 2: add(double, double)
            System.out.println("2. add(double a, double b):");
            System.out.print("   Enter first decimal: ");
            double dbl1 = scanner.nextDouble();
            System.out.print("   Enter second decimal: ");
            double dbl2 = scanner.nextDouble();
            System.out.println("   Result: " + calc.add(dbl1, dbl2));
            System.out.println();

            // Overloading Example 3: add(int, int, int)
            System.out.println("3. add(int a, int b, int c):");
            System.out.print("   Enter first integer: ");
            int int3 = scanner.nextInt();
            System.out.print("   Enter second integer: ");
            int int4 = scanner.nextInt();
            System.out.print("   Enter third integer: ");
            int int5 = scanner.nextInt();
            System.out.println("   Result: " + calc.add(int3, int4, int5));
            System.out.println();

            // Overloading Example 4: add(String, String)
            scanner.nextLine();
            System.out.println("4. add(String a, String b) - String concatenation:");
            System.out.print("   Enter first string: ");
            String str1 = scanner.nextLine();
            System.out.print("   Enter second string: ");
            String str2 = scanner.nextLine();
            System.out.println("   Result: " + calc.add(str1, str2));
            System.out.println();

            System.out.println("Notice: Same method name 'add()' but different parameter types!");
            System.out.println("This is METHOD OVERLOADING - resolved at COMPILE TIME\n");

            // ===== PART 2: Demonstrate Method Overriding =====
            System.out.println("\n===== PART 2: METHOD OVERRIDING =====");
            System.out.println("Method Overriding: Subclass provides specific implementation of parent's method\n");

            ScientificCalculator sciCalc = new ScientificCalculator();
            sciCalc.displayInfo();
            System.out.println();

            // Overriding Example 1: add(int, int) with logging
            System.out.println("1. Overridden add(int a, int b) with logging:");
            System.out.print("   Enter first integer: ");
            int over1 = scanner.nextInt();
            System.out.print("   Enter second integer: ");
            int over2 = scanner.nextInt();
            int overResult = sciCalc.add(over1, over2);
            System.out.println();

            // Overriding Example 2: add(double, double) with logging
            System.out.println("2. Overridden add(double a, double b) with logging:");
            System.out.print("   Enter first decimal: ");
            double over3 = scanner.nextDouble();
            System.out.print("   Enter second decimal: ");
            double over4 = scanner.nextDouble();
            double overResult2 = sciCalc.add(over3, over4);
            System.out.println();

            System.out.println("Notice: ScientificCalculator's add() includes logging!");
            System.out.println("This is METHOD OVERRIDING - resolved at RUNTIME\n");

            // ===== PART 3: New Methods in ScientificCalculator =====
            System.out.println("\n===== PART 3: SCIENTIFIC CALCULATOR FEATURES =====\n");

            // Power function
            System.out.println("1. Power Function:");
            System.out.print("   Enter base: ");
            double base = scanner.nextDouble();
            System.out.print("   Enter exponent: ");
            double exponent = scanner.nextDouble();
            double powerResult = sciCalc.power(base, exponent);
            System.out.println();

            // Square Root function
            System.out.println("2. Square Root Function:");
            System.out.print("   Enter number: ");
            double sqrtNum = scanner.nextDouble();
            double sqrtResult = sciCalc.squareRoot(sqrtNum);
            System.out.println();

            // ===== PART 4: Polymorphism Demonstration =====
            System.out.println("\n===== PART 4: POLYMORPHISM DEMONSTRATION =====\n");

            Calculator polyCalc1 = new Calculator();
            Calculator polyCalc2 = new ScientificCalculator(); // Upcasting

            System.out.println("Using Calculator reference with Calculator object:");
            polyCalc1.displayInfo();
            System.out.println("add(5, 3) = " + polyCalc1.add(5, 3));

            System.out.println("\nUsing Calculator reference with ScientificCalculator object:");
            polyCalc2.displayInfo(); // Calls overridden method
            System.out.println("add(5, 3) = " + polyCalc2.add(5, 3)); // Calls overridden method
            System.out.println();


        } catch (ArithmeticException e) {
            System.out.println("Mathematical Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}