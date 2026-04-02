package com.samu.dev.arcflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Office {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String cnpj;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
    private List<Project> projects;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;


}

