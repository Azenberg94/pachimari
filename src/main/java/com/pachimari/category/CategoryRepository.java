package com.pachimari.category;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface CategoryRepository extends MongoRepository<Category, String> {

    public Category findByName(String name);
    public Category findById(String id);

}
