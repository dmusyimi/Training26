package io.musyimidennis;
import java.util.Scanner;

public class ATM {
    private double balance;
    private int pin;
    private Scanner scanner;

    // Transaction charges
    private static final double WITHDRAWAL_FEE = 2.50;
    private static final double BALANCE_INQUIRY_FEE = 0.50;
    private static final int FREE_WITHDRAWALS = 3;
    private static final int FREE_INQUIRIES = 5;

    private int withdrawalCount;
    private int inquiryCount;

    public ATM(double initialBalance, int pin) {
        this.balance = initialBalance;
        this.pin = pin;
        this.scanner = new Scanner(System.in);
        this.withdrawalCount = 0;
        this.inquiryCount = 0;
    }

    public boolean verifyPIN() {
        System.out.print("Enter your PIN: ");
        int enteredPIN = scanner.nextInt();
        return enteredPIN == pin;
    }

    public void checkBalance() {
        System.out.println("\n--- Balance Inquiry ---");

        inquiryCount++;
        double fee = 0;

        if (inquiryCount > FREE_INQUIRIES) {
            fee = BALANCE_INQUIRY_FEE;
            balance -= fee;
            System.out.printf("Transaction fee charged: $%.2f\n", fee);
        } else {
            System.out.printf("Free inquiry %d of %d\n", inquiryCount, FREE_INQUIRIES);
        }

        System.out.printf("Your current balance: $%.2f\n", balance);
    }

    public void withdraw() {
        System.out.println("\n--- Withdrawal ---");
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();

        withdrawalCount++;
        double fee = 0;

        if (withdrawalCount > FREE_WITHDRAWALS) {
            fee = WITHDRAWAL_FEE;
            System.out.printf("Withdrawal fee: $%.2f\n", fee);
        } else {
            System.out.printf("Free withdrawal %d of %d (no fee)\n", withdrawalCount, FREE_WITHDRAWALS);
        }

        double totalAmount = amount + fee;

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            withdrawalCount--; // Don't count invalid attempts
        } else if (totalAmount > balance) {
            System.out.printf("Insufficient funds. You need $%.2f (amount + fee) but your balance is $%.2f\n",
                    totalAmount, balance);
            withdrawalCount--; // Don't count failed attempts
        } else {
            balance -= totalAmount;
            System.out.printf("Withdrawal successful! You withdrew $%.2f\n", amount);
            if (fee > 0) {
                System.out.printf("Transaction fee deducted: $%.2f\n", fee);
            }
            System.out.printf("Remaining balance: $%.2f\n", balance);
        }
    }

    public void deposit() {
        System.out.println("\n--- Deposit ---");
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else {
            balance += amount;
            System.out.printf("Deposit successful! You deposited $%.2f\n", amount);
            System.out.printf("New balance: $%.2f\n", balance);
        }
    }

    public void showMenu() {
        System.out.println("\n======= ATM Menu =======");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. View Transaction Fees");
        System.out.println("5. Exit");
        System.out.println("========================");
        System.out.print("Choose an option: ");
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
                Math.min(withdrawalCount, FREE_WITHDRAWALS), FREE_WITHDRAWALS);
        System.out.printf("Balance inquiries used: %d of %d free\n",
                Math.min(inquiryCount, FREE_INQUIRIES), FREE_INQUIRIES);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");

        if (!verifyPIN()) {
            System.out.println("Incorrect PIN. Access denied.");
            return;
        }

        System.out.println("PIN verified successfully!\n");

        boolean exit = false;
        while (!exit) {
            showMenu();
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
                    System.out.println("\nThank you for using our ATM. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Create ATM with initial balance of $1000 and PIN 1234
        ATM atm = new ATM(1000.00, 1234);
        atm.start();
    }
}