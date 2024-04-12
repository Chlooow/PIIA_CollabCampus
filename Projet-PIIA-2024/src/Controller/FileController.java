package Controller;

import Models.FileModel;
import View.FileView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class FileController extends Application {
    private FileModel model;
    private FileView view;

    public FileController() {
        this.model = new FileModel();
        //this.view = new FileView(model);
    }

    @Override
    public void start(Stage primaryStage) {
        //view.lectureSeule(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
