package com.pachimari.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andrem on 05/04/2017.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<ProductDto> getAllProducts(){
        return ProductAdapter.fromProductListToDtoList(productRepository.findAll());
    }
    @Override
    public List<ProductDto> getSelectedProducts(String name, String brand, Integer typeId){
        return ProductAdapter.fromProductListToDtoList(productRepository.findByOptionalParameters(name, brand, typeId));
    }
    @Override
    public ProductDto createProduct(ProductDto productDto){
        productRepository.save(ProductAdapter.fromDtoToProduct(productDto));
        return productDto;
    }
    @Override
    public ProductDto deleteProduct(String id){
        ProductEntity productToDelete = productRepository.findById(id);
        productRepository.delete(productToDelete);
        return ProductAdapter.fromProductToDto(productToDelete);
    }
    @Override
    public ProductDto updateProduct(ProductDto productDto){
        productRepository.save(ProductAdapter.fromDtoToProduct(productDto));
        return productDto;
    }
    @Override
    public ProductDto getProductById(String id){
        ProductEntity productToRetrieve = productRepository.findById(id);
        return ProductAdapter.fromProductToDto(productToRetrieve);
    }

}
