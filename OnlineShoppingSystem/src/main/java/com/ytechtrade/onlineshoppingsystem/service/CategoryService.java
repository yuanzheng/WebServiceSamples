package com.ytechtrade.onlineshoppingsystem.service;

import com.ytechtrade.onlineshoppingsystem.payload.CategoryDTO;
import com.ytechtrade.onlineshoppingsystem.payload.CategoryResponse;

public interface CategoryService {
    CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
