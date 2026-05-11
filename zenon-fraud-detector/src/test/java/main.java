void main() {

    Conta origenteste = new Conta("C3254354");

    Transaction transac_1 = new Transaction(1, Transaction.Tipo.PAYMENT, 9839.64, origenteste, 170136.0, 160296.36, origenteste, 0.0, 0.0, false,
            false);
    Transaction transac_2 = new Transaction(1, Transaction.Tipo.TRANSFER, 181.0, origenteste, 181.0, 0.0,
            origenteste, 0.0, 0.0,
            true, false);
    IO.println(transac_1 + "\n" + transac_2);

    String caminho = "data/PS_20174392719_1491204439457_log.csv";

    String caminhoComErro = "data/paysim_with_bad_data.csv";
    List<Transaction> transactionList = TransactionIngestor.read(caminho);
    IO.println(transactionList.size());
    for (int i = 0; i < 10; i++) {
        IO.println(transactionList.get(i).toString());
    }
    IO.println(transactionList.get(1).isFlaggedFraud());
    List<Transaction> transactionListComErro = TransactionIngestor.read(caminhoComErro);
    for (int j = 0; j < 10; j++) {
        IO.println(transactionListComErro.get(j));
    }
}
