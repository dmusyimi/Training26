package Random_Assignments.abstractionpaymentprocessing;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * PaymentTest - Demonstrates the Payment processing system
 */
public class PaymentTest {
    public static void main(String[] args) {
        System.out.println("===================================================");
        System.out.println("          PAYMENT PROCESSING SYSTEM DEMO");
        System.out.println("===================================================\n");
        
        // Test 1: Credit Card Payment - Successful
        System.out.println("TEST 1: Credit Card Payment - Successful");
        System.out.println("-------------------------------------------------");
        CreditCardPayment ccPayment = new CreditCardPayment(
            250.00,
            LocalDate.now(),
            "4532015112830366",
            YearMonth.of(2027, 12)
        );
        
        if (ccPayment.processPayment()) {
            System.out.println("\n" + ccPayment.getPaymentDetails());
        }
        
        System.out.println("\n\n");
        
        // Test 2: PayPal Payment - Successful
        System.out.println("TEST 2: PayPal Payment - Successful");
        System.out.println("-------------------------------------------------");
        PayPalPayment paypalPayment = new PayPalPayment(
            150.75,
            LocalDate.now(),
            "john.doe@example.com"
        );
        
        if (paypalPayment.processPayment()) {
            System.out.println("\n" + paypalPayment.getPaymentDetails());
        }
        
        System.out.println("\n\n");
        
        // Test 3: Bank Transfer Payment - Successful
        System.out.println("TEST 3: Bank Transfer Payment - Successful");
        System.out.println("-------------------------------------------------");
        BankTransferPayment bankPayment = new BankTransferPayment(
            5000.00,
            LocalDate.now(),
            "1234567890",
            "First National Bank"
        );
        
        if (bankPayment.processPayment()) {
            System.out.println("\n" + bankPayment.getPaymentDetails());
        }
        
        System.out.println("\n\n");
        
        // Test 4: Credit Card Payment - Expired Card (Failed)
        System.out.println("TEST 4: Credit Card Payment - Expired Card (Failed)");
        System.out.println("-------------------------------------------------");
        CreditCardPayment expiredCC = new CreditCardPayment(
            100.00,
            LocalDate.now(),
            "4532015112830366",
            YearMonth.of(2023, 6)  // Expired date
        );
        expiredCC.processPayment();
        
        System.out.println("\n\n");
        
        // Test 5: PayPal Payment - Invalid Email (Failed)
        System.out.println("TEST 5: PayPal Payment - Invalid Email (Failed)");
        System.out.println("-------------------------------------------------");
        PayPalPayment invalidPayPal = new PayPalPayment(
            200.00,
            LocalDate.now(),
            "invalid-email"  // Invalid email format
        );
        invalidPayPal.processPayment();
        
        System.out.println("\n\n");
        
        // Test 6: Bank Transfer - Invalid Amount (Failed)
        System.out.println("TEST 6: Bank Transfer - Invalid Amount (Failed)");
        System.out.println("-------------------------------------------------");
        BankTransferPayment invalidBank = new BankTransferPayment(
            -50.00,  // Negative amount
            LocalDate.now(),
            "1234567890",
            "Second National Bank"
        );
        invalidBank.processPayment();
        
        System.out.println("\n\n");
        
        // Test 7: Polymorphism Demonstration
        System.out.println("TEST 7: Polymorphism Demonstration");
        System.out.println("-------------------------------------------------");
        Payment[] payments = {
            new CreditCardPayment(100.00, LocalDate.now(), "4532015112830366", YearMonth.of(2027, 12)),
            new PayPalPayment(200.00, LocalDate.now(), "user@example.com"),
            new BankTransferPayment(300.00, LocalDate.now(), "9876543210", "Third National Bank")
        };
        
        double totalAmount = 0;
        int successfulPayments = 0;
        
        System.out.println("Processing batch of payments...\n");
        
        for (int i = 0; i < payments.length; i++) {
            System.out.println("Payment " + (i + 1) + ":");
            if (payments[i].processPayment()) {
                totalAmount += payments[i].getAmount();
                successfulPayments++;
            }
            System.out.println();
        }
        
        System.out.println("=================================================");
        System.out.println("BATCH PROCESSING SUMMARY:");
        System.out.println("Total Payments Processed: " + payments.length);
        System.out.println("Successful Payments: " + successfulPayments);
        System.out.println("Failed Payments: " + (payments.length - successfulPayments));
        System.out.println("Total Amount Processed: $" + String.format("%.2f", totalAmount));
        System.out.println("=================================================");
    }
}
