package Payments;

public class PaymentConfiguration {
    // Get your test API key from Stripe Dashboard
    private static final String STRIPE_SECRET_KEY = "sk_test_KEY";

    public static void initialize() {
        com.stripe.Stripe.apiKey = STRIPE_SECRET_KEY;
    }

}