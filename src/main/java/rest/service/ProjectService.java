package rest.service;

import rest.entity.Project;
import rest.entity.Worker;
import java.util.List;

public interface ProjectService {

    public List<Project> getAllProjects();

    public void saveProject(String projectTitle);

    public Project getProjectById(long projectId);

    public Project getProjectByName(String projectTitle);

    public void deleteProject(long projectId);

    public void updateProject(long projectId, String projectTitle);

    public List<Worker> getAllWorkersByProjectId(long projectId);
}
