package com.samu.dev.arcflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Office {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    @Size(min = 14, max = 14)
    private String cnpj;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "office")
    private List<User> users;

    @OneToMany(mappedBy = "office")
    private List<Project> projects;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public void addUser(User user) {
        user.setOffice(this);
        this.users.add(user);
    }

    public void addProject(Project project) {
        project.setOffice(this);
        this.projects.add(project);
    }


}

