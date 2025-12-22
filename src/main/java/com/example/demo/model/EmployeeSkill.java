package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;  // ← ADD: LocalDateTime

@Entity
@Table(name = "employee_skills")  // ← ADD: Explicit table name
public class EmployeeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // ← ADD: Optimize loading
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)  // ← ADD: Optimize loading
    @JoinColumn(name = "skill_id")
    private Skill skill;

    private String proficiencyLevel;  // ← Tests expect: "BEGINNER", "INTERMEDIATE", etc.
    private int yearsOfExperience;
    private boolean active;
    
    private LocalDateTime createdAt;  // ← ADD: Test expects LocalDateTime
    private LocalDateTime updatedAt;  // ← ADD: Test expects LocalDateTime

    public EmployeeSkill() {
    }

    public EmployeeSkill(Long id, Employee employee, Skill skill,
                        String proficiencyLevel, int yearsOfExperience,
                        boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {  // ← ADD timestamps
        this.id = id;
        this.employee = employee;
        this.skill = skill;
        this.proficiencyLevel = proficiencyLevel;
        this.yearsOfExperience = yearsOfExperience;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 🔥 LIFECYCLE METHODS (TESTS CALL THESE DIRECTLY)
    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        this.active = true;  // ← Test expects: assertTrue(new EmployeeSkill().getActive())
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }

    public String getProficiencyLevel() { return proficiencyLevel; }
    public void setProficiencyLevel(String proficiencyLevel) { this.proficiencyLevel = proficiencyLevel; }

    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    // ADD THESE GETTERS (TESTS EXPECT THEM)
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
