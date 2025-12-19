package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    // Basic helpers
    List<EmployeeSkill> findByEmployeeId(Long employeeId);

    List<EmployeeSkill> findBySkillId(Long skillId);

    // Required by tests
    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(long employeeId);

    List<EmployeeSkill> findBySkillIdAndActiveTrue(long skillId);

    // Required by tests: signature must match exactly
    @Query("""
           select es.employee
           from EmployeeSkill es
           where es.active = true
             and es.employee.active = true
             and es.skill.active = true
             and es.skill.name in ?1
           group by es.employee
           having count(distinct es.skill.name) = ?2
           """)
    List<Employee> findEmployeesByAllSkillNames(List<Object> skills, long userId);
}
