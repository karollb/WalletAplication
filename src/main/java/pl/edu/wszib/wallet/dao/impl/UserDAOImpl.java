package pl.edu.wszib.wallet.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.wallet.dao.IUserDAO;
import pl.edu.wszib.wallet.model.User;

import javax.persistence.NoResultException;

@Repository
public class UserDAOImpl implements IUserDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addNewUser(final User user) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserById(final Long id) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.wszib.wallet.model.User WHERE id = :id");
        query.setParameter("id", id);

        User result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.close();
        return result;
    }

    @Override
    public User getUserByLogin(final String login) {
        Session session = this.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.edu.wszib.wallet.model.User WHERE login = :login");
        query.setParameter("login", login);

        User result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.close();
        return result;
    }
}
