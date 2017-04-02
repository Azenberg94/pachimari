package com.pachimari.product;

/**
 * Created by andrem on 23/03/2017.
 */
public class ProductAdapter {

    public static ProductDto fromProductToDto(ProductEntity product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .typeId(product.getTypeId())
                .price(product.getPrice())
                .imageURL(product.getImageURL())
                .build();
    }

    public static ProductEntity fromDtoToProduct(ProductDto productDto){
        return ProductEntity.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .brand(productDto.getBrand())
                .typeId(productDto.getTypeId())
                .price(productDto.getPrice())
                .imageURL(productDto.getImageURL())
                .build();
    }

}
