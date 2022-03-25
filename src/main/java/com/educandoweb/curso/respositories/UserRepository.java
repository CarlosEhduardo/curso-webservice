package com.educandoweb.curso.respositories;

import com.educandoweb.curso.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
