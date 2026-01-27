package Random_Assignments.Employeeabstraction;

/**
 * EmployeeTest - Demonstrates the Employee abstraction hierarchy
 */
public class EmployeeTest {
    public static void main(String[] args) {
        // Create a Manager
        Manager manager = new Manager("Alice Johnson", 101, 80000, "Engineering");
        
        // Create a Developer
        Developer developer = new Developer("Bob Smith", 102, 70000, "Java");
        
        // Create an Intern
        Intern intern = new Intern("Charlie Brown", 103, 25000, "MIT");
        
        // Display information for all employees
        System.out.println("=".repeat(50));
        manager.displayInfo();
        
        System.out.println("\n" + "=".repeat(50));
        developer.displayInfo();
        
        System.out.println("\n" + "=".repeat(50));
        intern.displayInfo();
        
        // Demonstrate polymorphism
        System.out.println("\n" + "=".repeat(50));
        System.out.println("POLYMORPHISM DEMONSTRATION:");
        System.out.println("=".repeat(50));
        
        Employee[] employees = {manager, developer, intern};
        
        double totalSalaries = 0;
        double totalBonuses = 0;
        
        for (Employee emp : employees) {
            totalSalaries += emp.getSalary();
            totalBonuses += emp.calculateBonus();
        }
        
        System.out.println("Total Salaries: $" + String.format("%.2f", totalSalaries));
        System.out.println("Total Bonuses: $" + String.format("%.2f", totalBonuses));
        System.out.println("Total Compensation: $" + String.format("%.2f", totalSalaries + totalBonuses));
    }
}
