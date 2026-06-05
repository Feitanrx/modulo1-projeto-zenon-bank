import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TransactionRepositoryInterface {
    Optional<Transaction> findByNameEmLista(List<Transaction> lista, String name);
    Optional<Transaction> findByNameEmMap(Map<String, Transaction> map, String name);


}
