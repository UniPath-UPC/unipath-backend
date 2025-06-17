package com.unipath.ms_unipath.rest.resources.test;

import com.unipath.ms_unipath.rest.resources.DTOs.AnswerChasideDetailDTO;

import java.util.List;

public record TestRequest(List<AnswerChasideDetailDTO> listAnswers, String genre, String preferred_course_1, String preferred_course_2, String preferred_course_3,String district, String type_school,
                          Integer empathy_level, Integer listen_level, Integer solution_level, Integer communication_level, Integer teamwork_level, Float monthly_cost) {
}
