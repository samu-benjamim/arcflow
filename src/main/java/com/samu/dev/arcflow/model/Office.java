package com.samu.dev.arcflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class Office {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Float id;
    private String name;
    private String email;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    public Office() {
    }

    public Office(Float id, String name, String email, OffsetDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Float getId() {
        return id;
    }

    public void setId(Float id) {
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return Objects.equals(id, office.id) && Objects.equals(name, office.name) && Objects.equals(email, office.email) && Objects.equals(createdAt, office.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, createdAt);
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

