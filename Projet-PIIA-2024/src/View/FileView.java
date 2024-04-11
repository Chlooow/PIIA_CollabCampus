package View;

import Models.FileModel;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class FileView {
    private FileModel model;

    public FileView(FileModel model) {
        this.model = model;
    }

    public void createUI(Stage primaryStage) {
        primaryStage.setTitle("CollabCampus");

        // UI components creation
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Fichier");
        MenuItem menuItemOpenReadOnly = new MenuItem("Ouvrir en lecture seule");
        MenuItem menuItemOpenToEdit = new MenuItem("Ouvrir pour modifier");
        MenuItem menuItemSave = new MenuItem("Enregistrer");
        menuFile.getItems().addAll(menuItemOpenReadOnly, menuItemOpenToEdit, menuItemSave);
        menuBar.getMenus().add(menuFile);
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, textArea);

        // Set up event handlers
        menuItemOpenReadOnly.setOnAction(e -> openFile(primaryStage, textArea, false));
        menuItemOpenToEdit.setOnAction(e -> openFile(primaryStage, textArea, true));
        menuItemSave.setOnAction(e -> saveFile(textArea));

        // Display the scene
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openFile(Stage primaryStage, TextArea textArea, boolean editable) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir un fichier");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            model.openFile(selectedFile);
            try {
                textArea.setText(model.readFileContent());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            textArea.setEditable(editable);
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
