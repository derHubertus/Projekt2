package AppLauncher.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import AppLauncher.Main;

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
    @FXML
    private ImageView ivImageSet;


    private Main main;

    public void initialize(){
        lvGameList = new ListView<>();
    }

    public void setMain(Main main){
        this.main = main;
    }

    @FXML
    public void lbGameAddClicked(){
        main.getDirPath();
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
    @FXML
    public void paneGetImageFromWeb(){

        //ImageView img = new ImageView("https://cdn.cloudflare.steamstatic.com/steam/apps/apps/730/ss_9db552fd461722f1569e3292d8f2ea654c8ffdef.jpg");
        //gpImageSet.setStyle("-fx-background-image: url('https://cdn.cloudflare.steamstatic.com/steam/apps/apps/730/ss_9db552fd461722f1569e3292d8f2ea654c8ffdef.jpg')");
        Image img = new Image("images/imageimage.jpg");
        ivImageSet.setImage(img);

    }
    public void urlStart(String s){
        String url = "https://cdn.cloudflare.steamstatic.com/steam/apps/"+s;
        System.out.println(url);
    }







}
