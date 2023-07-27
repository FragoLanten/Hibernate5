package rest.service;

import org.springframework.stereotype.Service;
import rest.dao.WorkerDAO;
import rest.entity.Project;
import rest.entity.Worker;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService{

    private final WorkerDAO workerDAO;

    public WorkerServiceImpl(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
    }

    @Override
    public List<Worker> getAllWorkers() {
        return workerDAO.getAllWorkers();
    }

    @Override
    public void saveWorker(String workerName, long departmentId) {
        workerDAO.saveWorker(workerName, departmentId);
    }

    @Override
    public Worker getWorkerById(long workerId) {
        return workerDAO.getWorkerById(workerId);
    }

    @Override
    public Worker getWorkerByName(String workerName) {
        return workerDAO.getWorkerByName(workerName);
    }

    @Override
    public void deleteWorker(long workerId) {
        workerDAO.deleteWorker(workerId);
    }

    @Override
    public void updateWorker(long workerId, String workerName, long departmentId) {
        workerDAO.updateWorker(workerId, workerName, departmentId);
    }

    @Override
    public List<Project> getAllProjectsByWorker(long workerId) {
        return workerDAO.getAllProjectsByWorker(workerId);
    }
}
