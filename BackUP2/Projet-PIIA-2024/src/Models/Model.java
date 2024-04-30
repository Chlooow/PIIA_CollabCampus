package Models;

import javafx.scene.control.Alert;

import java.io.*;

/**
 * Model: The model represents the data and business logic of the application.
 * It is responsible for managing the state of the application, processing data, and enforcing business rules.
 * In web applications, the model often interacts with a database to retrieve and store data.
 * The model is independent of the user interface and can be reused across different applications.
 */


public class Model {

    public void createComment(String commentText) {
        String commentFileName = "src/Commentaires/Comment_" + System.currentTimeMillis() + ".txt";
        try {
            File myObj = new File(commentFileName);
            if (myObj.createNewFile()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(myObj))) {
                    writer.write(commentText);
                    System.out.println("Comment created and saved to file: " + commentFileName);
                } catch (IOException e) {
                    System.out.println("An error occurred while writing the comment to file.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }

    public void updateComment(String filePath, String newContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(newContent);
            System.out.println("Comment updated in file: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while updating the comment file: " + filePath);
            e.printStackTrace();
        }
    }

    public String readCommentFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the comment file: " + filePath);
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Shallow copy
    }
    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
