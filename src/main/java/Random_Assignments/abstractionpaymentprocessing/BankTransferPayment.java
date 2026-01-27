package Random_Assignments.abstractionpaymentprocessing;

import java.time.LocalDate;

/**
 * BankTransferPayment class - represents a bank transfer payment
 */
public class BankTransferPayment extends Payment {
    private String accountNumber;
    private String bankName;
    
    // Constructor
    public BankTransferPayment(double amount, LocalDate paymentDate, 
                              String accountNumber, String bankName) {
        super(amount, paymentDate);
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }
    
    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getBankName() {
        return bankName;
    }
    
    // Setters
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    
    // Validate account number (basic validation - should be numeric and 10-12 digits)
    private boolean validateAccountNumber() {
        if (accountNumber == null || accountNumber.length() < 10 || accountNumber.length() > 12) {
            System.out.println("Invalid account number: Must be 10-12 digits");
            return false;
        }
        if (!accountNumber.matches("\\d+")) {
            System.out.println("Invalid account number: Must contain only digits");
            return false;
        }
        return true;
    }
    
    // Validate bank name
    private boolean validateBankName() {
        if (bankName == null || bankName.trim().isEmpty()) {
            System.out.println("Invalid bank name: Bank name cannot be empty");
            return false;
        }
        return true;
    }
    
    // Implementation of abstract method
    @Override
    public boolean processPayment() {
        System.out.println("\n--- Processing Bank Transfer Payment ---");
        
        // Validate amount
        if (!validateAmount()) {
            System.out.println("Payment FAILED: Invalid amount");
            return false;
        }
        
        // Validate account number
        if (!validateAccountNumber()) {
            System.out.println("Payment FAILED: Invalid account number");
            return false;
        }
        
        // Validate bank name
        if (!validateBankName()) {
            System.out.println("Payment FAILED: Invalid bank name");
            return false;
        }
        
        // Process payment
        System.out.println("Initiating bank transfer...");
        System.out.println("Bank: " + bankName);
        System.out.println("Account: ****" + accountNumber.substring(accountNumber.length() - 4));
        System.out.println("Transferring $" + String.format("%.2f", getAmount()));
        System.out.println("Payment SUCCESSFUL!");
        System.out.println("Transaction ID: BT-" + System.currentTimeMillis());
        System.out.println("Note: Bank transfer may take 1-3 business days to complete");
        
        return true;
    }
    
    // Implementation of abstract method
    @Override
    public String getPaymentDetails() {
        StringBuilder details = new StringBuilder();
        details.append("=== BANK TRANSFER PAYMENT DETAILS ===\n");
        details.append("Payment Method: Bank Transfer\n");
        details.append("Bank Name: ").append(bankName).append("\n");
        details.append("Account Number: ****").append(accountNumber.substring(accountNumber.length() - 4)).append("\n");
        details.append("Amount: $").append(String.format("%.2f", getAmount())).append("\n");
        details.append("Payment Date: ").append(getFormattedDate()).append("\n");
        details.append("======================================");
        return details.toString();
    }
}
