package com.squad31.apiorangeportifolio.Domain.Mapper;

import TestUtils.ProjectTestUtils;
import com.squad31.apiorangeportifolio.Domain.Repository.UserRepository;
import com.squad31.apiorangeportifolio.Exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProjectMapperTest {

    @InjectMocks
    ProjectMapper projectMapper;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenAUserNotFoundForProject() throws IOException {

        Mockito.when(userRepository.findById(Mockito.any(UUID.class))).thenThrow(UserNotFoundException.class);

        assertThrows(UserNotFoundException.class, () -> projectMapper.mapNewProject(ProjectTestUtils.InvalidUserProjectDTO(), "imagem".getBytes(StandardCharsets.UTF_8)));

    }

}