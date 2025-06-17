package com.unipath.ms_unipath.security.domain.model.aggregates;

import com.unipath.ms_unipath.security.domain.model.entities.Role;
import com.unipath.ms_unipath.security.interfaces.rest.resources.SignUpResource;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;


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

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    public User() {
    }

    public User(Long id, String name, String lastName, LocalDate birthdate, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(SignUpResource command, Role role, String password) {
        this.name = command.name();
        this.lastName = command.lastName();
        this.birthdate = command.birthdate();
        this.email = command.email();
        this.password = password;
        this.role = role;
    }

}