package com.pachimari.product;

import java.util.ArrayList;
import java.util.List;

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

    public static List<ProductDto> fromProductListToDtoList(List<ProductEntity> productEntities){
        List<ProductDto> productDtoList = new ArrayList<ProductDto>();
        for(ProductEntity productEntity : productEntities){
            productDtoList.add(fromProductToDto(productEntity));
        }
        return  productDtoList;
    }
}
