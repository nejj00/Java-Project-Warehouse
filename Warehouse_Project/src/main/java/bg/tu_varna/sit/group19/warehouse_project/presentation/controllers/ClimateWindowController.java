package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.ClimateHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.holders.StatusHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.ClimateService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AlertMessages;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.ClimateCondition;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.WarehouseStatus;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.BaseWindowModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClimateWindowController {
    @FXML
    private TextField temperatureTextField;
    @FXML
    private TextField humidityTextField;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    private final ClimateHolder climateHolder = ClimateHolder.getInstance();

    @FXML
    public void initialize(){
        confirmButton.setOnMouseClicked(this::ConfirmClicked);
        cancelButton.setOnMouseClicked(this::CancelClicked);

        if(climateHolder.getClimateOpenMode() == Enums.OpenMode.UpdateMode)
        {
            temperatureTextField.setText(String.valueOf(climateHolder.getClimateCondition().getTemperature()));
            humidityTextField.setText(String.valueOf(climateHolder.getClimateCondition().getHumidity()));
        }
    }

    private final BaseWindowModel baseWindowModel = new BaseWindowModel();
    private void ConfirmClicked(MouseEvent mouseEvent) {
        if(temperatureTextField.getText().trim().isEmpty() || humidityTextField.getText().trim().isEmpty()) {
            AlertMessages.alertWarningMessage(baseWindowModel.getAlertTitle(), baseWindowModel.getAlertEmptyFields());
            return;
        }

        int temperature = Integer.parseInt(temperatureTextField.getText());
        int humidity = Integer.parseInt(humidityTextField.getText());

        if(climateHolder.getClimateOpenMode() == Enums.OpenMode.InsertMode)
            insertClimateDb(temperature, humidity);
        if(climateHolder.getClimateOpenMode() == Enums.OpenMode.UpdateMode)
            updateClimateDb(temperature, humidity);

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    private final ClimateService climateService = ClimateService.getInstance();
    private void insertClimateDb(int temperature, int humidity) {
        ClimateCondition climateCondition = new ClimateCondition();
        climateCondition.setTemperature(temperature);
        climateCondition.setHumidity(humidity);
        climateService.insertClimate(climateCondition);
        climateHolder.setClimateCondition(climateCondition);
    }

    private void updateClimateDb(int temperature, int humidity) {
        ClimateCondition climateCondition = new ClimateCondition();
        climateCondition.setId(climateHolder.getClimateCondition().getId());
        climateCondition.setTemperature(temperature);
        climateCondition.setHumidity(humidity);

        climateService.updateClimate(climateCondition);
        climateHolder.setClimateCondition(climateCondition);
    }

    private void CancelClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
