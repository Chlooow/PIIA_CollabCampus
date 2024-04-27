package Models;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.github.difflib.patch.*;

public class TextDifferenceSpotter {
    String originalText = "";
    private LocalDateTime lastModificationTimestamp = LocalDateTime.MIN;

    public String timeModif() {
        // Calculate modification based on the hour of the system
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }
    public void spotDifferences(TextArea referenceText, TextArea textModifiable) {
        if (originalText.isEmpty()) {
            originalText = textModifiable.getText();
        }
        // Get text content from both text areas
        String text1 = referenceText.getText();
        String text2 = textModifiable.getText();

        // Convert text content to list of strings (lines)
        List<String> lines1 = List.of(text1.split("\n"));
        List<String> lines2 = List.of(text2.split("\n"));

        // Generate differences between the two lists of strings
        Patch<String> patch = DiffUtils.diff(lines1, lines2);

        // Create a DiffRowGenerator to generate diff rows
        DiffRowGenerator diffRowGenerator = DiffRowGenerator.create()
                .showInlineDiffs(true) // Show inline differences
                .inlineDiffByWord(true) // Diff by word
                .oldTag(f -> "~") // Use ~ for the old lines
                .newTag(f -> "`") // Use ` for the new lines
                .build();

        // Generate diff rows
        List<DiffRow> diffRows = diffRowGenerator.generateDiffRows(lines1, lines2);

        String formattedTime = timeModif();

        // Display the differences
        for (DiffRow row : diffRows) {
            String line = row.getTag() == DiffRow.Tag.CHANGE ? row.getOldLine() : row.getNewLine();

            if (row.getTag() == DiffRow.Tag.INSERT) {
                referenceText.setStyle("-fx-highlight-fill: #66003E;");
                textModifiable.setStyle("-fx-highlight-fill: #66003E;");
                // Handle added lines
                System.out.println("Added: " + row.getNewLine() + " Modification at: " + formattedTime);
            } else if (row.getTag() == DiffRow.Tag.DELETE) {
                referenceText.setStyle("-fx-highlight-fill: red;");
                textModifiable.setStyle("-fx-highlight-fill: red;");
                // Handle deleted lines
                System.out.println("Deleted: " + row.getOldLine() + " Modification at: " + formattedTime);
            } else if (row.getTag() == DiffRow.Tag.CHANGE) {
                referenceText.setStyle("-fx-text-fill: #66003E;");
                textModifiable.setStyle("-fx-text-fill: #66003E;");
                // Handle changed lines
                System.out.println("Changed: " + row.getOldLine() + " -> " + row.getNewLine() + " Modification at: " + formattedTime);
            }
            lastModificationTimestamp = LocalDateTime.now();
        }
    }
    public void acceptModification(String referenceText, TextArea textModifiable) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Accept Modification");
        alert.setContentText("Are you sure you want to accept the modification?");
        if(!textModifiable.getText().equals(referenceText)) {
            referenceText = textModifiable.getText();
        }

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            showAlert("Tu as accepté la modification");
        }
    }

    public void refuseModification(String referenceText, TextArea textModifiable) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir refuser la modification ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            textModifiable.setText(referenceText);
            showAlert("Tu as refusé la modification");
        }
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
