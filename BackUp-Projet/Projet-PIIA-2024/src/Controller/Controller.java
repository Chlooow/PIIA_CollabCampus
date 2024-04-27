package Controller;

import Models.FileModel;
import Models.TextDifferenceSpotter;
import View.FileView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.swing.text.View;
import java.awt.event.TextEvent;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Optional;

/** Controller: The controller acts as an intermediary between the model and the view.
 It handles user input, updates the model accordingly, and updates the view to reflect any changes in the model.
 In web applications, controllers are often responsible for routing requests, processing form submissions,
 and coordinating interactions between the model and the view.
 */

public class Controller {
   // public TextArea textOpenReadOnly; //= new TextArea();
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FileView view;
    private FileModel modele;

    private File openedFile = null;

    @FXML
    private TextArea referenceText;

    @FXML
    private TextArea textModifiable;
    @FXML
    private Button viewCommentBtn;

    @FXML
    private ImageView imagebtnedit;

    @FXML
    private Button buttonokedition;

    @FXML
    private Label modificationtime;

    @FXML
    private void openReadOnly() {
        openFile();
    }


    // Method to open a file
    private void openFile() {
        Stage primaryStage = (Stage) referenceText.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            openedFile = selectedFile;
            displayFileContent(referenceText);
            displayFileContent(textModifiable);
            //textModifiable.setEditable(true);
        }
    }

    // Method to display the content of the file in the text area
    private void displayFileContent(TextArea textArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(openedFile))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            textArea.setText(content.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void saveFileAs() {
        Stage primaryStage = (Stage) textModifiable.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showSaveDialog(primaryStage);

        if (selectedFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                writer.write(textModifiable.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void saveFile() {
        if (openedFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(openedFile))) {
                writer.write(textModifiable.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            // Handle the case where no file is opened
            System.out.println("No file opened. Cannot save.");
        }
    }

    @FXML
    private void openCommentWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/comment-Window.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.showAndWait(); // Wait for comment window to close before proceeding
    }

    // ---------------------------------

    private String currentText;
    // accepter les modifications
    @FXML
    private void acceptModification(){
        currentText = referenceText.getText();
        TextDifferenceSpotter differenceSpotter = new TextDifferenceSpotter();
        differenceSpotter.acceptModification(currentText, textModifiable);
    }

    // refuser les modifications
    @FXML
    private void refuseModification(){
        currentText = referenceText.getText();
        TextDifferenceSpotter differenceSpotter = new TextDifferenceSpotter();
        differenceSpotter.refuseModification(currentText, textModifiable);
    }

    // differences de textes
    @FXML
    private void edition() {
        textModifiable.setEditable(true);
        imagebtnedit.setVisible(true);
        buttonokedition.setVisible(true);
    }

    @FXML
    private void spotDifference() {
        imagebtnedit.setVisible(false);
        buttonokedition.setVisible(false);
        textModifiable.setEditable(false);

        TextDifferenceSpotter differenceSpotter = new TextDifferenceSpotter();
        differenceSpotter.spotDifferences(referenceText, textModifiable);
        modificationtime.setText("Last modification at: " + differenceSpotter.timeModif());
    }

    // ---------------------------------

    // constructor
    public Controller() {
        this.modele = new FileModel();
        this.view = new FileView(modele);
        this.currentText = new String();
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

    public void goToEditable(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/page-edition.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Add this method to switch to the scene with read-only text area
    public void openReadOnlyScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/page-lecture-seule.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(scene);
        stage.show();
    }



//---------------------------------
    // brouillon
    public void lectureSeule() {
        TextArea textArea = view.getTextOpenReadOnly();
        System.out.println(textArea.getText());
        view.openFile(stage, false);
        System.out.println(textArea.getText());
    }
}
