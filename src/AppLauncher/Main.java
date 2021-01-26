package AppLauncher;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import AppLauncher.view.RootLayoutController;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private AnchorPane rootLayout;
    private ObservableList<String> gameList = FXCollections.observableArrayList();

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
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
