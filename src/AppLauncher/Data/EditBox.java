package AppLauncher.Data;

import AppLauncher.Main;
import AppLauncher.view.RootLayoutController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class EditBox {


    public static void display(Game game, Plattform plattform, Main main, double x, double y){

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
        Button adding = new Button("Bild hinzufügen");
        Button delete = new Button("Entfernen");
        Button close = new Button("Schließen");
        edit.setMinWidth(250);
        adding.setMinWidth(250);
        delete.setMinWidth(250);
        close.setMinWidth(250);

        TextInputDialog inputDiag = new TextInputDialog(game.getName());

        //Close

        close.setOnAction(event -> window.close());

        //AddingPic
        adding.setOnAction(event -> {
            File file = main.getDirPath();
            String location = "src/AppLauncher/files/"+game.getName()+"/"+file.getName();
            try {
                Files.copy(file.toPath(), new File(location).toPath(), REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                window.close();
            }
        });

        //Delete Game
        delete.setOnAction(event -> {
            plattform.deleteGame(game);
            window.close();
        });

        //Rename

        edit.setOnAction(event -> {
            inputDiag.showAndWait();
            //inputDiag.initModality(Modality.APPLICATION_MODAL);
            System.out.println(inputDiag.getEditor().getText());
            plattform.editGame(game, inputDiag.getEditor().getText());
            //game.setName(inputDiag.getEditor().getText());
            //plattform.save();
            window.close();

        });



        VBox vBox = new VBox();
        vBox.getChildren().addAll(edit, adding, delete, close);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();


    }



}
