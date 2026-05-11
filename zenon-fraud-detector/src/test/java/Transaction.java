public record Transaction(int step, Tipo type, double amount, Conta nameOrig, double oldbalanceOrg,
                          double newbalanceOrig,
                          Conta nameDest, double oldbalanceDest,
                          double newbalanceDest, boolean isFraud, boolean isFlaggedFraud) {


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

    public enum Tipo {
        CASH_IN, CASH_OUT, DEBIT, PAYMENT, TRANSFER
    }
}