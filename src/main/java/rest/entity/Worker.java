package rest.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private long department_id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="workersprojects",
            joinColumns = @JoinColumn(name="workerid"),
            inverseJoinColumns = @JoinColumn(name="projectid"))
    private List<Project> projectList;

    public Worker() {
    }

    public Worker(String name, long department_id) {
        this.name = name;
        this.department_id = department_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(long department_id) {
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department_id=" + department_id +
                '}';
    }
}
