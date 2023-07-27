package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.dao.ProjectDAO;
import rest.entity.Project;
import rest.entity.Worker;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectDAO projectDAO;

    public ProjectServiceImpl(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    @Override
    public void saveProject(String projectTitle) {
        projectDAO.saveProject(projectTitle);
    }

    @Override
    public Project getProjectById(long projectId) {
        return projectDAO.getProjectById(projectId);
    }

    @Override
    public Project getProjectByName(String projectTitle) {
        return projectDAO.getProjectByName(projectTitle);
    }

    @Override
    public void deleteProject(long projectId) {
        projectDAO.deleteProject(projectId);
    }

    @Override
    public void updateProject(long projectId, String projectTitle) {
        projectDAO.updateProject(projectId, projectTitle);
    }

    @Override
    public List<Worker> getAllWorkersByProjectId(long projectId) {
        return projectDAO.getAllWorkersByProjectId(projectId);
    }
}
