# PIIA_CollabCampus
PIIA - 2023/2024 Projet groupe 3 édition de texte binôme : Chéïma Hamrouni

fonction pour mettre la date :

```// Calculate modification based on the hour of the system
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            System.out.println("Modification at: " + formattedTime);
```

ok : Charger un fichier TXT de référence pouvant comporter jusqu’à 10 000 signes. Les
fichiers TXT sont des fichiers de texte brut, sans formatage. Il n’est pas attendu que le
programme prenne en compte des modifications de formatage. Ce texte devra être en
lecture seule.

- on peu retourner sur la page d'accueil de la page de lecture seul
- on peux access à la page editable depuis celle de lecture seule et inversement
- je peux ouvrir le .txt dans les deux textAreas (un modifiable et 1 en lecture seul)
- on peut "save as" mais pas simplement "Save"
- on peu commenter et regarder le commentaire

il reste à faire :

 Charger un fichier TXT comportant des modifications par rapport au texte de référence.
 Visualiser les deux textes à comparer côte à côte (le texte de référence et le texte modifié, plus récent) et distinguer les différents types de modifications (ajouts, suppressions, remplacements).
 Accepter des modifications.
 Refuser des modifications.


```java
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
    private final String commentFilePath = "comments.txt";

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
        commentChoiceBox.getItems().add(comment);
        commentChoiceBox.setValue(comment);
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
```


 Commenter des modifications.
 Éditer librement le texte comportant les modifications afin de formuler des contre�propositions. Les contre-propositions doivent être distinguables des autres types de
modifications.
 Sauvegarder au format TXT le fichier résultant du travail effectué sur les modifications
(acceptions, refus et contre-propositions).
