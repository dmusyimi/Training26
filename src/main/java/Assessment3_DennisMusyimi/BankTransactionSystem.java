package Assessment3_DennisMusyimi;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Custom Checked Exception for Insufficient Funds
class InsufficientFundsException extends Exception {
    private double currentBalance;
    private double requestedAmount;
    private String accountNumber;

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, double currentBalance, double requestedAmount) {
        super(message);
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
    }

    public InsufficientFundsException(String message, double currentBalance, double requestedAmount, String accountNumber) {
        super(message);
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
        this.accountNumber = accountNumber;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return super.toString() + 
               String.format(" [Account: %s, Balance: $%.2f, Requested: $%.2f]", 
                           accountNumber != null ? accountNumber : "N/A", currentBalance, requestedAmount);
    }
}

// Custom Unchecked Exception for Invalid Transactions
class InvalidTransactionException extends RuntimeException {
    private String transactionType;
    private double amount;
    private String reason;

    public InvalidTransactionException(String message) {
        super(message);
    }

    public InvalidTransactionException(String message, String transactionType) {
        super(message);
        this.transactionType = transactionType;
    }

    public InvalidTransactionException(String message, String transactionType, double amount) {
        super(message);
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public InvalidTransactionException(String message, String transactionType, double amount, String reason) {
        super(message);
        this.transactionType = transactionType;
        this.amount = amount;
        this.reason = reason;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        if (transactionType != null) {
            sb.append(" [Type: ").append(transactionType);
            if (amount != 0) {
                sb.append(", Amount: $").append(String.format("%.2f", amount));
            }
            if (reason != null) {
                sb.append(", Reason: ").append(reason);
            }
            sb.append("]");
        }
        return sb.toString();
    }
}

// Custom Checked Exception for Account Not Found
class AccountNotFoundException extends Exception {
    private String accountNumber;
    private String operation;

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String message, String accountNumber) {
        super(message);
        this.accountNumber = accountNumber;
    }

    public AccountNotFoundException(String message, String accountNumber, String operation) {
        super(message);
        this.accountNumber = accountNumber;
        this.operation = operation;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        if (accountNumber != null) {
            sb.append(" [Account: ").append(accountNumber);
            if (operation != null) {
                sb.append(", Operation: ").append(operation);
            }
            sb.append("]");
        }
        return sb.toString();
    }
}

// BankAccount Class with Robust Exception Handling
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constants for validation
    private static final double MIN_DEPOSIT = 0.01;
    private static final double MIN_WITHDRAWAL = 0.01;
    private static final double MAX_TRANSACTION = 50000.0;

    /**
     * Constructor for BankAccount
     */
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        validateAccountNumber(accountNumber);
        validateAccountHolder(accountHolder);
        validateInitialBalance(initialBalance);

        this.accountNumber = accountNumber.trim();
        this.accountHolder = accountHolder.trim();
        this.balance = roundAmount(initialBalance);
    }

    /**
     * Deposit money into the account
     */
    public void deposit(double amount) throws InvalidTransactionException {
        validateDepositAmount(amount);

        double roundedAmount = roundAmount(amount);
        balance += roundedAmount;

        System.out.printf("✓ Successfully deposited $%.2f to account %s%n", roundedAmount, accountNumber);
        System.out.printf("  New balance: $%.2f%n", balance);
    }

    /**
     * Withdraw money from the account
     */
    public void withdraw(double amount) throws InsufficientFundsException, InvalidTransactionException {
        validateWithdrawalAmount(amount);

        double roundedAmount = roundAmount(amount);

        if (roundedAmount > balance) {
            throw new InsufficientFundsException(
                String.format("Cannot withdraw $%.2f - insufficient funds", roundedAmount),
                balance, roundedAmount, accountNumber
            );
        }

        balance -= roundedAmount;

        System.out.printf("✓ Successfully withdrew $%.2f from account %s%n", roundedAmount, accountNumber);
        System.out.printf("  New balance: $%.2f%n", balance);
    }

    /**
     * Transfer money to another account
     */
    public void transfer(BankAccount toAccount, double amount) 
            throws InsufficientFundsException, InvalidTransactionException, AccountNotFoundException {

        validateTransferAccount(toAccount);
        validateTransferAmount(amount);

        double roundedAmount = roundAmount(amount);

        // Check if transferring to same account
        if (this.accountNumber.equals(toAccount.getAccountNumber())) {
            throw new InvalidTransactionException(
                "Cannot transfer to the same account", 
                "TRANSFER", 
                roundedAmount, 
                "Source and destination are identical"
            );
        }

        // Check for sufficient funds
        if (roundedAmount > balance) {
            throw new InsufficientFundsException(
                String.format("Cannot transfer $%.2f - insufficient funds", roundedAmount),
                balance, roundedAmount, accountNumber
            );
        }

        // Perform the transfer
        this.balance -= roundedAmount;
        toAccount.balance += roundedAmount;

        System.out.printf("✓ Successfully transferred $%.2f from %s to %s%n", 
                         roundedAmount, this.accountNumber, toAccount.getAccountNumber());
        System.out.printf("  Source balance: $%.2f, Destination balance: $%.2f%n", 
                         this.balance, toAccount.getBalance());
    }

    /**
     * Display account information
     */
    public void displayAccountInfo() {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("║         ACCOUNT DETAILS           ║");
        System.out.println("╠═══════════════════════════════════╣");
        System.out.printf("║ Number:  %-20s ║%n", accountNumber);
        System.out.printf("║ Holder:  %-20s ║%n", accountHolder);
        System.out.printf("║ Balance: $%-19.2f ║%n", balance);
        System.out.println("╚═══════════════════════════════════╝");
    }

    // Validation Methods
    private void validateAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new InvalidTransactionException(
                "Account number cannot be null or empty", 
                "ACCOUNT_CREATION"
            );
        }
        if (accountNumber.trim().length() < 3) {
            throw new InvalidTransactionException(
                "Account number must be at least 3 characters", 
                "ACCOUNT_CREATION"
            );
        }
    }

    private void validateAccountHolder(String accountHolder) {
        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new InvalidTransactionException(
                "Account holder name cannot be null or empty", 
                "ACCOUNT_CREATION"
            );
        }
        if (accountHolder.trim().length() < 2) {
            throw new InvalidTransactionException(
                "Account holder name must be at least 2 characters", 
                "ACCOUNT_CREATION"
            );
        }
    }

    private void validateInitialBalance(double initialBalance) {
        if (initialBalance < 0) {
            throw new InvalidTransactionException(
                "Initial balance cannot be negative", 
                "ACCOUNT_CREATION", 
                initialBalance
            );
        }
        if (Double.isNaN(initialBalance) || Double.isInfinite(initialBalance)) {
            throw new InvalidTransactionException(
                "Initial balance must be a valid number", 
                "ACCOUNT_CREATION", 
                initialBalance
            );
        }
    }

    private void validateDepositAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidTransactionException(
                "Deposit amount must be positive", 
                "DEPOSIT", 
                amount,
                "Amount must be greater than 0"
            );
        }
        if (amount < MIN_DEPOSIT) {
            throw new InvalidTransactionException(
                String.format("Minimum deposit is $%.2f", MIN_DEPOSIT), 
                "DEPOSIT", 
                amount
            );
        }
        if (amount > MAX_TRANSACTION) {
            throw new InvalidTransactionException(
                String.format("Maximum transaction is $%.2f", MAX_TRANSACTION), 
                "DEPOSIT", 
                amount
            );
        }
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new InvalidTransactionException(
                "Invalid deposit amount", 
                "DEPOSIT", 
                amount
            );
        }
    }

    private void validateWithdrawalAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidTransactionException(
                "Withdrawal amount must be positive", 
                "WITHDRAWAL", 
                amount
            );
        }
        if (amount < MIN_WITHDRAWAL) {
            throw new InvalidTransactionException(
                String.format("Minimum withdrawal is $%.2f", MIN_WITHDRAWAL), 
                "WITHDRAWAL", 
                amount
            );
        }
        if (amount > MAX_TRANSACTION) {
            throw new InvalidTransactionException(
                String.format("Maximum transaction is $%.2f", MAX_TRANSACTION), 
                "WITHDRAWAL", 
                amount
            );
        }
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new InvalidTransactionException(
                "Invalid withdrawal amount", 
                "WITHDRAWAL", 
                amount
            );
        }
    }

    private void validateTransferAccount(BankAccount toAccount) throws AccountNotFoundException {
        if (toAccount == null) {
            throw new AccountNotFoundException(
                "Destination account cannot be null", 
                "NULL_ACCOUNT", 
                "TRANSFER"
            );
        }
    }

    private void validateTransferAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidTransactionException(
                "Transfer amount must be positive", 
                "TRANSFER", 
                amount
            );
        }
        if (amount < MIN_WITHDRAWAL) {
            throw new InvalidTransactionException(
                String.format("Minimum transfer is $%.2f", MIN_WITHDRAWAL), 
                "TRANSFER", 
                amount
            );
        }
        if (amount > MAX_TRANSACTION) {
            throw new InvalidTransactionException(
                String.format("Maximum transaction is $%.2f", MAX_TRANSACTION), 
                "TRANSFER", 
                amount
            );
        }
        if (Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new InvalidTransactionException(
                "Invalid transfer amount", 
                "TRANSFER", 
                amount
            );
        }
    }

    private double roundAmount(double amount) {
        return BigDecimal.valueOf(amount)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();
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
}

public class BankTransactionSystem {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("        BANK TRANSACTION SYSTEM DEMO");
        System.out.println("     Comprehensive Exception Handling Testing");
        System.out.println("═══════════════════════════════════════════════════════\n");

        // Test 1: Successful Operations
        testSuccessfulOperations();

        // Test 2: Invalid Transaction Exceptions
        testInvalidTransactionExceptions();

        // Test 3: Insufficient Funds Exceptions
        testInsufficientFundsExceptions();

        // Test 4: Account Not Found Exceptions
        testAccountNotFoundExceptions();

        // Test 5: Account Creation Exceptions
        testAccountCreationExceptions();

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("           DEMONSTRATION COMPLETE");
        System.out.println("═══════════════════════════════════════════════════════");
    }

    private static void testSuccessfulOperations() {
        System.out.println("1. SUCCESSFUL OPERATIONS TEST");
        System.out.println("─────────────────────────────────────────────────────");

        try {
            // Create accounts
            BankAccount alice = new BankAccount("ACC001", "Alice Johnson", 1000.0);
            BankAccount bob = new BankAccount("ACC002", "Bob Smith", 500.0);

            System.out.println("Accounts created successfully!\n");

            alice.displayAccountInfo();
            System.out.println();
            bob.displayAccountInfo();
            System.out.println();

            // Perform operations
            alice.deposit(250.0);
            System.out.println();

            bob.withdraw(150.0);
            System.out.println();

            alice.transfer(bob, 300.0);
            System.out.println();

        } catch (Exception e) {
            System.err.println("❌ Unexpected error: " + e.getMessage());
        }

        System.out.println("─────────────────────────────────────────────────────\n");
    }

    private static void testInvalidTransactionExceptions() {
        System.out.println("2. INVALID TRANSACTION EXCEPTIONS TEST");
        System.out.println("─────────────────────────────────────────────────────");

        try {
            BankAccount testAccount = new BankAccount("TEST001", "Test User", 1000.0);

            // Test negative deposit
            System.out.println("Testing negative deposit...");
            try {
                testAccount.deposit(-100.0);
            } catch (InvalidTransactionException e) {
                System.err.println("❌ Caught: " + e.getMessage());
                System.err.println("   Details: " + e.toString() + "\n");
            }

            // Test zero withdrawal
            System.out.println("Testing zero withdrawal...");
            try {
                testAccount.withdraw(0.0);
            } catch (InvalidTransactionException | InsufficientFundsException e) {
                System.err.println("❌ Caught: " + e.getMessage() + "\n");
            }

            // Test transfer to same account
            System.out.println("Testing transfer to same account...");
            try {
                testAccount.transfer(testAccount, 100.0);
            } catch (InvalidTransactionException | InsufficientFundsException | AccountNotFoundException e) {
                System.err.println("❌ Caught: " + e.getMessage() + "\n");
            }

        } catch (Exception e) {
            System.err.println("Setup error: " + e.getMessage());
        }

        System.out.println("─────────────────────────────────────────────────────\n");
    }

    private static void testInsufficientFundsExceptions() {
        System.out.println("3. INSUFFICIENT FUNDS EXCEPTIONS TEST");
        System.out.println("─────────────────────────────────────────────────────");

        try {
            BankAccount poorAccount = new BankAccount("POOR001", "Poor User", 50.0);

            // Test insufficient funds for withdrawal
            System.out.println("Testing insufficient funds withdrawal (requesting $500 from $50 balance)...");
            try {
                poorAccount.withdraw(500.0);
            } catch (InsufficientFundsException e) {
                System.err.println("❌ Caught InsufficientFundsException: " + e.getMessage());
                System.err.println("   Balance: $" + e.getCurrentBalance() + 
                                 ", Requested: $" + e.getRequestedAmount() + 
                                 ", Account: " + e.getAccountNumber() + "\n");
            } catch (InvalidTransactionException e) {
                System.err.println("❌ Invalid transaction: " + e.getMessage() + "\n");
            }

            // Test insufficient funds for transfer
            System.out.println("Testing insufficient funds transfer...");
            BankAccount richAccount = new BankAccount("RICH001", "Rich User", 10000.0);
            try {
                poorAccount.transfer(richAccount, 1000.0);
            } catch (InsufficientFundsException e) {
                System.err.println("❌ Caught InsufficientFundsException: " + e.toString() + "\n");
            } catch (InvalidTransactionException | AccountNotFoundException e) {
                System.err.println("❌ Other exception: " + e.getMessage() + "\n");
            }

        } catch (Exception e) {
            System.err.println("Setup error: " + e.getMessage());
        }

        System.out.println("─────────────────────────────────────────────────────\n");
    }

    private static void testAccountNotFoundExceptions() {
        System.out.println("4. ACCOUNT NOT FOUND EXCEPTIONS TEST");
        System.out.println("─────────────────────────────────────────────────────");

        try {
            BankAccount validAccount = new BankAccount("VALID001", "Valid User", 500.0);

            // Test transfer to null account
            System.out.println("Testing transfer to null account...");
            try {
                validAccount.transfer(null, 100.0);
            } catch (AccountNotFoundException e) {
                System.err.println("❌ Caught AccountNotFoundException: " + e.getMessage());
                System.err.println("   Details: " + e.toString() + "\n");
            } catch (InsufficientFundsException | InvalidTransactionException e) {
                System.err.println("❌ Other exception: " + e.getMessage() + "\n");
            }

        } catch (Exception e) {
            System.err.println("Setup error: " + e.getMessage());
        }

        System.out.println("─────────────────────────────────────────────────────\n");
    }

    private static void testAccountCreationExceptions() {
        System.out.println("5. ACCOUNT CREATION EXCEPTIONS TEST");
        System.out.println("─────────────────────────────────────────────────────");

        // Test empty account number
        System.out.println("Testing empty account number...");
        try {
            new BankAccount("", "Valid Name", 100.0);
        } catch (InvalidTransactionException e) {
            System.err.println("❌ Caught: " + e.getMessage() + "\n");
        }

        // Test null account holder
        System.out.println("Testing null account holder...");
        try {
            new BankAccount("VALID001", null, 100.0);
        } catch (InvalidTransactionException e) {
            System.err.println("❌ Caught: " + e.getMessage() + "\n");
        }

        // Test negative initial balance
        System.out.println("Testing negative initial balance...");
        try {
            new BankAccount("VALID001", "Valid Name", -100.0);
        } catch (InvalidTransactionException e) {
            System.err.println("❌ Caught: " + e.getMessage());
            System.err.println("   Details: " + e.toString() + "\n");
        }

        // Test short account number
        System.out.println("Testing short account number...");
        try {
            new BankAccount("AB", "Valid Name", 100.0);
        } catch (InvalidTransactionException e) {
            System.err.println("❌ Caught: " + e.getMessage() + "\n");
        }

        System.out.println("─────────────────────────────────────────────────────\n");
    }
}
