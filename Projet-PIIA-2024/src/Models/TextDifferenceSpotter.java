package Models;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class TextDifferenceSpotter {
    public void spotDifferences(TextArea referenceText, TextArea textModifiable) {
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

        // Clear the listView
        //listView.getItems().clear();

        // Display the differences
        for (DiffRow row : diffRows) {
            String line = row.getTag() == DiffRow.Tag.CHANGE ? row.getOldLine() : row.getNewLine();
            Color color = row.getTag() == DiffRow.Tag.INSERT ? Color.BLACK : Color.web("#66003E");

            String hexColor = String.format("#%02X%02X%02X", (int)(color.getRed() * 255), (int)(color.getGreen() * 255), (int)(color.getBlue() * 255));

            if (row.getTag() == DiffRow.Tag.INSERT) {
                referenceText.setStyle("-fx-highlight-fill: " + hexColor + ";");
                textModifiable.setStyle("-fx-highlight-fill: " + hexColor + ";");
                // Handle added lines
                System.out.println("Added: " + row.getNewLine());
            } else if (row.getTag() == DiffRow.Tag.DELETE) {
                // Handle deleted lines
                System.out.println("Deleted: " + row.getOldLine());
            } else if (row.getTag() == DiffRow.Tag.CHANGE) {
                // Handle changed lines
                referenceText.setStyle("-fx-text-fill:" + hexColor + ";");
                textModifiable.setStyle("-fx-text-fill:" + hexColor + ";");
                System.out.println("Changed: " + row.getOldLine() + " -> " + row.getNewLine());
            }
        }
    }
}
