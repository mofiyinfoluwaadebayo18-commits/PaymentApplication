package Payments;

public class PaymentApplication {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // Create a test payment request
        // Using Stripe test card: 4242 4242 4242 4242
        PaymentRequest request = new PaymentRequest(
                2500,              // $25.00
                "usd",             // US Dollars
                "Test Purchase",   // Description
                "4242424242424242", // Test card number
                12,                // Expiry month
                2025,              // Expiry year
                "123"              // CVC
        );

        // Process the payment
        System.out.println("Processing payment...");
        PaymentResponse response = processor.processPayment(request);

        // Display result
        System.out.println("\nPayment Result");
        System.out.println(response);

        if (response.isSuccess()) {
            System.out.println("\n✓ Payment successful!");
            System.out.println("Transaction ID: " + response.getTransactionId());
        } else {
            System.out.println("\n✗ Payment failed!");
            System.out.println("Reason: " + response.getMessage());
        }
    }
}
