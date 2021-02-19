package AppLauncher.Data;

import AppLauncher.view.RootLayoutController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EditBox {


    public static void display(Game game, Plattform plattform, double x, double y){

        Stage window = new Stage();
        window.setTitle("Edit");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMaxWidth(250);
        window.setMaxHeight(150);
        window.setResizable(false);
        window.initStyle(StageStyle.UNDECORATED);
        window.setX(x+30);
        window.setY(y+30);

        Button edit = new Button("Namen ändern");
        Button delete = new Button("Entfernen");
        Button close = new Button("Schließen");
        edit.setMinWidth(250);
        delete.setMinWidth(250);
        close.setMinWidth(250);

        close.setOnAction(event -> window.close());
        delete.setOnAction(event -> {
            plattform.deleteGame(game);
            window.close();
        });


        VBox vBox = new VBox();
        vBox.getChildren().addAll(edit, delete, close);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();


    }



}