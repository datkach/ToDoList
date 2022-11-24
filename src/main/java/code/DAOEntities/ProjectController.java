package code.DAOEntities;

import code.Model.Project;

import javax.persistence.NoResultException;

public class ProjectController extends DAO<Project> {

    public ProjectController() {
        super();
    }

    public Project getProjectByName(String name) {
        try {
            Project project = entityManager.createQuery("SELECT p FROM Project p WHERE p.projectName = :var", Project.class)
                    .setParameter("var", name)
                    .getSingleResult();
            return project;
        } catch (NoResultException excep) {
            return null;
        }
    }

    public void delete(Project obj) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Project p WHERE p = " + obj.getProjectId()).executeUpdate();
        entityManager.getTransaction().commit();
        entityManagerFactory.close();
    }

}
