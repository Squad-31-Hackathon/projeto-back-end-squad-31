package com.squad31.apiorangeportifolio.Domain.Service;

import com.squad31.apiorangeportifolio.Domain.DTOs.Project.ProjectRequestDTO;
import com.squad31.apiorangeportifolio.Domain.DTOs.Project.TagResponseDTO;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Mapper.ProjectMapper;
import com.squad31.apiorangeportifolio.Domain.Repository.ProjectRepository;
import com.squad31.apiorangeportifolio.Exceptions.ImageProcessingException;
import com.squad31.apiorangeportifolio.Exceptions.ProjectNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class ProjectService {

    @Autowired
    private ProjectRepository repository;
    @Autowired
    private ProjectMapper mapper;

    public List<Project> getAllProjects(){
        return repository.findAll();
    }

    public List<Project> getByTag(String tag){
        return repository.findByTag(tag);
    }

    public Project getById(UUID uuid){
        return repository.findById(uuid)
                         .orElseThrow(ProjectNotFoundException::new);
    }

    @Transactional
    public Project createNewProject(ProjectRequestDTO request, byte[] image){
        try {
            Project newProject = repository.save(mapper.mapNewProject(request, image));
            log.info("Projeto registrado com sucesso");
            return newProject;
        } catch (IOException e){
            throw new ImageProcessingException();
        }
    }

    @Transactional
    public void deleteProject(UUID uuid){
        repository.deleteById(uuid);
        log.info("Projeto deletado com sucesso");
    }

    public List<String> getAllTags() {
        return repository.getTags();
    }

}
