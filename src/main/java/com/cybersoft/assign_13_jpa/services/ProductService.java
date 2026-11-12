package com.cybersoft.assign_13_jpa.services;

import com.cybersoft.assign_13_jpa.request.InsertProductRequestDTO;
import com.cybersoft.assign_13_jpa.request.UpdateProductRequestDTO;
import com.cybersoft.assign_13_jpa.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getProducts();

    ProductResponseDTO getProductById(Long id);

    List<ProductResponseDTO> getProductsByCategoryId(Long categoryId);

    void deleteProductById(Long id);

    ProductResponseDTO addProduct(InsertProductRequestDTO dto);

    ProductResponseDTO updateProduct(UpdateProductRequestDTO dto);
}
