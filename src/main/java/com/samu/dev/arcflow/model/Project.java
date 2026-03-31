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
    @JoinColumn(name = "responsible_id", nullable = false)
    private User responsible;
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

}
