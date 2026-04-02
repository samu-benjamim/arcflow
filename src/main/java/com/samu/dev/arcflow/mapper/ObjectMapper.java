package com.samu.dev.arcflow.mapper;

import com.samu.dev.arcflow.dto.client.ClientCreateRequest;
import com.samu.dev.arcflow.dto.client.ClientResponse;
import com.samu.dev.arcflow.dto.client.ClientUpdateRequest;
import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalCreateRequest;
import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalResponse;
import com.samu.dev.arcflow.dto.clientapproval.ClientApprovalUpdateRequest;
import com.samu.dev.arcflow.dto.document.DocumentCreateRequest;
import com.samu.dev.arcflow.dto.document.DocumentResponse;
import com.samu.dev.arcflow.dto.document.DocumentUpdateRequest;
import com.samu.dev.arcflow.dto.documentrevision.DocumentRevisionCreateRequest;
import com.samu.dev.arcflow.dto.documentrevision.DocumentRevisionResponse;
import com.samu.dev.arcflow.dto.modification.ModificationCreateRequest;
import com.samu.dev.arcflow.dto.modification.ModificationResponse;
import com.samu.dev.arcflow.dto.modification.ModificationUpdateRequest;
import com.samu.dev.arcflow.dto.office.OfficeCreateRequest;
import com.samu.dev.arcflow.dto.office.OfficeResponse;
import com.samu.dev.arcflow.dto.office.OfficeUpdateRequest;
import com.samu.dev.arcflow.dto.project.ProjectCreateRequest;
import com.samu.dev.arcflow.dto.project.ProjectSummaryResponse;
import com.samu.dev.arcflow.dto.project.ProjectUpdateRequest;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseCreateRequest;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseResponse;
import com.samu.dev.arcflow.dto.projectphase.ProjectPhaseUpdateRequest;
import com.samu.dev.arcflow.dto.task.TaskCreateRequest;
import com.samu.dev.arcflow.dto.task.TaskResponse;
import com.samu.dev.arcflow.dto.task.TaskUpdateRequest;
import com.samu.dev.arcflow.dto.timeentry.TimeEntryCreateRequest;
import com.samu.dev.arcflow.dto.timeentry.TimeEntryResponse;
import com.samu.dev.arcflow.dto.user.UserCreateRequest;
import com.samu.dev.arcflow.dto.user.UserResponse;
import com.samu.dev.arcflow.dto.user.UserUpdateRequest;
import com.samu.dev.arcflow.model.Client;
import com.samu.dev.arcflow.model.ClientApproval;
import com.samu.dev.arcflow.model.Document;
import com.samu.dev.arcflow.model.DocumentRevision;
import com.samu.dev.arcflow.model.Modification;
import com.samu.dev.arcflow.model.Office;
import com.samu.dev.arcflow.model.Project;
import com.samu.dev.arcflow.model.ProjectPhase;
import com.samu.dev.arcflow.model.Task;
import com.samu.dev.arcflow.model.TimeEntry;
import com.samu.dev.arcflow.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel =  "spring")
public interface ObjectMapper {

    //CREATE
    Office toEntityOffice (OfficeCreateRequest office);

    User toEntityUser (UserCreateRequest user);

    Client toEntityClient (ClientCreateRequest client);

    Project toEntityProject (ProjectCreateRequest project);

    ProjectPhase toEntityProjectPhase (ProjectPhaseCreateRequest projectPhase);

    Task toEntityTask (TaskCreateRequest task);

    TimeEntry toEntityTimeEntry (TimeEntryCreateRequest timeEntry);

    Document toEntityDocument (DocumentCreateRequest timeEntry);

    DocumentRevision toEntityDocumentRevision (DocumentRevisionCreateRequest timeEntry);

    ClientApproval toEntityClientApproval (ClientApprovalCreateRequest timeEntry);

    Modification toEntityModification (ModificationCreateRequest timeEntry);

    //UPDATE
    Office updateEntityOffice (OfficeUpdateRequest office, @MappingTarget Office entity);

    User updateEntityUser (UserUpdateRequest user, @MappingTarget User entity );

    Client updateEntityClient (ClientUpdateRequest client, @MappingTarget Client entity );

    Project updateEntityProject (ProjectUpdateRequest project, @MappingTarget Project entity );

    ProjectPhase updateEntityProjectPhase (ProjectPhaseUpdateRequest projectPhase, @MappingTarget ProjectPhase entity );

    Task updateEntityTask (TaskUpdateRequest task, @MappingTarget Task entity );

    Document updateEntityDocument (DocumentUpdateRequest timeEntry, @MappingTarget Document entity );

    ClientApproval updateEntityClientApproval (ClientApprovalUpdateRequest timeEntry, @MappingTarget ClientApproval entity );

    Modification updateEntityModification (ModificationUpdateRequest timeEntry, @MappingTarget Modification entity );

    //RESPONSE
    OfficeResponse toResoponseOffice (Office office);

    UserResponse toResoponseUser (User user);

    ClientResponse toResoponseClient (Client client);

    ProjectSummaryResponse toResoponseProject (Project project);

    ProjectPhaseResponse toResoponseProjectPhase (ProjectPhase projectPhase);

    TaskResponse toResoponseTask (Task task);

    TimeEntryResponse toResoponseTimeEntry (TimeEntry timeEntry);

    DocumentResponse toResoponseDocument (Document timeEntry);

    DocumentRevisionResponse toResoponseDocumentRevision (DocumentRevision timeEntry);

    ClientApprovalResponse toResoponseClientApproval (ClientApproval timeEntry);

    ModificationResponse toResoponseModification (Modification timeEntry);

    //CONVERT
    Office toResoponseConvertOffice (OfficeResponse office);

    User toResoponseConvertUser (UserResponse user);

    Client toResoponseConvertClient (ClientResponse client);

    Project toResoponseConvertProject (ProjectSummaryResponse project);

    ProjectPhase toResoponseConvertProjectPhase (ProjectPhaseResponse projectPhase);

    Task toResoponseConvertTask (TaskResponse task);

    TimeEntry toResoponseConvertTimeEntry (TimeEntryResponse timeEntry);

    Document toResoponseConvertDocument (DocumentResponse timeEntry);

    DocumentRevision toResoponseConvertDocumentRevision (DocumentRevisionResponse timeEntry);

    ClientApproval toResoponseConvertClientApproval (ClientApprovalResponse timeEntry);

    Modification toResoponseConvertModification (ModificationResponse timeEntry);

}
