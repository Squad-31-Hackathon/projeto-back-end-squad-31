package com.squad31.apiorangeportifolio.Controller;

import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectResponseDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.squad31.apiorangeportifolio.Domain.Mapper.ProjectMapper;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAll() {
        List<ProjectResponseDTO> response = service.getAllProjects().stream()
                                                                    .map(ProjectMapper::mapProjectResponse)
                                                                    .toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ProjectResponseDTO> getById(@PathVariable String uuid) {
        Project project = service.getById(uuid)
        ProjectResponseDTO response = ProjectMapper.mapProjectResponse(project);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(@RequestBody ProjectRequestDTO request){
        Project newProject = service.createNewProject(request);
        ProjectResponseDTO response = ProjectMapper.mapProjectResponse(newProject);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}