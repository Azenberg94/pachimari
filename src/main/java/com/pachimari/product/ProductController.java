package com.pachimari.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private ProductServiceImpl productService;

    @GetMapping("/find/{name}/{brand}/{type}")
    public List<ProductDto> getProductByOptionalParameters(@PathVariable("name") String name, @PathVariable("brand") String brand, @PathVariable("type") Integer type) {
        name = (name.equals("any")) ? null : name;
        brand = (brand.equals("any")) ? null : brand;
        type = (type == 0) ? null : type;
        return productService.getSelectedProducts(name, brand, type);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity createProduct(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidException();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{product_id}")
                .buildAndExpand(productService.createProduct(productDto)).toUri();
        return ResponseEntity.created(location).body(productDto);
    }
    @GetMapping("/{product_id}")
    public ProductDto getProductById(@PathVariable("product_id") String id){
        return productService.getProductById(id);
    }
    @DeleteMapping()
    public ResponseEntity deleteProduct(@RequestBody  String id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidException();
        }
        return new ResponseEntity(productService.deleteProduct(id),HttpStatus.OK);
    }
    @PutMapping()
    public ResponseEntity updateProduct(@RequestBody @Valid ProductDto productDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidException();
        }
        return new ResponseEntity(productService.updateProduct(productDto),HttpStatus.OK);
    }
}
