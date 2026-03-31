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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ProjectPhase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Enumerated(EnumType.STRING)
    private PhaseType type;

    @Enumerated(EnumType.STRING)
    private PhaseStatus status = PhaseStatus.PENDENTE;

    @Column(name = "phase_order")
    private Integer order;

    private BigDecimal completionPct = BigDecimal.ZERO;
    private LocalDateTime startDate;
    private LocalDateTime deadline;


    public ProjectPhase() {
    }

    public ProjectPhase(String id, Project project, PhaseType type, PhaseStatus status, Integer order, BigDecimal completionPct, LocalDateTime startDate, LocalDateTime deadline) {
        this.id = id;
        this.project = project;
        this.type = type;
        this.status = status;
        this.order = order;
        this.completionPct = completionPct;
        this.startDate = startDate;
        this.deadline = deadline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public PhaseType getType() {
        return type;
    }

    public void setType(PhaseType type) {
        this.type = type;
    }

    public PhaseStatus getStatus() {
        return status;
    }

    public void setStatus(PhaseStatus status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public BigDecimal getCompletionPct() {
        return completionPct;
    }

    public void setCompletionPct(BigDecimal completionPct) {
        this.completionPct = completionPct;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
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
        ProjectPhase that = (ProjectPhase) o;
        return Objects.equals(id, that.id) && Objects.equals(project, that.project) && type == that.type && status == that.status && Objects.equals(order, that.order) && Objects.equals(completionPct, that.completionPct) && Objects.equals(startDate, that.startDate) && Objects.equals(deadline, that.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, project, type, status, order, completionPct, startDate, deadline);
    }

    @Override
    public String toString() {
        return "ProjectPhase{" +
                "id='" + id + '\'' +
                ", project=" + project +
                ", type=" + type +
                ", status=" + status +
                ", order=" + order +
                ", completionPct=" + completionPct +
                ", startDate=" + startDate +
                ", deadline=" + deadline +
                '}';
    }
}
