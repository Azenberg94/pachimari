package com.pachimari.category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quentin on 02/04/17.
 */
public class CategoryAdapter {

    public static Category toCategory (CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .build();
    }

    public static CategoryDTO toCategoryDTO (Category category) {
        return CategoryDTO.builder().id(category.getId()).name(category.getName()).build();
    }

    public static List<CategoryDTO> toListCategoryDTO (List<Category> listCategory) {
        List<CategoryDTO> listCategoryDTO = new ArrayList<>();
        for (Category category: listCategory) {
            CategoryDTO categoryDTO = CategoryDTO.builder().id(category.getId()).name(category.getName()).build();
            listCategoryDTO.add(categoryDTO);
        }
        return listCategoryDTO;
    }

}
