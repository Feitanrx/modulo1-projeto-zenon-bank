public record Transaction(int step, Tipo type, double amount, String nameOrig, double oldbalanceOrg,
                          double newbalanceOrig,
                          String nameDest, double oldbalanceDest,
                          double newbalanceDest, boolean isFraud, boolean isFlaggedFraud) {



 public enum Tipo {
CASH_IN, CASH_OUT, DEBIT, PAYMENT , TRANSFER
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "step=" + step +
                ", type=" + type +
                ", amount=" + amount +
                ", nameOrig='" + nameOrig + '\'' +
                ", oldbalanceOrg=" + oldbalanceOrg +
                ", newbalanceOrig=" + newbalanceOrig +
                ", nameDest='" + nameDest + '\'' +
                ", oldbalanceDest=" + oldbalanceDest +
                ", newbalanceDest=" + newbalanceDest +
                ", isFraud=" + isFraud +
                ", isFlaggedFraud=" + isFlaggedFraud +
                '}';
    }
}