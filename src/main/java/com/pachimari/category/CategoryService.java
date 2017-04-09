package com.pachimari.category;

import java.util.List;

/**
 * Created by quentin on 05/04/17.
 */
public interface CategoryService {

    public List<CategoryDTO> getList();
    public void createCategory(CategoryDTO categoryDTO);
    public void deleteCategory(Integer id);
    public CategoryDTO updateCategory(CategoryDTO categoryDTO);

}
