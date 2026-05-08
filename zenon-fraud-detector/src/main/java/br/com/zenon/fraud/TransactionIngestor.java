package br.com.zenon.fraud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionIngestor {

    public static List<Transaction> read(String fileName) {

        List<Transaction> transactions = new ArrayList<>();

        Path path = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(path)) {

            String linha;
            int contador = 0;

            br.readLine(); // pula cabeçalho

            while ((linha = br.readLine()) != null && contador < 1000) {

                try {

                    String[] dado = linha.split(",");

                    Transaction transaction = new Transaction(
                            Integer.parseInt(dado[0]),
                            Transaction.Tipo.valueOf(dado[1]),
                            Double.parseDouble(dado[2]),
                            dado[3],
                            Double.parseDouble(dado[4]),
                            Double.parseDouble(dado[5]),
                            dado[6],
                            Double.parseDouble(dado[7]),
                            Double.parseDouble(dado[8]),
                            Integer.parseInt(dado[9]),
                            Integer.parseInt(dado[10])
                    );

                    transactions.add(transaction);

                    contador++;

                } catch (Exception e) {

                    System.out.println("Erro na linha:");
                    System.out.println(Arrays.toString(linha.split(",")));

                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }
}

