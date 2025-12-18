package com.example.demo.service;

import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillCategoryService {

    private final SkillCategoryRepository skillCategoryRepository;

    public SkillCategoryService(SkillCategoryRepository skillCategoryRepository){
        this.skillCategoryRepository = skillCategoryRepository;
    }

    public SkillCategory createCategory(SkillCategory category){
        return skillCategoryRepository.save(category);
    }

    public SkillCategory updateCategory(Long id, SkillCategory category){
        SkillCategory existing = getCategoryById(id);
        existing.setCategoryName(category.getCategoryName());
        existing.setDescription(category.getDescription());
        return skillCategoryRepository.save(existing);
    }

    public SkillCategory getCategoryById(Long id){
        return skillCategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    public List<SkillCategory> getAllCategories(){
        return skillCategoryRepository.findAll();
    }

    public void deactivateCategory(Long id){
        SkillCategory cat = getCategoryById(id);
        cat.setActive(false);
        skillCategoryRepository.save(cat);
    }
}
