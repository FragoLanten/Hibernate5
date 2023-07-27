package rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.entity.Department;
import rest.entity.Worker;
import rest.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> getAll() {
        List<Department> departments = departmentService.getAllDepartments();
        if (departments == null || departments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping(value="id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> getDepartmentById(@PathVariable long id) {
        Department department = departmentService.getDepartmentById(id);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping(value="name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> getDepartmentByName(@PathVariable String name) {
        Department department = departmentService.getDepartmentByName(name);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @GetMapping(value="/id/{id}/workers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Worker>> getAllWorkersByDepartmentId(@PathVariable int id) {
        List<Worker> workerList = departmentService.getAllWorkersByDepartmentId(id);
        if (workerList == null || workerList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(workerList, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity createDepartment(@RequestBody Department department) {
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            String departmentName = department.getName();
            departmentService.saveDepartment(departmentName);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PutMapping("id/{id}")
    public ResponseEntity updateDepartment(@PathVariable int id, @RequestBody Department department) {
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        String departmentName = department.getName();
        departmentService.updateDepartment(id, departmentName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteWorker(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
