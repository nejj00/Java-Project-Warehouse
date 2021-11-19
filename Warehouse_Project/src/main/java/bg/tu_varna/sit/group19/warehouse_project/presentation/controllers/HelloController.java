package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.AdminRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.BaseRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.ClimateRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseStatusRepository;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.HelloModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.LineNumberInputStream;
import java.util.List;

public class    HelloController implements EventHandler<MouseEvent> {
    @FXML
    private Label welcomeText;

    @FXML
    private Button helloButton;

    private final HelloModel model;

    public HelloController() {
        this.model = new HelloModel();
    }

    //private final AdminRepository adminRepository = AdminRepository.getInstance();
    private final ClimateRepository climateRepository = ClimateRepository.getInstance();
    //private final BaseRepository<WarehouseStatus> warehouseStatusBaseRepository = BaseRepository.getInstance();
    private final WarehouseStatusRepository warehouseStatusRepository = WarehouseStatusRepository.getInstance();

    @FXML
    private void initialize(){
        helloButton.setOnMouseClicked(this::handle);
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        /*
        Admin admin1 = new Admin();
        admin1.setFirstName("Ivan");
        admin1.setLastName("Ivanov");
        adminRepository.save(admin1);
        */

        /*
        ClimateCondition condition = new ClimateCondition();
        condition.setConditions("HUMID");
        climateRepository.save(condition);


        List<ClimateCondition> conditions = climateRepository.getAll();
        ClimateCondition condition1 = conditions.get(3);
        condition1.setConditions("WARM");

        climateRepository.update(condition1);
        */

        ClimateCondition condition2 = climateRepository.getById((long)4).get();
        System.out.println(condition2.getConditions());


        /*
        WarehouseStatus warehouseStatus = new WarehouseStatus();
        warehouseStatus.setStatus("FREE");
        warehouseStatusRepository.save(warehouseStatus);

        welcomeText.setText(model.getWelcomeMessage());
         */
    }
}