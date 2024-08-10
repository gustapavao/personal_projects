package br.com.pavao.personal_projects.controller;

import br.com.pavao.personal_projects.model.Projects;
import br.com.pavao.personal_projects.repositories.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/insert-project")
    public ModelAndView insertProject(Projects project) {
        ModelAndView modelAndView = new ModelAndView("insert/formProject");
        modelAndView.addObject("project", project);
        return modelAndView;
    }

    @PostMapping("insertProject")
    public ModelAndView addProject(@Valid Projects project, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("project/formProject");
            modelAndView.addObject("project", project);
        }else {
            modelAndView.setViewName("redirect:/projects-list");
            projectRepository.save(project);
        }
        return modelAndView;
    }

    @GetMapping("/project-list")
    public ModelAndView listProjects() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/project-list");
        modelAndView.addObject("alunoslist", projectRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView edit(@PathVariable("id")Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("project/editar");
        Projects project = projectRepository.getById(id);
        modelAndView.addObject("project", project);
        return modelAndView;
    }

    @PostMapping("/editar")
    public ModelAndView editar(Projects projects) {
        ModelAndView modelAndView = new ModelAndView();
        projectRepository.save(projects);
        modelAndView.setViewName("redirect:/project-list");
        return modelAndView;
    }

    @GetMapping("/remover/{id}")
    public String removerProject(@PathVariable("id") Long id) {
        projectRepository.deleteById(id);
        return "redirect:/alunos-adicionados";
    }

    @GetMapping("filter-projects")
    public ModelAndView filterProject() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profile/filtro-projects");
        return modelAndView;
    }

    @GetMapping("actived-projects")
    public ModelAndView projectsActivated() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Projeto/projetos-ativos");
        modelAndView.addObject("projetos-ativos", projectRepository.findActiveProjects());
        return modelAndView;
    }

    @GetMapping("deactived-projects")
    public ModelAndView deactivatedProjects() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("projeto/deactived-projects");
        modelAndView.addObject("projetos-inativos", projectRepository.findByStatusInativo());
        return modelAndView;
    }

    @PostMapping("/search-project")
    public ModelAndView searchProject(@RequestParam(required = false) String nome) {
        ModelAndView modelAndView = new ModelAndView();
        List<Projects> projectLista;
        if(nome == null || nome.trim().isEmpty()) {
            projectLista = projectRepository.findAll();
        } else {
            projectLista = projectRepository.findByNomeContainingIgnoreCase(nome);
        }
        modelAndView.addObject("list-of-projects", projectLista);
        modelAndView.setViewName("project/result");
        return modelAndView;
    }
}
