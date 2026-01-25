package Assessment2_DennisMusyimi.java;

import java.util.Scanner;

// Base Class: BankAccount
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor with validation
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        setAccountNumber(accountNumber);
        setAccountHolder(accountHolder);
        setBalance(balance);
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // Setters with validation
    public void setAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        this.accountNumber = accountNumber;
    }

    public void setAccountHolder(String accountHolder) {
        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder cannot be null or empty");
        }
        this.accountHolder = accountHolder;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive");
            return;
        }
        balance += amount;
        System.out.println("Deposited: $" + String.format("%.2f", amount));
        System.out.println("New Balance: $" + String.format("%.2f", balance));
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds");
            return;
        }
        balance -= amount;
        System.out.println("Withdrawn: $" + String.format("%.2f", amount));
        System.out.println("New Balance: $" + String.format("%.2f", balance));
    }

    public void displayInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + String.format("%.2f", balance));
    }
}

// Child Class: SavingsAccount
class SavingsAccount extends BankAccount {
    private double interestRate;
    private static final double MINIMUM_BALANCE = 100.0;

    public SavingsAccount(String accountNumber, String accountHolder, double balance, double interestRate) {
        super(accountNumber, accountHolder, balance);
        setInterestRate(interestRate);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate < 0 || interestRate > 100) {
            throw new IllegalArgumentException("Interest rate must be between 0 and 100");
        }
        this.interestRate = interestRate;
    }

    // Override withdraw to maintain minimum balance
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
            return;
        }
        if (getBalance() - amount < MINIMUM_BALANCE) {
            System.out.println("Cannot withdraw. Minimum balance of $" +
                    String.format("%.2f", MINIMUM_BALANCE) + " must be maintained");
            return;
        }
        super.withdraw(amount);
    }

    // Apply interest method
    public void applyInterest() {
        double interest = getBalance() * (interestRate / 100);
        deposit(interest);
        System.out.println("Interest applied at " + interestRate + "%");
    }

    @Override
    public void displayInfo() {
        System.out.println("=== Savings Account ===");
        super.displayInfo();
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Minimum Balance: $" + String.format("%.2f", MINIMUM_BALANCE));
    }
}

// Child Class: CheckingAccount
class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String accountHolder, double balance, double overdraftLimit) {
        super(accountNumber, accountHolder, balance);
        setOverdraftLimit(overdraftLimit);
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }

    // Override withdraw to allow overdraft
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive");
            return;
        }
        if (amount > getBalance() + overdraftLimit) {
            System.out.println("Insufficient funds. Overdraft limit is $" +
                    String.format("%.2f", overdraftLimit));
            return;
        }

        double newBalance = getBalance() - amount;
        setBalance(Math.max(0, newBalance));

        if (newBalance < 0) {
            System.out.println("Withdrawn: $" + String.format("%.2f", amount));
            System.out.println("Overdraft used: $" + String.format("%.2f", Math.abs(newBalance)));
            System.out.println("New Balance: -$" + String.format("%.2f", Math.abs(newBalance)));
        } else {
            System.out.println("Withdrawn: $" + String.format("%.2f", amount));
            System.out.println("New Balance: $" + String.format("%.2f", newBalance));
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("=== Checking Account ===");
        super.displayInfo();
        System.out.println("Overdraft Limit: $" + String.format("%.2f", overdraftLimit));
    }
}

public class Quiz4_BankAccount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Create Savings Account
            System.out.print("Enter account number: ");
            String savAccNum = scanner.nextLine();
            System.out.print("Enter account holder name: ");
            String savHolder = scanner.nextLine();
            System.out.print("Enter initial balance: ");
            double savBalance = scanner.nextDouble();
            System.out.print("Enter interest rate (%): ");
            double interestRate = scanner.nextDouble();
            scanner.nextLine();

            SavingsAccount savings = new SavingsAccount(savAccNum, savHolder, savBalance, interestRate);
            savings.displayInfo();

            System.out.print("\nEnter deposit amount: ");
            double depositAmt = scanner.nextDouble();
            savings.deposit(depositAmt);

            System.out.print("Enter withdrawal amount: ");
            double withdrawAmt = scanner.nextDouble();
            savings.withdraw(withdrawAmt);

            System.out.println("\nApplying interest...");
            savings.applyInterest();

            scanner.nextLine();

            // Create Checking Account
            System.out.print("\nEnter account number: ");
            String checkAccNum = scanner.nextLine();
            System.out.print("Enter account holder name: ");
            String checkHolder = scanner.nextLine();
            System.out.print("Enter initial balance: ");
            double checkBalance = scanner.nextDouble();
            System.out.print("Enter overdraft limit: ");
            double overdraft = scanner.nextDouble();

            CheckingAccount checking = new CheckingAccount(checkAccNum, checkHolder, checkBalance, overdraft);
            System.out.println();
            checking.displayInfo();

            System.out.print("\nEnter deposit amount: ");
            double checkDeposit = scanner.nextDouble();
            checking.deposit(checkDeposit);

            System.out.print("Enter withdrawal amount: ");
            double checkWithdraw = scanner.nextDouble();
            checking.withdraw(checkWithdraw);

            // Demonstrate polymorphism
            System.out.println("\n=== Polymorphism Demo ===");
            BankAccount[] accounts = {savings, checking};

            for (BankAccount account : accounts) {
                account.displayInfo();
                System.out.println();
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}