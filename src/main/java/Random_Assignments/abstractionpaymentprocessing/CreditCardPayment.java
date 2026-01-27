package Random_Assignments.abstractionpaymentprocessing;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * CreditCardPayment class - represents a credit card payment
 */
public class CreditCardPayment extends Payment {
    private String cardNumber;
    private YearMonth expiryDate;
    
    // Constructor
    public CreditCardPayment(double amount, LocalDate paymentDate, 
                            String cardNumber, YearMonth expiryDate) {
        super(amount, paymentDate);
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }
    
    // Getters
    public String getCardNumber() {
        return cardNumber;
    }
    
    public YearMonth getExpiryDate() {
        return expiryDate;
    }
    
    // Setters
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    public void setExpiryDate(YearMonth expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    // Validate card number (basic validation - should have 16 digits)
    private boolean validateCardNumber() {
        if (cardNumber == null || cardNumber.replaceAll("\\s|-", "").length() != 16) {
            System.out.println("Invalid card number: Must be 16 digits");
            return false;
        }
        return true;
    }
    
    // Validate expiry date
    private boolean validateExpiryDate() {
        YearMonth currentMonth = YearMonth.now();
        if (expiryDate.isBefore(currentMonth)) {
            System.out.println("Invalid expiry date: Card has expired");
            return false;
        }
        return true;
    }
    
    // Implementation of abstract method
    @Override
    public boolean processPayment() {
        System.out.println("\n--- Processing Credit Card Payment ---");
        
        // Validate amount
        if (!validateAmount()) {
            System.out.println("Payment FAILED: Invalid amount");
            return false;
        }
        
        // Validate card number
        if (!validateCardNumber()) {
            System.out.println("Payment FAILED: Invalid card number");
            return false;
        }
        
        // Validate expiry date
        if (!validateExpiryDate()) {
            System.out.println("Payment FAILED: Card expired");
            return false;
        }
        
        // Process payment
        System.out.println("Contacting payment gateway...");
        System.out.println("Authorizing payment of $" + String.format("%.2f", getAmount()));
        System.out.println("Payment SUCCESSFUL!");
        System.out.println("Transaction ID: CC-" + System.currentTimeMillis());
        
        return true;
    }
    
    // Implementation of abstract method
    @Override
    public String getPaymentDetails() {
        StringBuilder details = new StringBuilder();
        details.append("=== CREDIT CARD PAYMENT DETAILS ===\n");
        details.append("Payment Method: Credit Card\n");
        details.append("Card Number: **** **** **** ").append(cardNumber.substring(cardNumber.length() - 4)).append("\n");
        details.append("Expiry Date: ").append(expiryDate.getMonthValue()).append("/").append(expiryDate.getYear()).append("\n");
        details.append("Amount: $").append(String.format("%.2f", getAmount())).append("\n");
        details.append("Payment Date: ").append(getFormattedDate()).append("\n");
        details.append("===================================");
        return details.toString();
    }
}
