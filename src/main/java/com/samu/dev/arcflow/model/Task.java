package com.samu.dev.arcflow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phase_id", nullable = false)
    private ProjectPhase phase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_id", nullable = false)
    private User user;

    @Column(nullable = true)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "phase_order")
    private Integer order;

    @Column(name = "estimated_hours")
    private LocalTime estimatedHours;

    @Column(name = "dead_line")
    private LocalDateTime deadline;

    public Task() {
    }

    public Task(String id, ProjectPhase phase, User user, String title, String description, Status status, Integer order, LocalTime estimatedHours, LocalDateTime deadline) {
        this.id = id;
        this.phase = phase;
        this.user = user;
        this.title = title;
        this.description = description;
        this.status = status;
        this.order = order;
        this.estimatedHours = estimatedHours;
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProjectPhase getPhase() {
        return phase;
    }

    public void setPhase(ProjectPhase phase) {
        this.phase = phase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public LocalTime getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(LocalTime estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(phase, task.phase) && Objects.equals(user, task.user) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && status == task.status && Objects.equals(order, task.order) && Objects.equals(estimatedHours, task.estimatedHours) && Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phase, user, title, description, status, order, estimatedHours, deadline);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", phase=" + phase +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", order=" + order +
                ", estimatedHours=" + estimatedHours +
                ", deadline=" + deadline +
                '}';
    }
}
