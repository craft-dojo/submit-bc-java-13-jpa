package com.cybersoft.assign_13_jpa.services.imp;

import com.cybersoft.assign_13_jpa.entity.CategoryEntity;
import com.cybersoft.assign_13_jpa.repository.CategoryRepository;
import com.cybersoft.assign_13_jpa.request.InsertCategoryRequestDTO;
import com.cybersoft.assign_13_jpa.request.UpdateCategoryRequestDTO;
import com.cybersoft.assign_13_jpa.response.CategoryResponseDTO;
import com.cybersoft.assign_13_jpa.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponseDTO> getCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(CategoryResponseDTO::mapToDTO)
                .toList();
    }

    @Override
    public CategoryResponseDTO getCategoryById(Long id) {
        CategoryEntity categoryExisting = categoryRepository.findById(id).orElse(null);
        return categoryExisting != null ? CategoryResponseDTO.mapToDTO(categoryExisting) : null;
    }

    @Override
    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format("Category with id %s not found", id));
        }

        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponseDTO addCategory(InsertCategoryRequestDTO dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new EntityNotFoundException(String.format("Category with name %s is existing", dto.getName()));
        }

        CategoryEntity newCategory = CategoryEntity
                .builder()
                .name(dto.getName())
                .products(new ArrayList<>())
                .build();

        CategoryEntity createdCategory = categoryRepository.save(newCategory);
        return CategoryResponseDTO.mapToDTO(createdCategory);
    }

    @Override
    public CategoryResponseDTO updateCategory(UpdateCategoryRequestDTO dto) {
        CategoryEntity categoryExisting = categoryRepository
                .findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format("Category with id %s not found",
                        dto.getId())));

        if (!categoryExisting.getName().equals(dto.getName())
                && categoryRepository.findByName(dto.getName()).isPresent()) {
            throw new EntityNotFoundException(String.format("Category with name %s already exists", dto.getName()));
        }

        CategoryEntity updateCategoryEntity = CategoryEntity
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .products(categoryExisting.getProducts())
                .build();

        CategoryEntity updatedCategory = categoryRepository.save(updateCategoryEntity);
        return CategoryResponseDTO.mapToDTO(updatedCategory);
    }
}
