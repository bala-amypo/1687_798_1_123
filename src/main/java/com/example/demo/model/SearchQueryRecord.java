package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;
    private String skillsRequested;
    private int resultsCount;

    public SearchQueryRecord() {
    }

    public SearchQueryRecord(Long id, Long searcherId,
                             String skillsRequested, int resultsCount) {
        this.id = id;
        this.searcherId = searcherId;
        this.skillsRequested = skillsRequested;
        this.resultsCount = resultsCount;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getSearcherId() {
        return searcherId;
    }
    public void setSearcherId(Long searcherId) {
        this.searcherId = searcherId;
    }

    public String getSkillsRequested() {
        return skillsRequested;
    }
    public void setSkillsRequested(String skillsRequested) {
        this.skillsRequested = skillsRequested;
    }

    public int getResultsCount() {
        return resultsCount;
    }
    public void setResultsCount(int resultsCount) {
        this.resultsCount = resultsCount;
    }
}
