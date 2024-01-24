package com.squad31.apiorangeportifolio.Domain.Repository;

import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query ("SELECT e FROM Project e WHERE :tag IN elements(e.tags)")
    List<Project> findByTag(@Param("tag") String tag);

}
