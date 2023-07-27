package rest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import rest.entity.Department;
import rest.entity.Project;
import rest.entity.Worker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ProjectDAOImpl implements ProjectDAO{
    @Override
    public List<Project> getAllProjects() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<Project> projectList = session.createQuery("from Project ").list();
        session.getTransaction().commit();
        factory.close();
        return projectList;
    }

    @Override
    public void saveProject(String projectTitle) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        Project project = new Project(projectTitle);
        session.beginTransaction();
        session.save(project);
        session.getTransaction().commit();
        factory.close();
    }

    @Override
    public Project getProjectById(long projectId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Project project = session.get(Project.class, projectId);
        session.save(project);
        session.getTransaction().commit();
        factory.close();
        return project;
    }

    @Override
    public Project getProjectByName(String projectTitle) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Project where title=:projectTitle");
        query.setParameter("projectTitle", projectTitle);
        Project project = (Project) query.getSingleResult();
        session.getTransaction().commit();
        factory.close();
        return project;
    }

    @Override
    public void deleteProject(long projectId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete Project where title=:projectId")
                .setParameter("projectId", projectId).executeUpdate();
        session.getTransaction().commit();
        factory.close();
    }

    @Override
    public void updateProject(long projectId, String projectTitle) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery
                ("update Project set title=:projectTitle where id=:projectId");
        query.setParameter("projectTitle", projectTitle);
        query.setParameter("projectId", projectId);
        query.executeUpdate();
        session.getTransaction().commit();
        factory.close();
    }

    @Override
    public List<Worker> getAllWorkersByProjectId(long projectId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("select worker.id, worker.name, worker.department_id from Worker worker" +
                " join worker.projectList project where project.id=:projectId");
        query.setParameter("projectId", projectId);

        List<Object[]> res = query.getResultList();
        List<Worker> workerList= new ArrayList<>();

        Iterator it = res.iterator();
        while(it.hasNext()){
            Object[] line = (Object[]) it.next();
            Worker worker = new Worker();
            worker.setId((Long) line[0]);
            worker.setName((String) line[1]);
            worker.setDepartment_id((Long) line[2]);
            workerList.add(worker);
        }

        session.getTransaction().commit();
        factory.close();
        return workerList;
    }
}
