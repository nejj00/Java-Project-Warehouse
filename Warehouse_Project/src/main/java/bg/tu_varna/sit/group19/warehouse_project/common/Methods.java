package bg.tu_varna.sit.group19.warehouse_project.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Methods {
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
}
