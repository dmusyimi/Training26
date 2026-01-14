package io.musyimidennis;
import java.util.Scanner;
import java.util.HashMap;

class Account {
    private String accountNumber;
    private String name;
    private int age;
    private double balance;
    private int pin;
    private int withdrawalCount;
    private int inquiryCount;

    public Account(String accountNumber, String name, int age, double initialBalance, int pin) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.pin = pin;
        this.withdrawalCount = 0;
        this.inquiryCount = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPin() {
        return pin;
    }

    public int getWithdrawalCount() {
        return withdrawalCount;
    }

    public void incrementWithdrawalCount() {
        this.withdrawalCount++;
    }

    public void decrementWithdrawalCount() {
        this.withdrawalCount--;
    }

    public int getInquiryCount() {
        return inquiryCount;
    }

    public void incrementInquiryCount() {
        this.inquiryCount++;
    }
}

public class ATM {
    private static final double WITHDRAWAL_FEE = 2.50;
    private static final double BALANCE_INQUIRY_FEE = 0.50;
    private static final int FREE_WITHDRAWALS = 3;
    private static final int FREE_INQUIRIES = 5;
    private static final int MINIMUM_AGE = 18;

    private HashMap<String, Account> accounts;
    private Scanner scanner;
    private Account currentAccount;

    public ATM() {
        this.accounts = new HashMap<>();
        this.scanner = new Scanner(System.in);
        this.currentAccount = null;
    }

    public void registerAccount() {
        System.out.println("\n===== Account Registration =====");

        scanner.nextLine(); // Clear buffer

        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        if (age < MINIMUM_AGE) {
            System.out.println("\n❌ Registration Failed!");
            System.out.println("You must be at least " + MINIMUM_AGE + " years old to open an account.");
            System.out.println("Please visit our branch with a parent or guardian for a minor account.");
            return;
        }

        System.out.print("Enter initial deposit amount: $");
        double initialDeposit = scanner.nextDouble();

        if (initialDeposit < 100) {
            System.out.println("\n❌ Registration Failed!");
            System.out.println("Minimum initial deposit is $100.00");
            return;
        }

        System.out.print("Create a 4-digit PIN: ");
        int pin = scanner.nextInt();

        if (String.valueOf(pin).length() != 4) {
            System.out.println("\n❌ Registration Failed!");
            System.out.println("PIN must be exactly 4 digits.");
            return;
        }

        // Generate account number
        String accountNumber = "ACC" + (10000 + accounts.size() + 1);

        Account newAccount = new Account(accountNumber, name, age, initialDeposit, pin);
        accounts.put(accountNumber, newAccount);

        System.out.println("\n✓ Account Created Successfully!");
        System.out.println("================================");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + name);
        System.out.println("Age: " + age);
        System.out.printf("Initial Balance: $%.2f\n", initialDeposit);
        System.out.println("================================");
        System.out.println("Please save your account number for future login!");
    }

    public boolean login() {
        System.out.println("\n===== Login =====");
        scanner.nextLine(); // Clear buffer

        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (!accounts.containsKey(accountNumber)) {
            System.out.println("❌ Account not found!");
            return false;
        }

        Account account = accounts.get(accountNumber);

        System.out.print("Enter your PIN: ");
        int enteredPIN = scanner.nextInt();

        if (enteredPIN != account.getPin()) {
            System.out.println("❌ Incorrect PIN. Access denied.");
            return false;
        }

        currentAccount = account;
        System.out.println("\n✓ Login successful!");
        System.out.println("Welcome, " + currentAccount.getName() + "!");
        return true;
    }

    public void checkBalance() {
        System.out.println("\n--- Balance Inquiry ---");

        currentAccount.incrementInquiryCount();
        double fee = 0;

        if (currentAccount.getInquiryCount() > FREE_INQUIRIES) {
            fee = BALANCE_INQUIRY_FEE;
            currentAccount.setBalance(currentAccount.getBalance() - fee);
            System.out.printf("Transaction fee charged: $%.2f\n", fee);
        } else {
            System.out.printf("Free inquiry %d of %d\n",
                    currentAccount.getInquiryCount(), FREE_INQUIRIES);
        }

        System.out.printf("Your current balance: $%.2f\n", currentAccount.getBalance());
    }

    public void withdraw() {
        System.out.println("\n--- Withdrawal ---");
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();

        currentAccount.incrementWithdrawalCount();
        double fee = 0;

        if (currentAccount.getWithdrawalCount() > FREE_WITHDRAWALS) {
            fee = WITHDRAWAL_FEE;
            System.out.printf("Withdrawal fee: $%.2f\n", fee);
        } else {
            System.out.printf("Free withdrawal %d of %d (no fee)\n",
                    currentAccount.getWithdrawalCount(), FREE_WITHDRAWALS);
        }

        double totalAmount = amount + fee;

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            currentAccount.decrementWithdrawalCount();
        } else if (totalAmount > currentAccount.getBalance()) {
            System.out.printf("Insufficient funds. You need $%.2f (amount + fee) but your balance is $%.2f\n",
                    totalAmount, currentAccount.getBalance());
            currentAccount.decrementWithdrawalCount();
        } else {
            currentAccount.setBalance(currentAccount.getBalance() - totalAmount);
            System.out.printf("Withdrawal successful! You withdrew $%.2f\n", amount);
            if (fee > 0) {
                System.out.printf("Transaction fee deducted: $%.2f\n", fee);
            }
            System.out.printf("Remaining balance: $%.2f\n", currentAccount.getBalance());
        }
    }

    public void deposit() {
        System.out.println("\n--- Deposit ---");
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else {
            currentAccount.setBalance(currentAccount.getBalance() + amount);
            System.out.printf("Deposit successful! You deposited $%.2f\n", amount);
            System.out.printf("New balance: $%.2f\n", currentAccount.getBalance());
        }
    }

    public void viewTransactionFees() {
        System.out.println("\n--- Transaction Fee Information ---");
        System.out.printf("Withdrawal fee: $%.2f (after %d free withdrawals)\n",
                WITHDRAWAL_FEE, FREE_WITHDRAWALS);
        System.out.printf("Balance inquiry fee: $%.2f (after %d free inquiries)\n",
                BALANCE_INQUIRY_FEE, FREE_INQUIRIES);
        System.out.println("Deposit fee: FREE");
        System.out.println("\n--- Your Usage ---");
        System.out.printf("Withdrawals used: %d of %d free\n",
                Math.min(currentAccount.getWithdrawalCount(), FREE_WITHDRAWALS),
                FREE_WITHDRAWALS);
        System.out.printf("Balance inquiries used: %d of %d free\n",
                Math.min(currentAccount.getInquiryCount(), FREE_INQUIRIES),
                FREE_INQUIRIES);
    }

    public void showMainMenu() {
        System.out.println("\n======= ATM System =======");
        System.out.println("1. Register New Account");
        System.out.println("2. Login to Existing Account");
        System.out.println("3. Exit");
        System.out.println("==========================");
        System.out.print("Choose an option: ");
    }

    public void showAccountMenu() {
        System.out.println("\n======= ATM Menu =======");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. View Transaction Fees");
        System.out.println("5. Logout");
        System.out.println("========================");
        System.out.print("Choose an option: ");
    }

    public void start() {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║  Welcome to SecureBank ATM     ║");
        System.out.println("╚════════════════════════════════╝");

        boolean exit = false;

        while (!exit) {
            if (currentAccount == null) {
                showMainMenu();
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        registerAccount();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        System.out.println("\nThank you for using SecureBank ATM. Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("\nInvalid option. Please try again.");
                }
            } else {
                showAccountMenu();
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        viewTransactionFees();
                        break;
                    case 5:
                        System.out.println("\nLogging out...");
                        currentAccount = null;
                        break;
                    default:
                        System.out.println("\nInvalid option. Please try again.");
                }
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}