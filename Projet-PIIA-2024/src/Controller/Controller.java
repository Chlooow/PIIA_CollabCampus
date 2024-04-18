package Controller;

import Models.FileModel;
import View.FileView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.swing.text.View;
import java.awt.event.TextEvent;
import java.io.IOException;

/** Controller: The controller acts as an intermediary between the model and the view.
 It handles user input, updates the model accordingly, and updates the view to reflect any changes in the model.
 In web applications, controllers are often responsible for routing requests, processing form submissions,
 and coordinating interactions between the model and the view.
 */

public class Controller {
    public TextArea textOpenReadOnly; //= new TextArea();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FileView view;
    private FileModel modele;

    // constructor
    public Controller() {
        this.modele = new FileModel();
        this.view = new FileView(modele);
        //textOpenReadOnly.setText("Amineok");
    }

    // methods

    // Method pour la page de d'accueil 1
    public void switchToScene2(javafx.event.ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/View/page-accueil2.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Method pour la page de d'accueil 2
    public void switchToScene1(javafx.event.ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/page-accueil.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // method du bouton pour lire en lecture seule
    public void goToLectureSeule(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/page-lecture-seule.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void lectureSeule() {
        TextArea textArea = view.getTextOpenReadOnly();
        System.out.println(textArea.getText());
        view.openFile(stage, false);
        System.out.println(textArea.getText());
    }
}
