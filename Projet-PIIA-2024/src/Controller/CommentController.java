package Controller;

import Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CommentController {
    private Model modele;
    @FXML
    private TextArea commentaireArea;
    @FXML
    private Button editCommentButton;
    @FXML
    private Button saveCommentButton;

    private String savedComment = "";
    private final String commentFilePath = "comment.txt";

    public CommentController() {
        modele = new Model();
    }

    @FXML
    private void initialize() {
        // Set the TextArea initially non-editable
        commentaireArea.setEditable(false);
        // Retrieve the saved comment from the file
        try {
            if (Files.exists(Paths.get(commentFilePath))) {
                savedComment = new String(Files.readAllBytes(Paths.get(commentFilePath)));
            } else {
                Files.createFile(Paths.get(commentFilePath));
            }
        } catch (IOException e) {
            // Handle file read/write error
            e.printStackTrace();
        }
        commentaireArea.setText(savedComment);
    }
    @FXML
    private void saveComment() {
        // Get the comment from the TextArea
        savedComment = commentaireArea.getText();
        // Save the comment to the file
        try {
            Files.write(Paths.get(commentFilePath), savedComment.getBytes());
        } catch (IOException e) {
            // Handle file write error
            e.printStackTrace();
        }
        commentaireArea.setEditable(false);
        modele.showAlert("Comment saved successfully!");
    }

    @FXML
    private void editComment() {
        commentaireArea.setEditable(true);
    }

    @FXML
    private void confirmation(){

    }
}

