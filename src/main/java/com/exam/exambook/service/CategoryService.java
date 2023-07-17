package com.exam.exambook.service;

import com.exam.exambook.model.Category;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface CategoryService {

    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public Set<Category> getCategoriesSet();

    public Category getCategory(Long categoryId);

    public void deleteCategory(Long categoryId);
}
