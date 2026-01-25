package Assessment2_DennisMusyimi.java;

import java.util.Scanner;

// Base Class: Employee
class Employee {
    private String name;
    private String id;
    private double salary;

    // Constructor with parameters
    public Employee(String name, String id, double salary) {
        setName(name);
        setId(id);
        setSalary(salary);
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    // Setters with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.id = id;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }

    // calculateBonus method - returns 5% of salary
    public double calculateBonus() {
        return salary * 0.05;
    }

    // displayInfo method
    public void displayInfo() {
        System.out.println("Employee Information:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Salary: $" + String.format("%.2f", salary));
        System.out.println("Bonus: $" + String.format("%.2f", calculateBonus()));
    }
}

// Derived Class: Manager
class Manager extends Employee {
    private String department;
    private int teamSize;

    public Manager(String name, String id, double salary, String department, int teamSize) {
        super(name, id, salary);
        setDepartment(department);
        setTeamSize(teamSize);
    }

    public String getDepartment() {
        return department;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be null or empty");
        }
        this.department = department;
    }

    public void setTeamSize(int teamSize) {
        if (teamSize < 0) {
            throw new IllegalArgumentException("Team size cannot be negative");
        }
        this.teamSize = teamSize;
    }

    // Override calculateBonus - managers get 10% + 2% per team member
    @Override
    public double calculateBonus() {
        return getSalary() * 0.10 + (teamSize * getSalary() * 0.02);
    }

    @Override
    public void displayInfo() {
        System.out.println("Manager Information:");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Salary: $" + String.format("%.2f", getSalary()));
        System.out.println("Department: " + department);
        System.out.println("Team Size: " + teamSize);
        System.out.println("Bonus: $" + String.format("%.2f", calculateBonus()));
    }
}

// Derived Class: Developer
class Developer extends Employee {
    private String programmingLanguage;
    private int projectsCompleted;

    public Developer(String name, String id, double salary, String programmingLanguage, int projectsCompleted) {
        super(name, id, salary);
        setProgrammingLanguage(programmingLanguage);
        setProjectsCompleted(projectsCompleted);
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public int getProjectsCompleted() {
        return projectsCompleted;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        if (programmingLanguage == null || programmingLanguage.trim().isEmpty()) {
            throw new IllegalArgumentException("Programming language cannot be null or empty");
        }
        this.programmingLanguage = programmingLanguage;
    }

    public void setProjectsCompleted(int projectsCompleted) {
        if (projectsCompleted < 0) {
            throw new IllegalArgumentException("Projects completed cannot be negative");
        }
        this.projectsCompleted = projectsCompleted;
    }

    // Override calculateBonus - developers get 5% + $500 per project
    @Override
    public double calculateBonus() {
        return super.calculateBonus() + (projectsCompleted * 500);
    }

    @Override
    public void displayInfo() {
        System.out.println("Developer Information:");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Salary: $" + String.format("%.2f", getSalary()));
        System.out.println("Programming Language: " + programmingLanguage);
        System.out.println("Projects Completed: " + projectsCompleted);
        System.out.println("Bonus: $" + String.format("%.2f", calculateBonus()));
    }
}

// Derived Class: Intern
class Intern extends Employee {
    private String university;
    private int internshipDuration; // in months

    public Intern(String name, String id, double salary, String university, int internshipDuration) {
        super(name, id, salary);
        setUniversity(university);
        setInternshipDuration(internshipDuration);
    }

    public String getUniversity() {
        return university;
    }

    public int getInternshipDuration() {
        return internshipDuration;
    }

    public void setUniversity(String university) {
        if (university == null || university.trim().isEmpty()) {
            throw new IllegalArgumentException("University cannot be null or empty");
        }
        this.university = university;
    }

    public void setInternshipDuration(int internshipDuration) {
        if (internshipDuration <= 0) {
            throw new IllegalArgumentException("Internship duration must be positive");
        }
        this.internshipDuration = internshipDuration;
    }

    // Override calculateBonus - interns get 2% of salary
    @Override
    public double calculateBonus() {
        return getSalary() * 0.02;
    }

    @Override
    public void displayInfo() {
        System.out.println("Intern Information:");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Salary: $" + String.format("%.2f", getSalary()));
        System.out.println("University: " + university);
        System.out.println("Internship Duration: " + internshipDuration + " months");
        System.out.println("Bonus: $" + String.format("%.2f", calculateBonus()));
    }
}

// Main class to test the hierarchy
public class Quiz2_Employee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("=== Employee Management System ===");

            // Create base Employee
            System.out.println("--- Creating Employee ---");
            System.out.print("Enter employee name: ");
            String empName = scanner.nextLine();
            System.out.print("Enter employee ID: ");
            String empId = scanner.nextLine();
            System.out.print("Enter employee salary: ");
            double empSalary = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Employee employee = new Employee(empName, empId, empSalary);
            employee.displayInfo();

            // Create Manager
            System.out.println("\n--- Creating Manager ---");
            System.out.print("Enter manager name: ");
            String mgrName = scanner.nextLine();
            System.out.print("Enter manager ID: ");
            String mgrId = scanner.nextLine();
            System.out.print("Enter manager salary: ");
            double mgrSalary = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter department: ");
            String dept = scanner.nextLine();
            System.out.print("Enter team size: ");
            int teamSize = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Manager manager = new Manager(mgrName, mgrId, mgrSalary, dept, teamSize);
            System.out.println();
            manager.displayInfo();

            // Create Developer
            System.out.println("\n--- Creating Developer ---");
            System.out.print("Enter developer name: ");
            String devName = scanner.nextLine();
            System.out.print("Enter developer ID: ");
            String devId = scanner.nextLine();
            System.out.print("Enter developer salary: ");
            double devSalary = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter programming language: ");
            String progLang = scanner.nextLine();
            System.out.print("Enter projects completed: ");
            int projects = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Developer developer = new Developer(devName, devId, devSalary, progLang, projects);
            System.out.println();
            developer.displayInfo();

            // Create Intern
            System.out.println("\n--- Creating Intern ---");
            System.out.print("Enter intern name: ");
            String intName = scanner.nextLine();
            System.out.print("Enter intern ID: ");
            String intId = scanner.nextLine();
            System.out.print("Enter intern salary: ");
            double intSalary = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter university: ");
            String uni = scanner.nextLine();
            System.out.print("Enter internship duration (months): ");
            int duration = scanner.nextInt();

            Intern intern = new Intern(intName, intId, intSalary, uni, duration);
            System.out.println();
            intern.displayInfo();

            // Demonstrate polymorphism
            System.out.println("\n=== Polymorphism Demo ===");
            Employee[] employees = {employee, manager, developer, intern};
            double totalBonus = 0;

            for (Employee emp : employees) {
                totalBonus += emp.calculateBonus();
            }

            System.out.println("Total company bonus payout: $" + String.format("%.2f", totalBonus));

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}