package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.services.AgentService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.OwnerService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseContractService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.WarehouseRoomService;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.*;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseRepository;
import bg.tu_varna.sit.group19.warehouse_project.data.repositories.WarehouseRoomRepository;
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
    private ComboBox WarehouseCombobox;
    @FXML
    private ComboBox RoomCombobox;
    @FXML
    private AnchorPane AnchorPane;

    private OwnerService ownerService;
    private AgentService agentService;
    private WarehouseRoomService roomService;
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

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.View.AGENT_VIEW));
//        AgentWindowController controller = fxmlLoader.<AgentWindowController>getController();
//        controller.setUserFullName(admin.getFirstName() + " " + admin.getLastName());
//        controller.setAdmin(admin);

        WarehouseCombobox.getItems().addAll(warehouseRepository.getAll());

        for(WarehouseRoom room:warehouseRoomRepository.getAll()){
            RoomCombobox.getItems().add((int) room.getId(),room);
        }

        CreateButton.setOnMouseClicked(this::CreateClicked);
        CancelButton.setOnMouseClicked(this::CancelClicked);
        WarehouseCombobox.setOnAction(this::LoadRooms);
    }

    @FXML
    private void CreateClicked(MouseEvent mouseEvent) {
        if(Price.getText()==null&&FromDate.getValue()==null
                &&ToDate.getValue()==null&&RenterName.getText()==null
                &&RenterFamily.getText()==null&&RenterPhoneNumber.getText()==null
                &&AgentName.getText()==null&&OwnerName.getText()==null
                && WarehouseCombobox.getSelectionModel().isEmpty()
                && RoomCombobox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("title");
            alert.setHeaderText("head");
            alert.setContentText("Please fill all fields");
            alert.showAndWait();
        }
        else{
            LocalDate ld = FromDate.getValue();
            Calendar c =  Calendar.getInstance();
            c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
            Date date = c.getTime();

            ld = ToDate.getValue();
            c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
            Date date2 = c.getTime();

            float price = Float.parseFloat(Price.getText());

            Renter renter = new Renter();
            renter.setFirstName(RenterName.getText());
            renter.setLastName(RenterFamily.getText());
            renter.setPhoneNumber(RenterPhoneNumber.getText());

            Owner owner = new Owner();
            owner = ownerService.getOwnerByName(OwnerName.getText(),OwnerFamily.getText());

            long ID=0;
            WarehouseRoom warehouseRoom;
            warehouseRoom = roomService.getWarehouseRoomById(ID);

            Agent agent;
            agent = agentService.getAgentByName(AgentName.getText(),AgentFamily.getText());

            AddContractToDB(date,date2,price,warehouseRoom,agent,renter,owner);
        }
    }

    @FXML
    private void LoadRooms(Event event) {
        Warehouse warehouse = (Warehouse) WarehouseCombobox.getSelectionModel().getSelectedItem();

        RoomCombobox.getItems().addAll(warehouseRoomRepository.getAllRoomsFromWarehouseWithId(warehouse.getId()));
    }

    @FXML
    private void CancelClicked(MouseEvent mouseEvent) {
        AnchorPane.getChildren().clear();
    }

    private void AddContractToDB(Date fromDate, Date toDate, float Price, WarehouseRoom room, Agent agent, Renter renter, Owner owner) {
        //renter insert?

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
