import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class mainTransactionReport {
    static void main(String[] args) {
        String language = (args.length > 0 ? args[0] : "pt");
        Locale locale = Locale.of(language);
        NumberFormat integerFormatter = NumberFormat.getIntegerInstance(locale);
        NumberFormat currencyFormatter = DecimalFormat.getCurrencyInstance(locale);
        currencyFormatter.setCurrency(Currency.getInstance("USD"));

        String fileSemErro = "data/PS_20174392719_1491204439457_log.csv";
        var transactionReport = new TransactionReport();

        var resourceBundle = ResourceBundle.getBundle("report", locale);

        TransactionReport.Statistics statics = transactionReport.generateReport(fileSemErro);
        String fmtTotalTransactions = integerFormatter.format(statics.totalTransactions());
        String fmtTotalFrauds = integerFormatter.format(statics.totalFrauds());
        String fmtAmount = currencyFormatter.format(statics.totalAmount());

        String msgTotalTransactions = resourceBundle.getString("label.total.transactions");
        String msgTotalFrauds = resourceBundle.getString("label.total.frauds");
        String msgTotalAmount = resourceBundle.getString("label.total.amount");

        IO.println("""
                %s: %s
                %s: %s
                %s: %s
                
                """.formatted(
                msgTotalTransactions,fmtTotalTransactions,
                msgTotalFrauds,fmtTotalFrauds,
                msgTotalAmount,fmtAmount));
    }
}
