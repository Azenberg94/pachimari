package com.pachimari.category;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CategoryServiceImpl categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    /*
    private List<Category> categories = Stream.of(
            Category.builder().id(1).name("Smartphone").build(),
            Category.builder().id(2).name("Tablet").build(),
            Category.builder().id(3).name("TV").build()
    ).collect(toList());
    */

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getCategories(@RequestParam(value = "query", required = false) Integer query) {
        return categoryService.getList();
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable("name") String name) {
        return categoryService.getCategoryByName(name);
    }

    @GetMapping("/category/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryById(@PathVariable("id") String id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
        categoryService.createCategory(categoryDTO);
        return categoryDTO;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
        return categoryService.updateCategory(categoryDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteCategory(@RequestBody String id, BindingResult bindingResult) {
        return new ResponseEntity(categoryService.deleteCategory(id),HttpStatus.OK);
        //categoryService.deleteCategory(id);
    }


}
