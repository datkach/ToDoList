package code.DAOEntities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO<T> {

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public DAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("my-app");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void insert(T obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }


    public void update(T obj) {
        entityManager.getTransaction().begin();
        entityManager.merge(obj);
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }

}
