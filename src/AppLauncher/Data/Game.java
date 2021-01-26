package AppLauncher.Data;


public class Game {

    private String name;
    private String path;
    private String nickName;
    private String logoPath = "";


    public Game(String name, String path) {
        this.name = name;

        this.path = path;
    }

    public Game(String name, String path, String nickName) {
        this.name = name;
        this.nickName = nickName;
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


}
