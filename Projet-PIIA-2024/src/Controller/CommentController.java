package Controller;

import Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    private List<String> comments = new ArrayList<>();
    private final String commentFilePath = "comment.txt";

    public CommentController() {
        modele = new Model();
    }

    @FXML
    private void initialize() {
        // Set the TextArea initially non-editable
        commentaireArea.setEditable(false);
        // Retrieve the saved comments from the file
        try {
            if (Files.exists(Paths.get(commentFilePath))) {
                comments = Files.readAllLines(Paths.get(commentFilePath));
            } else {
                Files.createFile(Paths.get(commentFilePath));
            }
        } catch (IOException e) {
            // Handle file read/write error
            e.printStackTrace();
        }
        commentChoiceBox.getItems().addAll(comments);
        if (!comments.isEmpty()) {
            commentChoiceBox.setValue(comments.get(0));
            commentaireArea.setText(comments.get(0));
        }
    }

    @FXML
    private void saveComment() {
        // Get the comment from the TextArea
        String comment = commentaireArea.getText();
        // Save the comment to the file and add it to the list
        comments.add(comment);
        try {
            Files.write(Paths.get(commentFilePath), comments);
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
    private void selectComment() {
        String selectedComment = commentChoiceBox.getValue();
        commentaireArea.setText(selectedComment);
    }
}
