import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class TransactionReport {
    private record ReportTransaction(Double amount, boolean isFraud){
    }
    public record Statistics(long totalTransactions, long totalFrauds, double totalAmount){

        private final static Statistics ZERO = new Statistics(0,0, 0);

    private Statistics addReportTransaction(ReportTransaction rt){
               return new Statistics(
                       totalTransactions + 1,
                       totalFrauds + (rt.isFraud ? 1 : 0),
                       totalAmount + rt.amount);
                }
                private Statistics add(Statistics other){
    return new Statistics(totalTransactions + other.totalTransactions,
            totalFrauds + other.totalFrauds,
            totalAmount + other.totalAmount);
                }
}
        public Statistics generateReport(String fileName){
            Path path = Path.of(fileName);
            try(Stream<String> lines = Files.lines(path)){
                return lines
                        .skip(1)
                        .map(this::parseReportTransaction)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .reduce( Statistics.ZERO,
                                Statistics::addReportTransaction,
                                Statistics::add);
        } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    private Optional<ReportTransaction> parseReportTransaction(String linha){
try {
    String[] partes = linha.split(",");
    double amount = Double.parseDouble(partes[2]);
    boolean isFraud = partes[9].equals("1");

    return Optional.of(new ReportTransaction(amount,isFraud));
}catch (RuntimeException e) {
    return Optional.empty();
}
    }
}
