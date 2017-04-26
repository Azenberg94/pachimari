package com.pachimari.product;

import com.pachimari.order.model.OrderEntity;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by andrem on 23/03/2017.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ProductDto {
    @GeneratedValue(strategy = IDENTITY)
    @NotNull
    @Min(0)
    private String id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private String brand;
    @NotNull
    private String typeId;
    @NotNull
    @Min(0)
    private double price;
    @NotNull
    private String imageURL;

    /*
    public ProductDto(String name, String brand, String typeId, double price, String imageURL){
        this.name = name;
        this.brand = brand;
        this.typeId = typeId;
        this.price = price;
        this.imageURL = imageURL;
    }*/
}
