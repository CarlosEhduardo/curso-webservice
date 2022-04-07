package com.educandoweb.curso.services;

import com.educandoweb.curso.entities.User;
import com.educandoweb.curso.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.get();
    }

    public User insert(User obj) {
        return userRepository.save(obj);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User update(Long id, User obj) {
        User entityMonitored = userRepository.getOne(id);
        updateData(entityMonitored, obj);

        return userRepository.save(entityMonitored);
    }

    private void updateData(User entityMonitored, User obj) {
        entityMonitored.setName(obj.getName());
        entityMonitored.setEmail(obj.getEmail());
        entityMonitored.setPhone(obj.getPhone());
    }
}
