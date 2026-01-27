package Random_Assignments.abstractionpaymentprocessing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstract Payment class representing a general payment
 * with common attributes and behaviors
 */
public abstract class Payment {
    // Fields
    private double amount;
    private LocalDate paymentDate;
    
    // Constructor
    public Payment(double amount, LocalDate paymentDate) {
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
    
    // Getters
    public double getAmount() {
        return amount;
    }
    
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    
    // Setters
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    // Abstract methods - must be implemented by subclasses
    public abstract boolean processPayment();
    
    public abstract String getPaymentDetails();
    
    // Concrete method - validates payment amount
    public boolean validateAmount() {
        if (amount <= 0) {
            System.out.println("Invalid amount: Payment amount must be greater than 0");
            return false;
        }
        if (amount > 1000000) {
            System.out.println("Invalid amount: Payment amount exceeds maximum limit of $1,000,000");
            return false;
        }
        return true;
    }
    
    // Helper method to format date
    protected String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return paymentDate.format(formatter);
    }
    
    // Display basic payment information
    public void displayPaymentInfo() {
        System.out.println("Payment Amount: $" + String.format("%.2f", amount));
        System.out.println("Payment Date: " + getFormattedDate());
    }
}
