import java.util.Objects;

public record TransactionCustomer(String name, double oldbalance, double newbalance){
public TransactionCustomer{
    Objects.requireNonNull(name, "Não pode ser nulo.");
    if(name.isBlank()) throw new IllegalArgumentException("Name não pode estar vazio");
    if(oldbalance < 0)throw new IllegalArgumentException("oldbalance should be positive" + oldbalance);
    if(newbalance < 0) throw new IllegalArgumentException("newbalance should be positive" + newbalance);
}
}
