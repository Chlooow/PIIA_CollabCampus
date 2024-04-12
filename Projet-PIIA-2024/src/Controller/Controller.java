package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

/** Controller: The controller acts as an intermediary between the model and the view.
 It handles user input, updates the model accordingly, and updates the view to reflect any changes in the model.
 In web applications, controllers are often responsible for routing requests, processing form submissions,
 and coordinating interactions between the model and the view.
 */

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    // methods
    public void switchToScene2(javafx.event.ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/View/page-accueil2.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene1(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/page-accueil.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void goToLectureSeule(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/page-lecture-seule.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
