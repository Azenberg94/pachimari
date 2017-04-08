package com.pachimari.auth;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Loïc on 04/04/2017.
 */
@Repository
public interface AuthRepository extends MongoRepository<AuthEntity, String> {
    AuthEntity findByLoginAndPwd(String login, String pwd);
    AuthEntity findByLogin(String login);

}

