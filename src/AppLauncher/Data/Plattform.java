package AppLauncher.Data;


import org.json.JSONObject;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plattform {

    private List<Game> games = new ArrayList<>();
    private static int z = 0;
    private String name;

    public Plattform(String launcher) {
        this.name = launcher;
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void addGame(Game game) {
        this.games.add(game);
        z++;
    }

    public void save() {

        int counter = 0;
        JSONObject jsonObject = new JSONObject();
        for (Game g : games) {

            Map<String, String> map = new HashMap<>();
            map.put("path", g.getPath());
            map.put("name", g.getName());
            map.put("logoPath", g.getLogoPath());

            try {

                jsonObject.put(String.valueOf(counter), map);
                FileWriter fw = new FileWriter("src/AppLauncher/files/"+ this.name + ".json");

                fw.write(jsonObject.toString(2));
                fw.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            counter++;
        }

    }


}
