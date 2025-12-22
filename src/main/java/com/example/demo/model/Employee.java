package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;  // ← CHANGED: LocalDateTime

@Entity
@Table(name = "employees")  // ← ADD: Explicit table name
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String department;
    private String jobTitle;
    private boolean active;
    
    private LocalDateTime createdAt;  // ← FIXED: LocalDateTime
    private LocalDateTime updatedAt;  // ← FIXED: LocalDateTime

    // Default constructor
    public Employee() {
    }

    // Parameterized constructor (KEEP but update types)
    public Employee(Long id, String fullName, String email,
                   String department, String jobTitle,
                   boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {  // ← FIXED types
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.jobTitle = jobTitle;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();  // ← FIXED: LocalDateTime.now()
        this.createdAt = now;
        this.updatedAt = now;
        this.active = true;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();  // ← FIXED: LocalDateTime.now()
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDateTime getCreatedAt() { return createdAt; }  // ← FIXED return type
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }  // ← FIXED return type
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
