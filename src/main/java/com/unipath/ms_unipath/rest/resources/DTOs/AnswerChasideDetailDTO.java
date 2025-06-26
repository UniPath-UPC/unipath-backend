package com.unipath.ms_unipath.rest.resources.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerChasideDetailDTO {
    private Long id;            // ID real de la pregunta (usado para Chaside)
    private int score;          // 1 si marcó "Sí", 0 si "No"
    private Long questionId;    // (opcional) otro campo si quieres conservarlo

    public AnswerChasideDetailDTO() {}

    public AnswerChasideDetailDTO(Long id, int score) {
        this.id = id;
        this.score = score;
    }
}
