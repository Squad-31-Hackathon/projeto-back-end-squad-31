package com.squad31.apiorangeportifolio.Controller;

import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectResponseDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.Project.TagResponseDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.Project.UpdateProjectRequest;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.squad31.apiorangeportifolio.Domain.Mapper.ProjectMapper;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping ("/{uuid}")
    public ResponseEntity<ProjectResponseDTO> getById(@PathVariable UUID uuid) {
        Project project = service.getById(uuid);
        ProjectResponseDTO response = ProjectMapper.mapProjectResponse(project);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping ("/tag/{tag}")
    public ResponseEntity<List<ProjectResponseDTO>> getByTag(@PathVariable String tag) {
        List<ProjectResponseDTO> response = service.getByTag(tag).stream()
                                                    .map(ProjectMapper::mapProjectResponse)
                                                    .toList();

        if(response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<ProjectResponseDTO> create(@RequestBody ProjectRequestDTO request) {

        Project newProject = service.createNewProject(request);
        ProjectResponseDTO response = ProjectMapper.mapProjectResponse(newProject);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping ("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid){
        service.deleteProject(uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/tags")
    public ResponseEntity<List<TagResponseDTO>> getTags() {

        List<String> tags = service.getAllTags();

        return ResponseEntity.status(HttpStatus.OK).body(tags.stream().map(TagResponseDTO::new).toList());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable String uuid, @RequestPart UpdateProjectRequest request, @RequestPart MultipartFile file) throws IOException {

        Project updatedProject = service.updateProject(uuid, request, file.getBytes());
        ProjectResponseDTO response = ProjectMapper.mapProjectResponse(updatedProject);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
