package br.com.pavao.personal_projects.repositories;

import br.com.pavao.personal_projects.model.Projects;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects, Long> {
    @Query("SELECT a FROM Projects a WHERE a.status = 'active' ")
    public List<Projects> findActiveProjects();

    @Query("SELECT i FROM Projects i WHERE i.status = 'off' ")
    public List<Projects> findByStatusInativo();

    public List<Projects> findByNomeContainingIgnoreCase(String nome);
}
