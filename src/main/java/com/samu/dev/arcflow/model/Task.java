package com.samu.dev.arcflow.model;

import com.samu.dev.arcflow.model.types.TaskStatus;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDENTE;

    @Column(name = "phase_order")
    private Integer order;

    @Column(name = "estimated_hours",precision = 5, scale = 2)
    private BigDecimal estimatedHours;

    @Column(name = "dead_line")
    private LocalDate deadline;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TimeEntry> timeEntrys;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Task() {
    }

    public Task(String id, ProjectPhase phase, User user, String title, String description, TaskStatus status, Integer order, BigDecimal estimatedHours, LocalDate deadline, List<TimeEntry> timeEntrys, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.phase = phase;
        this.user = user;
        this.title = title;
        this.description = description;
        this.status = status;
        this.order = order;
        this.estimatedHours = estimatedHours;
        this.deadline = deadline;
        this.timeEntrys = timeEntrys;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public BigDecimal getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(BigDecimal estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public List<TimeEntry> getTimeEntrys() {
        return timeEntrys;
    }

    public void setTimeEntrys(List<TimeEntry> timeEntrys) {
        this.timeEntrys = timeEntrys;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(phase, task.phase) && Objects.equals(user, task.user) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && status == task.status && Objects.equals(order, task.order) && Objects.equals(estimatedHours, task.estimatedHours) && Objects.equals(deadline, task.deadline) && Objects.equals(timeEntrys, task.timeEntrys) && Objects.equals(createdAt, task.createdAt) && Objects.equals(updatedAt, task.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phase, user, title, description, status, order, estimatedHours, deadline, timeEntrys, createdAt, updatedAt);
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
                ", timeEntrys=" + timeEntrys +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
