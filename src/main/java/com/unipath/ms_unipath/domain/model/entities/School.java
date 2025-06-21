package com.unipath.ms_unipath.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @Column(name = "schoolUrl", nullable = false)
    private String schoolUrl;

    public School() {
    }

    public School(Long id, String name, String imageUrl, String schoolUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.schoolUrl = schoolUrl;
    }

    public School(String name, String imageUrl, String schoolUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.schoolUrl = schoolUrl;
    }
}
