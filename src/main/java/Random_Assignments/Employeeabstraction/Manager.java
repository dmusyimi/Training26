package Random_Assignments.Employeeabstraction;

/**
 * Manager class - represents a manager employee
 * Bonus: 15% of salary
 */
public class Manager extends Employee {
    private String department;
    
    // Constructor
    public Manager(String name, int id, double salary, String department) {
        super(name, id, salary);
        this.department = department;
    }
    
    // Getter
    public String getDepartment() {
        return department;
    }
    
    // Setter
    public void setDepartment(String department) {
        this.department = department;
    }
    
    // Implementation of abstract method - 15% of salary
    @Override
    public double calculateBonus() {
        return getSalary() * 0.15;
    }
    
    // Override displayInfo to include department
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Department: " + department);
        System.out.println("Position: Manager");
    }
}
