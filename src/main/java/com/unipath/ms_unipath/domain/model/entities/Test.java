package com.unipath.ms_unipath.domain.model.entities;

import com.unipath.ms_unipath.rest.resources.test.CreateTestResource;
import com.unipath.ms_unipath.security.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "genre", nullable = false)
    private String genre;

    @Getter
    @Column(name = "preferred_course_1", nullable = false)
    private String preferred_course_1;

    @Getter
    @Column(name = "preferred_course_2", nullable = false)
    private String preferred_course_2;

    @Getter
    @Column(name = "preferred_course_3", nullable = false)
    private String preferred_course_3;

    @Getter
    @Column(name = "district", nullable = false)
    private String district;

    @Getter
    @Column(name = "type_school", nullable = false)
    private String type_school;

    @Getter
    @Column(name = "area", nullable = false)
    private String area;

    @Getter
    @Column(name = "area2", nullable = false)
    private String area2;


    @Getter
    @Column(name = "empathy_level", nullable = false)
    private Integer empathy_level;

    @Getter
    @Column(name = "listen_level", nullable = false)
    private Integer listen_level;

    @Getter
    @Column(name = "solution_level", nullable = false)
    private Integer solution_level;

    @Getter
    @Column(name = "communication_level", nullable = false)
    private Integer communication_level;

    @Getter
    @Column(name = "teamwork_level", nullable = false)
    private Integer teamwork_level;

    @Getter
    @Column(name = "monthly_cost", nullable = false)
    private Float monthly_cost;

    @Getter
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TestCareer> testCareers = new HashSet<>();

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Getter
    @Column(name = "flg_favorites", nullable = false)
    private Integer flg_favorites;

    public Test(){
    }

    public Test(CreateTestResource testResource, User user) {
        this.genre = testResource.genre();
        this.preferred_course_1 = testResource.preferred_course_1();
        this.preferred_course_2 = testResource.preferred_course_2();
        this.preferred_course_3 = testResource.preferred_course_3();
        this.district = testResource.district();
        this.type_school = testResource.type_school();
        this.area = testResource.area();
        this.area2 = testResource.area2();
        this.empathy_level = testResource.empathy_level();
        this.listen_level = testResource.listen_level();
        this.solution_level = testResource.solution_level();
        this.communication_level = testResource.communication_level();
        this.teamwork_level = testResource.teamwork_level();
        this.monthly_cost = testResource.monthly_cost();
        this.user = user;
        this.fechaRegistro = testResource.fechaRegistro();
        this.flg_favorites = 0;
    }
}
