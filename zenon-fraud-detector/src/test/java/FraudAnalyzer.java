import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    public static List<Transaction> listaDeFraudes(List<Transaction> lista) {
        List<Transaction> comFraude = lista.stream()
                .filter(Transaction::isFraud)
                .toList();
        return comFraude;
    }

    public static void top3Fraudes(List<Transaction> lista) {
        DecimalFormat formatador = new DecimalFormat("#,##0.00");
lista.stream()
        .filter(Transaction::isFraud)
        .sorted(Comparator.comparing(Transaction::amount).reversed())
        .limit(3)
        .map(Transaction::amount)
        .map(formatador::format)
        .forEach(System.out::println);
    }

    public static void setSuspiciousClients(List<Transaction> listaSemErro5, int limit) {
        Set<String> suspeitos = listaSemErro5.stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .map(Transaction::origin)
                .map(TransactionCustomer::name)
                .limit(limit)
                .collect(Collectors.toSet());
        suspeitos.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

    }

    public static void financialLoss(List<Transaction> listaSemErro5) {
        DecimalFormat formatador = new DecimalFormat("#,##0.00");
        Double total = listaSemErro5.stream()
                .filter(Transaction::isFraud)
                .mapToDouble(Transaction::amount)
                .sum();
        String totalFormatado = formatador.format(total);
        System.out.println(totalFormatado);

    }

    public static void quantityFraudsPerType(List<Transaction> listacomErro) {

        Map<TransactionType,Long> count = listacomErro.stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(
                        Transaction::type,
                        Collectors.counting()
                        ));
        IO.println("Fraudes por tipo: ");
        count.forEach((type, countg) -> IO.println("- %s : %d".formatted(type,countg)));

    }
}
