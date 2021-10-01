package guide.springboot.sample.tasks;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String details;
    private String status;

    protected Task() {
    }

    public Task(String details, String status) {
        this.details = details;
        this.status = status;
    }

    public Task(UUID id, String details, String status) {
        this.id = id;
        this.details = details;
        this.status = status;
    }

    public UUID getId() { return id; }

    public String getDetails() { return details; }

    public void setDetails(String details) { this.details = details; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /*
    public Task(){

    }

    public Task(String details) {
        this.details = details;
    }


    @Override
    public String toString(){
        return ""
    }
*/
}
