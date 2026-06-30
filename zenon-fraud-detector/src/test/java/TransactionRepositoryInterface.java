import java.util.Optional;

public interface TransactionRepositoryInterface {
    void save(Transaction transaction);
    Optional<Transaction> findByNameEmLista(String name);
    Optional<Transaction> findByNameEmMap(String name);


}
