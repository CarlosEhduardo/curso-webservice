package com.educandoweb.curso.services;

import com.educandoweb.curso.entities.User;
import com.educandoweb.curso.respositories.UserRepository;
import com.educandoweb.curso.services.exceptions.DatabaseException;
import com.educandoweb.curso.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj) {
        return userRepository.save(obj);
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj) {
        try {
            User entityMonitored = userRepository.getOne(id);
            updateData(entityMonitored, obj);

            return userRepository.save(entityMonitored);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entityMonitored, User obj) {
        entityMonitored.setName(obj.getName());
        entityMonitored.setEmail(obj.getEmail());
        entityMonitored.setPhone(obj.getPhone());
    }
}
