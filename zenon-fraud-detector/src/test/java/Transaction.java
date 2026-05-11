import java.util.Objects;

public record Transaction(int step,
                          Tipo type,
                          double amount,
                          Conta nameOrig,
                          double oldbalanceOrg,
                          double newbalanceOrig,
                          Conta nameDest,
                          double oldbalanceDest,
                          double newbalanceDest,
                          boolean isFraud,
                          boolean isFlaggedFraud) {
    public Transaction {
        Objects.requireNonNull(type, "type não pode ser null");

        Objects.requireNonNull(nameOrig, "nameOrig não pode ser null");

        Objects.requireNonNull(nameDest, "nameDest não pode ser null");

        if(step <= 0 || amount <0 || oldbalanceOrg < 0|| newbalanceOrig <0|| oldbalanceDest <0|| newbalanceDest <0) {
            throw new IllegalArgumentException("Erro: ");
        }
    }


    @Override
    public String toString() {
        return
                step +
                "," + type +
                "," + amount +
                "," + nameOrig + '\'' +
                "," + oldbalanceOrg +
                "," + newbalanceOrig +
                "," + nameDest + '\'' +
                "," + oldbalanceDest +
                "," + newbalanceDest +
                "," + isFraud +
                "," + isFlaggedFraud + ""
                ;
    }



    public enum Tipo {
        CASH_IN, CASH_OUT, DEBIT, PAYMENT, TRANSFER
    }
}