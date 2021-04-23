package AppLauncher;

import AppLauncher.Data.Game;
import AppLauncher.Data.Plattform;
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
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Commerzbank Launcher™");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/logo.png")));

        initRootLayout();

        testData();
    }

    private void testData() {
        /*
        Plattform steam = new Plattform("steam");

        steam.load();

        for (Game g : steam.getGames()){
            System.out.println(g.getName());
        }

        steam.addGame(new Game("Battlefield", "c:\\BF.exe"));
        steam.addGame(new Game("CSGO", "c:\\counterstrike.exe"));
        steam.save();

        Plattform uplay = new Plattform("uplay");
        uplay.addGame(new Game("Rainbow", "c:\\R6.exe"));
        uplay.addGame(new Game("Wildlands", "c:\\counterstrike.exe"));
        uplay.save();

         */
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
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    public File getDirPath(){
        return fileChooser.showOpenDialog(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
//json file zum speichern der Files -> Liste an Spielobjekten
//spielobjekt: namen, pfad.exe, plattform, bild(?)
