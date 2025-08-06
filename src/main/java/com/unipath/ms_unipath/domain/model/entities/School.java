package com.unipath.ms_unipath.domain.model.entities;

import com.unipath.ms_unipath.rest.resources.school.CreateSchoolResource;
import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import com.unipath.ms_unipath.security.domain.model.entities.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
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

    public School(CreateSchoolResource resource) {
        this.name = resource.name();
        this.imageUrl = resource.imageUrl();
        this.schoolUrl = resource.schoolUrl();
    }
}
