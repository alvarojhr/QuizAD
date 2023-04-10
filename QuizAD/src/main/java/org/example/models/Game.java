package org.example.models;

public class Game {
    private final Player player;
    private final Player dealer;
    private final Deck deck;

    public Game(String playerName, int initialCoins) {
        if (initialCoins < 0) {
            throw new IllegalArgumentException("initialCoins must be non-negative");
        }
        this.player = new Player(playerName, initialCoins);
        this.dealer = new Player("Dealer", 0);
        this.deck = new Deck();
    }

    public Player getPlayer() {
        return player;
    }

    public Player getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public void dealInitialCards() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("deck is empty");
        }
        player.getHand().addCard(deck.draw());
        player.getHand().addCard(deck.draw());
        dealer.getHand().addCard(deck.draw());
        dealer.getHand().addCard(deck.draw());
    }

    public void hitPlayer() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("deck is empty");
        }
        player.getHand().addCard(deck.draw());
    }

    public void hitDealer() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("deck is empty");
        }
        dealer.getHand().addCard(deck.draw());
    }

    // Other methods to manage the game state (e.g., placing bets, determining the winner, handling a tie, etc.)

    @Override
    public String toString() {
        return "Game{\n" +
                "player=" + player +
                ",\n dealer=" + dealer +
                ",\n deck=" + deck +
                '}';
    }
}


//I verify that initialCoins is greater than or equal to zero.
//If dealInitialCards is empty it may be better to throw an exception before trying to deal cards, the same for hitPlayer() and hitDealer().
//Player has a toString() method, a toString() method is added to the Game class to show the current state of the game.
//Hand does not have a toString() method, we can add one