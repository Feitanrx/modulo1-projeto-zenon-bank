void main() {
    String fileNameComErro = "data/paysim_with_bad_data.csv";
    List<Transaction> listacomErro = TransactionIngestor.read(fileNameComErro);
    System.out.println(listacomErro.size());

    listacomErro.stream()
            .forEach(System.out::println);

}
