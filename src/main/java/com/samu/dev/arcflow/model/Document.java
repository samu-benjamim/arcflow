package com.samu.dev.arcflow.model;

import com.samu.dev.arcflow.model.types.DocumentType;
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
import jakarta.persistence.OneToOne;

import java.util.List;
import java.util.Objects;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phase_id", nullable = false)
    private ProjectPhase phase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploadedBy_id", nullable = false)
    private User user;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @Column(name = "documente_code")
    private String documentCode;

    @OneToOne
    @JoinColumn( name = "current_revision")
    private DocumentRevision currentRevision;

    @Column(name = "file_url")
    private String fileUrl;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<DocumentRevision> documentRevisions;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL)
    private List<ClientApproval> clientApprovals;

    public Document() {
    }

    public Document(Long id, ProjectPhase phase, User user, String name, DocumentType documentType, String documentCode, DocumentRevision currentRevision, String fileUrl, List<DocumentRevision> documentRevisions, List<ClientApproval> clientApprovals) {
        this.id = id;
        this.phase = phase;
        this.user = user;
        this.name = name;
        this.documentType = documentType;
        this.documentCode = documentCode;
        this.currentRevision = currentRevision;
        this.fileUrl = fileUrl;
        this.documentRevisions = documentRevisions;
        this.clientApprovals = clientApprovals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDocumentCode() {
        return documentCode;
    }

    public void setDocumentCode(String documentCode) {
        this.documentCode = documentCode;
    }

    public DocumentRevision getCurrentRevision() {
        return currentRevision;
    }

    public void setCurrentRevision(DocumentRevision currentRevision) {
        this.currentRevision = currentRevision;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public List<DocumentRevision> getDocumentRevisions() {
        return documentRevisions;
    }

    public void setDocumentRevisions(List<DocumentRevision> documentRevisions) {
        this.documentRevisions = documentRevisions;
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
        Document document = (Document) o;
        return Objects.equals(id, document.id) && Objects.equals(phase, document.phase) && Objects.equals(user, document.user) && Objects.equals(name, document.name) && documentType == document.documentType && Objects.equals(documentCode, document.documentCode) && Objects.equals(currentRevision, document.currentRevision) && Objects.equals(fileUrl, document.fileUrl) && Objects.equals(documentRevisions, document.documentRevisions) && Objects.equals(clientApprovals, document.clientApprovals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phase, user, name, documentType, documentCode, currentRevision, fileUrl, documentRevisions, clientApprovals);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", phase=" + phase +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", documentType=" + documentType +
                ", documentCode='" + documentCode + '\'' +
                ", currentRevision=" + currentRevision +
                ", fileUrl='" + fileUrl + '\'' +
                ", documentRevisions=" + documentRevisions +
                ", clientApprovals=" + clientApprovals +
                '}';
    }


}
