package AppLauncher.Data;


public class Game {

    private final Throwable IllegalArgumentException = new IllegalArgumentException();
    private String name;
    private String path;
    //private String launcher;
    private String nickName;
    private String logoPath = null;


    //Konstruktoren
    public Game(){}
    public Game(String name, String path, String launcher) throws Throwable {
        this.name = name;
        //this.launcher = launcher;
        this.path = path;
    }
    public Game(String name, String path, String launcher, String nickName) throws Throwable{
        this.name = name;
        //this.launcher = launcher;
        this.nickName = nickName;
        this.path = path;

    }

    //setter-methoden
    public void setPath(String newPath) throws Throwable{
        this.path = newPath;
    }
    public void setNickName(String newNickName){
        this.nickName = newNickName;
    }
    public void setLogoPath(String newLogoPath){
        this.logoPath = newLogoPath;
    }
    /*public void setName(String newName){
        this.name = newName;
    }
    public void setLauncher(String newLauncher){
        this.launcher = newLauncher;
    }*/

    //getter-methoden
    public String getName(){
        return this.name;
    }
    public String getPath(){
        return this.path;
    }
    /*public String getLauncher(){
        return this.launcher;
    }*/
    public String getNickName(){
        return this.nickName;
        //Bei keinem gegeben Namen try catch block drum machen, um NullPointerException abzufangen und so abfragen zu können, ob es == null ist
    }
    public String getLogoPath(){
        return this.logoPath;
    }



}
