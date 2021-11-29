package bg.tu_varna.sit.group19.warehouse_project.application;

import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
import bg.tu_varna.sit.group19.warehouse_project.presentation.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.net.URL;

public class MainApplication extends Application {

    private static final Logger log = Logger.getLogger(MainApplication.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws IOException  {

        PropertyConfigurator.configure(MainApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        URL loginPath = getClass().getResource(Constants.View.LOGIN_VIEW);
        URL path = getClass().getResource(Constants.View.MAIN_WINDOW_VIEW);
        URL adminPath = getClass().getResource(Constants.View.ADMIN_VIEW);
        URL agentPath = getClass().getResource(Constants.View.AGENT_VIEW);
        URL ownerPath = getClass().getResource(Constants.View.OWNER_VIEW);

        if(loginPath != null && path != null){

            Parent root = FXMLLoader.load(path);

            Scene mainScene = new Scene(root);
            mainScene.setFill(Color.TRANSPARENT);

            //set main screen scene and settings
            mainStage.setTitle(Constants.Values.MAIN_TITILE);
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainStage.setWidth(1600);
            mainStage.setHeight(900);
            mainStage.show();//load main screen


            //set second window scene
            root = FXMLLoader.load(loginPath);
            Stage loginStage = new Stage();
            Scene loginScene = new Scene(root);
            loginStage.setScene(loginScene);
            loginStage.showAndWait();//load login screen and waiting it to close
            //when it closes the account type will be saved in controller


            switch(LoginController.AccountType) {
                case Constants.AccountTypes.Admin: //load to main screen admin account version

                    root = FXMLLoader.load(adminPath);
                    Scene adminScene = new Scene(root);
                    mainStage.setScene(adminScene);
                    break;

                case Constants.AccountTypes.Agent: //load to main screen agent account version

                    root = FXMLLoader.load(agentPath);
                    Scene agentScene = new Scene(root);
                    mainStage.setScene(agentScene);
                    break;

                case Constants.AccountTypes.Owner: //load to main screen owner account version

                    root = FXMLLoader.load(ownerPath);
                    Scene ownerScene = new Scene(root);
                    mainStage.setScene(ownerScene);
                    break;
            }
        }
        else {
            log.error("Sorry, the main fxml could not be loaded");
            System.exit(-1);
        }

    }
}
