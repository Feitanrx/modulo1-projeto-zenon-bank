import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class main {
    static void main() {


    Transaction transac_1 = new Transaction(1, Transaction.Tipo.PAYMENT,9839.64,"C1231006815",170136.0,160296.36,"M1979787155",0.0,0.0,false,
            false);
    Transaction transac_2 = new Transaction(1, Transaction.Tipo.TRANSFER,181.0,"C1305486145",181.0,0.0,
            "C553264065",0.0,0.0,
            true,false);
IO.println(transac_1 + "\n" + transac_2 );

String caminho = "data/PS_20174392719_1491204439457_log.csv";
        List<Transaction> transactionList = TransactionIngestor.read(caminho);
        IO.println(transactionList.size());
        for (int i = 0; i < 10; i++) {
            IO.println(transactionList.get(i).toString());
        }



}
}
