package com.pachimari.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by andrem on 23/03/2017.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductsRepository productRepository;
    @Autowired
    List<ProductDto> productDTOS;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        productDTOS.clear();
        List<ProductEntity> productEntities = productRepository.findAll();
        for(ProductEntity product: productEntities){
            productDTOS.add(ProductAdapter.fromProductToDto(product));
        }
        return productDTOS;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity createAccount(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidException();
        }
        productRepository.save(ProductAdapter.fromDtoToProduct(productDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{product_id}")
                .buildAndExpand(productDto).toUri();
        return ResponseEntity.created(location).body(productDto);
    }
}
