package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.service.SearchQueryService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository searchQueryRecordRepository;
    private final EmployeeSkillRepository employeeSkillRepository;

    public SearchQueryServiceImpl(SearchQueryRecordRepository searchQueryRecordRepository,
                                  EmployeeSkillRepository employeeSkillRepository) {
        this.searchQueryRecordRepository = searchQueryRecordRepository;
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public SearchQueryRecord saveQuery(SearchQueryRecord record) {
        return searchQueryRecordRepository.save(record);
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("skills list must not be empty");
        }

        List<EmployeeSkill> allMappings = employeeSkillRepository.findAll();

        Map<Employee, Set<String>> map = new HashMap<>();
        for (EmployeeSkill es : allMappings) {
            if (!es.isActive() || !es.getEmployee().isActive() || !es.getSkill().isActive()) {
                continue;
            }
            map.computeIfAbsent(es.getEmployee(), e -> new HashSet<>())
               .add(es.getSkill().getName());
        }

        List<Employee> result = map.entrySet().stream()
                .filter(entry -> entry.getValue().containsAll(skills))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        SearchQueryRecord record = new SearchQueryRecord(
                null,
                userId,
                String.join(",", skills),
                result.size()
        );
        searchQueryRecordRepository.save(record);

        return result;
    }

    @Override
    public SearchQueryRecord getQueryById(Long id) {
        return searchQueryRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Query not found"));
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        return searchQueryRecordRepository.findBySearcherId(userId);
    }
}
