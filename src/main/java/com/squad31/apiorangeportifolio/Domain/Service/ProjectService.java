package com.squad31.apiorangeportifolio.Domain.Service;

import com.squad31.apiorangeportifolio.Controller.Request.CreateProjectRequest;
import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Mapper.ProjectMapper;
import com.squad31.apiorangeportifolio.Domain.Repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Project getById(String uuid){
        return repository.findById(uuid).get(); //.orElseThrow(); <- quando fizer as exceptions pode botar no lugar do .get()
    }

    @Transactional
    public void createNewProject(CreateProjectRequest request){
        repository.save(mapper.mapNewProject(request));
        log.info("Projeto registrado com sucesso");
    }

    @Transactional
    public void deleteProject(String uuid){
        repository.deleteById(uuid);
        log.info("Projeto deletado com sucesso");
    }

}
