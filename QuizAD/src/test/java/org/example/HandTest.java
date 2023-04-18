package org.example;

import org.example.models.Card;
import org.example.models.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    void testGetValueNoCards() {
        assertEquals(1, hand.getValue());
    }

    @Test
    void testGetValueSingleCard() {
        hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        assertEquals(11, hand.getValue());
    }

    @Test
    void testGetValueTwoCards() {
        hand.addCard(new Card( Card.Suit.CLUBS, Card.Rank.ACE));
        hand.addCard(new Card( Card.Suit.HEARTS, Card.Rank.FIVE));
        assertEquals(16, hand.getValue());
    }

    @Test
    void testGetValueAceWithValueOver21() {
        hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.TEN));
        hand.addCard(new Card(Card.Suit.DIAMONDS, Card.Rank.TEN));
        assertEquals(21, hand.getValue());
    }

    @Test
    void testGetValueMultipleAces() {
        hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.ACE));
        hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.ACE));
        assertEquals(12, hand.getValue());
    }

    @Test
    void testGetValueFaceCards() {
        hand.addCard(new Card(Card.Suit.CLUBS, Card.Rank.JACK));
        hand.addCard(new Card(Card.Suit.HEARTS, Card.Rank.QUEEN));
        hand.addCard(new Card( Card.Suit.DIAMONDS, Card.Rank.KING));
        assertEquals(30, hand.getValue());
    }
}
