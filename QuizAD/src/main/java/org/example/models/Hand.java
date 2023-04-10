package org.example.models;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getValue() {
        int value = 0;
        int aces = 0;
        for (Card card : cards) {
            switch (card.getRank()) {
                case ACE:   aces++; value += 11; break;
                case TWO:   value += 2; break;
                case THREE: value += 3; break;
                case FOUR:  value += 4; break;
                case FIVE:  value += 5; break;
                case SIX:   value += 6; break;
                case SEVEN: value += 7; break;
                case EIGHT: value += 8; break;
                case NINE:  value += 9; break;
                default:    value += 10; break; // Face cards are worth 10 points
            }
        }

        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }

    public boolean isBust() {
        return getValue() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append(", ");
        }

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }

        return sb.toString();
    }
}
