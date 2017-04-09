package com.pachimari.category;

import java.util.List;


public interface CategoryService {

    public List<CategoryDTO> getList();
    public CategoryDTO getCategoryByName(String name);
    public CategoryDTO getCategoryById(int id);
    public void createCategory(CategoryDTO categoryDTO);
    public void deleteCategory(Integer id);
    public CategoryDTO updateCategory(CategoryDTO categoryDTO);

}
