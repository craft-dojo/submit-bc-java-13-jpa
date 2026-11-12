package com.cybersoft.assign_13_jpa.controller;

import com.cybersoft.assign_13_jpa.request.InsertCategoryRequestDTO;
import com.cybersoft.assign_13_jpa.request.UpdateCategoryRequestDTO;
import com.cybersoft.assign_13_jpa.response.BaseResponse;
import com.cybersoft.assign_13_jpa.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getCategories() {
        BaseResponse res = BaseResponse.success(categoryService.getCategories());
        return ResponseEntity.ok(res);
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getCategoryById(@PathVariable Long id) {
        BaseResponse res = BaseResponse.success(categoryService.getCategoryById(id));
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> addCategory(@RequestBody InsertCategoryRequestDTO dto) {
        BaseResponse res = BaseResponse.success(categoryService.addCategory(dto));
        return ResponseEntity.ok(res);
    }

    @PutMapping
    public ResponseEntity<BaseResponse> updateCategory(@RequestBody UpdateCategoryRequestDTO dto) {
        BaseResponse res = BaseResponse.success(categoryService.updateCategory(dto));
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok(BaseResponse.success(null));
    }

}
