package com.Elvis.HiLoCardGame.deck;

import com.Elvis.HiLoCardGame.model.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates the standard 54 card deck
 */
@Service
public class Deck {

    public static List<Card> standardCardDeck() {
        List<Card> cardDeck = new ArrayList<>();
        // Clubs
        cardDeck.add(new Card("Two of Clubs", 2));
        cardDeck.add(new Card("Three of Clubs", 3));
        cardDeck.add(new Card("Four of Clubs", 4));
        cardDeck.add(new Card("Five of Clubs", 5));
        cardDeck.add(new Card("Six of Clubs", 6));
        cardDeck.add(new Card("Seven of Clubs", 7));
        cardDeck.add(new Card("Eight of Clubs", 8));
        cardDeck.add(new Card("Nine of Clubs", 9));
        cardDeck.add(new Card("Ten of Clubs", 10));
        cardDeck.add(new Card("Jack of Clubs", 10));
        cardDeck.add(new Card("Queen of Clubs", 10));
        cardDeck.add(new Card("King of Clubs", 10));
        cardDeck.add(new Card("Ace of Clubs", 10));
        // Diamonds
        cardDeck.add(new Card("Two of Diamonds", 2));
        cardDeck.add(new Card("Three of Diamonds", 3));
        cardDeck.add(new Card("Four of Diamonds", 4));
        cardDeck.add(new Card("Five of Diamonds", 5));
        cardDeck.add(new Card("Six of Diamonds", 6));
        cardDeck.add(new Card("Seven of Diamonds", 7));
        cardDeck.add(new Card("Eight of Diamonds", 8));
        cardDeck.add(new Card("Nine of Diamonds", 9));
        cardDeck.add(new Card("Ten of Diamonds", 10));
        cardDeck.add(new Card("Jack of Diamonds", 10));
        cardDeck.add(new Card("Queen of Diamonds", 10));
        cardDeck.add(new Card("King of Diamonds", 10));
        cardDeck.add(new Card("Ace of Diamonds", 10));
        // Hearts
        cardDeck.add(new Card("Two of Hearts", 2));
        cardDeck.add(new Card("Three of Hearts", 3));
        cardDeck.add(new Card("Four of Hearts", 4));
        cardDeck.add(new Card("Five of Hearts", 5));
        cardDeck.add(new Card("Six of Hearts", 6));
        cardDeck.add(new Card("Seven of Hearts", 7));
        cardDeck.add(new Card("Eight of Hearts", 8));
        cardDeck.add(new Card("Nine of Hearts", 9));
        cardDeck.add(new Card("Ten of Hearts", 10));
        cardDeck.add(new Card("Jack of Hearts", 10));
        cardDeck.add(new Card("Queen of Hearts", 10));
        cardDeck.add(new Card("King of Hearts", 10));
        cardDeck.add(new Card("Ace of Hearts", 10));
        // Spades
        cardDeck.add(new Card("Two of Hearts", 2));
        cardDeck.add(new Card("Three of Hearts", 3));
        cardDeck.add(new Card("Four of Hearts", 4));
        cardDeck.add(new Card("Five of Hearts", 5));
        cardDeck.add(new Card("Six of Hearts", 6));
        cardDeck.add(new Card("Seven of Hearts", 7));
        cardDeck.add(new Card("Eight of Hearts", 8));
        cardDeck.add(new Card("Nine of Hearts", 9));
        cardDeck.add(new Card("Ten of Hearts", 10));
        cardDeck.add(new Card("Jack of Hearts", 10));
        cardDeck.add(new Card("Queen of Hearts", 10));
        cardDeck.add(new Card("King of Hearts", 10));
        cardDeck.add(new Card("Ace of Hearts", 10));
        return cardDeck;
    }
}
