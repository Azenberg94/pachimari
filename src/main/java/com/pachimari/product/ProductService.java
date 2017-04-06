package com.pachimari.product;

import java.util.List;

/**
 * Created by andrem on 05/04/2017.
 */
public interface ProductService {
    public List<ProductDto> getAllProducts();
    public List<ProductDto> getSelectedProducts(String name, String brand, String typeId);

    public ProductDto createProduct(ProductDto productDto);

    public ProductDto deleteProduct(String id);
    public ProductDto updateProduct(ProductDto productDto);

    public ProductDto getProductById(String id);
}
