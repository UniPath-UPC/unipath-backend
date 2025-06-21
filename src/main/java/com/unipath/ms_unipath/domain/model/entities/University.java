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
    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    @Getter
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Getter
    @Column(name = "description", nullable = false)
    private String description;

    @Getter
    @Column(name = "color", nullable = false)
    private String color;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UniversityCareer> universityCareers = new HashSet<>();

    public University() {
    }

    public University(Long id, String name, String logoUrl, String imageUrl, String description, String color) {
        this.id = id;
        this.name = name;
        this.logoUrl = logoUrl;
        this.imageUrl = imageUrl;
        this.description = description;
        this.color = color;
    }

    public University(CreateUniversityResource resource) {
        this.name = resource.name();
        this.logoUrl = resource.logoUrl();
        this.imageUrl = resource.imageUrl();
        this.description = resource.description();
        this.color = resource.color();
    }

    public University update (UpdateUniversityResource resource) {
        this.name = resource.name();
        this.imageUrl = resource.imageUrl();

        return this;
    }
}
