package iks.gog.jpatst.service;

import iks.gog.jpatst.forms.UserCreateForm;
import iks.gog.jpatst.model.User;
import iks.gog.jpatst.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findById(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setName(form.getName());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        
        user.setRole(form.getRole());
        return userRepository.save(user);
    }


}
