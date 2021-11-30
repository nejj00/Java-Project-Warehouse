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

import static bg.tu_varna.sit.group19.warehouse_project.presentation.controllers.LoginController.accountType;

public class MainApplication extends Application {

    private static final Logger log = Logger.getLogger(MainApplication.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws IOException  {

        PropertyConfigurator.configure(MainApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        URL loginPath = getClass().getResource(Constants.View.LOGIN_VIEW);
        URL registerPath = getClass().getResource(Constants.View.REGISTER_VIEW);
        URL mainAdminPath = getClass().getResource(Constants.View.ADMIN_VIEW);
        URL mainAgentPath = getClass().getResource(Constants.View.AGENT_VIEW);
        URL mainOwnerPath = getClass().getResource(Constants.View.OWNER_VIEW);

        if(loginPath != null && mainAdminPath != null && mainAgentPath!=null&& mainOwnerPath!=null&&registerPath!=null){

            Parent root = FXMLLoader.load(mainAdminPath);

            Scene mainScene = new Scene(root);
            mainScene.setFill(Color.TRANSPARENT);

            /*
            //set main screen scene and settings
            mainStage.setTitle(Constants.Values.MAIN_TITILE);
            mainStage.setScene(mainScene);
            mainStage.setResizable(false);
            mainStage.setWidth(800);
            mainStage.setHeight(600);
            mainStage.show();//load main screen
             */

            //set second window scene
            root = FXMLLoader.load(loginPath);
            Stage loginStage = new Stage();
            Scene loginScene = new Scene(root);
            loginStage.setScene(loginScene);
            loginStage.showAndWait();//load login screen and waiting it to close
            //when it closes the account type will be saved in controller


            switch (accountType.getAccountType()) {
                case Admin -> {
                    root = FXMLLoader.load(mainAdminPath);
                    Scene admin = new Scene(root);
                    mainStage.setScene(admin);
                }
                case Agent -> {
                    root = FXMLLoader.load(mainAgentPath);
                    Scene agent = new Scene(root);
                    mainStage.setScene(agent);
                }
                case Owner -> {
                    root = FXMLLoader.load(mainOwnerPath);
                    Scene owner = new Scene(root);
                    mainStage.setScene(owner);
                }
            }
        }
        else {
            log.error("Sorry, the main fxml could not be loaded");
            System.exit(-1);
        }

    }
}
