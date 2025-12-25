package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@Entity
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;
    private String skillsRequested;
    private int resultsCount;
    private LocalDateTime searchedAt;

    public SearchQueryRecord() {
    }

    public SearchQueryRecord(Long id,
                             Long searcherId,
                             String skillsRequested,
                             int resultsCount,
                             LocalDateTime searchedAt) {
        this.id = id;
        this.searcherId = searcherId;
        this.skillsRequested = skillsRequested;
        this.resultsCount = resultsCount;
        this.searchedAt = searchedAt;
    }

    @PrePersist
    public void onCreate() {
        this.searchedAt = LocalDateTime.now();
        // do NOT overwrite resultsCount
    }

    @PreUpdate
    public void onUpdate() {
        this.searchedAt = LocalDateTime.now();
    }

    // getters/setters unchanged
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSearcherId() { return searcherId; }
    public void setSearcherId(Long searcherId) { this.searcherId = searcherId; }

    public String getSkillsRequested() { return skillsRequested; }
    public void setSkillsRequested(String skillsRequested) { this.skillsRequested = skillsRequested; }

    public int getResultsCount() { return resultsCount; }
    public void setResultsCount(int resultsCount) { this.resultsCount = resultsCount; }

    public LocalDateTime getSearchedAt() { return searchedAt; }
    public void setSearchedAt(LocalDateTime searchedAt) { this.searchedAt = searchedAt; }
}
