package com.pachimari.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getList() {
        return CategoryAdapter.toListCategoryDTO(categoryRepository.findAll());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return CategoryAdapter.toCategoryDTO(categoryRepository.findByName(name));
    }

    @Override
    public CategoryDTO getCategoryById(int id) {
        return CategoryAdapter.toCategoryDTO(categoryRepository.findById(id));
    }


    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        Category category = Category.builder().id(categoryDTO.getId()).name(categoryDTO.getName()).build();
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id);
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return CategoryAdapter.toCategoryDTO(category);
    }
}
