package org.example.models;

public class Game {
    private final Player player;
    private final Player dealer;
    private final Deck deck;

    public Game(String playerName, int initialCoins) {
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
        player.getHand().addCard(deck.draw());
        player.getHand().addCard(deck.draw());
        dealer.getHand().addCard(deck.draw());
        dealer.getHand().addCard(deck.draw());
    }

    public void hitPlayer() {
        player.getHand().addCard(deck.draw());
    }

    public void hitDealer() {
        dealer.getHand().addCard(deck.draw());
    }

    // Other methods to manage the game state (e.g., placing bets, determining the winner, handling a tie, etc.)
}

