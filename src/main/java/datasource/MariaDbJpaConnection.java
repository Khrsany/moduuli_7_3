package datasource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MariaDbJpaConnection {
    private static EntityManagerFactory emf = null;

    // Luo uusi EntityManager joka kerta kun tätä kutsutaan
    public static EntityManager getInstance() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("CurrencyUnit");
        }
        return emf.createEntityManager();
    }
}
