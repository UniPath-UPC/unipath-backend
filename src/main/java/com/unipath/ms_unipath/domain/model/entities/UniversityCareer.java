package com.unipath.ms_unipath.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "university_career")
public class UniversityCareer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

    @Column(name = "website_url")
    private String websiteUrl;

    public UniversityCareer() {}

    public UniversityCareer(University university, Career career, String websiteUrl) {
        this.university = university;
        this.career = career;
        this.websiteUrl = websiteUrl;
    }

}
