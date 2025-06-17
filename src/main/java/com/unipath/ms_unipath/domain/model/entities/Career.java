package com.unipath.ms_unipath.domain.model.entities;

import com.unipath.ms_unipath.rest.resources.career.CreateCareerResource;
import com.unipath.ms_unipath.rest.resources.university.CreateUniversityResource;
import com.unipath.ms_unipath.rest.resources.university.UpdateUniversityResource;
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
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UniversityCareer> universityCareers = new HashSet<>();

    @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TestCareer> testCareers = new HashSet<>();

    public Career() {
    }

    public Career(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Career(CreateCareerResource resource) {
        this.name = resource.name();
        this.description = resource.description();
    }
}
