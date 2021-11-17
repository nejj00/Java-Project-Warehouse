package bg.tuvarna.sit.example.presentation.models;

public class PositionListViewModel {
    private String position;

    public PositionListViewModel(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return String.format(" | %s | ",position);
    }
}
