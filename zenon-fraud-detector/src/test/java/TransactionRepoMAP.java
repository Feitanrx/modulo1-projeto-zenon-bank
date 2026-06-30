import java.util.Map;
import java.util.Optional;

public class TransactionRepoMAP implements TransactionRepositoryInterface{

    //Crie uma outra implementação que realiza o carregamento das transações em um Map<String, Transaction> e realize a busca novamente pelo cliente C1868032458. Prove a eficiência do Map medindo o novo tempo de busca.

//    String fileSemErro = "data/PS_20174392719_1491204439457_log.csv";
//
//    Map<String, Transaction> transactionMap = TransactionIngestor.read(fileSemErro, 50000);
String fileSemErro = "data/PS_20174392719_1491204439457_log.csv";

    Map<String, Transaction> listaMAP =
        TransactionIngestorMap.read(fileSemErro, 100000);

    @Override
    public void save(Transaction transaction) {
listaMAP.putIfAbsent(transaction.origin().name(), transaction);
    }

    @Override
    public Optional<Transaction> findByNameEmLista(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Transaction> findByNameEmMap(String name) {
        return Optional.ofNullable(listaMAP.get(name));
    }
}
