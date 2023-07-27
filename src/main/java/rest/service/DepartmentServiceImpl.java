package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dao.DepartmentDAO;
import rest.entity.Department;
import rest.entity.Worker;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentDAO departmentDAO;

    public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    @Override
    public void saveDepartment(String departmentName) {
        departmentDAO.saveDepartment(departmentName);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentDAO.getDepartmentByName(departmentName);
    }

    @Override
    public Department getDepartmentById(long departmentId) {
        return departmentDAO.getDepartmentById(departmentId);
    }

    @Override
    public void deleteDepartment(long departmentId) {
        departmentDAO.deleteDepartment(departmentId);
    }

    @Override
    public void updateDepartment(long departmentId, String departmentName) {
        departmentDAO.updateDepartment(departmentId, departmentName);
    }

    @Override
    public List<Worker> getAllWorkersByDepartmentId(long departmentId) {
        return departmentDAO.getAllWorkersByDepartmentId(departmentId);
    }
}
