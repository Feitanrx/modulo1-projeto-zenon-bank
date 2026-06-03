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

}
