package iks.gog.jpatst.service;

import iks.gog.jpatst.model.User;

public interface UserService {
    void save(User user);
    User findByName(String name);
}
