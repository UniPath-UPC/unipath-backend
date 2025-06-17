package com.unipath.ms_unipath.security.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "roles")
@Getter
public class Role {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    public Role(){
    }

    public Role(String name) {
        this.name = name;
    }

}
