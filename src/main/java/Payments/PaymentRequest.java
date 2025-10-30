package Payments;

public class PaymentRequest {
    private long amount;
    private String currency;
    private String description;
    private String cardNumber;
    private int expMonth;
    private int expYear;
    private String cvc;

    // Constructor
    public PaymentRequest(long amount, String currency, String description,
                          String cardNumber, int expMonth, int expYear, String cvc) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvc = cvc;
    }

    // Getters
    public long getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getDescription() { return description; }
    public String getCardNumber() { return cardNumber; }
    public int getExpMonth() { return expMonth; }
    public int getExpYear() { return expYear; }
    public String getCvc() { return cvc; }
}
