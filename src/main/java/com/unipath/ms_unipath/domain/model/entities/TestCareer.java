package com.unipath.ms_unipath.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Optional;

@Entity
@Table(name = "test_career")
public class TestCareer {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

    @Getter
    @Column(name = "hit_rate")
    private Float hitRate;

    public TestCareer() {}

    public TestCareer(Test test, Career career, Float hitRate) {
        this.test = test;
        this.career = career;
        this.hitRate = hitRate;
    }

}
