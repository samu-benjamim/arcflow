package com.samu.dev.arcflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Office {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

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

    public Office() {
    }

    public Office(Long id, String name, String email, List<User> users, List<Project> projects, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.users = users;
        this.projects = projects;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Objects.equals(id, office.id) && Objects.equals(name, office.name) && Objects.equals(email, office.email) && Objects.equals(users, office.users) && Objects.equals(projects, office.projects) && Objects.equals(createdAt, office.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, users, projects, createdAt);
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", users=" + users +
                ", projects=" + projects +
                ", createdAt=" + createdAt +
                '}';
    }
}

