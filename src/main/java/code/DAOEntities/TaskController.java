package code.DAOEntities;

import code.Model.Task;

public class TaskController extends DAO<Task> {

    public TaskController() {
        super();
    }

    public void delete(Task obj) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Task t WHERE t = " + obj.getTaskId()).executeUpdate();
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }

}
