package AppLauncher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    private FileChooser fileChooser;
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        fileChooser = new FileChooser();

        Button button = new Button("select File");
        button.setOnAction(event -> doSmth());

        VBox vBox = new VBox(button);

        primaryStage.setScene(new Scene(vBox, 300, 275));
        primaryStage.show();
    }

    private void doSmth() {

        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        System.out.println("Maurice stinkt immer noch :(");

    }


    public static void main(String[] args) {
        launch(args);
    }
}
