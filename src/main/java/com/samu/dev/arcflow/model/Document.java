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

}
