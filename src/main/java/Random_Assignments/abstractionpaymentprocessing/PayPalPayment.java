package Random_Assignments.abstractionpaymentprocessing;

import java.time.LocalDate;

/**
 * PayPalPayment class - represents a PayPal payment
 */
public class PayPalPayment extends Payment {
    private String email;
    
    // Constructor
    public PayPalPayment(double amount, LocalDate paymentDate, String email) {
        super(amount, paymentDate);
        this.email = email;
    }
    
    // Getter
    public String getEmail() {
        return email;
    }
    
    // Setter
    public void setEmail(String email) {
        this.email = email;
    }
    
    // Validate email format (basic validation)
    private boolean validateEmail() {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            System.out.println("Invalid email: Must be a valid email address");
            return false;
        }
        return true;
    }
    
    // Implementation of abstract method
    @Override
    public boolean processPayment() {
        System.out.println("\n--- Processing PayPal Payment ---");
        
        // Validate amount
        if (!validateAmount()) {
            System.out.println("Payment FAILED: Invalid amount");
            return false;
        }
        
        // Validate email
        if (!validateEmail()) {
            System.out.println("Payment FAILED: Invalid email");
            return false;
        }
        
        // Process payment
        System.out.println("Connecting to PayPal...");
        System.out.println("Authenticating account: " + email);
        System.out.println("Processing payment of $" + String.format("%.2f", getAmount()));
        System.out.println("Payment SUCCESSFUL!");
        System.out.println("Transaction ID: PP-" + System.currentTimeMillis());
        
        return true;
    }
    
    // Implementation of abstract method
    @Override
    public String getPaymentDetails() {
        StringBuilder details = new StringBuilder();
        details.append("=== PAYPAL PAYMENT DETAILS ===\n");
        details.append("Payment Method: PayPal\n");
        details.append("Email: ").append(email).append("\n");
        details.append("Amount: $").append(String.format("%.2f", getAmount())).append("\n");
        details.append("Payment Date: ").append(getFormattedDate()).append("\n");
        details.append("===============================");
        return details.toString();
    }
}
