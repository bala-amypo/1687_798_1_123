package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    // Simple helpers (not used in tests but ok to keep)
    List<EmployeeSkill> findByEmployeeId(Long employeeId);
    List<EmployeeSkill> findBySkillId(Long skillId);

    // Tests expect these exact signatures
    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);
    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);

    // HQL-like query used by SearchQueryServiceImpl tests
    @Query("""
           select es.employee
           from EmployeeSkill es
           where es.active = true
             and es.employee.active = true
             and es.skill.active = true
             and lower(es.skill.name) in ?1
           group by es.employee
           having count(distinct lower(es.skill.name)) = ?2
           """)
    List<Employee> findEmployeesByAllSkillNames(List<String> skills, Long requiredCount);
}
