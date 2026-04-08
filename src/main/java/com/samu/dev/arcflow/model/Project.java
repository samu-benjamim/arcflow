package com.samu.dev.arcflow.model;

import com.samu.dev.arcflow.model.types.PhaseStatus;
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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint
                (columnNames = {"office_id", "name"})
)
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
    private User manager;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ProjectType type;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private Status status = Status.ACTIVE;

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

    public void setDeadline(LocalDate deadline) {
        if (this.startDate != null && deadline.isBefore(this.startDate)) {
            throw new IllegalArgumentException("Deadline cannot be before start date");
        }
        this.deadline = deadline;
    }

    public void addPhase(ProjectPhase phase) {
        phase.setProject(this);
        phase.setOrder(this.phaseList.size() + 1);
        this.phaseList.add(phase);
    }

    public void addModification(Modification modification) {
        modification.setProject(this);
        this.modifications.add(modification);
    }

    public BigDecimal calculateProgress() {
        if (phaseList == null || phaseList.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = phaseList.stream()
                .map(ProjectPhase::getCompletionPct)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return total.divide(BigDecimal.valueOf(phaseList.size()), 2, RoundingMode.HALF_UP);
    }

    public void updateStatus() {
        boolean allDone = phaseList.stream()
                .allMatch(p -> p.getStatus() == PhaseStatus.CONCLUIDO);

        if (allDone) {
            this.status = Status.DONE;
        }
    }

}
