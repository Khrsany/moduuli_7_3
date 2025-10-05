package dao;

import datasource.MariaDbJpaConnection;
import entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class TransactionDao {

    public void save(Transaction transaction) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(transaction);
            tx.commit();
            System.out.println("✅ Transaction saved successfully: " + transaction);
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            // sulje vasta lopuksi — ei pidetä yhteyttä auki muissa metodeissa
            em.close();
        }
    }

    public List<Transaction> findAll() {
        // Luodaan kokonaan uusi EntityManager, koska edellinen on suljettu
        EntityManager em = MariaDbJpaConnection.getInstance();
        List<Transaction> transactions = null;

        try {
            transactions = em.createQuery("SELECT t FROM Transaction t", Transaction.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return transactions;
    }
}
