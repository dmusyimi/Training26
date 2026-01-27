package Random_Assignments.BankAccount_Static;

/**
 * BankAccountTest - Demonstrates static and instance members
 */
public class BankAccountTest {
    public static void main(String[] args) {
        System.out.println("===================================================");
        System.out.println("     BANK ACCOUNT SYSTEM - STATIC DEMO");
        System.out.println("===================================================\n");
        
        // Display initial bank information using static method
        System.out.println("Bank Name: " + BankAccount.getBankName());
        System.out.println("Initial Interest Rate: " + (BankAccount.getInterestRate() * 100) + "%");
        System.out.println("Total Accounts: " + BankAccount.getTotalAccounts());
        System.out.println();
        
        // Create first account
        System.out.println("--- Creating First Account ---");
        BankAccount account1 = new BankAccount("Alice Johnson", 1000.00);
        account1.displayAccountInfo();
        System.out.println("\nTotal Accounts after first account: " + BankAccount.getTotalAccounts());
        System.out.println();
        
        // Create second account
        System.out.println("--- Creating Second Account ---");
        BankAccount account2 = new BankAccount("Bob Smith", 2500.00);
        account2.displayAccountInfo();
        System.out.println("\nTotal Accounts after second account: " + BankAccount.getTotalAccounts());
        System.out.println();
        
        // Create third account
        System.out.println("--- Creating Third Account ---");
        BankAccount account3 = new BankAccount("Charlie Brown", 500.00);
        account3.displayAccountInfo();
        System.out.println("\nTotal Accounts after third account: " + BankAccount.getTotalAccounts());
        System.out.println("\n");
        
        // Demonstrate instance methods - deposit
        System.out.println("===================================================");
        System.out.println("     TESTING INSTANCE METHODS - DEPOSIT");
        System.out.println("===================================================");
        System.out.println("\n--- Alice deposits $500 ---");
        account1.deposit(500.00);
        System.out.println();
        
        // Demonstrate instance methods - withdraw
        System.out.println("--- Bob withdraws $1000 ---");
        account2.withdraw(1000.00);
        System.out.println();
        
        System.out.println("--- Charlie attempts to withdraw $1000 (insufficient funds) ---");
        account3.withdraw(1000.00);
        System.out.println("\n");
        
        // Apply interest with current rate (3%)
        System.out.println("===================================================");
        System.out.println("     APPLYING INTEREST AT 3% RATE");
        System.out.println("===================================================");
        System.out.println("\n--- Applying interest to Alice's account ---");
        account1.applyInterest();
        System.out.println();
        
        System.out.println("--- Applying interest to Bob's account ---");
        account2.applyInterest();
        System.out.println();
        
        System.out.println("--- Applying interest to Charlie's account ---");
        account3.applyInterest();
        System.out.println("\n");
        
        // Demonstrate static method - change interest rate
        System.out.println("===================================================");
        System.out.println("     CHANGING INTEREST RATE (STATIC METHOD)");
        System.out.println("===================================================");
        System.out.println("\n--- Bank changes interest rate to 5% ---");
        BankAccount.setInterestRate(0.05);
        System.out.println();
        
        // Apply new interest rate to all accounts
        System.out.println("--- Applying NEW 5% interest to all accounts ---");
        System.out.println("\nAlice's account:");
        account1.applyInterest();
        
        System.out.println("\nBob's account:");
        account2.applyInterest();
        
        System.out.println("\nCharlie's account:");
        account3.applyInterest();
        System.out.println("\n");
        
        // Display final state of all accounts
        System.out.println("===================================================");
        System.out.println("     FINAL ACCOUNT SUMMARY");
        System.out.println("===================================================");
        account1.displayAccountInfo();
        System.out.println();
        account2.displayAccountInfo();
        System.out.println();
        account3.displayAccountInfo();
        System.out.println();
        
        // Display overall statistics using static method
        System.out.println("===================================================");
        System.out.println("     OVERALL BANK STATISTICS");
        System.out.println("===================================================");
        System.out.println("Bank Name: " + BankAccount.getBankName());
        System.out.println("Total Accounts Created: " + BankAccount.getTotalAccounts());
        System.out.println("Current Interest Rate: " + (BankAccount.getInterestRate() * 100) + "%");
        
        // Calculate total deposits (using instance methods)
        double totalDeposits = account1.getBalance() + account2.getBalance() + account3.getBalance();
        System.out.println("Total Deposits in Bank: $" + String.format("%.2f", totalDeposits));
        System.out.println("===================================================");
        System.out.println();
        
        // Demonstrate creating more accounts
        System.out.println("--- Creating two more accounts ---");
        BankAccount account4 = new BankAccount("Diana Prince", 3000.00);
        BankAccount account5 = new BankAccount("Eve Adams", 1500.00);
        
        System.out.println(account4);
        System.out.println(account5);
        System.out.println("\nTotal Accounts NOW: " + BankAccount.getTotalAccounts());
        System.out.println();
        
        // Demonstrate that static changes affect all instances
        System.out.println("===================================================");
        System.out.println("     STATIC vs INSTANCE DEMONSTRATION");
        System.out.println("===================================================");
        System.out.println("Note: Static members (like interestRate) are SHARED");
        System.out.println("Instance members (like balance) are UNIQUE\n");
        
        System.out.println("Before rate change:");
        System.out.println("Account 4 interest rate: " + (BankAccount.getInterestRate() * 100) + "%");
        System.out.println("Account 5 interest rate: " + (BankAccount.getInterestRate() * 100) + "%");
        
        System.out.println("\nChanging interest rate to 7%...");
        BankAccount.setInterestRate(0.07);
        
        System.out.println("\nAfter rate change:");
        System.out.println("Account 4 interest rate: " + (BankAccount.getInterestRate() * 100) + "%");
        System.out.println("Account 5 interest rate: " + (BankAccount.getInterestRate() * 100) + "%");
        System.out.println("\nBoth accounts see the same rate because it's STATIC!");
        
        System.out.println("\nBut balances are different (INSTANCE members):");
        System.out.println("Account 4 balance: $" + String.format("%.2f", account4.getBalance()));
        System.out.println("Account 5 balance: $" + String.format("%.2f", account5.getBalance()));
        System.out.println("===================================================");
    }
}
