void main() {
    String fileNameComErro = "data/paysim_with_bad_data.csv";
    List<Transaction> listacomErro = TransactionIngestor.read(fileNameComErro, 10);

    /*System.out.println(listacomErro.size());
    listacomErro.stream()
            .forEach(System.out::println); */

    String fileSemErro = "data/PS_20174392719_1491204439457_log.csv";
    List<Transaction> listaSemErro5 = TransactionIngestor.read(fileSemErro, 50000);

    //Apenas transações onde isFraud == true, imprima o tamanho da lista.
    List<Transaction> comFraude = FraudAnalyzer.listaDeFraudes(listaSemErro5);
    System.out.println("1. Total de fraudes: " + comFraude.size());

    //Imprima as 3 fraudes de maior valor (amount).
    System.out.println("2. Top 3 Fraudes de Maior Valor:");
    FraudAnalyzer.top3Fraudes(listaSemErro5);

    //Obter apenas os nomes dos clientes de origem (nameOrig) dessas fraudes e depois gere uma lista sem repetições (Set ou distinct) com os 5 maiores clientes suspeitos.

    System.out.println("3. Clientes suspeitos:");
    FraudAnalyzer.setSuspiciousClients(listaSemErro5, 5);

    //Calcule o prejuízo total causado pelas fraudes (soma dos amount).

    System.out.println("4. Prejuízo total: " );
    FraudAnalyzer.financialLoss(listaSemErro5);

    //Conte quantas fraudes ocorreram por tipo de transação (CASH_OUT, TRANSFER, etc...).

    FraudAnalyzer.quantityFraudsPerType(listaSemErro5);

    IO.println("--------------------------------------------------------");

    List<Transaction> listaCM = TransactionIngestor.read(fileSemErro, 100000);


    //Optional<Transaction> c9598 =
    TransactionListRepository transactionRepositoryOBJ = new TransactionListRepository(listaCM);
    TransactionRepoMAP transactionIngestorMapOBJ = new TransactionRepoMAP();

    transactionRepositoryOBJ.findByNameEmLista(listaCM, "c9598").ifPresentOrElse(System.out::println, () -> IO.println("Transacao nao encontrada para cliente c9598"));

    transactionRepositoryOBJ.findByNameEmLista(listaCM, "C1231006815").ifPresentOrElse(System.out::println,() -> IO.println("Transacao não encontrada para cliente c9598"));

//Usando o TransactionListRepository, realize uma busca pelo nome da origem (nameOrig) da última transação da lista (C1868032458), o pior caso, e meça o tempo de busca usando System.nanoTime().
    long startTime = System.nanoTime();
    IO.println(startTime);

transactionRepositoryOBJ.findByNameEmLista(listaCM, "C1868032458").ifPresentOrElse(System.out::println, () -> IO.println("nao encontrado"));
    long endTime = System.nanoTime();
    IO.println(endTime);
IO.println((endTime - startTime)/1_000_000.0);

    Map<String, Transaction> listaMAP =
            TransactionIngestorMap.read(fileSemErro, 100000);
    long startTimeMap = System.nanoTime();
    IO.println(startTimeMap);
    transactionIngestorMapOBJ.findByNameEmMap(listaMAP, "C1868032458");
    long endTimeMap = System.nanoTime();
    IO.println(endTimeMap);
    IO.println((endTimeMap - startTimeMap )/1_000_000.0);
}
