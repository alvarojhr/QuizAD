package org.example.models;

public class Player {
    private final String name;
    private int coins;
    private final Hand hand;

    public Player(String name, int initialCoins) {
        this.name = name;
        this.coins = initialCoins;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int amount) {
        coins += amount;
    }

    public void subtractCoins(int amount) {
        coins -= amount;
    }

    public Hand getHand() {
        return hand;
    }

    // Other utility methods
}

