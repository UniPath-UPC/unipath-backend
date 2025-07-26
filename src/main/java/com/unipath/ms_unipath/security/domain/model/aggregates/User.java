package com.unipath.ms_unipath.security.domain.model.aggregates;

import com.unipath.ms_unipath.domain.model.entities.University;
import com.unipath.ms_unipath.rest.resources.university.UpdateUniversityResource;
import com.unipath.ms_unipath.rest.resources.user.UpdateUserResource;
import com.unipath.ms_unipath.security.domain.model.entities.Role;
import com.unipath.ms_unipath.security.interfaces.rest.resources.SignUpResource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Entity
@Table(name = "\"users\"")
@Getter
public class User {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Column(name = "lastname", nullable = false, length = 40)
    private String lastName;
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;
    @Column(name = "email", nullable = false, length = 40)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String school;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    public User() {
    }

    public User(Long id, String name, String lastName, LocalDate birthdate, String email, String password, Role role, String school) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.role = role;
        this.school = school;
    }

    public User(SignUpResource command, Role role, String password) {
        this.name = command.name();
        this.lastName = command.lastName();
        this.birthdate = command.birthdate();
        this.email = command.email();
        this.password = password;
        this.school = command.school();
        this.role = role;
    }

    public User update (UpdateUserResource resource, String password) {
        this.name = resource.name();
        this.lastName = resource.lastName();
        this.birthdate = resource.birthdate();
        this.email = resource.email();
        this.password = password;
        this.school = resource.school();

        return this;
    }
}