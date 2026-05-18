import java.util.Objects;

public record Transaction(int step,
                          TransactionType type,
                          double amount,
                          TransactionCustomer origin,
                          TransactionCustomer recipient,
                          boolean isFraud,
                          boolean isFlaggedFraud) {
    @Override
    public String toString() {
        return "Transaction{" +
                "step=" + step +
                ", type=" + type +
                ", amount=" + amount +
                ", origin=" + origin +
                ", recipient=" + recipient +
                ", isFraud=" + isFraud +
                ", isFlaggedFraud=" + isFlaggedFraud +
                "}";
    }

    public Transaction {

        if(step < 1 ) throw new IllegalArgumentException("Step should be positive" + step);
        if(amount < 0 ) throw new IllegalArgumentException("amount should be positive" + amount);

        Objects.requireNonNull(type, "type não pode ser null" + type);

        Objects.requireNonNull(origin, "nameOrig não pode ser null" + origin);

        Objects.requireNonNull(recipient, "nameDest não pode ser null" + recipient);


}
}