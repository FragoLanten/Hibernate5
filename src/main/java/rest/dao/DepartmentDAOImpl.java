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
public class DepartmentDAOImpl implements DepartmentDAO{

    @Override
    public List<Department> getAllDepartments() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Department> departmentList = session.createQuery("from Department").list();
        session.getTransaction().commit();
        factory.close();
        return departmentList;
    }

    @Override
    public void saveDepartment(String departmentName) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        Department department = new Department(departmentName);
        session.beginTransaction();
        session.save(department);
        session.getTransaction().commit();
        factory.close();
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Department where name=:departmentName");
        query.setParameter("departmentName", departmentName);
        Department department = (Department) query.getSingleResult();
        session.getTransaction().commit();
        factory.close();
        return department;
    }

    @Override
    public Department getDepartmentById(long departmentId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Department department = session.get(Department.class, departmentId);
        session.save(department);
        session.getTransaction().commit();
        factory.close();
        return department;
    }

    @Override
    public void updateDepartment(long departmentId, String departmentName) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery
                ("update Department set name=:departmentName where id=:departmentId");
        query.setParameter("departmentName", departmentName);
        query.setParameter("departmentId", departmentId);
        query.executeUpdate();
        session.getTransaction().commit();
        factory.close();
    }

    @Override
    public void deleteDepartment(long departmentId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete Department where name =:departmentId")
                .setParameter("departmentId", departmentId).executeUpdate();

        session.getTransaction().commit();
        factory.close();
    }

    @Override
    public List<Worker> getAllWorkersByDepartmentId(long departmentId) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("select worker.id, worker.name, worker.department_id from Worker worker where worker.department_id=:departmentId");
        query.setParameter("departmentId", departmentId);

        List<Object[]> res = query.getResultList();
        List<Worker> list= new ArrayList<>();

        Iterator it = res.iterator();
        while(it.hasNext()){
            Object[] line = (Object[]) it.next();
            Worker worker = new Worker();
            worker.setId((Long) line[0]);
            worker.setName((String) line[1]);
            worker.setDepartment_id((Long) line[2]);
            list.add(worker);
        }

        session.getTransaction().commit();
        factory.close();
        return list;
    }

}
