package Random_Assignments.BankAccount_Static;

/**
 * BankAccount class demonstrating static and instance members
 * Static members are shared across all instances
 * Instance members are unique to each object
 */
public class BankAccount {
    // Static Members (Class-level - shared by all instances)
    private static int totalAccounts = 0;
    private static final String BANK_NAME = "First Global Bank";
    private static double interestRate = 0.03;  // 3% default interest rate
    
    // Instance Members (Object-level - unique to each account)
    private int accountNumber;
    private String accountHolder;
    private double balance;
    
    // Constructor
    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        totalAccounts++;  // Increment static counter
        this.accountNumber = 1000 + totalAccounts;  // Generate unique account number
    }
    
    // Static Methods (operate on class-level data)
    
    /**
     * Get the total number of accounts created
     * @return total number of accounts
     */
    public static int getTotalAccounts() {
        return totalAccounts;
    }
    
    /**
     * Get the bank name
     * @return bank name
     */
    public static String getBankName() {
        return BANK_NAME;
    }
    
    /**
     * Set the interest rate for all accounts
     * @param newRate the new interest rate (as decimal, e.g., 0.05 for 5%)
     */
    public static void setInterestRate(double newRate) {
        if (newRate >= 0 && newRate <= 1.0) {
            interestRate = newRate;
            System.out.println("Interest rate updated to " + (newRate * 100) + "%");
        } else {
            System.out.println("Invalid interest rate. Must be between 0 and 1.0");
        }
    }
    
    /**
     * Get the current interest rate
     * @return current interest rate
     */
    public static double getInterestRate() {
        return interestRate;
    }
    
    // Instance Methods (operate on object-level data)
    
    /**
     * Deposit money into the account
     * @param amount amount to deposit
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + String.format("%.2f", amount));
            System.out.println("New balance: $" + String.format("%.2f", balance));
        } else {
            System.out.println("Invalid deposit amount. Must be positive.");
        }
    }
    
    /**
     * Withdraw money from the account
     * @param amount amount to withdraw
     * @return true if withdrawal successful, false otherwise
     */
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Must be positive.");
            return false;
        }
        
        if (amount > balance) {
            System.out.println("Insufficient funds. Available balance: $" + String.format("%.2f", balance));
            return false;
        }
        
        balance -= amount;
        System.out.println("Withdrawn: $" + String.format("%.2f", amount));
        System.out.println("New balance: $" + String.format("%.2f", balance));
        return true;
    }
    
    /**
     * Apply interest to the account balance
     * Uses the static interestRate
     */
    public void applyInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Interest applied: $" + String.format("%.2f", interest) + 
                         " at " + (interestRate * 100) + "% rate");
        System.out.println("New balance: $" + String.format("%.2f", balance));
    }
    
    // Instance Getters
    public int getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // Instance Setters
    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
    
    /**
     * Display complete account information
     */
    public void displayAccountInfo() {
        System.out.println("=====================================");
        System.out.println("Bank: " + BANK_NAME);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + String.format("%.2f", balance));
        System.out.println("Current Interest Rate: " + (interestRate * 100) + "%");
        System.out.println("=====================================");
    }
    
    /**
     * Display summary of account
     */
    @Override
    public String toString() {
        return "Account #" + accountNumber + " (" + accountHolder + "): $" + 
               String.format("%.2f", balance);
    }
}
