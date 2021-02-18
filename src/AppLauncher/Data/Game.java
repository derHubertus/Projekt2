package AppLauncher.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private String name;
    private String path;
    private String nickName;
    private String logoPath = "";
    private Path picturePath;
    private static int counter = 1;

    public Game(String name, String path){
        this.name = name;
        this.path = path;
        try{

            picturePath = createDirectory(name);

        }catch(IOException io){

            io.printStackTrace();

        }
    }

    private Path createDirectory(String name) throws IOException {
        Path location = Path.of("src/AppLauncher/files/" + name);
        if (!Files.exists(location)){
            Files.createDirectory(location);
            return location;
        }
        else{
            location = Path.of("src/AppLauncher/files/" + name + "("+counter+")");
            Files.createDirectory(location);
            counter++;
            return location;
        }
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

    //getter-methoden
    public Path getPicturePath() {
        return picturePath;
    }

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

    public void launchGame(){
        try {
            Process p = Runtime.getRuntime().exec(this.path);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }




}
