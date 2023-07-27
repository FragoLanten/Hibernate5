package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.entity.Project;
import rest.entity.Worker;
import rest.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("api/workers")

public class WorkerController {
    private final WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Worker>> getAll() {
        List<Worker> workers = workerService.getAllWorkers();
        if (workers == null || workers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }

    @GetMapping(value="/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Worker> getWorkerById(@PathVariable long id) {
        Worker worker = workerService.getWorkerById(id);
        return worker == null? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(worker, HttpStatus.OK);
    }

    @GetMapping(value="/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Worker> getWorkerByName(@PathVariable String name) {
        Worker worker = workerService.getWorkerByName(name);
        return worker == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(worker, HttpStatus.OK);
    }

    @GetMapping(value="/id/{id}/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> getAllProjectsByWorker(@PathVariable long id) {
        List<Project> projectList = workerService.getAllProjectsByWorker(id);
        if (projectList == null || projectList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(projectList, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity createWorker(@RequestBody Worker worker) {
        if (worker == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            String workerName = worker.getName();
            long departmentId = worker.getDepartment_id();
            workerService.saveWorker(workerName, departmentId);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity updateWorker(@PathVariable long id, @RequestBody Worker worker) {
        if (worker == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            String workerName = worker.getName();
            long departmentId = worker.getDepartment_id();
            workerService.updateWorker(id, workerName, departmentId);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteWorker(@PathVariable long id) {
        workerService.deleteWorker(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
