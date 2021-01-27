package AppLauncher;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import AppLauncher.view.RootLayoutController;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;
    private ObservableList<String> gameList = FXCollections.observableArrayList();
    private FileChooser fileChooser = new FileChooser();

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Hoomak Launcher™");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/logo.png")));
        //primaryStage.setScene(new Scene(root, 600, 480));
        //primaryStage.show();
        initRootLayout();
    }

    public void initRootLayout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setMinWidth(rootLayout.getMinWidth());
            primaryStage.setMinHeight(rootLayout.getMinHeight());
            primaryStage.setMaxHeight(rootLayout.getMaxHeight());
            primaryStage.setMaxWidth(rootLayout.getMaxWidth());
            primaryStage.setScene(scene);
            primaryStage.show();
            RootLayoutController controller = loader.getController();
            controller.setMain(this);
            //Steam-Objekt
            //Steam-JSON-Loader
            //Origin-Objekt
            //Origin-JSON-Loader
            //Uplay-Objekt
            //Uplay-JSON-Loader
            //Nach Knopfdruck ListView befüllen
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    public void getDirPath(){
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
