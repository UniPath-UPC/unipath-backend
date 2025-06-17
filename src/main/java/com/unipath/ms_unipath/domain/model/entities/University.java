package com.unipath.ms_unipath.domain.model.entities;

import com.unipath.ms_unipath.rest.resources.university.CreateUniversityResource;
import com.unipath.ms_unipath.rest.resources.university.UpdateUniversityResource;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "university")
public class University {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UniversityCareer> universityCareers = new HashSet<>();

    public University() {
    }

    public University(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public University(CreateUniversityResource resource) {
        this.name = resource.name();
        this.imageUrl = resource.imageUrl();
    }

    public University update (UpdateUniversityResource resource) {
        this.name = resource.name();
        this.imageUrl = resource.imageUrl();

        return this;
    }
}
