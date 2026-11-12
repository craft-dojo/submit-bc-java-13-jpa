package com.cybersoft.assign_13_jpa.controller;

import com.cybersoft.assign_13_jpa.request.InsertProductRequestDTO;
import com.cybersoft.assign_13_jpa.request.UpdateProductRequestDTO;
import com.cybersoft.assign_13_jpa.response.BaseResponse;
import com.cybersoft.assign_13_jpa.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllProducts() {
        BaseResponse res = BaseResponse.success(productService.getProducts());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getProductById(@PathVariable Long id) {
        BaseResponse res = BaseResponse.success(productService.getProductById(id));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search/{categoryId}")
    public ResponseEntity<BaseResponse> searchProducts(@PathVariable Long categoryId) {
        BaseResponse res = BaseResponse.success(productService.getProductsByCategoryId(categoryId));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok(BaseResponse.success(null));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> insertProduct(@RequestBody InsertProductRequestDTO dto) {
        BaseResponse res = BaseResponse.success(productService.addProduct(dto));
        return ResponseEntity.ok(res);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateProduct(@RequestBody UpdateProductRequestDTO dto) {
        BaseResponse res = BaseResponse.success(productService.updateProduct(dto));
        return ResponseEntity.ok(res);
    }

}
