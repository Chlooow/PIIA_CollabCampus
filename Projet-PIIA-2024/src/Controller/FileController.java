package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private File openedFile = null;

    @FXML
    private MenuItem menuItemOpenReadOnly;

    @FXML
    private TextArea textOpenReadOnly;

    // Method to open a file in read-only mode
    @FXML
    private void openReadOnly() {
        openFile(false);
    }

    // Method to open a file
    private void openFile(boolean editable) {
        Stage primaryStage = (Stage) textOpenReadOnly.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            openedFile = selectedFile;
            displayFileContent(textOpenReadOnly);
            textOpenReadOnly.setEditable(editable);
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

    // Method to save the current content of the text area to the file
    @FXML
    private void saveFile() {
        if (openedFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(openedFile))) {
                writer.write(textOpenReadOnly.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void switchToScene1(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/View/page-accueil.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
