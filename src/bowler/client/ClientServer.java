package bowler.client;

import bowler.gui.GUI;
import bowler.iplocator.IP;
import com.google.gson.Gson;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ClientServer {

    // static Map<String, String> players = new HashMap<>();
    static ArrayList<String> players = new ArrayList<>();

    public static void main(String[] args) {
        final GUI gui = new GUI();
        SwingUtilities.invokeLater(() -> gui.setVisible(true));

        get("/", (req, res) -> {
            res.type("application/json");
            return 200;
        });

        get("/add/player/:name", (req, res) -> {
            res.type("text");
            if(!req.params("name").equals("") && !players.contains(req.params("name"))) {
                players.add(req.params("name"));
                gui.addPlayer(req.params("name"));
                return 200;
            }
            return 404;
        });

        get("/remove/player/:name", (req, res) -> {
            res.type("text");
            if(!req.params("name").equals("") && players.contains(req.params("name"))) {
                players.remove(req.params("name"));
                gui.removePlayer(req.params("name"));
                return 200;
            }
            return 404;
        });

        get("/players", (req, res) -> {
            Map<String, String> map = new HashMap<>();
            int i = 1;
            for(String name : players) {
                map.put("player" + i, name);
                i++;
            }
            Gson gson = new Gson();
            res.type("application/json");
            return gson.toJson(map);
        });

        get("/iplocator", (req, res) -> {
            final IP ip = new IP();
            SwingUtilities.invokeLater(() -> ip.setVisible(true));
            return 200;
        });
    }
}