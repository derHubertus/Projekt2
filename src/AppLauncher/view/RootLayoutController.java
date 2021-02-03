package AppLauncher.view;

import AppLauncher.Data.EditBox;
import AppLauncher.Data.Game;
import AppLauncher.Data.GameCell;
import AppLauncher.Data.Plattform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import AppLauncher.Main;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.*;

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
    private ListView<Game> lvGameList = new ListView<>();
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



    private Plattform plattformSteam = new Plattform("Steam");
    private Plattform plattformOrigin = new Plattform("Origin");
    private Plattform plattformUplay = new Plattform("Uplay");
    private String chosenPlattform;
    private FileChooser fileChooser = new FileChooser();
    private ObservableList<Game> clearList = FXCollections.observableArrayList();



    public void initialize(){

        lbSteamClicked();

        lvGameList.setCellFactory(value -> {
            return new GameCell();
        });




    }
    public void setMain(Main main){
        this.main = main;
    }
    @FXML
    public void lbGameAddClicked(){
        File file = main.getDirPath();


        if (!file.canExecute()){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Achtung");
            alert.setHeaderText("Bitte eine .exe Datei wählen");
            alert.showAndWait();
        }else{
            String path = file.getPath();
            String name = getNameFromPath(path);

            switch (chosenPlattform){
                case "steam":
                    plattformSteam.addGame(new Game(name, path));
                    plattformSteam.save();
                    return;

                case "uplay":
                    plattformUplay.addGame(new Game(name, path));
                    plattformUplay.save();
                    return;

                case "origin":
                    plattformOrigin.addGame(new Game(name, path));
                    plattformOrigin.save();
                    return;

                default:
                    return;
            }
        }




    }
    @FXML
    public void lbSteamClicked(){
        lvGameList.getItems().removeAll();
        chosenPlattform = "steam";
        lvGameList.setItems(plattformSteam.getGames2());
        /*lvGameList.setCellFactory(value -> {
            return new GameCell();
        });*/

    }
    @FXML
    public void lbOriginClicked(){
        lvGameList.getItems().removeAll();
        chosenPlattform = "origin";
        lvGameList.setItems(plattformOrigin.getGames2());
        /*lvGameList.setCellFactory(value -> {
            return new GameCell();
        });*/

    }
    @FXML
    public void lbUplayClicked(){
        lvGameList.getItems().removeAll();
        chosenPlattform = "uplay";
        lvGameList.setItems(plattformUplay.getGames2());
        /*lvGameList.setCellFactory(value -> {
            return new GameCell();
        });*/

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
    @FXML
    public void launchGame(){

        try{
            lvGameList.getSelectionModel().getSelectedItem().launchGame();
            System.out.println("Hier sind wir schonmal");
        }catch (Exception e){
            System.out.println("Nix ausgewählt");
        }


    }
    @FXML
    public void listViewClick(MouseEvent event){

        double x = event.getScreenX();
        double y = event.getScreenY();
        if(lvGameList.getSelectionModel().isEmpty()){
            return;
        }

        if(event.getButton() == MouseButton.SECONDARY){
            System.out.println("\n\n");
            System.out.println(plattformSteam.getGames());
            EditBox.display(lvGameList.getSelectionModel().getSelectedItem(), plattformSteam, x, y);
            System.out.println(plattformSteam.getGames());
            plattformSteam.save();
        }


    }
    public String getNameFromPath(String path){

        String[] list = path.split("\\\\");
        System.out.println(Arrays.toString(list));
        String name = list[list.length-1];

        name = name.substring(0, name.length()-4); //.exe weg

        return name;


    }
    public Plattform getPlattformSteam() {
        return plattformSteam;
    }

    public void setPlattformSteam(Plattform plattformSteam) {
        this.plattformSteam = plattformSteam;
    }

    public Plattform getPlattformOrigin() {
        return plattformOrigin;
    }

    public void setPlattformOrigin(Plattform plattformOrigin) {
        this.plattformOrigin = plattformOrigin;
    }

    public Plattform getPlattformUplay() {
        return plattformUplay;
    }

    public void setPlattformUplay(Plattform plattformUplay) {
        this.plattformUplay = plattformUplay;
    }














}
