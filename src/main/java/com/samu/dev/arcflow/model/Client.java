package com.samu.dev.arcflow.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(name = "cpf_cnpj", unique = true,nullable = false)
    @Size(min = 11, max = 14)
    private String cpfCnpj;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "client")
    private List<Project> projects;

    @OneToMany(mappedBy = "client")
    private List<ClientApproval> clientApprovals;

    public void addProject(Project project) {
        project.setClient(this);
        this.projects.add(project);
    }
    public void addApproval(ClientApproval approval) {
        approval.setClient(this);
        this.clientApprovals.add(approval);
    }
}
