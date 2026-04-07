package com.samu.dev.arcflow.model;

import com.samu.dev.arcflow.model.types.PhaseStatus;
import com.samu.dev.arcflow.model.types.PhaseType;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPhase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private LocalDate startDate;

    private LocalDate deadline;

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL)
    private List<Document> documents;

    @OneToMany(mappedBy = "phase", cascade = CascadeType.ALL)
    private List<ClientApproval> clientApprovals;

}
