package bg.tu_varna.sit.group19.warehouse_project.business.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertMessages {
    public static boolean alertYesNoResult(String message, String title) {
        ButtonType yesDelete = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType noDelete = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING, message, yesDelete, noDelete);

        alert.setTitle(title);
        Optional<ButtonType> result = alert.showAndWait();

        return result.orElse(noDelete) == yesDelete;
    }

    public static void alertInformationMessage(String title, String header, String contextText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

    public static void alertWarningMessage(String title, String contextText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(contextText);
        alert.showAndWait();
    }

}
