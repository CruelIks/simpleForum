package iks.gog.jpatst.service;

import iks.gog.jpatst.forms.UserCreateForm;
import iks.gog.jpatst.model.User;


import java.util.Collection;

public interface UserService {
    void save(User user);

    User findByName(String name);

    User findById(long id);

    Collection<User> getAllUsers();

    User create(UserCreateForm userCreateForm);

    User getCurrentUser();
}
