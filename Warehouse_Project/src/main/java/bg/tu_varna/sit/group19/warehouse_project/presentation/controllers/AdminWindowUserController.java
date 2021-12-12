package bg.tu_varna.sit.group19.warehouse_project.presentation.controllers;

import bg.tu_varna.sit.group19.warehouse_project.business.holders.EnumHolder;
import bg.tu_varna.sit.group19.warehouse_project.business.services.AgentService;
import bg.tu_varna.sit.group19.warehouse_project.business.services.OwnerService;
import bg.tu_varna.sit.group19.warehouse_project.business.utils.ListContextMenu;
import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.common.Enums;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Agent;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.AgentAccount;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.Owner;
import bg.tu_varna.sit.group19.warehouse_project.data.entities.OwnerAccount;
import bg.tu_varna.sit.group19.warehouse_project.presentation.models.UserListViewModel;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class AdminWindowUserController implements EventHandler<MouseEvent> {

    @FXML
    private ListView usersListView;
    @FXML
    private AnchorPane mainAnchorPane;

    private final OwnerService ownerService = OwnerService.getInstance();

    private AgentService agentService = AgentService.getInstance();

    private final ListContextMenu listContextMenu = new ListContextMenu();

    private EnumHolder enumHolder = EnumHolder.getInstance();

    @FXML
    public void initialize() {
        if(enumHolder.getAccountType() == Enums.AccountType.Owner)
        {
            usersListView.setItems(ownerService.getAllOwners());
            usersListView.setContextMenu(listContextMenu.getMyContext());
        }
        else if (enumHolder.getAccountType() == Enums.AccountType.Agent)
        {
            usersListView.setItems(agentService.getAllAgents());
            usersListView.setContextMenu(listContextMenu.getMyContext());
        }


        listContextMenu.getMenuItemInsert().setOnAction((event)->{
            menuInsertAction();
        });

        listContextMenu.getMenuItemUpdate().setOnAction((event) ->{
            menuUpdateAction();
        });

        listContextMenu.getMenuItemDelete().setOnAction((event)->{
            menuDeleteAction();
        });
    }

    private void menuDeleteAction() {
        ButtonType foo = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType bar = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Are you sure you want to delete this record.", foo, bar);

        alert.setTitle("Deletion Warning");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(bar) != foo)
            return;

        if(enumHolder.getAccountType() == Enums.AccountType.Owner)
        {
            ObservableList<UserListViewModel<OwnerAccount>> userListViewModels = ownerService.getAllOwners();
            UserListViewModel<OwnerAccount> userListViewModel = userListViewModels.get(usersListView.getSelectionModel().getSelectedIndex());
            Owner owner = ownerService.getOwnerById(userListViewModel.getUserID());
            ownerService.deleteOwner(owner);
        }
        else if (enumHolder.getAccountType() == Enums.AccountType.Agent)
        {
            ObservableList<UserListViewModel<AgentAccount>> userListViewModels = agentService.getAllAgents();
            UserListViewModel<AgentAccount> userListViewModel = userListViewModels.get(usersListView.getSelectionModel().getSelectedIndex());
            Agent agent = agentService.getAgentById(userListViewModel.getUserID());
            agentService.deleteAgent(agent);
        }
    }

    URL pathRegister = getClass().getResource(Constants.View.REGISTER_VIEW);

    private void menuUpdateAction() {
        // check
        //ObservableList<UserListViewModel> userListViewModels2;
        //UserListViewModel<OwnerAccount> userListViewModel = userListViewModels.get(usersListView.getSelectionModel().getSelectedIndex());

        var userListViewModels =
                (enumHolder.getAccountType() == Enums.AccountType.Owner) ? ownerService.getAllOwners() : agentService.getAllAgents();

        var userListViewModel = userListViewModels.get(usersListView.getSelectionModel().getSelectedIndex());

        /*if(enumHolder.getAccountType() == Enums.AccountType.Owner)
        {
            userListViewModels<OwnerAccount = ownerService.getAllOwners();
            userListViewModel = userListViewModels.get(usersListView.getSelectionModel().getSelectedIndex());
        }
        else if (enumHolder.getAccountType() == Enums.AccountType.Agent)
        {
            usersListView.setItems(agentService.getAllAgents());
            usersListView.setContextMenu(listContextMenu.getMyContext());
        }*/

        Enums.OpenMode openMode = Enums.OpenMode.UpdateMode;
        FXMLLoader fxmlLoader = new FXMLLoader(pathRegister);
        BorderPane pane = getPaneFromRegister(fxmlLoader);

        RegisterController controller = fxmlLoader.<RegisterController>getController();
        controller.setOpenMode(openMode);

        if(enumHolder.getAccountType() == Enums.AccountType.Owner)
            controller.getAgentRadioButton().setVisible(false);
        else if(enumHolder.getAccountType() == Enums.AccountType.Agent)
            controller.getOwnerRadioButton().setVisible(false);

        controller.getRegisterPane().setPrefWidth(500);
        controller.getImagePane().setPrefWidth(0);

        controller.setUserID(userListViewModel.getUserID());
        controller.getFirstName().setText(userListViewModel.getFirstName());
        controller.getLastName().setText(userListViewModel.getLasName());
        controller.getUsername().setText(userListViewModel.getUsername());

        mainAnchorPane.getChildren().setAll(pane);
    }

    private void menuInsertAction() {
        Enums.OpenMode openMode = Enums.OpenMode.InsertMode;
        FXMLLoader fxmlLoader = new FXMLLoader(pathRegister);
        BorderPane pane = getPaneFromRegister(fxmlLoader);
        RegisterController controller = fxmlLoader.<RegisterController>getController();

        controller.setOpenMode(openMode);

        if(enumHolder.getAccountType() == Enums.AccountType.Owner)
            controller.getAgentRadioButton().setVisible(false);
        else if(enumHolder.getAccountType() == Enums.AccountType.Agent)
            controller.getOwnerRadioButton().setVisible(false);

        controller.getRegisterPane().setPrefWidth(500);
        controller.getImagePane().setPrefWidth(0);
        mainAnchorPane.getChildren().setAll(pane);
    }

    private BorderPane getPaneFromRegister(FXMLLoader fxmlLoader) {
        BorderPane pane = null;
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

    }
}
