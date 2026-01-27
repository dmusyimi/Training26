package Random_Assignments.Employeeabstraction;

/**
 * Intern class - represents an intern employee
 * Bonus: Fixed $500
 */
public class Intern extends Employee {
    private String university;
    
    // Constructor
    public Intern(String name, int id, double salary, String university) {
        super(name, id, salary);
        this.university = university;
    }
    
    // Getter
    public String getUniversity() {
        return university;
    }
    
    // Setter
    public void setUniversity(String university) {
        this.university = university;
    }
    
    // Implementation of abstract method - Fixed $500 bonus
    @Override
    public double calculateBonus() {
        return 500.0;
    }
    
    // Override displayInfo to include university
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("University: " + university);
        System.out.println("Position: Intern");
    }
}
