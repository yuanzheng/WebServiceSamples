package com.ytechtrade.inventorymanagementsystem.services;

import com.ytechtrade.inventorymanagementsystem.models.dtos.CategoryDTO;
import com.ytechtrade.inventorymanagementsystem.models.dtos.Response;

public interface CategoryService {
    Response createCategory(CategoryDTO categoryDTO);

    Response getAllCategories();

    Response getCategoryById(Long id);

    Response updateCategory(Long id, CategoryDTO categoryDTO);

    Response deleteCategory(Long id);
}
