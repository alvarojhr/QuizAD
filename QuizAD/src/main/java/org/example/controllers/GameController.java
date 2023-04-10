package org.example.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.example.models.Game;
import org.example.persistance.PersistenceManager;

import java.util.Scanner;

public class GameController {

    private final Scanner scanner = new Scanner(System.in);
    private final PersistenceManager persistenceManager = new PersistenceManager();
    private Game game;
    private boolean running;
    
    public GameController() {
        loadOrCreateGame();
        running = true;
    }
    
    public void start() throws IOException {
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    playRound();
                    break;
                case 2:
                    saveGame();
                    break;
                case 3:
                    loadGame();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    
    private void loadOrCreateGame() {
        Game loadedGame = persistenceManager.loadGame();
        
        if (loadedGame != null) {
            game = loadedGame;
            System.out.println("Game loaded.");
        } else {
            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();
            game = new Game(playerName, 1000);
            System.out.println("New game created.");
        }
    }
    
    private void displayMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Play a round");
        System.out.println("2. Save game");
        System.out.println("3. Load game");
        System.out.println("4. Quit");
        System.out.print("Enter your choice: ");
    }
    
    private void playRound() throws IOException {
        // Betting
        System.out.print("Enter your bet (10 to " + game.getPlayer().getCoins() + "): ");
        int bet = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (bet < 10 || bet > game.getPlayer().getCoins()) {
            System.out.println("Invalid bet amount. Please try again.");
            return;
        }
        
        game.getPlayer().subtractCoins(bet);
        // save player txt 
        savePlayerTxt("savePlayer");
        // Dealing cards
        game.getPlayer().getHand().addCard(game.getDeck().draw());
        game.getPlayer().getHand().addCard(game.getDeck().draw());
        game.getDealer().getHand().addCard(game.getDeck().draw());
        game.getDealer().getHand().addCard(game.getDeck().draw());

        // Player's turn
        boolean playerBusted = false;
        while (true) {
            System.out.println("Your hand: " + game.getPlayer().getHand());
            System.out.println("Dealer's up card: " + game.getDealer().getHand().getCards().get(0));
            System.out.println("1. Hit\n2. Stand");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                game.hitPlayer();
                if (game.getPlayer().getHand().isBust()) {
                    System.out.println("Your hand: " + game.getPlayer().getHand());
                    System.out.println("You busted!");
                    playerBusted = true;
                    break;
                }
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        // Dealer's turn
        boolean dealerBusted = false;
        if (!playerBusted) {
            while (game.getDealer().getHand().getValue() < 17) {
                game.hitDealer();
                if (game.getDealer().getHand().isBust()) {
                    System.out.println("Dealer's hand: " + game.getDealer().getHand());
                    System.out.println("Dealer busted!");
                    dealerBusted = true;
                    break;
                }
            }
        }

        // Determine outcome
        if (playerBusted) {
            System.out.println("You lost this round!");
        } else if (dealerBusted) {
            System.out.println("You won this round!");
            game.getPlayer().addCoins(bet * 2);
        } else {
            int playerValue = game.getPlayer().getHand().getValue();
            int dealerValue = game.getDealer().getHand().getValue();
            System.out.println("Your hand: " + game.getPlayer().getHand());
            System.out.println("Dealer's hand: " + game.getDealer().getHand());
            
            if (playerValue > dealerValue) {
                System.out.println("You won this round!");
                game.getPlayer().addCoins(bet * 2);
            } else if (playerValue < dealerValue) {
                System.out.println("You lost this round!");
            } else {
                System.out.println("It's a tie!");
                game.getPlayer().addCoins(bet); // Return the bet
            }
        }

        // Cleanup
        game.getPlayer().getHand().getCards().clear();
        game.getDealer().getHand().getCards().clear();
    }
    
    private void saveGame() {
        persistenceManager.saveGame(game);
        System.out.println("Game saved.");
    }
    
    private void loadGame() {
        Game loadedGame = persistenceManager.loadGame();
        if (loadedGame != null) {
            game = loadedGame;
            System.out.println("Game loaded.");
        } else {
            System.out.println("Error loading game.");
        }
    }

    private void savePlayerTxt(String name) throws IOException {
        try {
            File archi = new File(name + ".txt");
            FileWriter fw = new FileWriter(archi, true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(game.getPlayer().toString());
            bw.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
