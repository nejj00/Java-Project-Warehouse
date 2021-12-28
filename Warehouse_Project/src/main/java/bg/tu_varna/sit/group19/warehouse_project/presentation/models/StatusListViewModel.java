package bg.tu_varna.sit.group19.warehouse_project.presentation.models;

public class StatusListViewModel {
    private Long statusID;
    private String status;

    public StatusListViewModel(Long ID, String status){
        this.statusID = ID;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s", status);
    }

    public Long getStatusID() {
        return statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatusID(Long statusID) {
        this.statusID = statusID;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
