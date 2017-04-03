package com.pachimari.user.repository;

import com.pachimari.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Pierre on 25/03/2017.
 */
@Repository

public interface UserRepository  extends MongoRepository<User,Integer>{
    public User findById(Integer id);
    public User findByName(String name);
    public User findByLogin(String login);
    public List<User> findByType(String Type);

}