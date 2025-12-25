package com.example.demo.controller;

import com.example.demo.model.SkillCategory;
import com.example.demo.service.SkillCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill-categories")
public class SkillCategoryController {

    private final SkillCategoryService categoryService;

    public SkillCategoryController(SkillCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public SkillCategory create(@RequestBody SkillCategory category) {
        return categoryService.createCategory(category);
    }

    @PutMapping("/{id}")
    public SkillCategory update(@PathVariable Long id, @RequestBody SkillCategory category) {
        return categoryService.updateCategory(id, category);
    }

    @GetMapping("/{id}")
    public SkillCategory getById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<SkillCategory> getAll() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        categoryService.deactivateCategory(id);
    }
}
