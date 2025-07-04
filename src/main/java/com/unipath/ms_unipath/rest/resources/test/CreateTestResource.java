package com.unipath.ms_unipath.rest.resources.test;

import java.time.LocalDateTime;

public record CreateTestResource(String preferred_course_1, String genre, String preferred_course_2, String preferred_course_3, String district, String type_school, String area,String area2,
                                 Integer empathy_level, Integer listen_level, Integer solution_level, Integer communication_level, Integer teamwork_level, Float monthly_cost, LocalDateTime fechaRegistro) {
}
