package AppLauncher.Data;


import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                FileWriter fw = new FileWriter("src/AppLauncher/files/" + this.name + ".json");

                fw.write(jsonObject.toString(2));
                fw.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            counter++;
        }

    }

    public void load() {
        int counter = 0;
        String jsonPath = "src/AppLauncher/files/" + this.name + ".json";
        File file = new File(jsonPath);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONObject jsonObject = new JSONObject(content);

            while (true) {
                if (jsonObject.has("" + counter)) {

                    String name = new JSONObject(jsonObject.get(String.valueOf(counter)).toString()).get("name").toString();
                    String path = new JSONObject(jsonObject.get(String.valueOf(counter)).toString()).get("path").toString();
                    String logoPath = new JSONObject(jsonObject.get(String.valueOf(counter)).toString()).get("logoPath").toString();
                    System.out.println(name + " " + path + " " + logoPath);

                    Game game = new Game(name, path);
                    game.setLogoPath(logoPath);
                    games.add(game);

                } else {
                    break;
                }
                counter++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return this.games;
    }


}
