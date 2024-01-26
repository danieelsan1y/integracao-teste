package com.teste.integracao.inttest.repositories;

import com.teste.integracao.inttest.domain.User;

public interface UserRepository {

    User findUserByEmail(String email);
}
