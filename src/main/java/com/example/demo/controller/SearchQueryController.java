package com.example.demo.controller;

import com.example.demo.model.SearchQueryRecord;
import com.example.demo.service.SearchQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchQueryController {

    private final SearchQueryService searchQueryService;

    public SearchQueryController(SearchQueryService searchQueryService) {
        this.searchQueryService = searchQueryService;
    }

    @GetMapping
    public List search(@RequestParam List<String> skills,
                       @RequestParam Long userId) {
        return searchQueryService.searchEmployeesBySkills(skills, userId);
    }

    @GetMapping("/query/{id}")
    public SearchQueryRecord getQueryById(@PathVariable Long id) {
        return searchQueryService.getQueryById(id);
    }

    @GetMapping("/user/{userId}")
    public List<SearchQueryRecord> getQueriesForUser(@PathVariable Long userId) {
        return searchQueryService.getQueriesForUser(userId);
    }
}
