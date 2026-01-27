package Random_Assignments.BankAccount_Static;

/**
 * BankAccountAdvancedDemo - Shows advanced concepts and edge cases
 */
public class BankAccountAdvancedDemo {
    public static void main(String[] args) {
        System.out.println("===================================================");
        System.out.println("     ADVANCED STATIC MEMBERS DEMONSTRATION");
        System.out.println("===================================================\n");
        
        // 1. Accessing static members before creating any instances
        System.out.println("1. ACCESSING STATIC MEMBERS BEFORE INSTANCES");
        System.out.println("---------------------------------------------------");
        System.out.println("Can access static members without creating objects!");
        System.out.println("Bank Name: " + BankAccount.getBankName());
        System.out.println("Current Interest Rate: " + (BankAccount.getInterestRate() * 100) + "%");
        System.out.println("Total Accounts: " + BankAccount.getTotalAccounts());
        System.out.println();
        
        // 2. Static method can be called on class or instance (but class is preferred)
        System.out.println("2. CALLING STATIC METHODS");
        System.out.println("---------------------------------------------------");
        BankAccount acc1 = new BankAccount("John Doe", 1000);
        
        System.out.println("✓ PREFERRED: BankAccount.getTotalAccounts() = " + 
                         BankAccount.getTotalAccounts());
        System.out.println("✓ WORKS BUT NOT PREFERRED: acc1.getTotalAccounts() = " + 
                         acc1.getTotalAccounts());
        System.out.println("(Both return the same value because it's static)");
        System.out.println();
        
        // 3. Static variables are shared across ALL instances
        System.out.println("3. STATIC VARIABLES ARE SHARED");
        System.out.println("---------------------------------------------------");
        BankAccount acc2 = new BankAccount("Jane Smith", 2000);
        BankAccount acc3 = new BankAccount("Mike Wilson", 3000);
        
        System.out.println("Created 3 accounts total");
        System.out.println("acc1 sees totalAccounts: " + BankAccount.getTotalAccounts());
        System.out.println("acc2 sees totalAccounts: " + BankAccount.getTotalAccounts());
        System.out.println("acc3 sees totalAccounts: " + BankAccount.getTotalAccounts());
        System.out.println("All see the SAME value!");
        System.out.println();
        
        // 4. Changing static variable affects all instances
        System.out.println("4. CHANGING STATIC VARIABLE AFFECTS ALL");
        System.out.println("---------------------------------------------------");
        System.out.println("Before rate change:");
        System.out.println("Interest rate for acc1: " + (BankAccount.getInterestRate() * 100) + "%");
        System.out.println("Interest rate for acc2: " + (BankAccount.getInterestRate() * 100) + "%");
        System.out.println("Interest rate for acc3: " + (BankAccount.getInterestRate() * 100) + "%");
        
        System.out.println("\nChanging rate to 4%...");
        BankAccount.setInterestRate(0.04);
        
        System.out.println("\nAfter rate change:");
        System.out.println("Interest rate for acc1: " + (BankAccount.getInterestRate() * 100) + "%");
        System.out.println("Interest rate for acc2: " + (BankAccount.getInterestRate() * 100) + "%");
        System.out.println("Interest rate for acc3: " + (BankAccount.getInterestRate() * 100) + "%");
        System.out.println("ALL accounts now use the new rate!");
        System.out.println();
        
        // 5. Instance variables are unique to each object
        System.out.println("5. INSTANCE VARIABLES ARE UNIQUE");
        System.out.println("---------------------------------------------------");
        acc1.deposit(500);
        System.out.println("\nAfter depositing $500 to acc1:");
        System.out.println("acc1 balance: $" + String.format("%.2f", acc1.getBalance()));
        System.out.println("acc2 balance: $" + String.format("%.2f", acc2.getBalance()));
        System.out.println("acc3 balance: $" + String.format("%.2f", acc3.getBalance()));
        System.out.println("Only acc1's balance changed!");
        System.out.println();
        
        // 6. Static final (constant) cannot be changed
        System.out.println("6. STATIC FINAL CONSTANTS");
        System.out.println("---------------------------------------------------");
        System.out.println("Bank Name (static final): " + BankAccount.getBankName());
        System.out.println("This cannot be changed - it's a CONSTANT");
        System.out.println("Trying to change it would cause a compilation error");
        System.out.println();
        
        // 7. Practical use case - applying interest to all accounts
        System.out.println("7. PRACTICAL USE - BATCH INTEREST APPLICATION");
        System.out.println("---------------------------------------------------");
        BankAccount[] accounts = {acc1, acc2, acc3};
        
        System.out.println("Setting interest rate to 6% for year-end bonus...");
        BankAccount.setInterestRate(0.06);
        
        System.out.println("\nApplying interest to all accounts:");
        double totalInterestPaid = 0;
        
        for (int i = 0; i < accounts.length; i++) {
            double balanceBefore = accounts[i].getBalance();
            System.out.println("\nAccount " + (i + 1) + ":");
            accounts[i].applyInterest();
            double interestPaid = accounts[i].getBalance() - balanceBefore;
            totalInterestPaid += interestPaid;
        }
        
        System.out.println("\nTotal interest paid by bank: $" + 
                         String.format("%.2f", totalInterestPaid));
        System.out.println();
        
        // 8. Edge case testing
        System.out.println("8. EDGE CASE TESTING");
        System.out.println("---------------------------------------------------");
        
        System.out.println("Attempting invalid operations:\n");
        
        System.out.println("a) Invalid deposit (negative amount):");
        acc1.deposit(-100);
        
        System.out.println("\nb) Invalid withdrawal (exceeds balance):");
        acc1.withdraw(100000);
        
        System.out.println("\nc) Invalid interest rate (negative):");
        BankAccount.setInterestRate(-0.05);
        
        System.out.println("\nd) Invalid interest rate (> 100%):");
        BankAccount.setInterestRate(1.5);
        
        System.out.println();
        
        // 9. Summary
        System.out.println("===================================================");
        System.out.println("     KEY TAKEAWAYS");
        System.out.println("===================================================");
        System.out.println("STATIC MEMBERS:");
        System.out.println("  ✓ Shared across ALL instances");
        System.out.println("  ✓ Can be accessed without creating an instance");
        System.out.println("  ✓ Belong to the CLASS, not individual objects");
        System.out.println("  ✓ Use for: counters, shared config, utilities");
        System.out.println();
        System.out.println("INSTANCE MEMBERS:");
        System.out.println("  ✓ Unique to each object");
        System.out.println("  ✓ Require an instance to access");
        System.out.println("  ✓ Belong to individual OBJECTS");
        System.out.println("  ✓ Use for: object-specific data");
        System.out.println();
        System.out.println("STATIC FINAL:");
        System.out.println("  ✓ Constants that never change");
        System.out.println("  ✓ Shared across all instances");
        System.out.println("  ✓ Use UPPERCASE naming convention");
        System.out.println("===================================================");
        
        System.out.println("\nFinal Statistics:");
        System.out.println("Bank: " + BankAccount.getBankName());
        System.out.println("Total Accounts: " + BankAccount.getTotalAccounts());
        System.out.println("Current Rate: " + (BankAccount.getInterestRate() * 100) + "%");
    }
}
