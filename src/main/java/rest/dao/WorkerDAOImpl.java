package rest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import rest.entity.Project;
import rest.entity.Worker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class WorkerDAOImpl implements WorkerDAO {

    @Override
    public List<Worker> getAllWorkers() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<Worker> workerList = session.createQuery("from Worker").list();
        session.getTransaction().commit();
        factory.close();
        return workerList;
    }

    @Override
    public void saveWorker(String workerName, long departmentId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        Worker worker = new Worker(workerName, departmentId);
        session.beginTransaction();
        session.save(worker);
        session.getTransaction().commit();
        factory.close();
    }

    @Override
    public Worker getWorkerById(long workerId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Worker worker = session.get(Worker.class, workerId);
        session.save(worker);
        session.getTransaction().commit();
        factory.close();
        return worker;
    }

    @Override
    public Worker getWorkerByName(String workerName) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Worker where name=:name");
        query.setParameter("name", workerName);
        Worker worker = (Worker) query.getSingleResult();
        session.getTransaction().commit();
        factory.close();
        return worker;
    }

    @Override
    public void deleteWorker(long workerId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete Worker where id =:workerId")
                .setParameter("workerId", workerId).executeUpdate();

        session.getTransaction().commit();
        factory.close();
    }

    @Override
    public void updateWorker(long workerId, String workerName, long departmentId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery
                ("update Worker set name=:workerName, department_id=:departmentId where id=:workerId");
        query.setParameter("workerName", workerName);
        query.setParameter("departmentId", departmentId);
        query.setParameter("workerId", workerId);
        query.executeUpdate();
        session.getTransaction().commit();
        factory.close();
    }

    @Override
    public List<Project> getAllProjectsByWorker(long workerId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("select project.id, project.title from Project project join project.workerList worker where worker.id=:workerId");
        query.setParameter("workerId", workerId);

        List<Object[]> res = query.getResultList();
        List<Project> list = new ArrayList<>();

        Iterator it = res.iterator();
        while (it.hasNext()) {
            Object[] line = (Object[]) it.next();
            Project project = new Project();
            project.setId((Long) line[0]);
            project.setTitle((String) line[1]);
            list.add(project);
        }

        session.getTransaction().commit();
        factory.close();
        return list;
    }
}
