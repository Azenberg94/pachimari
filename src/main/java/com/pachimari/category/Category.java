package com.pachimari.category;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, name='%s']",
                id, name);
    }

}
