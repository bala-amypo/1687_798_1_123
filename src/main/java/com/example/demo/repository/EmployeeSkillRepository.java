package com.example.demo.repository;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    @Query("SELECT es.employee FROM EmployeeSkill es " +
           "WHERE es.skill.name IN :skills AND es.employee.active = true " +
           "GROUP BY es.employee HAVING COUNT(DISTINCT es.skill.id) = :#{#skills.size()}")
    List<Employee> findEmployeesByAllSkillNames(@Param("skills") List<String> skills, @Param("userId") Long userId);

    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);
    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);
}
