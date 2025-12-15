package com.company.template.category.controller;

import com.company.template.dto.ApiResponse;
import com.company.template.category.dto.CategoryCreateRequest;
import com.company.template.category.dto.CategoryResponse;
import com.company.template.category.dto.CategoryUpdateRequest;
import com.company.template.category.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"}, allowCredentials = "true")
public class CategoryAdminController {

    private final CategoryService categoryService;

    /**
     * 카테고리 목록 조회 (계층 구조)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAllCategories(
            @RequestParam(required = false, defaultValue = "true") boolean withHierarchy) {
        List<CategoryResponse> responses = withHierarchy
                ? categoryService.getAllCategoriesWithHierarchy()
                : categoryService.getRootCategories();
        return ResponseEntity.ok(ApiResponse.success(responses));
    }

    /**
     * 카테고리 단건 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategory(@PathVariable Long id) {
        CategoryResponse response = categoryService.getCategory(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 카테고리 생성
     */
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(
            @Valid @RequestBody CategoryCreateRequest request) {
        CategoryResponse response = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    /**
     * 카테고리 수정
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CategoryUpdateRequest request) {
        CategoryResponse response = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 카테고리 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
