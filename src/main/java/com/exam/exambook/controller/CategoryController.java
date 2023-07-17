package com.exam.exambook.controller;

import com.exam.exambook.model.Category;
import com.exam.exambook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category savedCategory = this.categoryService.addCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        Category category = this.categoryService.getCategory(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategories() {
        Set<Category> categoriesSet = this.categoryService.getCategoriesSet();
        return ResponseEntity.ok(categoriesSet);
    }

    @PutMapping("/")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        Category updatedCategory = this.categoryService.updateCategory(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted!");
    }

}
