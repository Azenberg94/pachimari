package com.pachimari.category;

import java.util.List;


public interface CategoryService {

    public List<CategoryDTO> getList();
    public CategoryDTO getCategoryByName(String name);
    public CategoryDTO getCategoryById(String id);
    public void createCategory(CategoryDTO categoryDTO);
    public CategoryDTO deleteCategory(String id);
    public CategoryDTO updateCategory(CategoryDTO categoryDTO);

}
