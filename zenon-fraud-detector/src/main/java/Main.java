import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static br.com.zenon.fraud.Transaction.Tipo.*;


public class Main {
    static void main() {
        Transaction transacao_1 = new Transaction(1,TRANSFER,
                181.0,"C1305486145",181.0,
                0.0,"C553264065",0.0,
                0.0,1,0);

        IO.println(transacao_1);
        Transaction transacao_2 = new Transaction(1,
                CASH_OUT,181.0,
                "C840083671",181.0,0.0,"C38997010",21182.0,
                0.0,1,0);
        IO.println(transacao_2);

String fileName = "data/PS_20174392719_1491204439457_log.csv";

        List<Transaction> transactions =
                TransactionIngestor.read(fileName);
        System.out.println(
                "Quantidade de transações: "
                        + transactions.size()
        );

        for (int i = 0; i < 10; i++) {
            IO.println(transactions.get(i));
        }
    }

    }

