package bg.tu_varna.sit.group19.warehouse_project.business.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ScenePaneSwitcher {
    public void ChangeScene(Stage stage, URL path){
        Parent root = null;
        try {
            root = FXMLLoader.load(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public Stage getStage(MouseEvent mouseEvent){
        Node node = (Node) mouseEvent.getSource();
        return (Stage) node.getScene().getWindow();
    }

    public static AnchorPane getPaneToSwitchTo(URL path) {
        AnchorPane pane = null;
        try {
            pane = FXMLLoader.load(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pane;
    }

    public static void openNewStageAndWait(URL path) {
        Stage myStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(path);
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);
        myStage.setScene(scene);
        myStage.showAndWait();
    }

}
