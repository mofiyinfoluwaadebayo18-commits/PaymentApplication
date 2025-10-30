package Payments;

import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.TokenCreateParams;

public class PaymentProcessor {

    public PaymentProcessor() {
        PaymentConfiguration.initialize();
    }


    public PaymentResponse processPayment(PaymentRequest request) {
        try {
            // Step 1: Create a token from card details
            Token token = createCardToken(request);

            // Step 2: Create a charge using the token
            Charge charge = createCharge(request, token.getId());

            // Step 3: Build and return success response
            return new PaymentResponse(
                    true,
                    "Payment processed successfully",
                    charge.getId(),
                    null
            );

        } catch (CardException e) {
            // Card was declined
            return new PaymentResponse(
                    false,
                    "Card declined: " + e.getMessage(),
                    null,
                    e.getCode()
            );



        } catch (InvalidRequestException e) {
            // Invalid parameters
            return new PaymentResponse(
                    false,
                    "Invalid request: " + e.getMessage(),
                    null,
                    "invalid_request"
            );

        } catch (AuthenticationException e) {
            // Authentication with Stripe failed
            return new PaymentResponse(
                    false,
                    "Authentication failed. Please check API keys.",
                    null,
                    "authentication_error"
            );

        } catch (ApiConnectionException e) {
            // Network problem
            return new PaymentResponse(
                    false,
                    "Network error. Please check your connection.",
                    null,
                    "network_error"
            );

        } catch (StripeException e) {
            // Generic Stripe error
            return new PaymentResponse(
                    false,
                    "Payment processing error: " + e.getMessage(),
                    null,
                    "stripe_error"
            );
        }
    }

    private Token createCardToken(PaymentRequest request) throws StripeException {
        TokenCreateParams params = TokenCreateParams.builder()
                .setCard(
                        TokenCreateParams.Card.builder()
                                .setNumber(request.getCardNumber())
                                .setExpMonth(String.valueOf(request.getExpMonth()))
                                .setExpYear(String.valueOf(request.getExpYear()))
                                .setCvc(request.getCvc())
                                .build()
                )
                .build();

        return Token.create(params);
    }


    private Charge createCharge(PaymentRequest request, String tokenId)
            throws StripeException {
        ChargeCreateParams params = ChargeCreateParams.builder()
                .setAmount(request.getAmount())
                .setCurrency(request.getCurrency())
                .setDescription(request.getDescription())
                .setSource(tokenId)
                .build();

        return Charge.create(params);
    }
}