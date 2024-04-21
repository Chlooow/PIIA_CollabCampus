package Controller;

import Models.Model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommentController {
    private Model modele;
    @FXML
    private TextArea commentaireArea;
    @FXML
    private Button editCommentButton;
    @FXML
    private Button saveCommentButton;
    @FXML
    private ChoiceBox<String> commentChoiceBox;

    // Constants
    private static final int MAX_CHARACTER_LIMIT = 200; // Adjust this as needed
    private static final String COMMENT_DIRECTORY = "src/Commentaires";

    public CommentController() {
        modele = new Model();
    }

    // --------------------------

    @FXML
    private void initialize() {
        // Set the TextArea initially non-editable
        commentaireArea.setEditable(false);
        populateCommentChoiceBox();

    }

    @FXML
    private void saveComment() {
        String commentaire = commentaireArea.getText();
        commentaireArea.setEditable(false);
        if (commentaire.length() <= MAX_CHARACTER_LIMIT) {
            // Check if the selected comment file already exists
            String selectedComment = commentChoiceBox.getValue();
            String commentFilePath = COMMENT_DIRECTORY + "/" + selectedComment;
            File existingFile = new File(commentFilePath);

            if (existingFile.exists()) {
                // Update the existing comment file
                modele.updateComment(commentFilePath, commentaire);
            } else {
                // Save the comment (create a new comment file)
                modele.createComment(commentaire);
            }
            modele.showAlert("Comment saved successfully!");
        } else {
            modele.showAlert("Comment cannot exceed " + MAX_CHARACTER_LIMIT + " characters.");
        }
    }

    @FXML
    private void newComment() {
        commentaireArea.clear();
        commentaireArea.setEditable(true);
    }

    @FXML
    private void editComment() {
        commentaireArea.setEditable(true);
    }

    @FXML
    private void selectComment() {
        String selectedComment = commentChoiceBox.getValue();
        String readComment = modele.readCommentFile(COMMENT_DIRECTORY + "/" + selectedComment);
        commentaireArea.setText(readComment);
    }

    private void populateCommentChoiceBox() {
        // Get the list of files in the comment directory
        File directory = new File(COMMENT_DIRECTORY);
        File[] files = directory.listFiles();

        if (files != null) {
            // Convert file array to list of file names
            ObservableList<String> fileNames = FXCollections.observableArrayList();
            Arrays.stream(files).forEach(file -> fileNames.add(file.getName()));

            // Add the file names to the choice box
            commentChoiceBox.setItems(fileNames);
        } else {
            System.out.println("No comment files found in the directory: " + directory);
        }
    }

    @FXML
    private void deleteComment() {
        String selectedComment = commentChoiceBox.getValue();

    }
}
