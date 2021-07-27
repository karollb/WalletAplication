package pl.edu.wszib.wallet.services;

import pl.edu.wszib.wallet.model.User;

public interface IUserService {
    void authenticate(final User user);

    void logout();

    boolean addNewUser(final User user);

    User getUserById(final Long id);
}
