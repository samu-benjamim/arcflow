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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
