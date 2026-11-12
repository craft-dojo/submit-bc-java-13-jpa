package com.cybersoft.assign_13_jpa.services.imp;

import com.cybersoft.assign_13_jpa.entity.CategoryEntity;
import com.cybersoft.assign_13_jpa.entity.ProductEntity;
import com.cybersoft.assign_13_jpa.repository.CategoryRepository;
import com.cybersoft.assign_13_jpa.repository.ProductRepository;
import com.cybersoft.assign_13_jpa.request.InsertProductRequestDTO;
import com.cybersoft.assign_13_jpa.request.UpdateProductRequestDTO;
import com.cybersoft.assign_13_jpa.response.ProductResponseDTO;
import com.cybersoft.assign_13_jpa.services.ProductService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductResponseDTO> getProducts() {
        return productRepository
                .findAll()
                .stream()
                .map(ProductResponseDTO::mapToDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        ProductEntity productExiting = productRepository
                .findById(id)
                .orElse(null);

        return productExiting != null ? ProductResponseDTO.mapToDTO(productExiting) : null;
    }

    @Override
    public void deleteProductById(Long id) {
        ProductEntity productExiting = productRepository
                .findById(id)
                .orElseThrow(() -> {
                    String message = String.format("Product with id %s not found", id);
                    return new EntityNotFoundException(message);
                });

        productExiting
                .getCategory()
                .getProducts()
                .remove(productExiting);

        productRepository.delete(productExiting);
    }

    @Override
    public ProductResponseDTO addProduct(InsertProductRequestDTO dto) {
        if (productRepository.existsByName(dto.getName())) {
            throw new EntityExistsException(String.format("Product with name %s already exists", dto.getName()));
        }

        CategoryEntity categoryExisting = categoryRepository
                .findById(dto.getCategoryId())
                .orElseThrow(() -> {
                    String message = String.format("Category with id %s not found", dto.getCategoryId());
                    return new EntityNotFoundException(message);
                });

        ProductEntity newProduct = ProductEntity
                .builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .category(categoryExisting)
                .build();

        ProductEntity createdProduct = productRepository.save(newProduct);
        categoryExisting.getProducts().add(createdProduct);
        return ProductResponseDTO.mapToDTO(createdProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(UpdateProductRequestDTO dto) {
        ProductEntity productExiting = productRepository
                .findById(dto.getId())
                .orElseThrow(() -> {
                    String errMes = String.format("Product with id %s not found", dto.getId());
                    return new EntityNotFoundException(errMes);
                });

        if (!productExiting.getName().equals(dto.getName())
                && productRepository.findByName(dto.getName()).isPresent()) {
            throw new EntityExistsException(String.format("Product with name %s already exists", dto.getName()));
        }

        CategoryEntity categoryExist = categoryRepository
                .findById(dto.getCategoryId())
                .orElseThrow(() -> {
                    String errMes = String.format("Category with id %s not found", dto.getCategoryId());
                    return new EntityNotFoundException(errMes);
                });

        categoryExist.getProducts().remove(productExiting);

        ProductEntity updateProduct = ProductEntity
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .category(categoryExist)
                .build();

        ProductEntity updatedProduct = productRepository.save(updateProduct);
        categoryExist.getProducts().add(updatedProduct);

        return ProductResponseDTO.mapToDTO(updatedProduct);
    }

    @Override
    public List<ProductResponseDTO> getProductsByCategoryId(Long categoryId) {
        CategoryEntity categoryExiting = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> {
                    String errMes = String.format("Category with id %s not found", categoryId);
                    return new EntityNotFoundException(errMes);
                });

        return productRepository
                .findByCategory(categoryExiting)
                .stream()
                .map(ProductResponseDTO::mapToDTO)
                .toList();
    }

}
