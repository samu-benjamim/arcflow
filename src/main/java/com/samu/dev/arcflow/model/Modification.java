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
import java.time.LocalTime;

@Entity
public class Modification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phase_id")
    private ProjectPhase phase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_id", nullable = false)
    private User requested;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private OriginModification origin;

    @Column(name = "impact_hours")
    private LocalTime impactHours;

    private BigDecimal extraCost;

    @Enumerated(EnumType.STRING)
    private StatusModification status;

}
