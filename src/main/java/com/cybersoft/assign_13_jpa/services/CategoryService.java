package com.cybersoft.assign_13_jpa.services;

import com.cybersoft.assign_13_jpa.request.InsertCategoryRequestDTO;
import com.cybersoft.assign_13_jpa.request.UpdateCategoryRequestDTO;
import com.cybersoft.assign_13_jpa.response.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getCategories();

    CategoryResponseDTO getCategoryById(Long id);

    void deleteCategoryById(Long id);

    CategoryResponseDTO addCategory(InsertCategoryRequestDTO dto);

    CategoryResponseDTO updateCategory(UpdateCategoryRequestDTO dto);
}
