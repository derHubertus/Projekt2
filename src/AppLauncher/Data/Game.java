package AppLauncher.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private String name;
    private String path;
    private String nickName;
    private String logoPath = "";
    private List<String> picturePaths = new ArrayList<>();


    public Game(String name, String path) {
        this.name = name;
        this.path = path;
    }


    //setter-methoden
    public void setPath(String newPath) {
        this.path = newPath;
    }

    public void setNickName(String newNickName) {
        this.nickName = newNickName;
    }

    public void setLogoPath(String newLogoPath) {
        this.logoPath = newLogoPath;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPicturePaths(List<String> picturePaths) {
        this.picturePaths = picturePaths;
    }


    //getter-methoden
    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public String getNickName() {
        return this.nickName;

    }

    public String getLogoPath() {
        return this.logoPath;
    }

    public List<String> getPicturePaths() {
        return picturePaths;
    }

    public void launchGame(){
        try {
            Process p = Runtime.getRuntime().exec(this.path);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }




}
