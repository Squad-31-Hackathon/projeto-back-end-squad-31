package com.squad31.apiorangeportifolio.Domain.Service;

import TestUtils.ProjectTestUtils;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Mapper.ProjectMapper;
import com.squad31.apiorangeportifolio.Domain.Repository.ProjectRepository;
import com.squad31.apiorangeportifolio.Exceptions.ProjectNotFoundException;
import com.squad31.apiorangeportifolio.Exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {

    @InjectMocks
    ProjectService projectService;

    @Mock
    ProjectRepository projectRepository;
    @Mock
    ProjectMapper projectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAListOfProjectEntitesWhenGetAllIsCalled(){

        Mockito.when(projectRepository.findAll()).thenReturn(List.of(ProjectTestUtils.ValidProject()));

        List<Project> projects = projectService.getAllProjects();

        assertNotNull(projects);
        assertEquals(List.of(ProjectTestUtils.ValidProject()), projects);

    }

    @Test
    void shouldReturnASProjectEntityWhenGetByIdIsCalled(){

        Mockito.when(projectRepository.findById(ProjectTestUtils.projectUUID)).thenReturn(Optional.of(ProjectTestUtils.ValidProject()));

        Project project = projectService.getById(ProjectTestUtils.projectUUID);

        assertNotNull(project);
        assertEquals(ProjectTestUtils.ValidProject(), project);

    }

    @Test
    void shouldThrowProjectNotFoundExceptionWhenAIdNotFound(){

        Mockito.when(projectRepository.findById(Mockito.any(UUID.class))).thenThrow(new ProjectNotFoundException());

        assertThrows(ProjectNotFoundException.class, () -> projectService.getById(UUID.randomUUID()));

    }

    @Test
    void shouldReturnAProjectEntityWhenANewProjectIsSaved() throws IOException {

        Mockito.when(projectMapper.mapNewProject(ProjectTestUtils.ValidProjectDTO(), "imagem".getBytes(StandardCharsets.UTF_8)))
                .thenReturn(ProjectTestUtils.ValidProject());
        Mockito.when(projectRepository.save(Mockito.any(Project.class)))
                .thenReturn(ProjectTestUtils.ValidProject());

        Project project = projectService.createNewProject(ProjectTestUtils.ValidProjectDTO(), "imagem".getBytes(StandardCharsets.UTF_8));

        assertNotNull(project);
        assertEquals(ProjectTestUtils.ValidProject(), project);

    }




}