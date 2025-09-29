package dao;

import datasource.MariaDbJpaConnection;
import entity.Currency;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class CurrencyDao {

    public List<Currency> findAll() {
        EntityManager em = MariaDbJpaConnection.getInstance();
        List<Currency> currencies = em.createQuery("SELECT c FROM Currency c", Currency.class).getResultList();
        em.close();
        return currencies;
    }

    public void save(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(currency);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
