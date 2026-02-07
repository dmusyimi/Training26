package Random_Assignments;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// Student class
class Student {
    private int id;
    private String name;
    private double grade;

    // Constructor
    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    // toString method for easy display
    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-20s | Grade: %.2f", id, name, grade);
    }
}

// StudentManager class
class StudentManager {
    private ArrayList<Student> students;

    // Constructor
    public StudentManager() {
        students = new ArrayList<>();
    }

    // Add a student
    public boolean addStudent(Student student) {
        // Check if student ID already exists
        if (findStudent(student.getId()) != null) {
            System.out.println("Error: Student with ID " + student.getId() + " already exists!");
            return false;
        }
        students.add(student);
        System.out.println("Student added successfully!");
        return true;
    }

    // Remove student by ID
    public boolean removeStudent(int id) {
        Student student = findStudent(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student with ID " + id + " removed successfully!");
            return true;
        }
        System.out.println("Error: Student with ID " + id + " not found!");
        return false;
    }

    // Remove student by index
    public boolean removeStudentByIndex(int index) {
        if (index >= 0 && index < students.size()) {
            Student removed = students.remove(index);
            System.out.println("Removed: " + removed);
            return true;
        }
        System.out.println("Error: Invalid index!");
        return false;
    }

    // Find student by ID
    public Student findStudent(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Find students by name (partial match)
    public ArrayList<Student> findStudentsByName(String name) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(student);
            }
        }
        return result;
    }

    // Update student information
    public boolean updateStudent(int id, String newName, double newGrade) {
        Student student = findStudent(id);
        if (student != null) {
            student.setName(newName);
            student.setGrade(newGrade);
            System.out.println("Student updated successfully!");
            return true;
        }
        System.out.println("Error: Student not found!");
        return false;
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    STUDENT LIST");
        System.out.println("=".repeat(60));
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
        System.out.println("=".repeat(60));
        System.out.println("Total students: " + students.size());
    }

    // Display students with grade above a threshold
    public void displayStudentsAboveGrade(double threshold) {
        System.out.println("\nStudents with grade above " + threshold + ":");
        boolean found = false;
        for (Student student : students) {
            if (student.getGrade() > threshold) {
                System.out.println(student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students found.");
        }
    }

    // Get total number of students
    public int getTotalStudents() {
        return students.size();
    }

    // Calculate average grade
    public double calculateAverageGrade() {
        if (students.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (Student student : students) {
            sum += student.getGrade();
        }
        return sum / students.size();
    }

    // Get student at specific index
    public Student getStudentAt(int index) {
        if (index >= 0 && index < students.size()) {
            return students.get(index);
        }
        return null;
    }

    // Clear all students
    public void clearAllStudents() {
        students.clear();
        System.out.println("All students cleared from the system.");
    }

    // Check if list is empty
    public boolean isEmpty() {
        return students.isEmpty();
    }

    // Sort students by grade (descending)
    public void sortByGradeDescending() {
        students.sort((s1, s2) -> Double.compare(s2.getGrade(), s1.getGrade()));
        System.out.println("Students sorted by grade (highest to lowest).");
    }

    // Sort students by name
    public void sortByName() {
        students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        System.out.println("Students sorted by name (alphabetically).");
    }

    // Demonstrate iterator usage
    public void demonstrateIterator() {
        System.out.println("\nDemonstrating Iterator:");
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println(student);
        }
    }
}

// Main class to demonstrate the system
public class Studentmanagement_ArrayList {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        // Demonstrate various ArrayList operations
        System.out.println("===== STUDENT MANAGEMENT SYSTEM =====\n");

        // 1. Adding students (add operation)
        System.out.println("1. ADDING STUDENTS:");
        manager.addStudent(new Student(101, "Alice Johnson", 85.5));
        manager.addStudent(new Student(102, "Bob Smith", 92.0));
        manager.addStudent(new Student(103, "Charlie Brown", 78.5));
        manager.addStudent(new Student(104, "Diana Prince", 95.5));
        manager.addStudent(new Student(105, "Eve Davis", 88.0));

        // Try adding duplicate
        System.out.println("\nAttempting to add duplicate:");
        manager.addStudent(new Student(101, "Duplicate Student", 70.0));

        // 2. Display all students (iteration)
        System.out.println("\n2. DISPLAYING ALL STUDENTS:");
        manager.displayAllStudents();

        // 3. Find student (search operation)
        System.out.println("\n3. FINDING STUDENT BY ID:");
        Student found = manager.findStudent(103);
        if (found != null) {
            System.out.println("Found: " + found);
        }

        // 4. Find students by name
        System.out.println("\n4. FINDING STUDENTS BY NAME:");
        ArrayList<Student> searchResults = manager.findStudentsByName("a");
        System.out.println("Students with 'a' in their name:");
        for (Student s : searchResults) {
            System.out.println(s);
        }

        // 5. Update student
        System.out.println("\n5. UPDATING STUDENT:");
        manager.updateStudent(102, "Robert Smith", 94.0);
        manager.displayAllStudents();

        // 6. Calculate statistics
        System.out.println("\n6. STATISTICS:");
        System.out.println("Total students: " + manager.getTotalStudents());
        System.out.printf("Average grade: %.2f\n", manager.calculateAverageGrade());

        // 7. Filter students
        System.out.println("\n7. FILTERING STUDENTS:");
        manager.displayStudentsAboveGrade(90.0);

        // 8. Get student at index
        System.out.println("\n8. GETTING STUDENT BY INDEX:");
        Student studentAt2 = manager.getStudentAt(2);
        if (studentAt2 != null) {
            System.out.println("Student at index 2: " + studentAt2);
        }

        // 9. Sort operations
        System.out.println("\n9. SORTING OPERATIONS:");
        manager.sortByGradeDescending();
        manager.displayAllStudents();

        manager.sortByName();
        manager.displayAllStudents();

        // 10. Iterator demonstration
        System.out.println("\n10. ITERATOR DEMONSTRATION:");
        manager.demonstrateIterator();

        // 11. Remove student
        System.out.println("\n11. REMOVING STUDENT:");
        manager.removeStudent(103);
        manager.displayAllStudents();

        // 12. Check if empty
        System.out.println("\n12. CHECKING IF EMPTY:");
        System.out.println("Is empty? " + manager.isEmpty());

        // 13. Interactive menu (optional)
        System.out.println("\n\n===== INTERACTIVE MENU =====");
        boolean running = true;

        while (running) {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Find Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Calculate Average Grade");
            System.out.println("6. Sort by Grade");
            System.out.println("7. Sort by Name");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Grade: ");
                    double grade = scanner.nextDouble();
                    manager.addStudent(new Student(id, name, grade));
                    break;

                case 2:
                    System.out.print("Enter ID to remove: ");
                    int removeId = scanner.nextInt();
                    manager.removeStudent(removeId);
                    break;

                case 3:
                    System.out.print("Enter ID to find: ");
                    int findId = scanner.nextInt();
                    Student student = manager.findStudent(findId);
                    if (student != null) {
                        System.out.println("Found: " + student);
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 4:
                    manager.displayAllStudents();
                    break;

                case 5:
                    System.out.printf("Average Grade: %.2f\n", manager.calculateAverageGrade());
                    break;

                case 6:
                    manager.sortByGradeDescending();
                    manager.displayAllStudents();
                    break;

                case 7:
                    manager.sortByName();
                    manager.displayAllStudents();
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        scanner.close();
    }
}