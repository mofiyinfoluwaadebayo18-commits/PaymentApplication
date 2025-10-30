package Payments;

public class PaymentResponse {
    private boolean success;
    private String message;
    private String transactionId;
    private String errorCode;

    public PaymentResponse(boolean success, String message,
                           String transactionId, String errorCode) {
        this.success = success;
        this.message = message;
        this.transactionId = transactionId;
        this.errorCode = errorCode;
    }

    // Getters
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getTransactionId() { return transactionId; }
    public String getErrorCode() { return errorCode; }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
