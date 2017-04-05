package com.pachimari.image;

import com.pachimari.product.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by andrem on 04/04/2017.
 */
public interface ImageRepository extends MongoRepository<ProductEntity, String> {
    public ProductEntity findById(String id);
}
