package Random_Assignments;

import java.util.Scanner;

public class practice_ex2_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a Name: ");
       String name = scanner.nextLine();

        System.out.println("Enter a Age:  " );
        int age = scanner.nextInt();

        System.out.println("Please enter your GPA:");
       float gpa = scanner.nextFloat();

        System.out.println("Is Graduating(True/False) : " );
        boolean graduating = scanner.nextBoolean();

        System.out.println("=====Students Information====");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
        System.out.println("Graduating: " + graduating);

        scanner.close();
    }
}
