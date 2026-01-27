package Random_Assignments.Employeeabstraction;

/**
 * Abstract Employee class representing a general employee
 * with common attributes and behaviors
 */
public abstract class Employee {
    // Fields
    private String name;
    private int id;
    private double salary;
    
    // Constructor
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }
    
    public double getSalary() {
        return salary;
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract double calculateBonus();
    
    // Concrete method - displays employee information
    public void displayInfo() {
        System.out.println("Employee Information:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Salary: $" + String.format("%.2f", salary));
        System.out.println("Bonus: $" + String.format("%.2f", calculateBonus()));
        System.out.println("Total Compensation: $" + String.format("%.2f", salary + calculateBonus()));
    }
}
