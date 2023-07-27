package rest.dao;

import rest.entity.Department;
import rest.entity.Worker;

import java.util.List;

public interface DepartmentDAO {
    public List<Department> getAllDepartments();

    public void saveDepartment(String departmentName);

    public Department getDepartmentByName(String departmentName);

    public Department getDepartmentById(long departmentId);

    public void deleteDepartment(long departmentId);

    public void updateDepartment(long departmentId, String departmentName);

    public List<Worker> getAllWorkersByDepartmentId(long departmentId);

}
