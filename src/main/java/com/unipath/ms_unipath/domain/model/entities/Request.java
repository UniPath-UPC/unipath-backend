package com.unipath.ms_unipath.domain.model.entities;

import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Entity
@Table(name = "request")
public class Request {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Getter
    @Column(name = "validation_code", nullable = false)
    private String validation_code;

    @Getter
    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDateTime fechaSolicitud;

    @Getter
    @Column(name = "validation", nullable = false)
    private Integer validation;

    public Request() {}

    public Request(User user, String validation_code, LocalDateTime fechaSolicitud, Integer validation) {
        this.user = user;
        this.validation_code = validation_code;
        this.fechaSolicitud = fechaSolicitud;
        this.validation = validation;
    }
}
