package com.samu.dev.arcflow.model;

import com.samu.dev.arcflow.model.types.ProjectType;
import com.samu.dev.arcflow.model.types.Status;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private ProjectType type;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "total_area_m2")
    private BigDecimal totalAreaM2;

    @Column(name = "contract_value")
    private BigDecimal contractValue;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "dead_line")
    private LocalDate deadline;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectPhase> phaseList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Modification> modifications;

    public Project() {
    }

    public Project(Long id, Office office, Client client, User responsible, String name, ProjectType type, Status status, BigDecimal totalAreaM2, BigDecimal contractValue, LocalDate startDate, LocalDate deadline, List<ProjectPhase> phaseList, List<Modification> modifications) {
        this.id = id;
        this.office = office;
        this.client = client;
        this.user = responsible;
        this.name = name;
        this.type = type;
        this.status = status;
        this.totalAreaM2 = totalAreaM2;
        this.contractValue = contractValue;
        this.startDate = startDate;
        this.deadline = deadline;
        this.phaseList = phaseList;
        this.modifications = modifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getResponsible() {
        return user;
    }

    public void setResponsible(User responsible) {
        this.user = responsible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectType getType() {
        return type;
    }

    public void setType(ProjectType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getTotalAreaM2() {
        return totalAreaM2;
    }

    public void setTotalAreaM2(BigDecimal totalAreaM2) {
        this.totalAreaM2 = totalAreaM2;
    }

    public BigDecimal getContractValue() {
        return contractValue;
    }

    public void setContractValue(BigDecimal contractValue) {
        this.contractValue = contractValue;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public List<ProjectPhase> getPhaseList() {
        return phaseList;
    }

    public void setPhaseList(List<ProjectPhase> phaseList) {
        this.phaseList = phaseList;
    }

    public List<Modification> getModifications() {
        return modifications;
    }

    public void setModifications(List<Modification> modifications) {
        this.modifications = modifications;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(office, project.office) && Objects.equals(client, project.client) && Objects.equals(user, project.user) && Objects.equals(name, project.name) && type == project.type && status == project.status && Objects.equals(totalAreaM2, project.totalAreaM2) && Objects.equals(contractValue, project.contractValue) && Objects.equals(startDate, project.startDate) && Objects.equals(deadline, project.deadline) && Objects.equals(phaseList, project.phaseList) && Objects.equals(modifications, project.modifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, office, client, user, name, type, status, totalAreaM2, contractValue, startDate, deadline, phaseList, modifications);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", office=" + office +
                ", client=" + client +
                ", responsible=" + user +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", totalAreaM2=" + totalAreaM2 +
                ", contractValue=" + contractValue +
                ", startDate=" + startDate +
                ", deadline=" + deadline +
                ", phaseList=" + phaseList +
                ", modifications=" + modifications +
                '}';
    }
}
