package code.DAOEntities;

import code.Model.Profile;
import code.Model.Users;

import javax.persistence.NoResultException;

public class ProfileController extends DAO<Profile> {

    public ProfileController() {
        super();
    }

    public Profile getProfileByUserId(Users user) {
        try {
            Profile profile = entityManager.createQuery("SELECT p FROM Users u INNER JOIN Profile p ON u.userId = p.user.userId WHERE p.user.userId = :var", Profile.class)
                    .setParameter("var", user.getUserId())
                    .getSingleResult();
            return profile;
        } catch (NoResultException excep) {
            return null;
        }
    }

}
