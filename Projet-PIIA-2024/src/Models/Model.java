package Models;

import javafx.scene.control.Alert;

/**
 * Model: The model represents the data and business logic of the application.
 * It is responsible for managing the state of the application, processing data, and enforcing business rules.
 * In web applications, the model often interacts with a database to retrieve and store data.
 * The model is independent of the user interface and can be reused across different applications.
 */


public class Model {

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
