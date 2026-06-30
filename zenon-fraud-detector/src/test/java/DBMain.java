import java.util.List;

public class DBMain {
    static void main() {

        ConnectionFactory.getConnection();
        IO.println("Conexão com BD criada");

        TransactionSQLRepository repository = new TransactionSQLRepository();

    long startTimeSQL = System.nanoTime();
        List<Transaction> transactions = TransactionIngestor.read("data/PS_20174392719_1491204439457_log.csv", 10000);
        IO.println(transactions.size());

        transactions.forEach(repository::save);

        long endTimeSQL = System.nanoTime();

    }
}
