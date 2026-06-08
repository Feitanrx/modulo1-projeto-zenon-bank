import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Classe
public class TransactionIngestorMap {
    // Cria metodo com retorno em formato de list, static para não precisar criar
    // objeto para utilizar o metodo.
    public static Map<String, Transaction> read(String nameFile, int limit) {
        //criar um arraylist
        List<Transaction> transactionsList = new ArrayList<>();
        //cria um map
        Map<String, Transaction> transactionMap = new HashMap<>();
        //faz o path do arquivo CSV, seja qual for.
        Path path = Paths.get(nameFile);

        //ler o csv
        try (BufferedReader br = new BufferedReader(new FileReader(nameFile))){
            String linha;
            br.readLine();
            int contador = 0;
            while ((linha = br.readLine()) != null && contador < limit) {
                try {
                    Transaction transaction = TransactionIngestor.parseTransaction(linha);

                    transactionMap.put(transaction.origin().name(), transaction);
                    contador++;
                } catch (Exception e) {
                    IO.println("Erro: " + linha + "|" + e);
                }
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

        return transactionMap;
    }
}
