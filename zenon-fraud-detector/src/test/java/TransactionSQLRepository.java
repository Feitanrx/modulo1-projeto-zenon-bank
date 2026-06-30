import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TransactionSQLRepository implements TransactionRepositoryInterface{

String sql = """
        SELECT id, step, `type`, amount,
            name_origin, old_balance_origin, new_balance_origin,
            name_recipient, old_balance_recipient, new_balance_recipient,
            is_fraud, is_flagged_fraud
        FROM zenon_frauds.transactions
        WHERE origin_name = ?
        ORDER BY step
        LIMIT 1
        """;

    @Override
    public void save(Transaction transacion) {
String sql = """
        INSERT into transactions
        (step, `type`, amount, name_origin, old_balance_origin, new_balance_origin,
         name_recipient, old_balance_recipient, new_balance_recipient, is_fraud, id_flagged_fraud)
         values (?,?,?,?,?,?,?,?,?,?,?)
        """;
try(Connection conn = ConnectionFactory.getConnection();
PreparedStatement ps = conn.prepareStatement(sql)){

    ps.setInt(1,transacion.step());
    ps.setString(2, transacion.type().name());
    ps.setDouble(3, transacion.amount());

    ps.setString(4, transacion.origin().name());
    ps.setDouble(5, transacion.origin().oldbalance());
    ps.setDouble(6, transacion.origin().newbalance());

    ps.setString(7, transacion.recipient().name());
    ps.setDouble(8, transacion.recipient().oldbalance());
    ps.setDouble(9, transacion.recipient().newbalance());

    ps.setBoolean(10, transacion.isFraud());
    ps.setBoolean(11, transacion.isFlaggedFraud());

    ps.execute();
} catch (SQLException e) {
    throw new RuntimeException("Erro ao salvar nova transação" + e);
}

    }

    @Override
    public Optional<Transaction> findByNameEmLista(String name) {

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery();) {
                if(rs.next()){
                Transaction transaction = mapResultSetToTransaction(rs);
                return Optional.of(transaction);
            }else {
                    IO.println("Transação nao encontrada para origin: " + name);
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar transacao da origem: " + e);
        }
    }

    private Transaction mapResultSetToTransaction(ResultSet rs) {
        try {
            int step = rs.getInt("step");
            TransactionType type = TransactionType.valueOf(rs.getString("type"));
            double amount = rs.getDouble("amount");
            String originName = rs.getString("name_origin");
            double originOldBalance= rs.getDouble("old_balance_origin");
            double originNewBalance = rs.getDouble("new_balance_origin");
            TransactionCustomer origin = new TransactionCustomer(originName, originOldBalance, originNewBalance);


            String recipientName= rs.getString("name_recipient");
            double recipientOldBalance= rs.getDouble("old_balance_recipient");
            double recipientNewBalance = rs.getDouble("new_balance_recipient");
            TransactionCustomer recipient = new TransactionCustomer(recipientName, recipientOldBalance, recipientNewBalance);
            boolean isFraud = rs.getBoolean("is_fraud");
            boolean isFlaggedFraud = rs.getBoolean("is_flagged_fraud");
            return new Transaction(step, type, amount, origin, recipient, isFraud, isFlaggedFraud);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Transaction> findByNameEmMap(String name) {
return Optional.empty();
    }
}
