package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;  // ← FIXED: LocalDateTime

@Entity
@Table(name = "search_query_records")  // ← ADD: Explicit table name
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;
    private String skillsRequested;
    private int resultsCount;
    
    private LocalDateTime searchedAt;  // ← FIXED: LocalDateTime (NOT Instant)

    public SearchQueryRecord() {
    }

    public SearchQueryRecord(Long id, Long searcherId,
                            String skillsRequested, int resultsCount,
                            LocalDateTime searchedAt) {  // ← FIXED: LocalDateTime param
        this.id = id;
        this.searcherId = searcherId;
        this.skillsRequested = skillsRequested;
        this.resultsCount = resultsCount;
        this.searchedAt = searchedAt;
    }

    // 🔥 LIFECYCLE METHODS (TESTS EXPECT THESE)
    @PrePersist
    public void onCreate() {
        this.searchedAt = LocalDateTime.now();  // ← FIXED: LocalDateTime.now()
    }

    @PreUpdate  // ← ADD: Missing from your version
    public void onUpdate() {
        this.searchedAt = LocalDateTime.now();  // Refresh timestamp on updates
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSearcherId() { return searcherId; }
    public void setSearcherId(Long searcherId) { this.searcherId = searcherId; }

    public String getSkillsRequested() { return skillsRequested; }
    public void setSkillsRequested(String skillsRequested) { this.skillsRequested = skillsRequested; }

    public int getResultsCount() { return resultsCount; }
    public void setResultsCount(int resultsCount) { this.resultsCount = resultsCount; }

    public LocalDateTime getSearchedAt() { return searchedAt; }  // ← FIXED return type
    public void setSearchedAt(LocalDateTime searchedAt) { this.searchedAt = searchedAt; }
}
