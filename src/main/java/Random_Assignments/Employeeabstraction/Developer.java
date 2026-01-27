package Random_Assignments.Employeeabstraction;

/**
 * Developer class - represents a software developer employee
 * Bonus: 10% of salary
 */
public class Developer extends Employee {
    private String programmingLanguage;
    
    // Constructor
    public Developer(String name, int id, double salary, String programmingLanguage) {
        super(name, id, salary);
        this.programmingLanguage = programmingLanguage;
    }
    
    // Getter
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }
    
    // Setter
    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
    
    // Implementation of abstract method - 10% of salary
    @Override
    public double calculateBonus() {
        return getSalary() * 0.10;
    }
    
    // Override displayInfo to include programming language
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Programming Language: " + programmingLanguage);
        System.out.println("Position: Developer");
    }
}
