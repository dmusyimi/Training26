package io.musyimidennis.Assignments;

import java.util.Scanner;

public class Age_Category {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();
        if (age >= 0 && age <= 2) {
            System.out.println("infant!");
        }else if (age>=3 && age<=12) {
            System.out.println("child!");
          } else if (age>=13 && age<=19) {
             System.out.println("Teenager!");
            } else if (age>=20 && age<=64) {
            System.out.println("Adult");
              } else if (age>=65){
            System.out.println("Senior ");



        }

    }


    }
