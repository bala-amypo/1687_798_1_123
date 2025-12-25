package com.example.demo.validator;

import java.util.List;

public class ProficiencyValidator {

    // Allowed proficiency values used in tests: BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
    private static final List<String> ALLOWED = List.of(
            "BEGINNER",
            "INTERMEDIATE",
            "ADVANCED",
            "EXPERT"
    );

    public static void validate(String proficiency) {
        if (proficiency == null || !ALLOWED.contains(proficiency)) {
            throw new IllegalArgumentException("Invalid proficiency level");
        }
    }
}
