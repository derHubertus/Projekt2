package AppLauncher.view;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
    @FXML
    private MenuBar menuBar;
    @FXML
    private HBox hboxTop;
    @FXML
    private HBox hboxBottom;
    @FXML
    private AnchorPane apBackGround;
    @FXML
    private MenuItem mbItemClose;
    @FXML
    private MenuItem mbItemLight;
    @FXML
    private MenuItem mbItemDark;
    @FXML
    private MenuItem mbItemAbout;

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
    public String urlStart(String s){
        String url = "https://cdn.cloudflare.steamstatic.com/steam/apps/"+s;
        return url;
    }
    @FXML
    public void paneGetImageFromWeb(){
        String plusurl = "730/ss_2fcee01bace72bc47a2ad0ba82620588239e93df.jpg";
        String url = urlStart(plusurl);
        Image img = new Image(url, apGameInfo.getHeight(), apGameInfo.getWidth(), true, false);
        ivImageSet.fitWidthProperty().bind(apGameInfo.widthProperty());
        ivImageSet.fitHeightProperty().bind(apGameInfo.heightProperty());
        ivImageSet.setPreserveRatio(true);
        ivImageSet.setImage(img);
    }
    @FXML
    public void switchSkinDark(){
        //apGameInfo.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        //hboxBottom.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        //hboxTop.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        lvGameList.setStyle("-fx-background-color: white;");

        //apBackGround.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        menuBar.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        //ivImageSet.setImage(new Image("AppLauncher/images/imageimage.jpg"));
        mbItemAbout.setStyle("-fx-background-color : #D3D3D3;");
        mbItemClose.setStyle("-fx-background-color : #D3D3D3;");
        mbItemDark.setStyle("-fx-background-color : #D3D3D3;");
        mbItemLight.setStyle("-fx-background-color : #D3D3D3;");

    }
    @FXML
    public void switchSkinLight(){
        //apGameInfo.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        //hboxBottom.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        //hboxTop.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        lvGameList.setStyle("-fx-background-color: blue;");
        apBackGround.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        menuBar.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        mbItemAbout.setStyle("-fx-background-color : #FFFFFF;");
        mbItemClose.setStyle("-fx-background-color : #FFFFFF;");
        mbItemDark.setStyle("-fx-background-color : #FFFFFF;");
        mbItemLight.setStyle("-fx-background-color : #FFFFFF;");
    }











}
