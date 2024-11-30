package com.coding.dojo.Ecommerce.services.interf;

import com.coding.dojo.Ecommerce.dto.CategoryDto;
import com.coding.dojo.Ecommerce.dto.Response;

public interface CategoryService {

    Response createCategory(CategoryDto categoryRequest);
    Response updateCategory(Long categoryId, CategoryDto categoryRequest);
    Response getAllCategories();
    Response getCategoryById(Long categoryId);
    Response deleteCategory(Long categoryId);
}