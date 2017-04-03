package com.pachimari.user.repository;

import com.pachimari.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by Pierre on 25/03/2017.
 */
@Repository
<<<<<<< HEAD
public interface UserRepository  extends MongoRepository<User,Integer>{
    public User findById(Integer id);
    public User findByName(String name);
=======
public interface UserRepository  extends MongoRepository<User,Long>{

>>>>>>> a4e2acbd441a0930c399f790b619c38103b552ae
}
