package code.DAOEntities;

import code.Model.Done;
import code.Model.Task;

import javax.persistence.NoResultException;

public class DoneController extends DAO<Done> {

    public DoneController() {
        super();
    }

    public void delete(Done obj) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Done d WHERE d = " + obj.getDoneId()).executeUpdate();
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }

    public Done findDone(Task task) {
        try {
            Done done = entityManager.createQuery("SELECT d FROM Done d WHERE d.task = :var", Done.class)
                    .setParameter("var", task)
                    .getSingleResult();
            return done;
        } catch (NoResultException excep) {
            return null;
        }
    }
}
