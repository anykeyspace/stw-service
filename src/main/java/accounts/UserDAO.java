package accounts;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDAO {

    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UserProfile getUserByLogin(String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UserProfile.class);
        return (UserProfile) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    public UserProfile getUserById(long id) throws HibernateException {
        return (UserProfile) session.get(UserProfile.class, id);
    }

    public long insertUser(String login, String pass, String email) throws HibernateException {
        return (Long) session.save(new UserProfile(login, pass, email));
    }

}
