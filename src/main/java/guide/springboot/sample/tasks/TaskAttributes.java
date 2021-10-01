package guide.springboot.sample.tasks;

public class TaskAttributes {
    private String details;
    private String status;

    public TaskAttributes(String details, String status) {
        this.details = details;
        this.status = status;
    }

    public String getDetails(){
        return details;
    }
    public void setDetails(String details){
        this.details = details;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
}

/*
    @JsonCreator
    TaskAttributes(@JsonProperty("details") final String details, String status){
        this.details = details;
        this.status = status;
    }
*/
