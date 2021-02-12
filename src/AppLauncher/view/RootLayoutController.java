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
import org.w3c.dom.css.RGBColor;

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
    private ImageView spImageView;
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
    @FXML
    private StackPane sp;

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

        //spImageView.fitWidthProperty().bind(apBackGround.widthProperty());
        //spImageView.setFitWidth(apBackGround.getWidth());
        //spImageView.fitHeightProperty().bind(apBackGround.heightProperty());
        //spImageView.setFitHeight(apBackGround.getHeight());
        spImageView.setPreserveRatio(false);


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
    private void handleAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hoomak Launcher™ - About");
        alert.setHeaderText("Laucher for all your games!");
        alert.setContentText("Made by DC&BI Team");

        alert.showAndWait();
    }

    @FXML
    private void handleExit(){
        System.exit(0);
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

    public String getImageColor(String s){
        if (s.contains("red")){
            return "red";
        }
        else if (s.contains("white")){
            return "white";
        }
        else{
            return "black";
        }
    }

    public void changeColorHover(Label label){
        label.setTextFill(Color.ORANGERED);
    }

    public void changeColorDefault(Label label){
        label.setTextFill(Color.rgb(255,146,0));
    }

    public void changeColorWhite(Label label) {
        label.setTextFill(Color.DODGERBLUE);
    }

    public void changeColorWhiteHover(Label label) {
        label.setTextFill(Color.MIDNIGHTBLUE);
    }

    public void changeColorRed(Label label){
        label.setTextFill(Color.WHITE);
    }

    public void changeColorRedHover(Label label){
        label.setTextFill(Color.DARKORANGE);
    }

    public void changeAllLabelColor(String s){
        if (s.equals("white")){
            changeColorWhite(lbSteam);
            changeColorWhite(lbOrigin);
            changeColorWhite(lbUplay);
            changeColorWhite(lbGamePlay);
            changeColorWhite(lbGameAdd);
            changeColorWhite(lbGameNameDisplay);
        }
        else if (s.equals("red")){
            changeColorRed(lbSteam);
            changeColorRed(lbOrigin);
            changeColorRed(lbUplay);
            changeColorRed(lbGamePlay);
            changeColorRed(lbGameAdd);
            changeColorRed(lbGameNameDisplay);
        }
        else if (s.equals("black")){
            changeColorDefault(lbSteam);
            changeColorDefault(lbOrigin);
            changeColorDefault(lbUplay);
            changeColorDefault(lbGamePlay);
            changeColorDefault(lbGameAdd);
            changeColorDefault(lbGameNameDisplay);
        }
    }

    @FXML
    public void lbSteamHover(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("red")){
            changeColorRedHover(lbSteam);
        }
        else if(color.equals("white")){
            changeColorWhiteHover(lbSteam);
        }
        else if(color.equals("black")){
            changeColorHover(lbSteam);
        }
    }

    @FXML
    public void lbSteamDefault(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("red")){
            changeColorRed(lbSteam);
        }
        else if(color.equals("white")){
            changeColorWhite(lbSteam);
        }
        else if(color.equals("black")){
            changeColorDefault(lbSteam);
        }
    }

    @FXML
    public void lbOriginHover(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("red")){
            changeColorRedHover(lbOrigin);
        }
        else if(color.equals("white")){
            changeColorWhiteHover(lbOrigin);
        }
        else if(color.equals("black")){
            changeColorHover(lbOrigin);
        }
    }

    @FXML
    public void lbOriginDefault(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("red")){
            changeColorRed(lbOrigin);
        }
        else if(color.equals("white")){
            changeColorWhite(lbOrigin);
        }
        else if(color.equals("black")){
            changeColorDefault(lbOrigin);
        }
    }

    @FXML
    public void lbUplayHover(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("red")){
            changeColorRedHover(lbUplay);
        }
        else if(color.equals("white")){
            changeColorWhiteHover(lbUplay);
        }
        else if(color.equals("black")){
            changeColorHover(lbUplay);
        }
    }

    @FXML
    public void lbUplayDefault(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("red")){
            changeColorRed(lbUplay);
        }
        else if(color.equals("white")){
            changeColorWhite(lbUplay);
        }
        else if(color.equals("black")){
            changeColorDefault(lbUplay);
        }
    }

    @FXML
    public void lbGameAddHover(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("red")){
            changeColorRedHover(lbGameAdd);
        }
        else if(color.equals("white")){
            changeColorWhiteHover(lbGameAdd);
        }
        else if(color.equals("black")){
            changeColorHover(lbGameAdd);
        };
    }

    @FXML
    public void lbGameAddDefault(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("red")){
            changeColorRed(lbGameAdd);
        }
        else if(color.equals("white")){
            changeColorWhite(lbGameAdd);
        }
        else if(color.equals("black")){
            changeColorDefault(lbGameAdd);
        }
    }

    @FXML
    public void lbGamePlayDefault(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("red")){
            changeColorRed(lbGamePlay);
        }
        else if(color.equals("white")){
            changeColorWhite(lbGamePlay);
        }
        else if(color.equals("black")){
            changeColorDefault(lbGamePlay);
        }
    }

    @FXML
    public void lbGamePlayHover(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("red")){
            changeColorRedHover(lbGamePlay);
        }
        else if(color.equals("white")){
            changeColorWhiteHover(lbGamePlay);
        }
        else if(color.equals("black")){
            changeColorHover(lbGamePlay);
        }
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
    public void switchSkinBlack(){
        //Wir wissen nicht wieso, aber es funktioniert besser als mit apBackGround, weil es weniger verpixelt ist.
        Image img = new Image("AppLauncher/images/apBack.jpeg");
        spImageView.setImage(img);
        changeAllLabelColor("black");
    }

    @FXML
    public void switchSkinWhite(){
        Image img = new Image("AppLauncher/images/whiteBack.jpg");
        spImageView.setImage(img);
        changeAllLabelColor("white");
    }

    @FXML
    public void switchSkinRed(){
        Image img = new Image("AppLauncher/images/redBack.jpg");
        spImageView.setImage(img);
        changeAllLabelColor("red");
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
