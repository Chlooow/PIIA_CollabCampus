import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception{
        // Create a new Scene
        Scene scene = new Scene(new Group(), 450, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Welcome");
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}