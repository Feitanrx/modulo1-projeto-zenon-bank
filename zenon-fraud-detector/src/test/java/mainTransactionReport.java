public class mainTransactionReport {
    static void main() {
        String fileSemErro = "data/PS_20174392719_1491204439457_log.csv";

        var transactionReport = new TransactionReport();
        TransactionReport.Statistics statics = transactionReport.generateReport(fileSemErro);
        IO.println("""
                Total de linhas: %d
                Total de fraudes: %d
                Total de amount: %.2f
                
                """.formatted(statics.totalTransactions(), statics.totalFrauds(), statics.totalAmount()));
    }
}
