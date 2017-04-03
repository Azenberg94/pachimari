package com.pachimari.user.repository;

import com.pachimari.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by Pierre on 25/03/2017.
 */
@Repository

public interface UserRepository  extends MongoRepository<User,Integer>{
    public User findById(Integer id);
    public User findByName(String name);

}
