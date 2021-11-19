package bg.tuvarna.sit.example.presentation.controlers;

import bg.tuvarna.sit.example.business.services.PositionService;
import bg.tuvarna.sit.example.presentation.models.HelloModel;
import bg.tuvarna.sit.example.presentation.models.PositionListViewModel;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class HelloController implements EventHandler<MouseEvent> {

    private final PositionService service = PositionService.getInstance();

    @FXML
    private Label welcomeText;

    @FXML
    private Button helloButton;

    @FXML
    private ListView<PositionListViewModel> listview;

    private final HelloModel model;

    public HelloController() {
        this.model = new HelloModel();
    }

    @FXML
    private void initialize(){
        helloButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("mouse click detected! " + event.getSource());
            }
        });
    }

    @Override
    public void handle(MouseEvent mouseEvent){

        welcomeText.setText(model.getWelcomeMessage());

        ObservableList<PositionListViewModel> positionListViewModels = service.GetAll();
        listview.setItems(positionListViewModels);
    }

}