package com.samu.dev.arcflow.repository;

import com.samu.dev.arcflow.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Lista todos os projetos de um escritório (tenant isolation)
    List<Project> findAllByOfficeId(Long officeId);

    // Busca por id garantindo que pertence ao escritório correto
    @Query("SELECT p FROM Project p WHERE p.id = :id AND p.office.id = :officeId")
    Optional<Project> findByIdAndOfficeId(@Param("id") Long id, @Param("officeId") Long officeId);

    // Verifica duplicidade de nome dentro do mesmo escritório
    boolean existsByOfficeIdAndName(Long officeId, String name);
}
