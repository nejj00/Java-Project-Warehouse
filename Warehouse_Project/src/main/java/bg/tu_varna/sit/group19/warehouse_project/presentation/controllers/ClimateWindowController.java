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
    private TextField conditionsTextField;
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
            conditionsTextField.setText(climateHolder.getClimateCondition().getConditions());
    }

    private final BaseWindowModel baseWindowModel = new BaseWindowModel();
    private void ConfirmClicked(MouseEvent mouseEvent) {
        if(conditionsTextField.getText().trim().isEmpty()) {
            AlertMessages.alertWarningMessage(baseWindowModel.getAlertTitle(), baseWindowModel.getAlertEmptyFields());
            return;
        }

        String conditions = conditionsTextField.getText();

        if(climateHolder.getClimateOpenMode() == Enums.OpenMode.InsertMode)
            insertClimateDb(conditions);
        if(climateHolder.getClimateOpenMode() == Enums.OpenMode.UpdateMode)
            updateClimateDb(conditions);

        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }

    private final ClimateService climateService = ClimateService.getInstance();
    private void insertClimateDb(String conditions) {
        ClimateCondition climateCondition = new ClimateCondition();
        climateCondition.setConditions(conditions);
        climateService.insertClimate(climateCondition);
        climateHolder.setClimateCondition(climateCondition);
    }

    private void updateClimateDb(String conditions) {
        ClimateCondition climateCondition = new ClimateCondition();
        climateCondition.setId(climateHolder.getClimateCondition().getId());
        climateCondition.setConditions(conditions);

        climateService.updateClimate(climateCondition);
        climateHolder.setClimateCondition(climateCondition);
    }

    private void CancelClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}
