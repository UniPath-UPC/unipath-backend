package com.unipath.ms_unipath.domain.model.entities;

import com.unipath.ms_unipath.rest.resources.career.CreateCareerResource;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "career")
public class Career {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Getter
    @Column(name = "imageUrl", nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UniversityCareer> universityCareers = new HashSet<>();

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TestCareer> testCareers = new HashSet<>();

    public Career() {
    }

    public Career(Long id, String name, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Career(CreateCareerResource resource) {
        this.name = resource.name();
        this.description = resource.description();
        this.imageUrl = resource.imageUrl();
    }
}
