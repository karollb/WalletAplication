package pl.edu.wszib.wallet.dao;

import pl.edu.wszib.wallet.model.User;

public interface IUserDAO {
    void addNewUser(final User user);

    User getUserById(final Long id);

    User getUserByLogin(final String login);
}
