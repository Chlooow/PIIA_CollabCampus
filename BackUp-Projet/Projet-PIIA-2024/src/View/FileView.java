package View;

import Models.FileModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FileView {
    private FileModel model;
    @FXML
    public MenuItem menuItemOpenReadOnly;
    @FXML
    private TextArea textOpenReadOnly;

    public FileView(FileModel model) {
        this.model = model;
        //this.textOpenReadOnly = new TextArea();
        //textOpenReadOnly.setText("Amineok");
    }

    // getter
    public TextArea getTextOpenReadOnly() {
        return textOpenReadOnly;
    }

    public void openFile(Stage primaryStage, boolean editable) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir un fichier");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            System.out.print("lol ");
            model.openFile(selectedFile);
            try {
               textOpenReadOnly.setText(model.readFileContent());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            displayFileContent(selectedFile, this.textOpenReadOnly);
            textOpenReadOnly.setEditable(editable);
        }
    }

    // Méthode pour afficher le contenu du fichier dans la zone de texte
    private void displayFileContent(File file, TextArea textArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.print(line);
                content.append(line).append("\n");
            }
            textArea.setText("Amine");
            System.out.print("Texte affiché :)");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void saveFile(TextArea textArea) {
        try {
            model.saveFile(textArea.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
