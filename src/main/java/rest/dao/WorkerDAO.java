package rest.dao;

import rest.entity.Project;
import rest.entity.Worker;

import java.util.List;

public interface WorkerDAO {
    public List<Worker> getAllWorkers();

    public void saveWorker(String workerName, long departmentId);

    public Worker getWorkerById(long workerId);

    public Worker getWorkerByName(String workerName);

    public void deleteWorker(long workerId);

    public void updateWorker(long workerId, String workerName, long departmentId);

    public List<Project> getAllProjectsByWorker(long workerId);

}
