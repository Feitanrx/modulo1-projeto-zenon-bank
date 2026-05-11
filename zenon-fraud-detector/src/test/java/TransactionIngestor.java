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
            while ((linha = br.readLine()) != null && contador < 20) {
                try {
                    String[] partes = linha.split(",");
                    Conta origem = new Conta (partes[3]);
                    Conta destino = new Conta (partes[6]);

//if(Integer.parseInt(partes[0])>=1 | Double.parseDouble(partes[2]) < 0 | Double.parseDouble(partes[4]) < 0 | Double.parseDouble(partes[5]) < 0
        //| Double.parseDouble(partes[7]) < 0 | Double.parseDouble(partes[8]) < 0){
    //IO.println("Erro nos valores");}

                    Transaction transaction = new Transaction(
                            Integer.parseInt(partes[0]),
                            Transaction.Tipo.valueOf(partes[1]),
                            Double.parseDouble(partes[2]),
                            origem,
                            Double.parseDouble(partes[4]),
                            Double.parseDouble(partes[5]),
                            destino,
                            Double.parseDouble(partes[7]),
                            Double.parseDouble(partes[8]),
                            Boolean.parseBoolean(partes[9]),
                            Boolean.parseBoolean(partes[10])
                    );
//step, type, amount, nameOrig, oldbalanceOrg, newbalanceOrig,
// nameDest, oldbalanceDest, newbalanceDest, isFraud, isFlaggedFraud

                    //adiciona cada transacao convertida a transactionsList
                    transactionsList.add(transaction);
                    IO.println(transaction);

                    contador++;
                } catch (Exception e) {
                    IO.println("Erro: ");
                }
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
        return transactionsList;
    }
}
