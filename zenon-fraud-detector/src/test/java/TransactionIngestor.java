import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//Classe
public class TransactionIngestor {
    // Cria metodo com retorno em formato de list, static para não precisar criar
    // objeto para utilizar o metodo.
    public static List<Transaction> read(String nameFile) {
        //criar um arraylist
        List<Transaction> transactionsList = new ArrayList<>();
        //faz o path do arquivo CSV, seja qual for.
        Path path = Paths.get(nameFile);

        //ler o csv
        try (BufferedReader br = new BufferedReader(new FileReader(nameFile))){
            String linha;
            br.readLine();
            int contador = 0;
            int limite = 20;
            while ((linha = br.readLine()) != null && contador < limite) {
                try {
                    String[] partes = linha.split(",");

                    //Cria os dois Customers
                    double oldBalance = Double.parseDouble(partes[4]);
                    double newBalance = Double.parseDouble(partes[5]);
                    TransactionCustomer origin = new TransactionCustomer(partes[3], oldBalance, newBalance);

                    double oldBalanceDest = Double.parseDouble(partes[7]);
                    double newBalanceDest = Double.parseDouble(partes[8]);
                    TransactionCustomer dest = new TransactionCustomer(partes[6],oldBalanceDest,newBalanceDest);

                    //Cria a transaction
                    Transaction transaction = new Transaction(
                            Integer.parseInt(partes[0]),
                            TransactionType.valueOf(partes[1]),
                            Double.parseDouble(partes[2]),
                            origin,
                            dest,
                            Integer.parseInt(partes[9]) == 1,
                            Integer.parseInt(partes[10]) == 1
                    );

                    //adiciona cada transacao convertida a transactionsList
                    transactionsList.add(transaction);
                    contador++;
                } catch (Exception e) {
                    IO.println("Erro: " + linha + "|" + e);
                }
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
        return transactionsList;
    }
}
