package com.samu.dev.arcflow.model;

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

import java.util.List;
import java.util.Objects;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id", nullable = false)
    private Office office;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;

    @Column(unique = true)
    private String address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ClientApproval> clientApprovals;


    public Client() {
    }

    public Client(Long id, Office office, String name, String email, String phone, String cpfCnpj, String address, List<Project> projects, List<ClientApproval> clientApprovals) {
        this.id = id;
        this.office = office;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpfCnpj = cpfCnpj;
        this.address = address;
        this.projects = projects;
        this.clientApprovals = clientApprovals;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<ClientApproval> getClientApprovals() {
        return clientApprovals;
    }

    public void setClientApprovals(List<ClientApproval> clientApprovals) {
        this.clientApprovals = clientApprovals;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(office, client.office) && Objects.equals(name, client.name) && Objects.equals(email, client.email) && Objects.equals(phone, client.phone) && Objects.equals(cpfCnpj, client.cpfCnpj) && Objects.equals(address, client.address) && Objects.equals(projects, client.projects) && Objects.equals(clientApprovals, client.clientApprovals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, office, name, email, phone, cpfCnpj, address, projects, clientApprovals);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", office=" + office +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", address='" + address + '\'' +
                ", projects=" + projects +
                ", clientApprovals=" + clientApprovals +
                '}';
    }
}
