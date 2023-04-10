package org.example.persistance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.models.Game;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersistenceManager {
    private static final String GAME_SAVE_FILE = "game_save.json";
    private final Gson gson;

    public PersistenceManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void saveGame(Game game) {
        String json = gson.toJson(game);
        Path filePath = Paths.get(GAME_SAVE_FILE);

        try {
            Files.write(filePath, json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    public Game loadGame() {
        Path filePath = Paths.get(GAME_SAVE_FILE);

        // Create the JSON file if it doesn't exist
        if (!Files.exists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                System.err.println("Error creating game save file: " + e.getMessage());
                return null;
            }
        }

        try {
            String json = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            if (json.isEmpty()) {
                return null;
            }
            return gson.fromJson(json, Game.class);
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
            return null;
        }
    }
}
