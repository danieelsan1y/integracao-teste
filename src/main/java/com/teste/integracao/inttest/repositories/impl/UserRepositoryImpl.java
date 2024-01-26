package com.teste.integracao.inttest.repositories.impl;

import com.teste.integracao.inttest.domain.User;
import com.teste.integracao.inttest.repositories.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    public User findUserByEmail(String email){
        User user = new User(email,"123456");
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        return user;
    }
}
