package com.example.demo.service;

import com.example.demo.model.SearchQueryRecord;
import com.example.demo.model.Employee;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchQueryService {

    private final SearchQueryRecordRepository searchRepo;
    private final EmployeeSkillRepository employeeSkillRepo;

    public SearchQueryService(SearchQueryRecordRepository searchRepo,
                              EmployeeSkillRepository employeeSkillRepo){
        this.searchRepo = searchRepo;
        this.employeeSkillRepo = employeeSkillRepo;
    }

    public SearchQueryRecord saveQuery(SearchQueryRecord query){
        if(query.getSkillsRequested() == null || query.getSkillsRequested().isEmpty())
            throw new IllegalArgumentException("must not be empty");
        return searchRepo.save(query);
    }

    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId){
        if(skills == null || skills.isEmpty()) throw new IllegalArgumentException("must not be empty");

        List<Employee> results = employeeSkillRepo.findEmployeesByAllSkillNames(skills, userId);

        SearchQueryRecord record = new SearchQueryRecord();
        record.setSearcherId(userId);
        record.setSkillsRequested(String.join(",", skills));
        record.setResultsCount(results.size());
        saveQuery(record);

        return results;
    }

    public SearchQueryRecord getQueryById(Long id){
        return searchRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Query not found"));
    }

    public List<SearchQueryRecord> getQueriesForUser(Long userId){
        return searchRepo.findBySearcherId(userId);
    }
}
