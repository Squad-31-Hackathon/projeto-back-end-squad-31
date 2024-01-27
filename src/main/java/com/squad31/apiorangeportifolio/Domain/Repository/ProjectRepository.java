package com.squad31.apiorangeportifolio.Domain.Repository;

import com.squad31.apiorangeportifolio.Domain.Entity.Project;
import com.squad31.apiorangeportifolio.Domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query ("SELECT e FROM Project e WHERE :tag IN elements(e.tags)")
    List<Project> findByTag(@Param("tag") String tag);

    List<Project> findByUser(User user);

    @Query("SELECT DISTINCT tag FROM Project p, IN(p.tags) tag")
    List<String> getAvailableTags();

}
