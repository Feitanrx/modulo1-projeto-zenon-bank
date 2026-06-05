import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepositoryInterface {
    //Crie uma classe TransactionListRepository que define um método que busca uma transação por nome do cliente de origem e retorna Optional<Transaction>, indicando que o valor pode não existir. Teste a busca na Main com o arquivo PaySim completo / sem erros, com valores existentes (ex. C1231006815)  e não existentes (ex. C12345). Exemplo de retorno:

    private final List<Transaction> transactions;

    public TransactionListRepository(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);
        this.transactions = transactions;
    }

    @Override
    public Optional<Transaction> findByNameEmLista(List<Transaction> lista, String name) {

        return lista.stream()
                .filter(t -> t.origin().name().equals(name))
                .findFirst();
    }

    @Override
    public Optional<Transaction> findByNameEmMap(Map<String, Transaction> map, String name) {
        return Optional.empty();
    }
}
