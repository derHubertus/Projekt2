package AppLauncher.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import AppLauncher.Main;

import java.io.File;

public class RootLayoutController {

    @FXML
    private Label lbSteam;
    @FXML
    private Label lbOrigin;
    @FXML
    private Label lbUplay;
    @FXML
    private Label lbGameAdd;
    @FXML
    private Label lbGamePlay;
    @FXML
    private Label lbGameNameDisplay;
    @FXML
    private ListView<String> lvGameList;
    @FXML
    private AnchorPane apGameInfo;

    private Main main;

    public void initialize(){
        lvGameList = new ListView<>();
    }

    public void setMain(Main main){
        this.main = main;
    }

    @FXML
    public void lbSteamClicked(){
        lvGameList.getItems().addAll();
    }
    public void changeColorHover(Label label){
        label.setTextFill(Color.GRAY);
    }
    public void changeColorDefault(Label label){
        label.setTextFill(Color.BLACK);
    }
    @FXML
    public void lbSteamHover(){
        changeColorHover(lbSteam);
    }
    @FXML
    public void lbSteamDefault(){
        changeColorDefault(lbSteam);
    }
    @FXML
    public void lbOriginHover(){
        changeColorHover(lbOrigin);
    }
    @FXML
    public void lbOriginDefault(){
        changeColorDefault(lbOrigin);
    }
    @FXML
    public void lbUplayHover(){
        changeColorHover(lbUplay);
    }
    @FXML
    public void lbUplayDefault(){
        changeColorDefault(lbUplay);
    }
    @FXML
    public void lbGameAddHover(){
        changeColorHover(lbGameAdd);
    }
    @FXML
    public void lbGameAddDefault(){
        changeColorDefault(lbGameAdd);
    }
    @FXML
    public void lbGamePlayDefault(){
        changeColorDefault(lbGamePlay);
    }
    @FXML
    public void lbGamePlayHover(){
        changeColorHover(lbGamePlay);
    }




}
