package bg.tu_varna.sit.group19.warehouse_project.application;

import bg.tu_varna.sit.group19.warehouse_project.common.Constants;
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

public class LoginApplication extends Application {

    private static final Logger log = Logger.getLogger(LoginApplication.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage loginStage) throws IOException  {

        PropertyConfigurator.configure(LoginApplication.class.getResource(Constants.Configurations.LOG4J_PROPERTIES));
        URL path = getClass().getResource(Constants.View.LOGIN_VIEW);

        if(path != null){
            Parent root = FXMLLoader.load(path);

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            loginStage.setTitle(Constants.Values.LOGIN_TITILE);
            loginStage.setScene(scene);
            loginStage.setResizable(false);
            loginStage.setWidth(600);
            loginStage.setHeight(400);
            loginStage.show();
        }else {
            log.error("Sorry, the login fxml could not be loaded");
            System.exit(-1);
        }

    }
}
