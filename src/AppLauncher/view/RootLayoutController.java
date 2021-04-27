package AppLauncher.view;

import AppLauncher.Data.EditBox;
import AppLauncher.Data.Game;
import AppLauncher.Data.GameCell;
import AppLauncher.Data.Plattform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
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
    private Label lbSearch;
    @FXML
    private TextField tfSearch;
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
    private MenuItem mbItemAbout;
    @FXML
    private MenuItem mbItemFile;
    @FXML
    private MenuItem mbItemSkin;
    @FXML
    private MenuItem mbItemHelp;
    @FXML
    private MenuItem mbItemBlack;
    @FXML
    private MenuItem mbItemWhite;
    @FXML
    private MenuItem mbItemGrey;
    @FXML
    private MenuItem mbItemLanguage;
    @FXML
    private MenuItem mbItemENG;
    @FXML
    private MenuItem mbItemDE;
    @FXML
    private MenuItem mbItemPL;
    @FXML
    private StackPane sp;

    private Main main;
    
    private Plattform plattformSteam = new Plattform("Steam");
    private Plattform plattformOrigin = new Plattform("Origin");
    private Plattform plattformUplay = new Plattform("Uplay");
    private String chosenPlattform;
    private FileChooser fileChooser = new FileChooser();
    private ObservableList<Game> clearList = FXCollections.observableArrayList();
    private FilteredList<Game> filteredList;



    public void initialize(){
        switchSkinWhite();
        lbSteamClicked();

        lvGameList.setCellFactory(value -> {
            return new GameCell();
        });


        spImageView.setPreserveRatio(false);
        ivImageSet.setPreserveRatio(false);

        lvGameList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Game>() {
            @Override
            public void changed(ObservableValue<? extends Game> observableValue, Game oldValue, Game newValue) {
                try{
                    lbGameNameDisplay.setText(newValue.getName());

                    File pictureDirectory = new File(newValue.getPicturePath());
                    File[] listOfPictures = pictureDirectory.listFiles();

                    assert listOfPictures != null;
                    if (listOfPictures.length > 0) {
                        Image img = new Image(listOfPictures[0].getPath().substring(3)); //Alle Bilder als "1" speicher
                        ivImageSet.setImage(img);
                    }
                    else{
                        ivImageSet.setImage(null);
                    }

                }catch(NullPointerException e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void searchGame(){
        // Suchfunktion der ListView

        lvGameList.setItems(filteredList);
        tfSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate(data -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseSearch = newValue.toLowerCase();
                return data.getName().toLowerCase(Locale.ROOT).contains(lowerCaseSearch);
            });
        }));
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
        filteredList = new FilteredList<>(plattformSteam.getGames2(), data -> true);
        searchGame();
        /*lvGameList.setCellFactory(value -> {
            return new GameCell();
        });*/
    }

    @FXML
    public void lbOriginClicked(){
        lvGameList.getItems().removeAll();
        chosenPlattform = "origin";
        lvGameList.setItems(plattformOrigin.getGames2());
        filteredList = new FilteredList<>(plattformOrigin.getGames2(), data -> true);
        searchGame();
        /*lvGameList.setCellFactory(value -> {
            return new GameCell();
        });*/

    }

    @FXML
    public void lbUplayClicked(){
        lvGameList.getItems().removeAll();
        chosenPlattform = "uplay";
        lvGameList.setItems(plattformUplay.getGames2());
        filteredList = new FilteredList<>(plattformUplay.getGames2(), data -> true);
        searchGame();
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

    public String getImageColor(String s){
        if (s.contains("grey")){
            return "grey";
        }
        else if (s.contains("white")){
            return "white";
        }
        else{
            return "black";
        }
    }

    public void changeColorHover(Label label){
        label.setTextFill(Color.rgb(238,150,13));
    }

    public void changeColorDefault(Label label){
        label.setTextFill(Color.rgb(251,184,9));
    }

    public void changeColorWhite(Label label) {
        label.setTextFill(Color.GOLD);
    }

    public void changeColorWhiteHover(Label label) {
        label.setTextFill(Color.DARKGOLDENROD);
    }

    public void changeColorGrey(Label label){
        label.setTextFill(Color.WHITE);
    }

    public void changeColorGreyHover(Label label){
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
            changeColorWhite(lbSearch);
        }
        else if (s.equals("grey")){
            changeColorGrey(lbSteam);
            changeColorGrey(lbOrigin);
            changeColorGrey(lbUplay);
            changeColorGrey(lbGamePlay);
            changeColorGrey(lbGameAdd);
            changeColorGrey(lbGameNameDisplay);
            changeColorGrey(lbSearch);
        }
        else if (s.equals("black")){
            changeColorDefault(lbSteam);
            changeColorDefault(lbOrigin);
            changeColorDefault(lbUplay);
            changeColorDefault(lbGamePlay);
            changeColorDefault(lbGameAdd);
            changeColorDefault(lbGameNameDisplay);
            changeColorDefault(lbSearch);
        }
    }
    @FXML
    public void lbSteamHover(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("grey")){
            changeColorGreyHover(lbSteam);
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
        if (color.equals("grey")){
            changeColorGrey(lbSteam);
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
        if (color.equals("grey")){
            changeColorGreyHover(lbOrigin);
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
        if (color.equals("grey")){
            changeColorGrey(lbOrigin);
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
        if (color.equals("grey")){
            changeColorGreyHover(lbUplay);
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
        if (color.equals("grey")){
            changeColorGrey(lbUplay);
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
        if (color.equals("grey")){
            changeColorGreyHover(lbGameAdd);
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
        if (color.equals("grey")){
            changeColorGrey(lbGameAdd);
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
        if (color.equals("grey")){
            changeColorGrey(lbGamePlay);
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
        if (color.equals("grey")){
            changeColorGreyHover(lbGamePlay);
        }
        else if(color.equals("white")){
            changeColorWhiteHover(lbGamePlay);
        }
        else if(color.equals("black")){
            changeColorHover(lbGamePlay);
        }
    }
    @FXML
    public void lbSearchDefault(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("grey")){
            changeColorGrey(lbSearch);
        }
        else if(color.equals("white")){
            changeColorWhite(lbSearch);
        }
        else if(color.equals("black")){
            changeColorDefault(lbSearch);
        }
    }

    @FXML
    public void lbSearchHover(){
        String color = getImageColor(spImageView.getImage().getUrl().split("/")[spImageView.getImage().getUrl().split("/").length-1]);
        if (color.equals("grey")){
            changeColorGreyHover(lbSearch);
        }
        else if(color.equals("white")){
            changeColorWhiteHover(lbSearch);
        }
        else if(color.equals("black")){
            changeColorHover(lbSearch);
        }
    }


    @FXML
    public void switchSkinWhite(){
        //Wir wissen nicht wieso, aber es funktioniert besser als mit apBackGround, weil es weniger verpixelt ist.
        Image img = new Image("AppLauncher/images/apBack.jpeg");
        spImageView.setImage(img);
        changeAllLabelColor("black");
    }

    @FXML
    public void switchSkinBlack(){
        Image img = new Image("AppLauncher/images/whiteBack.jpg");
        spImageView.setImage(img);
        changeAllLabelColor("white");
    }

    @FXML
    public void switchSkinGrey(){
        Image img = new Image("AppLauncher/images/greyBack.jpg");
        spImageView.setImage(img);
        changeAllLabelColor("grey");
    }

    @FXML
    public void changeLanguageENG(){
        changeLanguage("eng");
    }

    @FXML
    public void changeLanguageDE(){
        changeLanguage("de");
    }

    @FXML
    public void changeLanguagePL(){
        changeLanguage("pl");
    }

    public void changeLanguage(String identifier){
        if (identifier.equalsIgnoreCase("eng")){
            mbItemClose.setText("Close");
            mbItemFile.setText("File");
            mbItemHelp.setText("Help");
            mbItemAbout.setText("About");
            mbItemSkin.setText("Skin");
            mbItemWhite.setText("White");
            mbItemGrey.setText("Grey");
            mbItemBlack.setText("Black");
            mbItemLanguage.setText("Language");
            mbItemDE.setText("German");
            mbItemENG.setText("English");
            mbItemPL.setText("Polish");
            lbGameAdd.setText("ADD TO LIBRARY");
            lbGamePlay.setText("LAUNCH");
            lbSearch.setText("Search:");
        }
        else if (identifier.equalsIgnoreCase("de")){
            mbItemClose.setText("Schließen");
            mbItemFile.setText("Datei");
            mbItemHelp.setText("Hilfe");
            mbItemAbout.setText("Über uns");
            mbItemSkin.setText("Aussehen");
            mbItemWhite.setText("Weiß");
            mbItemGrey.setText("Grau");
            mbItemBlack.setText("Schwarz");
            mbItemLanguage.setText("Sprache");
            mbItemDE.setText("Deutsch");
            mbItemENG.setText("Englisch");
            mbItemPL.setText("Polnisch");
            lbGameAdd.setText("ZUR BIBLIOTHEK HINZUFÜGEN");
            lbGamePlay.setText("STARTEN");
            lbSearch.setText("Suchen:");
        }
        else if (identifier.equalsIgnoreCase("pl")){
            mbItemClose.setText("Zamknij");
            mbItemFile.setText("Plik");
            mbItemHelp.setText("Pomoc");
            mbItemAbout.setText("O nas");
            mbItemSkin.setText("Skórka");
            mbItemWhite.setText("Biały");
            mbItemGrey.setText("Szary");
            mbItemBlack.setText("Czarny");
            mbItemLanguage.setText("Język");
            mbItemDE.setText("Niemiecki");
            mbItemENG.setText("Angielski");
            mbItemPL.setText("Polski");
            lbGameAdd.setText("DODAJ DO BIBLIOTEKI");
            lbGamePlay.setText("START");
            lbSearch.setText("Szukaj:");
        }
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

            if(chosenPlattform.equals("uplay")){
                EditBox.display(lvGameList.getSelectionModel().getSelectedItem(), plattformUplay, main, x, y);
                lvGameList.setCellFactory(value -> new GameCell());
                plattformUplay.save();
            }

            if(chosenPlattform.equals("steam")){
                EditBox.display(lvGameList.getSelectionModel().getSelectedItem(), plattformSteam, main, x, y);
                lvGameList.setCellFactory(value -> new GameCell());
                plattformSteam.save();
            }
            if(chosenPlattform.equals("origin")) {
                EditBox.display(lvGameList.getSelectionModel().getSelectedItem(), plattformOrigin, main, x, y);
                lvGameList.setCellFactory(value -> new GameCell());
                plattformOrigin.save();
            }

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




    // setPicturePaths in Game.java oder Ordner System?
    // Ein Ordner mit allen Images (customImages) oder Viele Ordner für jeweils ein Spiel?
    // Neues Attribut "imagePath/dirPath" in die JSON Datei packen?
    // Ordner für Images direkt beim Hinzufügen eines Spiels erstellen? + Vor allem Wie?
    // Ein Image hochladen oder später die Auswahl erlauben

    //In files bilderordner erstellen

    //Slideshow / Pfeile (MenuItem)









}
