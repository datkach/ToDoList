package code.DAOEntities;

import code.Model.Users;

import javax.persistence.*;

public class UserController extends DAO<Users> {

    public UserController() {
        super();
    }

    public Users getUserByLogin(String login) {
        try {
            Users user = entityManager.createQuery("SELECT u FROM Users u WHERE u.login = :var", Users.class)
                    .setParameter("var", login)
                    .getSingleResult();
            return user;
        } catch (NoResultException excep) {
            return null;
        }
    }

}
