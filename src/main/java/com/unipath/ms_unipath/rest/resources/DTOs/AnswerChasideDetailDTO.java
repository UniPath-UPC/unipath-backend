package com.unipath.ms_unipath.rest.resources.DTOs;

import lombok.Getter;

@Getter
public class AnswerChasideDetailDTO {
    // Getters y Setters
    private Long id;
    private int score; // Puntuación de la respuesta (ej. 1 para "Sí", 0 para "No")

    // Constructores
    public AnswerChasideDetailDTO() {}

    public AnswerChasideDetailDTO(Long id, int score) {
        this.id = id;
        this.score = score;
    }

    public void setQuestionId(Long id) { this.id = id; }

    public void setScore(int score) { this.score = score; }
}
