package br.com.pavao.personal_projects.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.pavao.personal_projects.model.Projects;
import br.com.pavao.personal_projects.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api")
public class ProjectControllerr {
    private final Logger logger = LoggerFactory.getLogger(ProjectControllerr.class);

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("projects")
    public ResponseEntity<Object> getAllProjects() {
        try {
            Iterable<Projects> customers = projectRepository.findAll();
            return new ResponseEntity<Object>(customers, HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<Object> getProjectById(@PathVariable("id") Long id) {
        try {
            Projects project = projectRepository.findById(id).get();
            if(project != null) {
                return new ResponseEntity<Object>(project, HttpStatus.OK);
            } else {
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/projects")
    public ResponseEntity<Object> createProject(@RequestBody Projects projects) {
        try {
            Projects savedProject = projectRepository.save(projects);
            return new ResponseEntity<Object>(savedProject, HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/project/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable("id") Long id, @RequestBody Projects project) {
        try {
            project.setId(id);
            Projects savedProject = projectRepository.save(project);
            return new ResponseEntity<Object>(savedProject, HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") Long id) {
        try {
            projectRepository.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch(Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }

//    private static final String IMAGE_DIRECTORY = "/static/img/";

//    @PostMapping("/images")
//    public ResponseEntity<Map<String, byte[]>> getImages(@RequestBody List<String> imageNames) {
//        Map<String, byte[]> images = new HashMap<>();
//        System.out.println(imageNames);
//        for (String imageName : imageNames) {
//            try {
//                Path imagePath = Paths.get(IMAGE_DIRECTORY + imageName + ".svg"); // Ou ajuste a extensão conforme necessário
//                if (Files.exists(imagePath)) {
//                    Resource imgFile = new UrlResource(imagePath.toUri());
//                    byte[] imageBytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
//                    images.put(imageName, imageBytes);
//                } else {
//                    logger.warn("Imagem não encontrada ");
//                }
//            } catch (IOException ex) {
//                logger.error("Erro ao carregar a imagem ", ex);
//            }
//        }
//
//            if (images.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            } else {
//                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(images);
//            }
//        }
}
