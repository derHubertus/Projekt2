package AppLauncher.Data;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plattform {

    private List<Game> games = new ArrayList<>();
    private static int z = 0;
    private String name;

    public Plattform(String name){
        this.name = name;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void addGame(Game game){
        this.games.add(game);
        z++;
    }
/*
    public void save(){
        while (true){
            int counter = 0;
            JSONObject jsonObject = new JSONObject();
            if(this.games.containsKey(counter)){
                JSONObject gameJson = new JSONObject();
                Game game = this.games.get(counter);

                gameJson.put("path", game.getPath());
                gameJson.put("name", game.getName());
                gameJson.put("logo", game.getLogoPath());
                jsonObject.put(String.valueOf(counter), new JSONObject());

            }
        }

    }
    */

}
