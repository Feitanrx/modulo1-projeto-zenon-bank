import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepositoryInterface {
    //Crie uma classe TransactionListRepository que define um método que busca uma transação por nome do cliente de origem e retorna Optional<Transaction>, indicando que o valor pode não existir. Teste a busca na Main com o arquivo PaySim completo / sem erros, com valores existentes (ex. C1231006815)  e não existentes (ex. C12345). Exemplo de retorno:

    private final List<Transaction> transactions;

    String fileSemErro = "data/PS_20174392719_1491204439457_log.csv";

    List<Transaction> listaCM = TransactionIngestor.read(fileSemErro, 100000);


    public TransactionListRepository(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);
        this.transactions = transactions;
    }

    @Override
    public void save(Transaction transacion) {
        this.transactions.add(transacion);
    }

    @Override
    public Optional<Transaction> findByNameEmLista( String name) {

        return transactions.stream()
                .filter(t -> t.origin().name().equals(name))
                .findFirst();
    }

    @Override
    public Optional<Transaction> findByNameEmMap(String name) {
        return Optional.empty();
    }
}
