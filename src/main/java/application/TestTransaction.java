package application;

import dao.TransactionDao;
import entity.Currency;
import entity.Transaction;

public class TestTransaction {
    public static void main(String[] args) {
        // Luodaan pari valuuttaa
        Currency eur = new Currency("EUR", "Euro", 1.0);
        Currency usd = new Currency("USD", "US Dollar", 1.1);

        // Lasketaan esimerkkitulos
        double amount = 100.0;
        double resultAmount = amount * usd.getRate() / eur.getRate();

        // Luodaan uusi transaktio
        Transaction transaction = new Transaction(amount, resultAmount, eur, usd);

        // Tallennetaan tietokantaan
        TransactionDao dao = new TransactionDao();
        dao.save(transaction);

        // Tulostetaan kaikki tietokannasta
        System.out.println("💾 Kaikki tietokannan transaktiot:");
        dao.findAll().forEach(System.out::println);
    }
}
