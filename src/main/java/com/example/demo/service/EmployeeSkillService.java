package com.example.demo.service;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Employee;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillService(EmployeeSkillRepository employeeSkillRepository,
                                EmployeeRepository employeeRepository,
                                SkillRepository skillRepository){
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    public EmployeeSkill createEmployeeSkill(EmployeeSkill es){
        // Validation
        Employee emp = employeeRepository.findById(es.getEmployee().getId())
                .orElseThrow(() -> new IllegalArgumentException("inactive employee"));
        if(!emp.getActive()) throw new IllegalArgumentException("inactive employee");

        Skill skill = skillRepository.findById(es.getSkill().getId())
                .orElseThrow(() -> new IllegalArgumentException("inactive skill"));
        if(!skill.getActive()) throw new IllegalArgumentException("inactive skill");

        List<String> levels = Arrays.asList("Beginner","Intermediate","Advanced","Expert");
        if(!levels.contains(es.getProficiencyLevel())) throw new IllegalArgumentException("Invalid proficiency");

        if(es.getYearsOfExperience() < 0) throw new IllegalArgumentException("Experience years");

        return employeeSkillRepository.save(es);
    }

    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId){
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    public List<EmployeeSkill> getEmployeesBySkill(Long skillId){
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }

    public void deactivateEmployeeSkill(Long id){
        EmployeeSkill es = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mapping not found"));
        es.setActive(false);
        employeeSkillRepository.save(es);
    }
}
