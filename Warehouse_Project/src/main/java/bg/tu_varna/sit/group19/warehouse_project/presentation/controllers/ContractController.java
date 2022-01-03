package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.AgentHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.*;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.AlertMessages;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.*;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseRoomRepository;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.ContractWindowModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class ContractController {
    @FXML
    private TextField Price;
    @FXML
    private DatePicker FromDate;
    @FXML
    private DatePicker ToDate;
    @FXML
    private Button CreateButton;
    @FXML
    private Button CancelButton;
    @FXML
    private TextField RenterName;
    @FXML
    private TextField RenterFamily;
    @FXML
    private TextField RenterPhoneNumber;
    @FXML
    private Label AgentName;
    @FXML
    private Label AgentFamily;
    @FXML
    private Label OwnerName;
    @FXML
    private Label OwnerFamily;
    @FXML
    private ComboBox<Warehouse> WarehouseCombobox;
    @FXML
    private ComboBox<WarehouseRoom> RoomCombobox;
    @FXML
    private AnchorPane AnchorPane;

    private AgentHolder agentHolder = AgentHolder.getInstance();

    private OwnerService ownerService;
    private AgentService agentService;
    private final WarehouseRoomService roomService = WarehouseRoomService.getInstance();
    private WarehouseContractService contractService;
    private WarehouseRoomRepository warehouseRoomRepository;
    private WarehouseRepository warehouseRepository;

    @FXML
    private void initialize(){
        ownerService = new OwnerService();
        agentService = new AgentService();
        contractService = new WarehouseContractService();
        warehouseRoomRepository = new WarehouseRoomRepository();
        warehouseRepository = new WarehouseRepository();

        AgentName.setText(agentHolder.getAgent().getFirstName());
        AgentFamily.setText(agentHolder.getAgent().getLastName());

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.View.AGENT_VIEW));
//        AgentWindowController controller = fxmlLoader.<AgentWindowController>getController();
//        controller.setUserFullName(admin.getFirstName() + " " + admin.getLastName());
//        controller.setAdmin(admin);

        WarehouseCombobox.getItems().addAll(warehouseRepository.getAll());

//        for(WarehouseRoom room:warehouseRoomRepository.getAll()){
//            RoomCombobox.getItems().add((int) room.getId(),room);
//        }

        CreateButton.setOnMouseClicked(this::CreateClicked);
        CancelButton.setOnMouseClicked(this::CancelClicked);
        WarehouseCombobox.setOnAction(this::onComboSelectionChanged);
    }

    private final ContractWindowModel contractWindowModel = new ContractWindowModel();
    @FXML
    private void CreateClicked(MouseEvent mouseEvent) {
        if(Price.getText().trim().isEmpty() || FromDate.getValue()==null || ToDate.getValue()==null
                || RenterName.getText().trim().isEmpty() || RenterFamily.getText().trim().isEmpty() || RenterPhoneNumber.getText().trim().isEmpty()
                || WarehouseCombobox.getSelectionModel().isEmpty() || RoomCombobox.getSelectionModel().isEmpty()
                || Price.getText().trim().isEmpty()) {
            AlertMessages.alertWarningMessage(contractWindowModel.getAlertTitle(), contractWindowModel.getAlertEmptyFields());
        }
        else{
            LocalDate localDate = FromDate.getValue();
            Calendar calendar =  Calendar.getInstance();
            calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
            Date fromDate = calendar.getTime();

            localDate = ToDate.getValue();
            calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
            Date toDate = calendar.getTime();

            float price = Float.parseFloat(Price.getText());
            Renter renter = new Renter();
            renter.setFirstName(RenterName.getText());
            renter.setLastName(RenterFamily.getText());
            renter.setPhoneNumber(RenterPhoneNumber.getText());

//            Owner owner = new Owner();
//            owner = ownerService.getOwnerByName(OwnerName.getText(),OwnerFamily.getText());

            long ID = RoomCombobox.getSelectionModel().getSelectedItem().getId();
            WarehouseRoom warehouseRoom;
            warehouseRoom = roomService.getWarehouseRoomById(ID);

            Owner owner = warehouseRoom.getWarehouse().getOwner();

//            Agent agent;
//            agent = agentService.getAgentByName(AgentName.getText(),AgentFamily.getText());

            Agent agent = agentHolder.getAgent();
            boolean result = AlertMessages.alertYesNoResult(contractWindowModel.getAlertConfirmQuestion(), contractWindowModel.getAlertTitle());
            if(!result)
                return;

            AddContractToDB(fromDate, toDate, price, warehouseRoom, agent, renter, owner);
            agent.setRating(agent.getRating() + 1);
            agentService.updateAgent(agent);
            AnchorPane.getChildren().clear();
        }
    }

    @FXML
    private void onComboSelectionChanged(Event event) {
        RoomCombobox.getItems().clear();
        Warehouse warehouse = WarehouseCombobox.getSelectionModel().getSelectedItem();
        if(!warehouse.getStatus().getStatus().equals("free"))
        {
            AlertMessages.alertWarningMessage(contractWindowModel.getAlertTitle(), contractWindowModel.getAlertWarehouseNotFree());
            return;
        }

        OwnerName.setText(warehouse.getOwner().getFirstName());
        OwnerFamily.setText(warehouse.getOwner().getLastName());
        RoomCombobox.getItems().addAll(warehouseRoomRepository.getAllRoomsFromWarehouseWithId(warehouse.getId()));
    }

    @FXML
    private void CancelClicked(MouseEvent mouseEvent) {
        AnchorPane.getChildren().clear();
    }
    private final RenterService renterService = RenterService.getInstance();

    private void AddContractToDB(Date fromDate, Date toDate, float Price, WarehouseRoom room, Agent agent, Renter renter, Owner owner) {

        renterService.insertRenter(renter);

        WarehouseContract contract = new WarehouseContract();
        contract.setAgent(agent);
        contract.setOwner(owner);
        contract.setFromDate(fromDate);
        contract.setPrice(Price);
        contract.setRenter(renter);
        contract.setToDate(toDate);
        contract.setWarehouseRoom(room);
        contractService.insertContract(contract);
    }
}
