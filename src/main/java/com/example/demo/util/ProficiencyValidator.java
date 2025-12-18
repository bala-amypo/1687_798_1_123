package com.example.demo.util;

import com.example.demo.model.EmployeeSkill;

import java.util.Set;

public class ProficiencyValidator {

    // Allowed proficiency levels
    private static final Set<String> ALLOWED_LEVELS = Set.of(
            "Beginner", "Intermediate", "Advanced", "Expert"
    );

    private ProficiencyValidator() {
        // utility class, no instances
    }

    /**
     * Validate the proficiency level and yearsOfExperience
     * for an EmployeeSkill mapping.
     *
     * Throws IllegalArgumentException with messages that your
     * test can assert on.
     */
    public static void validate(EmployeeSkill mapping) {
        if (mapping.getYearsOfExperience() < 0) {
            // message keyword from assignment: "Experience years"
            throw new IllegalArgumentException("Experience years must be >= 0");
        }

        String level = mapping.getProficiencyLevel();
        if (level == null || !ALLOWED_LEVELS.contains(level)) {
            // message keyword from assignment: "Invalid proficiency"
            throw new IllegalArgumentException("Invalid proficiency");
        }
    }
}
