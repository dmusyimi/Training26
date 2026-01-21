//DENNIS MUSYIMI//
package io.musyimidennis;
import java.util.Scanner;
public class Assessment1_DennisMusyimi {
    public static void main(String[] args) {
        // --QUIZ PROBLEM 1 VARIABLE OPERATIONS--//
        System.out.println("---VARIABLE OPERATIONS---");
        // Declare integers//
        int a = 15;
        int b = 4;
        int c = 7;

        //Calculate the sum of three numbers//
        int sum = a + b + c;
        System.out.println("The sum of a, b and c is: " + sum);
        //Calculate the product of a and b//
        int product = a * b;
        System.out.println("The product of a, b is:" + product);
        //Calculate the result of (a+b)*c //
        int equation = (a + b) * c;
        System.out.println("The result of the equation is: " + equation);
        //Calculate the reminder when a is divided by b //
        int remainder = (a % b);
        System.out.println("The remainder of a divided by b is: " + remainder);

        //---QUIZ PROBLEM 2  GRADE CALCULATOR---//
        System.out.println("---GRADE CALCULATOR---");
        System.out.print("Enter the Student's score (0-100): ");
        int score = new Scanner(System.in).nextInt();
        if (score < 0 || score > 100) {
            System.out.println("Please enter a score range of (0-100)");
        } else if (score >= 90 && score <= 100) {
            System.out.println("Grade: A");
        } else if (score >= 80 && score < 90) {
            System.out.println("Grade: B");
        } else if (score >= 70 && score < 80) {
            System.out.println("Grade: C");
        } else if (score >= 60 && score < 70) {
            System.out.println("Grade: D");
        } else {
            System.out.println("Grade: F");
        }

        //---QUIZ PROBLEM 3  NUMBER SEQUENCE---//
        System.out.println("---NUMBER SEQUENCE---");
        for (int i = 1; i <= 20; i++) {
            System.out.println(i);
            if (i % 5 == 0) {
                System.out.println("Multiple of 5");
            } else if (i % 2 == 0)
                System.out.println("Even");

            else {
                System.out.println("Odd");
            }

        }

       // PROBLEM 4: INPUT VALIDATION
        System.out.println("---INPUT VALIDATION---");
        Scanner scanner = new Scanner(System.in);
        int number;

        // Keep asking until a valid positive integer is entered//
        while (true) {
            System.out.print("Enter a positive integer: ");

            if (scanner.hasNextInt()) {
                number = scanner.nextInt();

                if (number > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive integer greater than 0.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }


        long factorial = 1;
        int i = 1;
        while (i <= number) {
            factorial *= i;
            i++;
        }

        System.out.println("The factorial of " + number + " is " + factorial);


        //PROBLEM 5 PATTERN PRINTING//
        System.out.println("---PATTERN PRINTING---");

        int rows = 5;

        // Nested for loops to print the pattern
        for (int k = 1; k <= rows; k++) {
            for (int j = 1; j <= k; j++) {
                System.out.print("*");
            }
            System.out.println();
            scanner.close();
        }
    }
}

