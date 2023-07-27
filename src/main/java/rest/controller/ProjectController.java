package rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.entity.Project;
import rest.entity.Worker;
import rest.service.ProjectService;

import java.util.List;

@RestController
@RequestMapping("api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        if (projects == null || projects.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping(value="id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProjectById(@PathVariable long id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping(value="title/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getProjectByName(@PathVariable String title) {
        Project project = projectService.getProjectByName(title);
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping(value="/id/{id}/workers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Worker>> getAllWorkersByProjecttId(@PathVariable long id) {
        List<Worker> workerList = projectService.getAllWorkersByProjectId(id);
        if (workerList == null || workerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(workerList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity createProject(@RequestBody Project project) {
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            String projectTitle = project.getTitle();
            projectService.saveProject(projectTitle);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity updateProject(@PathVariable long id, @RequestBody Project project) {
        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            String projectTitle = project.getTitle();
            projectService.updateProject(id, projectTitle);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteProject(@PathVariable long id) {
        projectService.deleteProject(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
