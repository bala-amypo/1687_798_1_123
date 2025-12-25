package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill mapping) {
        // 1) experience must be >= 0
        if (mapping.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years cannot be negative");
        }

        // 2) proficiency must be one of allowed values
        String p = mapping.getProficiencyLevel();
        if (p == null ||
                !(p.equals("BEGINNER") ||
                  p.equals("INTERMEDIATE") ||
                  p.equals("ADVANCED") ||
                  p.equals("EXPERT"))) {
            throw new IllegalArgumentException("Invalid proficiency level");
        }

        // 3) load employee and ensure active
        Employee employee = employeeRepository.findById(mapping.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        if (!employee.getActive()) {
            throw new IllegalArgumentException("Cannot assign skill to inactive employee");
        }

        // 4) load skill and ensure active
        Skill skill = skillRepository.findById(mapping.getSkill().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));
        if (!skill.getActive()) {
            throw new IllegalArgumentException("Cannot assign inactive skill");
        }

        mapping.setEmployee(employee);
        mapping.setSkill(skill);
        mapping.onCreate();
        return employeeSkillRepository.save(mapping);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill mapping) {
        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));
        existing.setProficiencyLevel(mapping.getProficiencyLevel());
        existing.setYearsOfExperience(mapping.getYearsOfExperience());
        existing.setActive(mapping.getActive());
        existing.onUpdate();
        return employeeSkillRepository.save(existing);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        // tests expect findByEmployeeIdAndActiveTrue
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        // tests expect findBySkillIdAndActiveTrue
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }

    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill existing = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mapping not found"));
        existing.setActive(false);
        existing.onUpdate();
        employeeSkillRepository.save(existing);
    }
}
