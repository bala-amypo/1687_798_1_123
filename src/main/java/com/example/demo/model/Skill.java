package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;  // ← ADD: LocalDateTime

@Entity
@Table(name = "skills")  // ← ADD: Explicit table name
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    @Column(name = "category_id")  // ← ADD: FK to SkillCategory
    private Long categoryId;       // ← CHANGE: Long ID (not String category)
    
    private String description;
    private boolean active;
    
    private LocalDateTime createdAt;  // ← ADD: Test expects LocalDateTime
    private LocalDateTime updatedAt;  // ← ADD: Test expects LocalDateTime

    public Skill() {
    }

    public Skill(Long id, String name, Long categoryId,  // ← FIXED: Long categoryId
                String description, boolean active,
                LocalDateTime createdAt, LocalDateTime updatedAt) {  // ← ADD timestamps
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 🔥 LIFECYCLE METHODS (TESTS EXPECT THESE EXACT NAMES)
    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.active = true;  // ← Test: assertTrue(new Skill().getActive())
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getCategoryId() { return categoryId; }  // ← FIXED: Long getter
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // ADD THESE GETTERS (TESTS EXPECT THEM)
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
