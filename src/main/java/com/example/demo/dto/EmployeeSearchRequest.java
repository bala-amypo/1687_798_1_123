package com.example.demo.dto;

import java.util.List;

public class EmployeeSearchRequest {

    private List<String> skills;
    private Long userId;

    public EmployeeSearchRequest() {
    }

    public EmployeeSearchRequest(List<String> skills, Long userId) {
        this.skills = skills;
        this.userId = userId;
    }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
