package pl.edu.wszib.wallet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.wallet.dao.IUserDAO;
import pl.edu.wszib.wallet.model.User;
import pl.edu.wszib.wallet.services.IUserService;
import pl.edu.wszib.wallet.session.SessionObject;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Autowired
    public UserServiceImpl(final IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void authenticate(final User user) {
        User userFromDataBase = this.userDAO.getUserByLogin(user.getLogin());
        if (userFromDataBase != null) {
            if (user.getPassword().equals(userFromDataBase.getPassword())) {
                this.sessionObject.setLoggedUser(user);
            }
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);

    }

    @Override
    public boolean addNewUser(final User user) {
        if (this.userDAO.getUserByLogin(user.getLogin()) == null) {
            this.userDAO.addNewUser(user);
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(final Long id) {
        return this.userDAO.getUserById(id);
    }
}
