package code.DAOEntities;

import code.Model.Team;

import javax.persistence.*;

public class TeamController extends DAO<Team> {

    public TeamController() {
        super();
    }

    public Team getTeamByName(String name) {
        try {
            Team team = entityManager.createQuery("SELECT t FROM Team t WHERE t.teamName = :var", Team.class)
                    .setParameter("var", name)
                    .getSingleResult();
            return team;
        } catch (NoResultException excep) {
            return null;
        }
    }

}
