package rest.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="workersprojects",
            joinColumns = @JoinColumn(name="projectid"),
            inverseJoinColumns = @JoinColumn(name="workerid"))
    private List<Worker> workerList;

    public Project() {
    }

    public Project(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
